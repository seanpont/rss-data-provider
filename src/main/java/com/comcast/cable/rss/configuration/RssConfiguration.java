package com.comcast.cable.rss.configuration;

import com.yammer.dropwizard.config.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

public class RssConfiguration extends Configuration {
    
    @NotEmpty
    @JsonProperty
    private String[] rssFeeds;
    
    @NotEmpty
    @JsonProperty
    private String[] defaultFeeds;

    
    public String[] getRssFeeds() {
        return rssFeeds;
    }


    /**
     * @return the defaults
     */
    public String[] getDefaultFeeds() {
        return defaultFeeds;
    }


}
