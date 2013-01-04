package com.comcast.cable.cvs.rss.reader;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Just the good stuff
 * @author spont200
 */
public class RssFeedSummary {

    @NotEmpty
    @JsonProperty
    private int id;

    @NotEmpty
    @JsonProperty
    private String title;

    @NotEmpty
    @JsonProperty
    private String description;

    @NotEmpty
    @JsonProperty
    private String link;
    
    @NotEmpty
    @JsonProperty
    private String url;
    
    @NotEmpty
    @JsonProperty
    private int numStories;

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the link
     */
    public String getLink() {
        return link;
    }

    /**
     * @param link the link to set
     */
    public void setLink(String link) {
        this.link = link;
    }

    /**
     * @return the numStories
     */
    public int getNumStories() {
        return numStories;
    }

    /**
     * @param numStories the numStories to set
     */
    public void setNumStories(int numStories) {
        this.numStories = numStories;
    }

    public void addStory() {
        numStories++;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RssFeedSummary [id=").append(id).append(", ");
        if (title != null)
            builder.append("title=").append(title).append(", ");
        if (description != null)
            builder.append("description=").append(description).append(", ");
        if (link != null)
            builder.append("link=").append(link).append(", ");
        if (url != null)
            builder.append("url=").append(url).append(", ");
        builder.append("numStories=").append(numStories).append("]");
        return builder.toString();
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }


    


}
