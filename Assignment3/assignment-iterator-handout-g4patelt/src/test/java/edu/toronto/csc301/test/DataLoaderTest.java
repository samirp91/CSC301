package edu.toronto.csc301.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import edu.toronto.csc301.IDataLoader;
import edu.toronto.csc301.ITweet;
import edu.toronto.csc301.test.util.AssignmentTestBase;

public class DataLoaderTest extends AssignmentTestBase {

  private IDataLoader loader;
  
  @Before
  public void setUp() throws Exception {
	  loader = createDataLoader();  
  }

  //===========================================================================
  // Private Helper Methods
  
  /**
   * 
   * @param iter iterator to test
   * @param expectedData The tweet data we expect (each String[] has two items, the username and the
   *        text).
   */
  private void testData(Iterator<ITweet> iter, String [][] expectedData) {
      int i = 0;
      while (iter.hasNext()) {
        assertTrue("iterator visits more elements than expectedData contains",
            i < expectedData.length);
        ITweet tweet = iter.next();
        assertEquals(expectedData[i][0], tweet.getUsername());
        assertEquals(expectedData[i][1], tweet.getText());
        i++;
      }
      assertFalse("iterator visits fewer items than expectedData contains",
          i < expectedData.length);
  }

  private InputStream openResource(String resourceAbsolutePath) {
    return getClass().getResourceAsStream(resourceAbsolutePath);
  }

  /**
   * Verify that the data loaded from a file is as expected.
   * 
   * @param resourceAbsolutePath Absolute path to a resource file (resource files are located in the
   *        src/test/resources folder)
   * @param expectedData The tweet data we expect (each String[] has two items, the username and the
   *        text).
   */
  private void testLoadedData(String resourceAbsolutePath, String[][] expectedData)
      throws IOException {
    InputStream inputStream = openResource(resourceAbsolutePath);
    if (inputStream == null) {
      System.out.println("cannot open " + resourceAbsolutePath);
      throw new IOException("cannot open " + resourceAbsolutePath);
    }
    try {
      testData(loader.iterator(inputStream), expectedData );
    } finally {
      inputStream.close();
    }
  }
  

  /**
   * Verify that the data loaded from a file is as expected.
   * 
   * @param resourceAbsolutePath Absolute path to a resource file (resource files are located in the
   *        src/test/resources folder)
   * @param expectedData The tweet data we expect (each String[] has two items, the username and the
   *        text).
   * @param tagsToFilterBy array of Strings listing the hashtags that the tweets will be filtered by
   */
  private void testFilteredLoadedData(String resourceAbsolutePath, String[][] expectedData,
      String... tagsToFilterBy) throws IOException {
    InputStream inputStream = openResource(resourceAbsolutePath);
    if (inputStream == null) {
      System.out.println("cannot open " + resourceAbsolutePath);
      throw new IOException("cannot open " + resourceAbsolutePath);
    }
    try {
      testData(loader.iterator(inputStream, new HashSet<String>(Arrays.asList(tagsToFilterBy))),
          expectedData);
    } finally {
      inputStream.close();
    }
  }
  
  private Set<String> toSet(String... hashTags) {
	Set<String> set = new HashSet<String>();
	for (int i = 0; i < hashTags.length; i++) {
		set.add(hashTags[i]);
	}
	return set;
  }
  
  private InputStream createFakeStream(int numOfTweets, String text){
	  StringBuilder sb = new StringBuilder();
	  for (int i = 1; i <= numOfTweets; i++) {
		sb.append("@user" + i + ": " + text.trim() + "\n");
	  }
	  return new ByteArrayInputStream(sb.toString().getBytes(StandardCharsets.UTF_8));
  }

  
  //===========================================================================
  // Test Cases ...

  
  
  // Check the type of the returned iterator

  @Test
  public void checkDataLoaderIteratorReturnsTweetStreamReadingIterator() throws IOException, ClassNotFoundException {
    Iterator<ITweet> iter = loader.iterator(openResource("/test-data-1.txt"));
    assertEquals(CLASS_NAME_TWEET_STREAM_ITERATOR, iter.getClass().getName());
  }
  
  
  @Test
  public void checkDataLoaderIteratorReturnsTweetFilteringIterator() throws IOException, ClassNotFoundException {
    Iterator<ITweet> iter = loader.iterator(openResource("/test-data-1.txt"), toSet("foo"));
    assertEquals(CLASS_NAME_TWEET_FILTER_ITERATOR, iter.getClass().getName());
  }
  
  //---------------------------------------------------------------------------


  @Test
  public void verifyDataSet1IsLoadedCorrectly() throws IOException {
    String[][] expectedData = {
        {"Alice", "Hi Bob, are you taking #csc301 this term?"}
    };
    testLoadedData("/test-data-1.txt", expectedData);
  }

  @Test
  public void verifyDataSet2IsLoadedCorrectly() throws IOException {
    String[][] expectedData = {
        {"Alice", "Hi Bob, are you taking #csc301 this term?"},
        {"Bob", "No Alice, I took it last year"}, 
        {"Bob", "#bestCourseEver!"}
    };
    testLoadedData("/test-data-2.txt", expectedData);
  }

  @Test
  public void verifyDataSet3IsLoadedCorrectly() throws IOException {
    String[][] expectedData = {
        {"Alice", "Hello world!"}, 
        {"Bob", "Who is taking #csc301 this term?"},
        {"Charlie", "I am. Taking #csc301, #csc324 and #csc309 ... #noSleep this term."}
    };
    testLoadedData("/test-data-3.txt", expectedData);
  }

