package com.comcast.cable.rss.services;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.comcast.cable.cvs.rss.reader.RssFeed;
import com.comcast.cable.cvs.rss.reader.RssFetcher;

public class RssServiceImpl implements RssService{

    private final String[] allFeeds;
    private final String[] defaultFeeds;

    private final RssFetcher rssFetcher;
    
	private final Map<String, RssFeed> cache;

    public RssServiceImpl(String[] allFeeds, String[] defaultFeeds, RssFetcher rssFetcher) {
        this.allFeeds = allFeeds;
        this.defaultFeeds = defaultFeeds;
        this.rssFetcher = rssFetcher;
        cache = new ConcurrentHashMap<String, RssFeed>();
    }
    
    public RssFeed getFeed(String url) {
    	RssFeed feed = cache.get(url);
    	if (feed == null) {
    		feed = fetch(url);
    		cache.put(url, feed);
    	}
    	return feed;
    }

    private RssFeed fetch(String url) {
    	RssFeed feed = rssFetcher.fetchRssFeed(url);
		cache.put(url, feed);
		return feed;
    }

    public String[] getDefaults() {
        return null;
    }

}
