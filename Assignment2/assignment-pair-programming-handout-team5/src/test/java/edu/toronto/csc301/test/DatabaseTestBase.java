package edu.toronto.csc301.test;

import static org.junit.Assert.assertEquals;

import java.util.LinkedList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.toronto.csc301.IDatabase;
import edu.toronto.csc301.ITweet;
import edu.toronto.csc301.test.util.AssignmentTestBase;

public abstract class DatabaseTestBase extends AssignmentTestBase{
	
	public static final double EPSILON = 0.001;
	protected IDatabase db;
	
	
	//-------------------------------------------------------------------------
	// Sub-classes will provide us with a data-set of tweets (as a String[][]),
	// and the expected results for the various IDatabase methods.
	
	/*
	 * NOTE: Feel free to return null, and no data will be loaded 
	 */
	public abstract String[][] getDataSet();
	
	public abstract int expectedTotalNumberOfTweets();
	public abstract int expectedNumberOfUsers();
	public abstract int expectedNumberOfUniqueHashTags();
	public abstract double expectedAverageNumberOfHashTagsPerTweet();
	public abstract double expectedAverageNumberOfTweetsPerUser();
	public abstract String expectedMostPopularHashTag();
	
	
	//-------------------------------------------------------------------------
	// Before each test method runs, we:
	// 1. Create a new database
	// 2. Convert the result of getDataSet() to a list of ITweet objects 
	// 3. Load the list to the new database.
	
	@Before
	public void setUp() throws Exception {
		db = createDatabase();
		
		// Convert the data items (String[]) into ITweet objects. 
		String[][] data = getDataSet();
		if(data != null){
			List<ITweet> tweets = new LinkedList<ITweet>();
			for (int i = 0; i < data.length; i++) {
				tweets.add(createTweet(data[i][0], data[i][1]));
			}
			
			// Load the tweets into the DB
			db.loadTweets(tweets);
		}
	}


	//-------------------------------------------------------------------------
	// Verify that each database method returns the expected result 
	
	
	
	
	@Test
	public void verifyNumberOfTweet() {
		assertEquals(expectedTotalNumberOfTweets(), db.getTotalNumberOfTweets());
	}
	
	@Test
	public void verifyNumberOfUsers() {
		assertEquals(expectedNumberOfUsers(), db.getNumberOfUniqueUsers());
	}
	
	@Test
	public void verifyNumberOfUniqueHashTags() {
		assertEquals(expectedNumberOfUniqueHashTags(), db.getNumberOfUniqueHashTags());
	}
	
	@Test
	public void verifyAverageNumberOfHashTagsPerTweet() {
		assertEquals(expectedAverageNumberOfHashTagsPerTweet(), db.getAverageNumberOfHashTagsPerTweet(), EPSILON);
	}
	
	@Test
	public void verifyAverageNumberOfTweetsPerUser() {
		assertEquals(expectedAverageNumberOfTweetsPerUser(), db.getAverageNumberOfTweetsPerUser(), EPSILON);
	}
	
	@Test
	public void verifyMostPopularHashTags() {
		assertEquals(expectedMostPopularHashTag(), db.getMostPopularHashTag());
	}


}
