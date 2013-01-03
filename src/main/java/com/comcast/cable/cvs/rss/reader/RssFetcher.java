package com.comcast.cable.cvs.rss.reader;

import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class RssFetcher {
    
    private RssFetcher() {
    }
    
    private static RssFetcher instance = new RssFetcher();
    public static RssFetcher getInstance() {
        return instance;
    }

    public static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");

    SAXParserFactory factory = SAXParserFactory.newInstance();
    RssFeedCleaner cleaner = new RssFeedCleaner();

    private static String[] allFeeds = new String[] {
        "http://rss.nytimes.com/services/xml/rss/nyt/Business.xml",
        "http://news.yahoo.com/rss/stock-markets",
        "http://news.yahoo.com/rss/movies",
        "http://news.yahoo.com/rss/politics",
        "http://news.yahoo.com/rss/world",
        "http://news.google.com/?output=rss",
        "http://rss.cnn.com/rss/cnn_topstories.rss",
        "http://feeds.bbci.co.uk/news/rss.xml",
        "http://feeds.bbci.co.uk/news/world/rss.xml",
        "http://feeds.bbci.co.uk/news/business/rss.xml",
        "http://www.reddit.com/.rss",
        "http://news.ycombinator.com/rss",
    };
    
    public String[] getAllFeeds() {
        return allFeeds;
    }

    public List<String> getDefaultFeeds() {
        List<String> defaults = new ArrayList<String>();
        for (int ii = 0; ii < 3; ii++) {
            defaults.add(allFeeds[ii]);
        }
        return defaults;
    }

    private Map<String, RssFeed> cache = new ConcurrentHashMap<String, RssFeed>(); 
    
    public RssFeed fetchRssFeed(String url) {
        if (cache.containsKey(url)) {
            return cache.get(url);
        }
        try {
            SAXParser saxParser = factory.newSAXParser();
            SaxRssHandler handler = new SaxRssHandler();
            saxParser.parse(new URL(url).openStream(), handler);
            RssFeed rssFeed = handler.getRssFeed();
            rssFeed.setUrl(url);
            cleaner.clean(rssFeed);
            cache.put(url, rssFeed);
            return rssFeed;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }

}
