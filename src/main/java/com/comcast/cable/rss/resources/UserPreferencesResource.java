package com.comcast.cable.rss.resources;

import javax.ws.rs.DELETE;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.comcast.cable.rss.services.UserService;
import com.yammer.metrics.annotation.Timed;

@Path("/{user}/preferences")
@Produces(MediaType.APPLICATION_JSON)
public class UserPreferencesResource {
    
    private final UserService userService;
    
    public UserPreferencesResource(UserService userService) {
        this.userService = userService;
    }

    @PUT
    @Timed
    public void add(
            @PathParam("user") String userId,
            @QueryParam("feed") String feed) {
        userService.addFeed(userId, feed);
    }
    
    @DELETE
    @Timed
    public void remove(
            @PathParam("user") String userId,
            @QueryParam("feed") String feed) {
        userService.removeFeed(userId, feed);
    }
    
    
    
}