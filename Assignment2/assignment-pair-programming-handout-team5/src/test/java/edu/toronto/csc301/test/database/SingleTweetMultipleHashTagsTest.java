package edu.toronto.csc301.test.database;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import edu.toronto.csc301.test.DatabaseTestBase;

public class SingleTweetMultipleHashTagsTest extends DatabaseTestBase {


	@Override
	public String[][] getDataSet() {
		return new String[][]{
			{"Charlie", "I am. Taking #csc301, #csc324 and #csc309 ... #noSleep this term."}
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
		return 4;
	}

	@Override
	public double expectedAverageNumberOfHashTagsPerTweet() {
		return 4;
	}

	@Override
	public double expectedAverageNumberOfTweetsPerUser() {
		return 1;
	}

	@Override
	public String expectedMostPopularHashTag() {
		throw new RuntimeException("Please override verifyMostPopularHashTags().");
	}
	
	@Override
	public void verifyMostPopularHashTags() {
		assertThat(db.getMostPopularHashTag(), anyOf(
			is("csc301"), is("csc309"), is("csc324"), is("noSleep"))
		);
	}
	
}
