package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Iterator;

public class TweetIteratorSqlite implements Iterator<Tweet> {

	private String dbPath = "";
	private int offset = 0;
	private int limit = 1000;
	private Connection connection;
	private ResultSet results;
	private boolean reachedEnd = false;
	
	public TweetIteratorSqlite(String dbPath, int offset) {
		this.dbPath = dbPath;
		this.offset = offset;
	}

	public TweetIteratorSqlite(String dbPath) {
		this.dbPath = dbPath;
	}

	public boolean hasNext() {
		
		if (results == null) {
			getMore();
		}
		
		return !reachedEnd;
	}

	public Tweet next() {
		Tweet tweet = null;
		try {
			
			if (results == null || !results.next()) {
				getMore();
			} //if
					
			if (!reachedEnd) {		
				tweet = new Tweet();
				
				tweet.setText(results.getString("text"));
				tweet.setId(results.getString("id"));
				tweet.setLang(results.getString("lang"));
				
			} //if				
		
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		} //try
	
		return tweet;
	}

	private void getMore() {
		try
		{

			if (connection == null) {
				// load the sqlite-JDBC driver using the current class loader
				Class.forName("org.sqlite.JDBC");

				// create a database connection
				connection = DriverManager.getConnection("jdbc:sqlite:"+ dbPath);
			} //if

			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);  // set timeout to 30 sec.

			results = statement.executeQuery("SELECT * FROM tweets ORDER BY _rowid_ LIMIT "+ offset +","+ limit);
			offset += limit;
			
			if (!results.next()) {
				reachedEnd = true;
				if(connection != null) {
					connection.close();
				}
			}

		}
		catch (ClassNotFoundException e)
		{
			System.err.println(e.getMessage());
		}
		catch(SQLException e)
		{
			// if the error message is "out of memory", 
			// it probably means no database file is found
			System.err.println(e.getMessage());
		}
	
	}
}