  // ------------------ filtering iterator ---------------------
  
  // Invalid argument checks ...


  @Test(expected = IllegalArgumentException.class)
  public void verifyDataSet1IsLoadedCorrectly_FilterByEmptyHashtag() throws IOException {
    loader.iterator(openResource("/test-data-1.txt"), new HashSet<String>());
  }

  @Test(expected = NullPointerException.class)
  public void verifyDataSet1IsLoadedCorrectly_FilterByNullHashtag() throws IOException {
    loader.iterator(openResource("/test-data-1.txt"), null); // null is illegal for second arg
  }
  
  // Test data-set 1 ...


  @Test
  public void verifyDataSet1IsLoadedCorrectly_FilterByExistingHardCodedHashtag()
      throws IOException {
    String[][] expectedData = {
    	{"Alice", "Hi Bob, are you taking #csc301 this term?"}
    };
    testFilteredLoadedData("/test-data-1.txt", expectedData, "csc301");
  }

  @Test
  public void verifyDataSet1IsLoadedCorrectly_FilterByNonExistingHardCodedHashtag()
      throws IOException {
    String[][] expectedData = {};
    testFilteredLoadedData("/test-data-1.txt", expectedData, "foobar");
  }
  
  
  // Test data-set 2 ...
  
  @Test
  public void verifyDataSet2IsFilteredCorrectly1() throws IOException {
    String[][] expectedData = {		
    		{"Alice", "Hi Bob, are you taking #csc301 this term?"}
    };
    testFilteredLoadedData("/test-data-2.txt", expectedData, "csc301");
  }
  
  @Test
  public void verifyDataSet2IsFilteredCorrectly2() throws IOException {
    String[][] expectedData = {		
    		{"Bob", "#bestCourseEver!"}
    };
    testFilteredLoadedData("/test-data-2.txt", expectedData, "bestCourseEver");
  }
  
  @Test
  public void verifyDataSet2IsFilteredCorrectly3() throws IOException {
    String[][] expectedData = {};
    testFilteredLoadedData("/test-data-2.txt", expectedData, "csc301", "bestCourseEver");
  }
  
  
  // Test data-set 3 ...
  

  @Test
  public void verifyDataSet3IsLoadedCorrectly_FilterByOneTag() throws IOException {
    String[][] expectedData = {
    		{"Bob", "Who is taking #csc301 this term?"},
    		{"Charlie", "I am. Taking #csc301, #csc324 and #csc309 ... #noSleep this term."}
    };
    testFilteredLoadedData("/test-data-3.txt", expectedData, "csc301");
  }

  @Test
  public void verifyDataSet3IsLoadedCorrectly_FilterByTwoTag() throws IOException {
    String[][] expectedData = {
    		{"Charlie", "I am. Taking #csc301, #csc324 and #csc309 ... #noSleep this term."}
    };
    testFilteredLoadedData("/test-data-3.txt", expectedData, "csc301", "noSleep");
  }
  
  
  //---------------------------------------------------------------------------
  // Test lazy-loading ...
  
  
  @Test
  public void verifyStreamingIteratorDoesNotReadOnConstruction() throws IOException{
	  // Open a stream with a single tweet
	  InputStream inputStream = openResource("/test-data-1.txt");
	  // Create the iterator, but DON'T call hasNext() or next()
	  loader.iterator(inputStream);
	  
	  // Verify that the iterator didn't read from the stream
	  BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
	  assertEquals("@Alice : Hi Bob, are you taking #csc301 this term?", br.readLine());
  }
  
  @Test
  public void verifyFilteringIteratorDoesNotReadOnConstruction() throws IOException{
	  // Open a stream with a single tweet
	  InputStream inputStream = openResource("/test-data-1.txt");
	  // Create the iterator, but DON'T call hasNext() or next()
	  loader.iterator(inputStream, toSet("foo"));
	  
	  // Verify that the iterator didn't read from the stream
	  BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
	  assertEquals("@Alice : Hi Bob, are you taking #csc301 this term?", br.readLine());
  }
  

  
  @Test
  public void verifyStreamingIteratorIsLazyLoaded() throws IOException{
	  // Create a fake stream with many tweets
	  InputStream inputStream = createFakeStream(3000, "Hello World #testing ...");
	  
	  // Create the iterator, but DON'T call hasNext() or next()
	  Iterator<ITweet> tweets = loader.iterator(inputStream);
	  // Create a bufferedReader that uses the same stream object
	  BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
	  
	  
	  assertTrue(tweets.hasNext()); // Causes the iterator to read from the stream 
	  
	  // The iterator read some data from the stream, we want to verify 
	  // that it didn't read all of the data.
	  // That is, we want to verify that the iterator is lazily-loading
	  // data from the stream.
	  
	  // Let's make sure there is something left to read from the stream
	  assertNotNull(br.readLine());
  }
  
  
  @Test
  public void verifyFileringIteratorIsLazyLoaded() throws IOException{
	  // Identical to the test above, only with a filtering iterator
	  InputStream inputStream = createFakeStream(3000, "Hello World #testing ...");
	  // Create a filtering iterator
	  Iterator<ITweet> tweets = loader.iterator(inputStream, toSet("testing"));
	  BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
	  assertTrue(tweets.hasNext());
	  assertNotNull(br.readLine());
  }
  
  
}
