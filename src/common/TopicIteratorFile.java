package common;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TopicIteratorFile implements Iterator<Topic> {

	private boolean reachedEnd = false;
	private BufferedReader br;
	private Pattern topicNumPat;
	
	public TopicIteratorFile(String filePath) {
		
		try {
			br = new BufferedReader(new FileReader(filePath));
			String line;
			while ((line = br.readLine()) != null) {
				if (line.equals("<top>")) {
					break;
				} //if
		    } // while
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	    // Create a Pattern object
	    topicNumPat = Pattern.compile("<num> Number: MB(\\d+)");

	}
	
	public boolean hasNext() {
		return !reachedEnd;
	}

	public Topic next() {
		if (reachedEnd) {
			return null;
		}
		
		Topic query = new Topic();

		try {
			String line;
			// match topic number
			while ((line = br.readLine()) != null) {
			    // Now create matcher object.
			    Matcher m = topicNumPat.matcher(line);
				if (m.find()) {
					String g = m.group(1);
					query.setId( Integer.parseInt(g));
					break;
				} //if
		    } // while
			
			while ((line = br.readLine()) != null) {
				if (line.equals("<title>")) {
					line = br.readLine();
					query.setTitle(line);
					break;
				}
			}
			
			while ((line = br.readLine()) != null) {
				if (line.equals("<desc> Description:")) {
					break;
				}
			}		
	
			String description = "";
			while ((line = br.readLine()) != null) {
				if (line.equals("<narr> Narrative:")) {
					break;
				} else {
					description += line + " ";
				}
			}
			query.setDescription(description.trim());
			
			String narrative = "";
			while ((line = br.readLine()) != null) {
				if (line.equals("</top>")) {
					break;
				} else {
					narrative += line + " ";
				}
			}
			query.setNarrative(narrative.trim());

			while ((line = br.readLine()) != null) {
				if (line.equals("<top>")) {
					break;
				}
			}
			
			if (line == null) {
				reachedEnd = true;
			}
		
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return query;
	}
	
	public static void main(String[] args) {
		TopicIteratorFile it = new TopicIteratorFile("./data/topics.txt");
		
		Topic q;
		while ((q = it.next()) != null) {
		
			System.out.println("Id:"+ q.getId());
			System.out.println("Title:"+ q.getTitle());
			System.out.println("Desc:"+ q.getDescription());
			System.out.println("Narative:"+ q.getNarrative());
			System.out.println("====");
		} 
	}

}
