package com.comcast.cable.rss.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.comcast.cable.cvs.rss.reader.RssFeed;
import com.comcast.cable.rss.services.RssService;

@Path("/rss")
@Produces(MediaType.APPLICATION_JSON)
public class RssFeedResource {
    
    private final RssService rssService;

    public RssFeedResource(RssService rssService) {
        this.rssService = rssService;
    }

    @GET
    public RssFeed fetch(@QueryParam("id") int id) {
        return rssService.getFeedById(id);
    }
    
    

}