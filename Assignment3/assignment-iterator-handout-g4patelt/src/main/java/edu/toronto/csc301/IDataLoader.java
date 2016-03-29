package edu.toronto.csc301;

import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Set;

/**
 * Implementations of this interface allow us to read Tweets.
 */
public interface IDataLoader {
	
  /**
   * Return an iterator that visits all Tweets in <code>data</code>.
   * The returned iterator reads from <code>data</code> in lazy-loading fashion.
   * 
   * @param data A stream of text that represents a sequence of Tweets.
   */
  public Iterator<ITweet> iterator (InputStream data) throws IOException;
  
  /**
   * Return an iterator that visits those Tweets in <code>data</code> that
   * contains all of the given <code>hashTags</code>.
   * The returned iterator reads from <code>data</code> in lazy-loading fashion.
   * 
   * @param data A stream of text that represents a sequence of Tweets.
   * @param hashTags Set of hash-tags used for filtering.
   *   The returned iterator only visits Tweets containing all of the given hash-tags.   
   */
  public Iterator<ITweet> iterator (InputStream data, Set<String> hashtags) throws IOException;

}
