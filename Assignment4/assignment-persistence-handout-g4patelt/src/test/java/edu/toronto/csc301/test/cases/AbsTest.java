package edu.toronto.csc301.test.cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import org.junit.Before;

import edu.toronto.csc301.IPost;
import edu.toronto.csc301.ISerializer;
import edu.toronto.csc301.IUser;
import edu.toronto.csc301.IUserStore;
import edu.toronto.csc301.util.CSC301TestBase;

public class AbsTest extends CSC301TestBase{

	
	protected IUserStore userStore;
	protected ISerializer serializer;
	
	protected IUserStore createUserStore() throws Exception{
		return newInstance(ImplementationStructureTests.CLASS_NAME_USER_STORE);
	}
	
	protected ISerializer createSerializer() throws Exception{
		return newInstance(ImplementationStructureTests.CLASS_NAME_SERIALIZER);
	}
	
	
	protected IUser createTestUser() throws Exception{
		// "Random" strings ...
		String username = UUID.randomUUID().toString();
		String password = UUID.randomUUID().toString();
		String bio      = UUID.randomUUID().toString();
		return userStore.createUser(username, password, bio);
	}
	

	@Before
	public void beforeEachTest() throws Exception{
		userStore = createUserStore();
		serializer = createSerializer();
	}
	
	
	protected void assertRoughlyEquals(LocalDateTime t1, LocalDateTime t2){
		assertTrue(Duration.between(t1, t2).getSeconds() < 1);
	}
	
	
	protected Set<String> toSet(String ... strings){
		Set<String> s = new HashSet<String>();
		Collections.addAll(s, strings);
		return s;
	}
	
	
	protected void assertIteratorVisitsExactly(Iterator<IUser> iterator, String ... usernames){
		Set<String> usernamesFromIterator = new HashSet<String>();
		while (iterator.hasNext()) {
			usernamesFromIterator.add(iterator.next().getUsername());
		}
		assertEquals(toSet(usernames), usernamesFromIterator);
	}
	
	protected void assertIteratorVisitsExactly(Iterator<IUser> iterator, IUser ... users){
		String[] usernames = new String[users.length];
		for (int i = 0; i < users.length; i++) {
			usernames[i] = users[i].getUsername();
		}
		assertIteratorVisitsExactly(iterator, usernames);
	}
	
	
	//-------------------------------------------------------------------------
	// Private helper functionality
	
	@FunctionalInterface
	private interface SerializeHelper<T> {
		void serializeObject(T obj, OutputStream out) throws Exception;
	}
	
	@FunctionalInterface
	private interface DeserializeHelper<T> {
		T deserializeObject(InputStream input) throws Exception;
	}
	
	
	private <T> T serializeDeserialize(T obj, SerializeHelper<T> serializeFunc, DeserializeHelper<T> deserializeFunc) throws Exception{
		try(ByteArrayOutputStream output = new ByteArrayOutputStream()){
			serializeFunc.serializeObject(obj, output);
			output.close();
			try(ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray())){
				return deserializeFunc.deserializeObject(input);
			}
		}
	}
	
	//-------------------------------------------------------------------------
	
	
	protected IUser serializeDeserialize(IUser user) throws Exception{
		return serializeDeserialize(user, 
				(u, output) -> serializer.serialize(u, output), 
				(input) -> serializer.deserializeUser(input));
	}
	
	protected IPost serializeDeserialize(IPost post) throws Exception{
		return serializeDeserialize(post, 
				(p, output) -> serializer.serialize(p, output), 
				(input) -> serializer.deserializePost(input));
	}
	
	protected IUserStore serializeDeserialize(IUserStore store) throws Exception{
		return serializeDeserialize(store, 
				(s, output) -> serializer.serialize(s, output), 
				(input) -> serializer.deserializeUserStore(input));
	}
}
