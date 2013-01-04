package com.comcast.cable.rss.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.Store;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.PrefixQuery;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import com.comcast.cable.cvs.rss.reader.RssFeed;
import com.comcast.cable.cvs.rss.reader.RssFeedSummary;
import com.yammer.dropwizard.lifecycle.Managed;

public class SearchServiceImpl implements SearchService, Managed {

    private static final Logger logger = Logger.getLogger(SearchServiceImpl.class);
    
    private final RssService rssService;
    private final String[] feeds;
    private Directory dir;
    private Analyzer analyzer;
    private IndexSearcher searcher;
    private IndexReader reader;
    
    /**
     * If we're here, it means that the rssService has cached all feeds
     * @param feeds 
     * @param rssService
     */
    public SearchServiceImpl(String[] feeds, RssService rssService) {
        this.feeds = feeds;
        this.rssService = rssService;

    }
    
    public void start() throws Exception {
        logger.info("indexing feeds");
        indexFeeds();
        logger.info("indexing complete");
    }

    public void stop() throws Exception {
        logger.info("closing index");
        searcher.close();
        reader.close();
        dir.close();
        logger.info("index closed");
    }


    private void indexFeeds() throws Exception {
        dir = new RAMDirectory();
        analyzer = new StandardAnalyzer(Version.LUCENE_36);
        IndexWriterConfig conf = new IndexWriterConfig(Version.LUCENE_36, analyzer);
        
        IndexWriter indexWriter = new IndexWriter(dir, conf);

        for (String url : feeds) {
            logger.info("indexing: "+url); 
            
            RssFeed feed = rssService.getFeed(url);
            
            if (feed == null) {
                logger.warn("feed "+url+" was null!");
                continue;
            }

            Document doc = new Document();
            doc.add(new Field("id", feed.getUrl(), Store.YES, Index.NOT_ANALYZED));
            doc.add(new Field("content", feed.getUrl(), Store.NO, Index.ANALYZED));
            doc.add(new Field("content", feed.getLink(), Store.NO, Index.ANALYZED));
            doc.add(new Field("content", feed.getTitle(), Store.NO, Index.ANALYZED));
            doc.add(new Field("content", feed.getDescription(), Store.NO, Index.ANALYZED));

            indexWriter.addDocument(doc);
            logger.info("added "+url+" to index");
        }
        
        logger.info("commiting docs to index");
        indexWriter.close();
        
        logger.info("openning index reader and searcher");
        reader = IndexReader.open(dir);
        searcher = new IndexSearcher(reader);
        logger.info("index reader and searcher opened");
    }
    
    public List<RssFeedSummary> find(String key) {
        Term term = new Term("content", key);
        Query query = new PrefixQuery(term);
        
        List<RssFeedSummary> feeds = new ArrayList<RssFeedSummary>();
        try {
            TopDocs topDocs = searcher.search(query, 3);
            for (ScoreDoc scoreDoc : topDocs.scoreDocs) {
                Document doc = searcher.doc(scoreDoc.doc);
                String url = doc.get("id");
                feeds.add(rssService.getSummary(url));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return feeds;
    }
    

}















