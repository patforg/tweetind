package searcher;


import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;

import common.Topic;
import common.TopicIteratorFile;

public class Searcher {


	public static void main(String[] args) throws IOException, ParseException {
		String runName = "detailed_query";

		// prepare index
		String indexName = "baseline";
		File indexFile = new File("./data/"+ indexName +"_index");
		Path indexPath = indexFile.toPath();
		Directory directory = FSDirectory.open(indexPath);

		DirectoryReader ireader = DirectoryReader.open(directory);
		IndexSearcher isearcher = new IndexSearcher(ireader);

		// setup basic query
		Analyzer analyzer = new StandardAnalyzer();
		QueryParser parser = new QueryParser("text", analyzer);

		// setup date field for result file
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		String today = sdf.format(date);

		// setup results file
		PrintWriter resultWriter = new PrintWriter("./data/"+ runName  +"_results.txt", "UTF-8");

		// process each topic
		TopicIteratorFile topicIterator = new TopicIteratorFile("./data/topics.txt");
		Topic topic;
		String queryText;
		while ((topic = topicIterator.next()) != null) {

			// prepare query text
			queryText = "";
			queryText =  topic.getTitle() + " "+ topic.getDescription(); // + " "+ t.getNarrative();
			queryText = queryText.replaceAll("/", "");

			// process results
			Query query = parser.parse(queryText);
			ScoreDoc[] hits = isearcher.search(query, 10).scoreDocs;

			int rank = 1;
			int score;
			System.out.print(topic.getId());
			for (int i = 0; i < hits.length; i++) {

				score = (int)  Math.round(hits[i].score * 1000);
				Document hitDoc = isearcher.doc(hits[i].doc);

				resultWriter.print(today + " ");
				resultWriter.print("MB"+ topic.getId() + " ");
				resultWriter.print("Q0 ");
				resultWriter.print(hitDoc.get("id") + " ");
				resultWriter.print(rank + " ");
				resultWriter.print(score + " ");
				resultWriter.println(runName);
				System.out.print(".");

				rank++;

			}
			System.out.println(".");
		} 

		// clean up
		resultWriter.close();
		ireader.close();
		directory.close();
	}

}
