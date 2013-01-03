package com.comcast.cable.cvs.rss.reader;

import java.net.URL;
import java.nio.charset.Charset;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;


public class RssFetcher {
    
    public static final Charset DEFAULT_CHARSET = Charset.forName("ISO-8859-1");

    SAXParserFactory factory = SAXParserFactory.newInstance();
    RssFeedCleaner cleaner = new RssFeedCleaner();

    public RssFeed fetchRssFeed(String url) {
        try {
            SAXParser saxParser = factory.newSAXParser();
            SaxRssHandler handler = new SaxRssHandler();
            saxParser.parse(new URL(url).openStream(), handler);
            RssFeed rssFeed = handler.getRssFeed();
            rssFeed.setUrl(url);
            cleaner.clean(rssFeed);
            return rssFeed;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        
    }

}
