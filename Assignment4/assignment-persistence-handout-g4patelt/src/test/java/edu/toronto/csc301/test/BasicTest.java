package edu.toronto.csc301.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import edu.toronto.csc301.test.cases.ImplementationStructureTests;
import edu.toronto.csc301.test.cases.InvalidArgumentsTests;
import edu.toronto.csc301.test.cases.UserTests;
import edu.toronto.csc301.test.cases.PostTests;
import edu.toronto.csc301.test.cases.UserStoreTests;

@RunWith(Suite.class)
@SuiteClasses({
	ImplementationStructureTests.class,
	InvalidArgumentsTests.class,
	UserTests.class,
	PostTests.class,
	UserStoreTests.class
})
public class BasicTest {


}