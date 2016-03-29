package edu.toronto.csc301.test;

import org.junit.Test;

import edu.toronto.csc301.IDataLoader;
import edu.toronto.csc301.IDatabase;
import edu.toronto.csc301.ITweet;
import edu.toronto.csc301.test.util.AssignmentTestBase;


public class ImplementationStructureTest extends AssignmentTestBase{

	
	@Test
	public void verifyTweetImplExists() {
		assertClassExists(CLASS_NAME_TWEET);
	}
	
	@Test
	public void verifyDataLoaderImplExists() {
		assertClassExists(CLASS_NAME_DATA_LOADER);
	}
	
	@Test
	public void verifyDatabaseImplExists() {
		assertClassExists(CLASS_NAME_DATABASE);
	}
	
	
	
	@Test
	public void verifyTweetImplInterface() throws ClassNotFoundException {
		
		assertClassImplementsInterface(CLASS_NAME_TWEET, ITweet.class);
	}
	
	@Test
	public void verifyDataLoaderImplInterface() throws ClassNotFoundException {
		assertClassImplementsInterface(CLASS_NAME_DATA_LOADER, IDataLoader.class);
	}
	
	@Test
	public void verifyDatabaseImplInterface() throws ClassNotFoundException {
		assertClassImplementsInterface(CLASS_NAME_DATABASE, IDatabase.class);
	}
	
	
	@Test
	public void verifyTweetHasTwoArgConstrutor() throws ClassNotFoundException {
		assertClassHasConstructor(CLASS_NAME_TWEET, String.class, String.class);
	}

	@Test
	public void verifyDatabaseHasDefaultConstructor() throws ClassNotFoundException {
		assertClassHasDefaultConstructor(CLASS_NAME_DATABASE);
	}
	
	@Test
	public void verifyDataLoaderHasDefaultConstructor() throws ClassNotFoundException {
		assertClassHasDefaultConstructor(CLASS_NAME_DATA_LOADER);
	}
	

}
