package common;

import java.io.File;
import java.util.Collection;

import org.deeplearning4j.models.embeddings.loader.WordVectorSerializer;
import org.deeplearning4j.models.word2vec.Word2Vec;

public class TestVectors {

	public static void main(String[] args) {
		Word2Vec vec = WordVectorSerializer.readWord2VecModel(new File("./data/glove.twitter.27B.200d.txt"));
		Collection<String> n = vec.wordsNearest("quilt", 10);
		System.out.println(n);
		
		n = vec.wordsNearest("show", 10);
		System.out.println(n);
	}

}
