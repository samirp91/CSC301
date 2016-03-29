package edu.toronto.csc301.test.util;

import edu.toronto.csc301.IDataLoader;
import edu.toronto.csc301.util.CSC301TestBase;


public class AssignmentTestBase extends CSC301TestBase {

	private static final String PACKAGE = "edu.toronto.csc301.";
	public static final String CLASS_NAME_DATA_LOADER = PACKAGE + "DataLoader";
    public static final String CLASS_NAME_TWEET_STREAM_ITERATOR = PACKAGE +  "TweetStreamReadingIterator";
    public static final String CLASS_NAME_TWEET_FILTER_ITERATOR = PACKAGE +  "TweetFilteringIterator";
	
	
	public IDataLoader createDataLoader() throws Exception{
		return newInstance(AssignmentTestBase.CLASS_NAME_DATA_LOADER);
	}
	
}
