package indexer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;

import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import common.Tweet;
import common.TweetIteratorSqlite;

public class Indexer {

	private IndexWriter iwriter;
	
	private Iterator<Tweet> tweetIt;
	private String indexPath;
	private String dbPath;
	
	public static void main(String[] args) {

		String indexName = "baseline";
		new Indexer("./data/"+ indexName +"_index", "./data/db.sqlite").run();
	    
	}
	
	public Indexer(String indexPath, String dbPath) {
		this.indexPath = indexPath;
		this.dbPath = dbPath;
	}
	
	public void run() {
		try {
			Tweet tweet;
			String progressString = ".";
			setup();
			int count = 0;
			tweet = tweetIt.next();
			
			while(tweet != null) {					
				
				Document doc = new Document();			    
			    doc.add(new Field("text", tweet.getText(), TextField.TYPE_STORED));
			    doc.add(new Field("id", tweet.getId(), TextField.TYPE_STORED));
				iwriter.addDocument(doc);
				count++;
				
				if (count % 5000 == 0) {
					System.out.println(count);
				} else if (count % 200 == 0) {
					System.out.print(progressString);
				} //if				
				tweet = tweetIt.next();
			}
			System.out.println("\nIndexed "+ count +" documents.");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			cleanup();
		}
	}
	
	private void setup() throws IOException {
		
		File file = new File(indexPath);
		Path path = file.toPath();
	    Directory directory = FSDirectory.open(path);
	    
	    Analyzer analyzer = new StandardAnalyzer();
	    IndexWriterConfig config = new IndexWriterConfig(analyzer);
	    
	    iwriter = new IndexWriter(directory, config);
	    
	    tweetIt = new TweetIteratorSqlite(dbPath);
		
	}

	private void cleanup() {
		try {
			iwriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
