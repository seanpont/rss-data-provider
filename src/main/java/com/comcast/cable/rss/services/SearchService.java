package com.comcast.cable.rss.services;

import java.util.List;

import com.comcast.cable.cvs.rss.reader.RssFeed;

public interface SearchService {

	List<RssFeed> find(String query);

}
