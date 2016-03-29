package edu.toronto.csc301.test.database;

import edu.toronto.csc301.test.DatabaseTestBase;

public class DataSet2Test extends DatabaseTestBase{


	@Override
	public String[][] getDataSet() {
		return new String[][]{
			{"Alice", "Hello world!"},
			{"Bob", "Who is taking #csc301 this term?"},
			{"Charlie", "I am. Taking #csc301, #csc324 and #csc309 ... #noSleep this term."},
			{"Bob", "I guess I'll see you at #csc301 lecture."},
			{"Alice", "Charlie, I'll be taking #csc324 with you."},
		};
	}


	@Override
	public int expectedTotalNumberOfTweets() {
		return 5;
	}

	@Override
	public int expectedNumberOfUsers() {
		return 3;
	}

	@Override
	public int expectedNumberOfUniqueHashTags() {
		return 4;
	}

	@Override
	public double expectedAverageNumberOfHashTagsPerTweet() {
		return 7.0 / 5;
	}

	@Override
	public double expectedAverageNumberOfTweetsPerUser() {
		return 5.0 / 3;
	}

	@Override
	public String expectedMostPopularHashTag() {
		return "csc301";
	}

}