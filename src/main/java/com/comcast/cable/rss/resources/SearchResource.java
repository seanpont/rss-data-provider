package com.comcast.cable.rss.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.comcast.cable.cvs.rss.reader.RssFeedSummary;
import com.comcast.cable.rss.services.SearchService;
import com.yammer.metrics.annotation.Timed;

@Path("/search")
@Produces(MediaType.APPLICATION_JSON)
public class SearchResource {

	private final SearchService searchService;

	public SearchResource(SearchService searchService) {
		this.searchService = searchService;
	}
	
	@GET
	@Timed
	public List<RssFeedSummary> get(@QueryParam("q") String query) {
		return searchService.find(query);
	}
	
	

}
