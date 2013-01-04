package com.comcast.cable.rss;

import com.comcast.cable.cvs.rss.reader.RssFetcher;
import com.comcast.cable.rss.configuration.RssConfiguration;
import com.comcast.cable.rss.health.RssHealthCheck;
import com.comcast.cable.rss.resources.RssFeedResource;
import com.comcast.cable.rss.resources.SearchResource;
import com.comcast.cable.rss.resources.UserFeedResource;
import com.comcast.cable.rss.resources.UserPreferencesResource;
import com.comcast.cable.rss.services.RssServiceImpl;
import com.comcast.cable.rss.services.SearchServiceImpl;
import com.comcast.cable.rss.services.UserServiceImpl;
import com.yammer.dropwizard.Service;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;

public class RssDataProviderService extends Service<RssConfiguration> {
    
    public static void main(String[] args) throws Exception {
        new RssDataProviderService().run(args);
    }

    @Override
    public void initialize(Bootstrap<RssConfiguration> bootstrap) {
        bootstrap.setName("rss-data-provider");
    }

    @Override
    public void run(RssConfiguration configuration, Environment environment) {
        //config
        final String[] feeds = configuration.getRssFeeds();
        final String[] defaultFeeds = configuration.getDefaultFeeds();
        
        //set up services
        RssServiceImpl rssService = new RssServiceImpl(feeds, defaultFeeds, new RssFetcher());
        UserServiceImpl userService = new UserServiceImpl(rssService);
        SearchServiceImpl searchService = new SearchServiceImpl(feeds, rssService);
        
        //telling env to manage this service allows it to close the index on shutdown
        environment.manage(searchService);
        
        //resources, these are like controllers
        environment.addResource(new UserPreferencesResource(userService));
        environment.addResource(new UserFeedResource(userService));
        environment.addResource(new RssFeedResource(rssService));
        environment.addResource(new SearchResource(searchService));
        
        //health checks
        environment.addHealthCheck(new RssHealthCheck(configuration));

    }

}