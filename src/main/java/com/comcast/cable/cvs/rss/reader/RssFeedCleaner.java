package com.comcast.cable.cvs.rss.reader;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringEscapeUtils;

public class RssFeedCleaner {

    public void clean(RssFeed rssFeed) {
        rssFeed.setDescription(clean(rssFeed.getDescription()));
        rssFeed.setTitle(clean(rssFeed.getTitle()));
        if (rssFeed.getImage() == null) {
            //try to use the favicon
            rssFeed.setImage(faviconize(rssFeed.getUrl()));
        }
        for (RssStory rssStory : rssFeed.getStories()) {
            cleanUpStory(rssStory);
        }
    }

    private void cleanUpStory(RssStory rssStory) {
        if (rssStory.getImage() == null) {
            rssStory.setImage(extractImage(rssStory.getDescription()));
        } else {
            rssStory.setImage(cleanImage(rssStory.getImage()));
        }
        rssStory.setDescription(clean(rssStory.getDescription()));
        rssStory.setTitle(clean(rssStory.getTitle()));
    }

    private static String cleanImage(String img) {
        if (img.contains("http")) {
            img = img.substring(img.lastIndexOf("http"));
        }
        if (!img.startsWith("http:")) {
            img = "http:" + img;
        }
        return img;
    }
    
    private static Pattern p = Pattern.compile("\\....?/");
    public static String faviconize(String url) {
        Matcher matcher = p.matcher(url);
        boolean find = matcher.find();
        if (find) {
            return url.substring(0, matcher.end()) + "favicon.ico";
        }
        return null;
    }


    private String clean(String string) {
        return StringEscapeUtils.unescapeHtml(string.replaceAll("\\<[^>]*>", " ").trim());
    }
    
    private static Pattern imagePattern = Pattern.compile("<img src=\"[^\"] + \"");

    private String extractImage(String description) {
        //Try to get from media content
        
        Matcher matcher = imagePattern.matcher(description);
        String img = null;
        if (matcher.find()) {
            img = description.substring(matcher.start(), matcher.end());
            img = img.substring(img.indexOf('"') + 1, img.lastIndexOf('"'));
            //trim to last instance of http:
            img = cleanImage(img);
        }
        
        return img;
    }


}
