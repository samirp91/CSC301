package edu.toronto.csc301.test.cases;

import org.junit.Test;

import edu.toronto.csc301.ISerializer;
import edu.toronto.csc301.IUserStore;
import edu.toronto.csc301.util.CSC301TestBase;


public class ImplementationStructureTests extends CSC301TestBase{

	//-------------------------------------------------------------------------
	
	private static final String PACKAGE = "edu.toronto.csc301.impl.";

	public static final String CLASS_NAME_USER_STORE = PACKAGE + "UserStore";
	public static final String CLASS_NAME_SERIALIZER = PACKAGE + "Serializer";
	
	//-------------------------------------------------------------------------

	
	
	// Verify that the required classes exist
	
	@Test
	public void userStoreImplExists() {
		assertClassExists(CLASS_NAME_USER_STORE);
	}
	
	@Test
	public void serializerImplExists() {
		assertClassExists(CLASS_NAME_SERIALIZER);
	}
	
	
	
	// Verify that the required classes implement the appropriate interfaces.
	
	@Test
	public void userStoreImplementIUserStore() throws ClassNotFoundException {
		assertClassImplementsInterface(CLASS_NAME_USER_STORE, IUserStore.class);
	}
		
	@Test
	public void serializerImplementISerializer() throws ClassNotFoundException {
		assertClassImplementsInterface(CLASS_NAME_SERIALIZER, ISerializer.class);
	}
	
	
	
	// Verify that the required classes have the required constructors
	
	@Test
	public void userStoreHasDefaultConstructor() throws ClassNotFoundException {
		assertClassHasConstructor(CLASS_NAME_USER_STORE);
	}
	
	@Test
	public void serializerHasDefaultConstructor() throws ClassNotFoundException {
		assertClassHasConstructor(CLASS_NAME_SERIALIZER);
	}
	
	
}
