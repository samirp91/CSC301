package edu.toronto.csc301.test.cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.junit.Test;

import edu.toronto.csc301.IUser;

/**
 * Test the serialization/deserialization of the IUser implementation.
 * Test the properties only (i.e. not testing relations)
 */
public class UserTests extends AbsTest {
	
	// Sanity tests ...
	
	@Test
	public void createUserDoesNotThrowAnError() throws Exception{
      userStore.createUser("alice", "123456", "My biography");
	}
	
	@Test
	public void createMultipleUsersDoesNotThrowAnError() throws Exception{
		for (int i = 0; i < 50; i++) {
			userStore.createUser("test-user-" + i, "123456", null);
		}
	}
	
	@Test
	public void createUserDoesNotReturnNull() throws Exception{
		assertNotNull(userStore.createUser("alice", "123456", "My biography"));
	}
	
	
	// Check that properties are set correctly on created users ...
	
	@Test
	public void createUserSetsUsernameProperly() throws Exception{
		String username = UUID.randomUUID().toString();
		IUser u = userStore.createUser(username, "123456", "My biography");
		assertEquals(username, u.getUsername());
	}
	
	@Test
	public void createUserSetsPasswordProperly() throws Exception{
		String password = UUID.randomUUID().toString();
		IUser u = userStore.createUser("alice", password, "My biography");
		assertEquals(password, u.getPassword());
	}
	
	@Test
	public void createUserSetsBioProperly() throws Exception{
		String bio = UUID.randomUUID().toString();
		IUser u = userStore.createUser("alice", "123456", bio);
		assertEquals(bio, u.getBio());
	}
	
