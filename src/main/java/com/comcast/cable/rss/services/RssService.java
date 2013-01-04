package com.comcast.cable.rss.services;

import com.comcast.cable.cvs.rss.reader.RssFeed;
import com.comcast.cable.cvs.rss.reader.RssFeedSummary;


public interface RssService {

    String[] getDefaults();

	RssFeed getFeed(String url);

    RssFeedSummary getSummary(String url);

    RssFeed getFeedById(int id);


}
