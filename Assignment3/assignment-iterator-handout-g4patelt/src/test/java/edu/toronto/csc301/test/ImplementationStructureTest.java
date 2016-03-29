package edu.toronto.csc301.test;

import java.io.InputStream;
import java.util.Iterator;
import java.util.function.Predicate;

import org.junit.Test;

import edu.toronto.csc301.IDataLoader;
import edu.toronto.csc301.test.util.AssignmentTestBase;


public class ImplementationStructureTest extends AssignmentTestBase{


  @Test
  public void verifyDataLoaderImplExists() {
    assertClassExists(AssignmentTestBase.CLASS_NAME_DATA_LOADER);
  }
  
  @Test
  public void verifyStreamIteratorImplExists() {
    assertClassExists(AssignmentTestBase.CLASS_NAME_TWEET_STREAM_ITERATOR);
  }
  
  @Test
  public void verifyFilteringIteratorImplExists() {
    assertClassExists(AssignmentTestBase.CLASS_NAME_TWEET_FILTER_ITERATOR);
  }


  @Test
  public void verifyDataLoaderImplInterface() throws ClassNotFoundException {
    assertClassImplementsInterface(
    		AssignmentTestBase.CLASS_NAME_DATA_LOADER, IDataLoader.class);
  }

  @Test
  public void verifyStreamIteratorImplInterface() throws ClassNotFoundException {
    assertClassImplementsInterface(
    		AssignmentTestBase.CLASS_NAME_TWEET_STREAM_ITERATOR, Iterator.class);
  }

  @Test
  public void verifyStreamIteratorHasConstructor() throws ClassNotFoundException {
    assertClassHasConstructor(AssignmentTestBase.CLASS_NAME_TWEET_STREAM_ITERATOR,
        InputStream.class);
  }

  @Test
  public void verifyFilteringIteratorImplInterface() throws ClassNotFoundException {
    assertClassImplementsInterface(AssignmentTestBase.CLASS_NAME_TWEET_FILTER_ITERATOR,
        Iterator.class);
  }
  
  @Test
  public void verifyFilteringIteratorHasConstructor() throws ClassNotFoundException {
    assertClassHasConstructor(
            AssignmentTestBase.CLASS_NAME_TWEET_FILTER_ITERATOR,
            Iterator.class, Predicate.class);
  }  
  


  @Test
  public void verifyDataLoaderHasDefaultConstructor() throws ClassNotFoundException {
	  assertClassHasDefaultConstructor(AssignmentTestBase.CLASS_NAME_DATA_LOADER);
  }


}
