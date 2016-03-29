package edu.toronto.csc301;

import java.util.List;

public interface IDatabase {

	/**
	 * Insert the given tweets into this database.
	 */
	public void loadTweets(List<ITweet> tweets);
	
	
	public int getTotalNumberOfTweets();
	public int getNumberOfUniqueUsers();
	public int getNumberOfUniqueHashTags();
	
	public double getAverageNumberOfHashTagsPerTweet();
	public double getAverageNumberOfTweetsPerUser();
	
	public String getMostPopularHashTag();
	
}
