package com.comcast.cable.rss.services;

import java.util.List;

import com.comcast.cable.cvs.rss.reader.RssFeed;

public interface UserService {

    void addFeed(String userId, String feed);

    void removeFeed(String userId, String feed);

    List<RssFeed> getFeeds(String userId);

}
