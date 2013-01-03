package com.comcast.cable.cvs.rss.reader;

import java.util.ArrayList;
import java.util.List;

/**
 * The good stuff in an RSS Feed
 * @author spont200
 */
public class RssFeed {

    private String image;
    private String title;
    private String description;
    private String link;
    private List<RssStory> stories;
    private String url;
    
    
    /**
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(String image) {
        this.image = image;
    }

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
     * @return the stories
     */
    public List<RssStory> getStories() {
        return stories;
    }

    /**
     * @param stories the stories to set
     */
    public void setStories(List<RssStory> stories) {
        this.stories = stories;
    }


    public void addStory(RssStory rssStory) {
        if (stories == null) {
            stories = new ArrayList<RssStory>();
        }
        stories.add(rssStory);
    }

    public void setUrl(String url) {
        this.url = url;
    }
    
    public String getUrl() {
        return url;
    }
    
    /**
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RssFeed [");
        if (image != null) {
            builder.append("image=").append(image).append(", ");
        }
        if (title != null) {
            builder.append("title=").append(title).append(", ");
        }
        if (description != null) {
            builder.append("description=").append(description).append(", ");
        }
        if (link != null) {
            builder.append("link=").append(link).append(", ");
        }
        if (stories != null) {
            builder.append("stories=\n");
            for (RssStory story : stories) {
                builder.append(story).append("\n");
            }
        }
        builder.append("]");
        return builder.toString();
    }
    
    
}
