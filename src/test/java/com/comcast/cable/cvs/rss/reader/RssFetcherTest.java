package com.comcast.cable.cvs.rss.reader;

import org.testng.annotations.Test;

public class RssFetcherTest {
    
    @Test(enabled=false)
    public void testFetch() {
        RssFetcher fetcher = new RssFetcher();
        RssFeed feed = fetcher.fetchRssFeed("http://rss.nytimes.com/services/xml/rss/nyt/Business.xml");
        feed.setStories(null);
        System.out.println(feed);
    }
}
