package edu.toronto.csc301.test.util;

import edu.toronto.csc301.IDatabase;
import edu.toronto.csc301.IDataLoader;
import edu.toronto.csc301.ITweet;
import edu.toronto.csc301.util.CSC301TestBase;

public abstract class AssignmentTestBase extends CSC301TestBase {

	private static final String PACKAGE = "edu.toronto.csc301.";
	public static final String CLASS_NAME_TWEET = PACKAGE + "Tweet";
	public static final String CLASS_NAME_DATA_LOADER = PACKAGE + "DataLoader";
	public static final String CLASS_NAME_DATABASE = PACKAGE + "Database";
	
	
	protected ITweet createTweet(String username, String text) throws Exception{
		return newInstance(CLASS_NAME_TWEET, 
				new Class<?>[] {String.class, String.class}, 
				username, text);
	}
	
	protected IDatabase createDatabase() throws Exception{
		return newInstance(CLASS_NAME_DATABASE);
	}

	protected IDataLoader createDataLoader() throws Exception{
		return newInstance(CLASS_NAME_DATA_LOADER);
	}
	
}
