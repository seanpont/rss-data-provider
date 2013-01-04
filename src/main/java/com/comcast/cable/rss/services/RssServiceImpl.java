package com.comcast.cable.rss.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.apache.log4j.Logger;

import com.comcast.cable.cvs.rss.reader.RssFeed;
import com.comcast.cable.cvs.rss.reader.RssFeedSummary;
import com.comcast.cable.cvs.rss.reader.RssFetcher;

public class RssServiceImpl implements RssService{

    private static final Logger logger = Logger.getLogger(RssServiceImpl.class);
    
    private final String[] allFeeds;
    private final String[] defaultFeeds;

    private final RssFetcher rssFetcher;
    
	private final Map<Integer, RssFeed> cache;

	
    public RssServiceImpl(String[] allFeeds, String[] defaultFeeds, RssFetcher rssFetcher) {
        this.allFeeds = allFeeds;
        this.defaultFeeds = defaultFeeds;
        this.rssFetcher = rssFetcher;
        cache = new ConcurrentHashMap<Integer, RssFeed>();
    }
    
    public RssFeed getFeed(String url) {
    	RssFeed feed = cache.get(url);
    	if (feed == null) {
    	    logger.info("fetching rss feed "+url);
    		feed = fetch(url);
    	}
    	return feed;
    }

    private RssFeed fetch(String url) {
    	RssFeed feed = rssFetcher.fetchRssFeed(url);
		cache.put(url.hashCode(), feed);
		return feed;
    }


    public RssFeedSummary getSummary(String url) {
        return cache.get(url.hashCode()).getSummary();
    }

    public RssFeed getFeedById(int id) {
        return cache.get(id);
    }
    
    public String[] getDefaults() {
        return defaultFeeds;
    }
    
    public String[] getAllFeeds() {
        return allFeeds;
    }

}
