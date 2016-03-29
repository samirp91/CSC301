package edu.toronto.csc301.test.database;

import edu.toronto.csc301.test.DatabaseTestBase;

public class SingleTweetNoHashTagsTest extends DatabaseTestBase {


	@Override
	public String[][] getDataSet() {
		return new String[][]{
			{"Alice", "Hello world!"}
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
		return 0;
	}

	@Override
	public double expectedAverageNumberOfHashTagsPerTweet() {
		return 0;
	}

	@Override
	public double expectedAverageNumberOfTweetsPerUser() {
		return 1;
	}

	@Override
	public String expectedMostPopularHashTag() {
		return null;
	}

}
