package com.comcast.cable.rss.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.comcast.cable.cvs.rss.reader.RssFeedSummary;
import com.comcast.cable.rss.services.UserService;

@Path("/{user}/feeds")
@Produces(MediaType.APPLICATION_JSON)
public class UserFeedResource {
    
    private final UserService userService;

    public UserFeedResource(UserService userService) {
        this.userService = userService;
    }

    @GET
    public List<RssFeedSummary> fetch(
            @PathParam("user") String userId) {
        return userService.getFeeds(userId);
    }
    
    

}