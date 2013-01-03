package com.comcast.cable.cvs.rss.reader;

public class RssCategory {

    private String name;
    private String[] feeds;

    public RssCategory(String name, String... feeds) {
        this.setName(name);
        this.setFeeds(feeds);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the feeds
     */
    public String[] getFeeds() {
        return feeds;
    }

    /**
     * @param feeds the feeds to set
     */
    public void setFeeds(String[] feeds) {
        this.feeds = feeds;
    }
    
}
