package edu.toronto.csc301.test.cases;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Iterator;

import org.junit.Test;

import edu.toronto.csc301.IUser;
import edu.toronto.csc301.IUserStore;



/**
 * Test serialization of the IUserStore implementation.
 * (Without testing object relations)
 */
public class UserStoreTests extends AbsTest {


	@Test
	public void serializeDeserializeEmptyStore() throws Exception{
		IUserStore store2 = serializeDeserialize(this.userStore);
		Iterator<IUser> allUsers = store2.getAllUsers();
		assertNotNull(allUsers);
		assertFalse(allUsers.hasNext());
	}
	
	
	@Test
	public void createUserThenSerializeDeserializeStore() throws Exception{
		this.userStore.createUser("alice", "123456", null);
		assertIteratorVisitsExactly(serializeDeserialize(this.userStore).getAllUsers(), 
									"alice");
	}
	
	
	@Test
	public void createMultipleUsersThenSerializeDeserializeStore() throws Exception{
		this.userStore.createUser("alice", "123456", null);
		this.userStore.createUser("bob", "123456", null);
		this.userStore.createUser("charlie", "123456", null);
		
		assertIteratorVisitsExactly(serializeDeserialize(this.userStore).getAllUsers(), 
									"alice", "bob", "charlie");
	}
	
	
	@Test
	public void multipleSerializeDeserialize() throws Exception{
		this.userStore.createUser("alice", "123456", null);
		this.userStore.createUser("bob", "123456", null);
		this.userStore.createUser("charlie", "123456", null);
		
		IUserStore store2 = serializeDeserialize(this.userStore);
		store2.createUser("david", "123456", null);
		store2.createUser("eva", "123456", null);
		
		assertIteratorVisitsExactly(this.userStore.getAllUsers(), 
									"alice", "bob", "charlie");
		assertIteratorVisitsExactly(store2.getAllUsers(), 
									"alice", "bob", "charlie", "david", "eva");
		
		IUserStore store3 = serializeDeserialize(store2);
		store3.createUser("frank", "123456", null);
		
		assertIteratorVisitsExactly(serializeDeserialize(store3).getAllUsers(), 
				"alice", "bob", "charlie", "david", "eva", "frank");
	}

	
	
}
