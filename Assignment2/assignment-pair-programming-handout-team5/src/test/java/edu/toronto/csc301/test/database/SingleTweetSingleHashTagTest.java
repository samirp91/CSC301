package edu.toronto.csc301.test.database;

import edu.toronto.csc301.test.DatabaseTestBase;

public class SingleTweetSingleHashTagTest extends DatabaseTestBase {


	@Override
	public String[][] getDataSet() {
		return new String[][]{
			{"Bob", "Who is taking #csc301 this term?"}
		};
	}


	@Override
	public int expectedTotalNumberOfTweets() {
		return 1;
	}

	@Override
	public int expectedNumberOfUsers() {
		return 1;
	}

	@Override
	public int expectedNumberOfUniqueHashTags() {
		return 1;
	}

	@Override
	public double expectedAverageNumberOfHashTagsPerTweet() {
		return 1;
	}

	@Override
	public double expectedAverageNumberOfTweetsPerUser() {
		return 1;
	}

	@Override
	public String expectedMostPopularHashTag() {
		return "csc301";
	}

}
