package com.comcast.cable.cvs.rss.reader;

public class RssStory {

    private String image;
    private String title;
    private String description;
    private String link;
    
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
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("RssStory [");
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
            builder.append("link=").append(link);
        }
        builder.append("]");
        return builder.toString();
    }
    
}
