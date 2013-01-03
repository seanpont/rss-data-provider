package com.comcast.cable.rss.resources;

import java.util.List;

import javax.management.Notification;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;

import org.eclipse.jetty.server.Response;

import com.comcast.cable.cvs.rss.reader.RssFeed;
import com.comcast.cable.rss.services.RssService;
import com.comcast.cable.rss.services.UserService;
import com.google.common.base.Optional;
import com.yammer.dropwizard.jersey.params.IntParam;
import com.yammer.dropwizard.jersey.params.LongParam;

@Path("/{user}/feeds")
@Produces(MediaType.APPLICATION_JSON)
public class RssFeedResource {
    
    private final UserService userService;
    private final RssService rssService;

    public RssFeedResource(RssService rssService, UserService userService) {
        this.rssService = rssService;
        this.userService = userService;
    }

    @GET
    public List<RssFeed> fetch(
            @PathParam("user") String userId) {
        return userService.getFeeds(userId);
    }
    
    

}