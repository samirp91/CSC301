package edu.toronto.csc301.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

import org.junit.Test;

import edu.toronto.csc301.ITweet;
import edu.toronto.csc301.test.util.AssignmentTestBase;

public class TweetTest extends AssignmentTestBase{
	
	public static final int MAX_USERNAME_LENGTH = 32;
	public static final int MAX_TEXT_LENGTH = 140;

	/**
	 * Convenience method.
	 */
	private ITweet t(String username, String text) throws Exception{
		return createTweet(username, text);
	}
	
	private String generateOneCharString(char c, int length){
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(c);
		}
		return sb.toString();
	}
	
	
	// ------------------------------------------------------------------------
	// Constructor

	
	@Test
	public void verifyCanCreateTweet() throws Exception {
		t("average_joe", "Hello world");
	}
	
	@Test
	public void verifyCanCreateTweetWithMaxLengthUsername() throws Exception {
		String username = generateOneCharString('x', MAX_USERNAME_LENGTH);
		t(username, "Hello world");
	}
	
	@Test
	public void verifyCanCreateTweetWithMaxLengthText() throws Exception {
		String text = generateOneCharString('x', MAX_TEXT_LENGTH);
		t("average_joe", text);
	}
	
	
	// Null Checks ...
	
	@Test(expected=NullPointerException.class)
	public void verifyCannotCreateTweetWithNullText() throws Exception {
		t("average_joe", null);
	}

	@Test(expected=NullPointerException.class)
	public void verifyCannotCreateTweetWithNullUsername() throws Exception {
		t(null, "Hello world");
	}

	
	// Invalid argument checks ...
	
	@Test(expected=IllegalArgumentException.class)
	public void verifyCannotCreateTweetWithUsernameContainingInvalidCharacter() throws Exception { 
		t("average.joe", "Hello world");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifyCannotCreateTweetWithEmptyUsername() throws Exception {
		t("", "Hello world");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifyCannotCreateTweetWithEmptyText() throws Exception { 
		t("average_joe", "");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifyCannotCreateTweetWithWhiteSpaceOnlyText() throws Exception { 
		t("average_joe", "   ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifyCannotCreateTweetWithTooLongUsername() throws Exception { 
		String username = generateOneCharString('x', MAX_USERNAME_LENGTH + 1);
		t(username, "Hello world");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifyCannotCreateTweetWithTooLongText() throws Exception { 
		String text = generateOneCharString('x', MAX_TEXT_LENGTH + 1);
		t("average_joe", text);
	}
	
	// ------------------------------------------------------------------------
	// Basic getters and setters

	@Test
	public void verifyUsernameIsSetCorrectly() throws Exception {
		String username = "average_joe"; 
		ITweet tweet = t(username, "Hello world");
		assertEquals(username, tweet.getUsername());
	}

	@Test
	public void verifyTextIsSetCorrectly() throws Exception {
		String text = "Hello world"; 
		ITweet tweet = t("average_joe", text);
		assertEquals(text, tweet.getText());
	}
	
	
	// Null checks ...
	
	@Test(expected=NullPointerException.class)
	public void verifyCannotSetNullText() throws Exception {
		t("average_joe", "Hello world").setText(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void verifyCannotSetNullUsername() throws Exception {
		t("average_joe", "Hello world").setUsername(null);
	}
	
	
	// Invalid arguments checks ...
	
	@Test(expected=IllegalArgumentException.class)
	public void verifySetUsernameWithInvalidCharacter() throws Exception { 
		t("average_joe", "Hello world").setUsername("average.joe");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifySetUsernameWithInvalidCharacter2() throws Exception { 
		t("average_joe", "Hello world").setUsername("average-joe");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifySetUsernameWithInvalidCharacter3() throws Exception { 
		t("average_joe", "Hello world").setUsername("average_joe!");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifyCannotSetEmptyUsername() throws Exception {
		t("average_joe", "Hello world").setUsername("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifyCannotSetWhiteSpaceOnlyUsername() throws Exception {
		t("average_joe", "Hello world").setUsername("  ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifyCannotSetEmptyText() throws Exception { 
		t("average_joe", "Hello world").setText("");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifyCannotSetWhiteSpaceOnlyText() throws Exception { 
		t("average_joe", "Hello world").setText("    ");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifyCannotSetTooLongUsername() throws Exception { 
		t("average_joe", "Hello world").setUsername(
				generateOneCharString('x', MAX_USERNAME_LENGTH + 1));
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void verifyCannotSetTooLongText() throws Exception { 
		t("average_joe", "Hello world").setText(
				generateOneCharString('x', MAX_TEXT_LENGTH + 1));
	}
	
	// ------------------------------------------------------------------------
	// equals()
	
	@Test
	public void verifyEqualsMethodOnTweetsWithSameUsernameAndText() throws Exception {
		String username = "average_joe";
		String text = "Hello world"; 
		assertEquals(t(username, text), t(username, text));
	}
	
	@Test
	public void verifyEqualsMethodOnTweetsWithSameUsernameAndDifferentText() throws Exception {
		String username = "average_joe";
		String text1 = "Hello world"; 
		String text2 = "Goodbye world";
		assertNotEquals(t(username, text1), t(username, text2));
	}
	
	@Test
	public void verifyEqualsMethodOnTweetsWithSameTextAndDifferentUsername() throws Exception {
		String username1 = "average_joe";
		String username2 = "average_jane";
		String text = "Hello world"; 
		assertNotEquals(t(username1, text), t(username2, text));
	}
	
	@Test
	public void verifyEqualsMethodOnTweetsWithDifferentUsernameAndText() throws Exception {
		String username1 = "average_joe";
		String username2 = "average_jane";
		String text1 = "Hello world"; 
		String text2 = "Goodbye world";
		assertNotEquals(t(username1, text1), t(username2, text2));
	}
	

	// Hash tag parsing I
	
	@Test
	public void verifyEmptyHashTagSetWhenThereAreNoHashTagsInTheText() throws Exception {
		ITweet tweet = t("average_joe", "Hello world");
		assertTrue(tweet.getHashTags().isEmpty());
	}
	
	@Test
	public void verifyHashTagSetWhenThereIsASingleHashTagAtTheBeginningOfTheText() throws Exception {
		ITweet tweet = t("average_joe", "#Hello world");
		assertSetItemsAre(tweet.getHashTags(), "Hello");
	}
	
	@Test
	public void verifyHashTagSetWhenThereIsASingleHashTagAtTheEndOfTheText() throws Exception {
		ITweet tweet = t("average_joe", "Hello #world");
		assertSetItemsAre(tweet.getHashTags(), "world");
	}
	
	@Test
	public void verifyHashTagSetWhenThereIsASingleHashTagInTheMiddleOfTheText() throws Exception {
		ITweet tweet = t("average_joe", "Hello #world, I am here!");
		assertSetItemsAre(tweet.getHashTags(), "world");
	}
	
	@Test
	public void verifyHashTagSetWhenThereAreMultipleHashTagsInTheText() throws Exception {
		ITweet tweet = t("average_joe", "Good luck to everybody in #csc301! #fall2015 #UofT #compSci");
		assertSetItemsAre(tweet.getHashTags(), "csc301", "fall2015", "UofT", "compSci");
	}
	
	
	// Hash tag parsing II
	
	@Test
	public void verifyHashTagCanIncludeUnderscore() throws Exception {
		ITweet tweet = t("average_joe", "A hash tag with #under_score");
		assertSetItemsAre(tweet.getHashTags(), "under_score");
	}
	
	@Test
	public void verifyHashTagCanIncludeNumbers() throws Exception {
		ITweet tweet = t("average_joe", "The name is ... #007bond");
		assertSetItemsAre(tweet.getHashTags(), "007bond");
	}
	
	@Test
	public void verifyHashTagIsOnlyAlphaNumericOrUnderscore1() throws Exception {
		ITweet tweet = t("average_joe", "A hash tag with #under_score!");
		assertSetItemsAre(tweet.getHashTags(), "under_score");
	}
	
	@Test
	public void verifyHashTagIsOnlyAlphaNumericOrUnderscore2() throws Exception {
		ITweet tweet = t("average_joe", "#Hello, world");
		assertSetItemsAre(tweet.getHashTags(), "Hello");
	}
	
	@Test
	public void verifyHashTagIsOnlyAlphaNumericOrUnderscore3() throws Exception {
		ITweet tweet = t("average_joe", "Hello #world.");
		assertSetItemsAre(tweet.getHashTags(), "world");
	}
	
	
	@Test
	public void verifyHashTagIsOnlyAlphaNumericOrUnderscore4() throws Exception {
		ITweet tweet = t("average_joe", "#hash-tag");
		assertSetItemsAre(tweet.getHashTags(), "hash");
	}
	
	
	@Test
	public void verifyNoEmptyHashTags() throws Exception {
		ITweet tweet = t("average_joe", "This tweet has # no hash-tags.");
		assertTrue(tweet.getHashTags().isEmpty());
	}
	
	
	// Hash tag parsing III

	@Test
	public void verifyCannotChangeTheHashTagSetDirectly() throws Exception{
		ITweet tweet = t("average_joe", "Hello #world, I am #here!");
		Set<String> hashTags = tweet.getHashTags();

		// Try to change the set directly
		try{
			hashTags.clear();
			// We didn't change the text, so we expect getHashTags() to return a set with two hash-tags.
			assertSetItemsAre(tweet.getHashTags(), "world", "here");
		} catch (UnsupportedOperationException e){
			// Great! Test passed - If clear() is not supported, then we cannot change the set directly.
		}
	}
	
	
	@Test
	public void verifySetTextUpdatesHashTags() throws Exception{
		ITweet tweet = t("average_joe", "Hello #world!");
		tweet.setText("No hash tags");
		assertTrue(tweet.getHashTags().isEmpty());
	}
	

}
