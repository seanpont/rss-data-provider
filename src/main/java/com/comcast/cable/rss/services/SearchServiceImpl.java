package com.comcast.cable.rss.services;

import java.util.List;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.comcast.cable.cvs.rss.reader.RssFeed;

public class SearchServiceImpl implements SearchService {

	private final RssService rssService;
	private final String[] feeds;

	/**
	 * If we're here, it means that the rssService has cached all feeds
	 * @param feeds 
	 * @param rssService
	 */
	public SearchServiceImpl(String[] feeds, RssService rssService) {
		this.feeds = feeds;
		this.rssService = rssService;
		
		indexFeeds();
	}

	private void indexFeeds() {
		Directory dir = new RAMDirectory();
		Analyzer analyzer = new StandardAnalyzer(Version.LUCENE_36);
		IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_36, analyzer);
		try {
			IndexWriter indexWriter = new IndexWriter(dir, conf);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

		for (String url : feeds) {
			indexFeed(url);
		}
		
	}

	private void indexFeed(String url) {
		RssFeed feed = rssService.getFeed(url);
		
	}

	public List<RssFeed> find(String query) {
		// TODO Auto-generated method stub
		return null;
	}

}
