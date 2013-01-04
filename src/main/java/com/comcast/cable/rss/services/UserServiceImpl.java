package com.comcast.cable.rss.services;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.comcast.cable.cvs.rss.reader.RssFeedSummary;

public class UserServiceImpl implements UserService {

    Map<String, List<String>> preferences;
    
    RssService rssService;
    
    public UserServiceImpl(RssService rssService) {
        this.rssService = rssService;
        this.preferences = new ConcurrentHashMap<String, List<String>>();
    }

    public void addFeed(String userId, String feed) {
        preferences.get(userId).add(feed);
    }

    public void removeFeed(String userId, String feed) {
        preferences.get(userId).add(feed);
    }

    public List<RssFeedSummary> getFeeds(String userId) {
        List<String> list = preferences.get(userId);
        if (list == null) {
            preferences.put(userId, Arrays.asList(rssService.getDefaults()));
        }
        List<RssFeedSummary> feeds = new ArrayList<RssFeedSummary>();
        for (String url : list) {
            feeds.add(rssService.getSummary(url));
        }
        return feeds;
    }

    

}
