package com.comcast.cable.rss.services;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.comcast.cable.cvs.rss.reader.RssFeed;

public class RssServiceImpl implements RssService{

    private String[] allFeeds;
    private String[] defaultFeeds;
    private Map<String, RssFeed> cache;
    private ExecutorService executor;

    public RssServiceImpl(String[] allFeeds, String[] defaultFeeds) {
        this.allFeeds = allFeeds;
        this.defaultFeeds = defaultFeeds;
        cache = new ConcurrentHashMap<String, RssFeed>();
        this.executor = Executors.newFixedThreadPool(10);
        
        //populate cache
        for (String feed : allFeeds) {
            fetch(feed);
        }
    }

    private void fetch(String feed) {
        
    }

    public List<String> getDefaults() {
        return null;
    }

}
