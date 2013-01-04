package com.comcast.cable.rss.services;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.comcast.cable.cvs.rss.reader.RssFeed;
import com.comcast.cable.cvs.rss.reader.RssFeedSummary;

public class SearchServiceImplTest {

    private RssService rssService;
    private String[] feeds;
    private SearchServiceImpl searchService;

    @BeforeClass
    public void setup() throws Exception {
        feeds = new String[] {"http://rss.nytimes.com/services/xml/rss/nyt/Business.xml"};
        rssService = mock(RssService.class);
        searchService = new SearchServiceImpl(feeds, rssService);
        
        RssFeed feed = new RssFeed();
        feed.setUrl("http://rss.nytimes.com/services/xml/rss/nyt/Business.xml");
        feed.setImage("http://graphics8.nytimes.com/images/misc/NYT_logo_rss_250x40.png");
        feed.setTitle("NYT > Business Day");
        feed.setDescription("Business");
        feed.setLink("http://www.nytimes.com/pages/business/index.html?partner=rss&emc=rss");
        when(rssService.getFeed(feed.getUrl())).thenReturn(feed);
        when(rssService.getSummary(feed.getUrl())).thenReturn(feed.getSummary());
        
        searchService.start();
    }
    
    @AfterClass
    public void teardown() throws Exception {
        searchService.stop();
        
    }
    
    @Test
    public void testSearch() {
        String query = "bus";
        List<RssFeedSummary> results = searchService.find(query);
        System.out.println(query + ": "+results);
        
//        Assert.assertEquals(results.get(0).getDescription(), "Business");
        
        query = "ny";
        results = searchService.find(query);
        System.out.println(query + ": " + results);
        
    }
}
