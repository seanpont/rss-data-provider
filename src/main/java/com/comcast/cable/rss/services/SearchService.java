package com.comcast.cable.rss.services;

import java.util.List;

import com.comcast.cable.cvs.rss.reader.RssFeedSummary;

public interface SearchService {

	List<RssFeedSummary> find(String query);

}
