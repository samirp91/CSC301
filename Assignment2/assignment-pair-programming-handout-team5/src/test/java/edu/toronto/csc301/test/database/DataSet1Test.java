package edu.toronto.csc301.test.database;

import static org.hamcrest.core.AnyOf.anyOf;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import edu.toronto.csc301.test.DatabaseTestBase;

public class DataSet1Test extends DatabaseTestBase {

	@Override
	public String[][] getDataSet() {
		return new String[][]{
			{"Alice", "Ready? #yes Excited? #yes Scared? #yes"},
			{"Bob", "Hello #world"}
		};
	}

	@Override
	public int expectedTotalNumberOfTweets() {
		return 2;
	}

	@Override
	public int expectedNumberOfUsers() {
		return 2;
	}

	@Override
	public int expectedNumberOfUniqueHashTags() {
		return 2;
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
		throw new RuntimeException("Please override verifyMostPopularHashTags().");
	}
	
	@Override
	public void verifyMostPopularHashTags() {
		assertThat(db.getMostPopularHashTag(), anyOf(is("yes"), is("world")));
	}

}
