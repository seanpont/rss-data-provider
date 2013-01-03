package com.comcast.cable.rss.services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.comcast.cable.cvs.rss.reader.RssFeed;

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

    public List<RssFeed> getFeeds(String userId) {
        List<String> list = preferences.get(userId);
        if (list == null) {
            preferences.put(userId, rssService.getDefaults());
        }
        return null;
    }

    

}