	@Test
	public void createUserSetsBioProperly2() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		assertNull(u.getBio());
	}
	
	@Test
	public void createUserSetsRegistrationTimeProperly() throws Exception{
		IUser u = userStore.createUser("alice", "123456", "My biography");
		assertRoughlyEquals(LocalDateTime.now(), u.getRegistrationTime());
	}
	
	
	// Check that the user-store enforces unique user names
	
	@Test(expected=IllegalArgumentException.class)
	public void usernameMustBeUnique() throws Exception{
		String username = UUID.randomUUID().toString();
		userStore.createUser(username, "123456", "My biography");
		userStore.createUser(username, "654321", "Someone else's biography");
	}
	
	
	
	// Test getUser
	
	@Test
	public void getNonExistingUserReturnsNull() throws Exception{
		assertNull(userStore.getUser("foo"));
	}
	
	@Test
	public void getExistingUserReturnsTheCorrectUser() throws Exception{
		String username = UUID.randomUUID().toString();
		userStore.createUser(username, "123456", "My biography");
		IUser u2 = userStore.getUser(username);
		assertEquals(username, u2.getUsername());
	}

	
	
	// Test getAllUsers
	
	@Test
	public void getAllUsersIsNotNull() throws Exception{
		assertNotNull(userStore.getAllUsers());
	}
	
	@Test
	public void getAllUsersFromAnEmptyStore() throws Exception{
		assertFalse(userStore.getAllUsers().hasNext());
	}
	
	@Test
	public void getAllUsersFromStoreWithOneUser() throws Exception{
		userStore.createUser("alice", "123456", "My biography");
		Iterator<IUser> allUsers = userStore.getAllUsers();
		
		assertTrue(allUsers.hasNext());
		assertEquals("alice", allUsers.next().getUsername());
		assertFalse(allUsers.hasNext());
	}
	
	@Test
	public void getAllUsersFromStoreWithMultipleUser() throws Exception{
		// Create a few users
		Set<String> usernames = toSet("alice", "bob", "charlie");
		for(String name : usernames){
			userStore.createUser(name, "123456", null);
		}
		
		// Get an iterator of all users
		Iterator<IUser> users = userStore.getAllUsers();
		int count = 0;
		
		// Check that the iterator visits exactly the users we expect
		while (users.hasNext()) {
			assertTrue(usernames.contains(users.next().getUsername()));
			count++;
		}
		assertEquals(usernames.size(), count);
	}
	
	
	
	
	//-------------------------------------------------------------------------
	// Test serialization ...
	
	
	@Test
	public void serializationDoesNotThrowAnError() throws Exception{
		try(ByteArrayOutputStream output = new ByteArrayOutputStream()){
			serializer.serialize(createTestUser(), output);
		}	
	}
	
	@Test
	public void serializationDeserializationDoesNotThrowAnError() throws Exception{
		serializeDeserialize(createTestUser());
	}
	
	@Test
	public void serializeDeserializeDoesNotReturnNull() throws Exception{
		assertNotNull(serializeDeserialize(createTestUser()));
		
	}
	
	@Test
	public void serializeDeserializeAndCheckUsername() throws Exception{
		IUser u = createTestUser();
		assertEquals(u.getUsername(), serializeDeserialize(u).getUsername());
	}
	
	@Test
	public void serializeDeserializeAndCheckPassword() throws Exception{
		IUser u = createTestUser();
		assertEquals(u.getPassword(), serializeDeserialize(u).getPassword());
	}
	
	@Test
	public void serializeDeserializeAndCheckBio() throws Exception{
		IUser u = createTestUser();
		assertEquals(u.getBio(), serializeDeserialize(u).getBio());
	}
	
	@Test
	public void serializeDeserializeAndCheckBio2() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		assertNull(serializeDeserialize(u).getBio());
	}
	
	@Test
	public void serializeDeserializeAndCheckBio3() throws Exception{
		String bio = "My \n bio";  // Containing a new line
		IUser u = userStore.createUser("alice", "123456", bio);
		assertEquals(bio, serializeDeserialize(u).getBio());
	}
	
	@Test
	public void serializeDeserializeAndCheckBio4() throws Exception{
		String bio = "> <My> bio>";  // Containing characters that might break naive XML parsing
		IUser u = userStore.createUser("alice", "123456", bio);
		assertEquals(bio, serializeDeserialize(u).getBio());
	}
	
	@Test
	public void serializeDeserializeAndCheckBio5() throws Exception{
		String bio = "} My ][ bio";  // Containing characters that might break naive JSON parsing
		IUser u = userStore.createUser("alice", "123456", bio);
		assertEquals(bio, serializeDeserialize(u).getBio());
	}
	
	@Test
	public void serializeDeserializeAndCheckBio6() throws Exception{
		// And an even more extreme edge case ...
		// A bio containing every ascii character (multiple times)
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 256; j++) {
				sb.append((char) j);
			}
		}
		String bio = sb.toString();
		IUser u = userStore.createUser("alice", "123456", bio);
		assertEquals(bio, serializeDeserialize(u).getBio());
	}
	
	
	
	@Test
	public void serializeDeserializeAndCheckRegistrationTime() throws Exception{
		IUser u = createTestUser();
		assertRoughlyEquals(LocalDateTime.now(), serializeDeserialize(u).getRegistrationTime());
	}
	
	
	
	
	private void testSerializeDeserializeUsers(IUser ... users) throws Exception{
		try(ByteArrayOutputStream output = new ByteArrayOutputStream()){
			for (int i = 0; i < users.length; i++) {
				serializer.serialize(users[i], output);
			}
			
			output.close();
			try(ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray())){
				Iterator<IUser> itr = serializer.deserializeUsers(input);
				for (int i = 0; i < users.length; i++) {
					assertTrue(itr.hasNext());
					assertEquals(users[i].getUsername(), itr.next().getUsername());
				}
				assertFalse(itr.hasNext());
			}
		}
	}
	
	
	@Test
	public void testDeserializeUsersWithZeroUsers() throws Exception{
		testSerializeDeserializeUsers();
	}
	
	@Test
	public void testDeserializeUsersWithOneUser() throws Exception{
		testSerializeDeserializeUsers(createTestUser());
	}
	
	@Test
	public void testDeserializeUsersWithMultipleUsers() throws Exception{
		testSerializeDeserializeUsers(createTestUser(), createTestUser(), createTestUser());
	}
	
	
	
}
