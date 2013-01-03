package com.comcast.cable.rss.services;

import com.comcast.cable.cvs.rss.reader.RssFeed;


public interface RssService {

    String[] getDefaults();

	RssFeed getFeed(String url);

}
