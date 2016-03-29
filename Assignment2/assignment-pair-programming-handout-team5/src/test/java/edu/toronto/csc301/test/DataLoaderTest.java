package edu.toronto.csc301.test;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import edu.toronto.csc301.IDataLoader;
import edu.toronto.csc301.ITweet;
import edu.toronto.csc301.test.util.AssignmentTestBase;

public class DataLoaderTest extends AssignmentTestBase{
	
	private IDataLoader loader;
	
	
	/**
	 * Verify that the data loaded from a file is as expected.
	 * 
	 * @param resourceAbsolutePath Absolute path to a resource file (resource
	 * 		files are located in the src/test/resources folder)
	 * @param expectedData The tweet data we expect (each String[] has two items, 
	 * 		the username and the text).
	 */
	private void testLoadedData(String resourceAbsolutePath, String[][] expectedData) throws IOException{
		InputStream inputStream =  getClass().getResourceAsStream(resourceAbsolutePath);
		try{
			List<ITweet> tweets = loader.load(inputStream);
			
			assertEquals(expectedData.length, tweets.size());
			
			for (int i = 0; i < expectedData.length; i++) {
				assertEquals(expectedData[i][0], tweets.get(i).getUsername());
				assertEquals(expectedData[i][1], tweets.get(i).getText());
			}
			
		} finally {
			inputStream.close();
		}
	}
	

	@Before
	public void setUp() throws Exception {
		loader = createDataLoader();
	}

	
	
	
	@Test
	public void verifyDataSet1IsLoadedCorrectly() throws IOException {
		String[][] expectedData = {
			{"Alice", "Hi Bob, are you taking #csc301 this term?"}
		};
		
		testLoadedData("/data-loader-test-data-1.txt", expectedData);
	}
	
	
	@Test
	public void verifyDataSet2IsLoadedCorrectly() throws IOException {
		String[][] expectedData = {
			{"Alice", "Hi Bob, are you taking #csc301 this term?"},
			{"Bob", "No Alice, I took it last year"},
			{"Bob", "#bestCourseEver!"}
		};
		
		testLoadedData("/data-loader-test-data-2.txt", expectedData);
	}
	
	
	@Test
	public void verifyDataSet3IsLoadedCorrectly() throws IOException {
		String[][] expectedData = {
			{"john_doe", "A Tweet that ends with a few white spaces"},
			{"john_doe", "Also, let's make sure blank lines are ignored."},
			{"john_doe", "Spaces between the username and text are optional."},
			{"john_doe", "Spaces between the username and text are ignored."}
		};
		
		testLoadedData("/data-loader-test-data-3.txt", expectedData);
	}
	
	
}
