package com.comcast.cable.rss.health;

import com.comcast.cable.rss.configuration.RssConfiguration;
import com.yammer.metrics.core.HealthCheck;

public class RssHealthCheck extends HealthCheck {

    public RssHealthCheck(RssConfiguration configuration) {
        super("config");
    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }
}