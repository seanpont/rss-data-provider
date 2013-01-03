package com.comcast.cable.cvs.rss.reader;

import java.util.Arrays;
import java.util.HashMap;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * A concise RSS Feed parser.<br><br>
 * 
 * Example use:<br>
 * SAXParserFactory factory = SAXParserFactory.newInstance();
 * SAXParser saxParser = factory.newSAXParser();<br>
 * SaxRssHandler handler = new SaxRssHandler();<br>
 * saxParser.parse(new URL(url).openStream(), handler);<br>
 * RssFeed rssFeed = handler.getRssFeed();<br>

 * @author spont200
 */
public class SaxRssHandler extends DefaultHandler {

    private RssFeed rssFeed = new RssFeed();
    private RssStory rssStory = new RssStory();

    private StringBuilder tagBuilder = new StringBuilder();
    private StringBuilder contentBuilder = new StringBuilder();
    private Setter setter;

    private HashMap<String, Setter> tagMap;

    public SaxRssHandler() {
        tagMap = new HashMap<String, Setter>();
        
        tagMap.put(".rss.channel.title", setFeedTitle);
        tagMap.put(".rss.channel.description", setFeedDesc);
        tagMap.put(".rss.channel.image.url", setFeedImage);
        tagMap.put(".rss.channel.link", setFeedLink);
        
        tagMap.put(".rss.channel.item", setStory);
        tagMap.put(".rss.channel.item.title", setStoryTitle);
        tagMap.put(".rss.channel.item.description", setStoryDesc);
        tagMap.put(".rss.channel.item.media:content", setStoryImage);
        tagMap.put(".rss.channel.item.link", setStoryLink);
    }
    
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        tagBuilder.append(".").append(qName);
        setter = tagMap.get(tagBuilder.toString());
        if (setter != null) {
            setter.set(attributes);
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (setter != null) {
            contentBuilder.append(arrToStr(ch, start, length));
        }
    }
    
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (setter != null) {
            setter.set(contentBuilder.toString());
            contentBuilder = new StringBuilder();
        }
        int len = tagBuilder.length();
        tagBuilder.delete(len - qName.length() - 1, len);
        setter = tagMap.get(tagBuilder.toString());
    }
    
    private String arrToStr(char[] ch, int start, int length) {
        return new String(Arrays.copyOfRange(ch, start, start + length));
    }
    
    /**
     * @return the rssFeed
     */
    public RssFeed getRssFeed() {
        return rssFeed;
    }

    
    abstract class Setter {
        void set(String val) {
        }
        void set(Attributes attributes) {
        }
    }

    private Setter setFeedTitle = new Setter() {
        public void set(String val) {
            rssFeed.setTitle(val);
        }
    };
    
    private Setter setFeedDesc = new Setter() {
        public void set(String val) {
            rssFeed.setDescription(val);
        }
    };

    private Setter setFeedLink = new Setter() {
        public void set(String val) {
            rssFeed.setLink(val);
        }
    };

    private Setter setFeedImage = new Setter() {
        public void set(String val) {
            rssFeed.setImage(val);
        }
    };
    
    private Setter setStoryTitle = new Setter() {
        public void set(String val) {
            rssStory.setTitle(val);
        }
    };

    private Setter setStoryDesc = new Setter() {
        public void set(String val) {
            rssStory.setDescription(val);
        }
    };

    private Setter setStoryImage = new Setter() {
        public void set(Attributes attributes) {
            rssStory.setImage(attributes.getValue("url"));
        }
    };

    private Setter setStoryLink = new Setter() {
        public void set(String val) {
            rssStory.setLink(val);
        }
    };

    private Setter setStory = new Setter() {
        public void set(String val) {
            rssFeed.addStory(rssStory);
            rssStory = new RssStory();
        }
    };

    
}
