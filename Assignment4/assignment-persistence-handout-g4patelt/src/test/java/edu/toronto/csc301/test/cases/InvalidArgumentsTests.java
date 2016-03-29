package edu.toronto.csc301.test.cases;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import org.junit.Test;

import edu.toronto.csc301.IPost;
import edu.toronto.csc301.IUser;
import edu.toronto.csc301.IUserStore;
import edu.toronto.csc301.IPost.PostType;

public class InvalidArgumentsTests extends AbsTest {

	
	/**
	 * Convenience helper.
	 */
	private IUser testUser() throws Exception{
		return userStore.createUser("alice", "123456", "My biography");
	}
	
	
	//-------------------------------------------------------------------------

	
	@Test(expected=NullPointerException.class)
	public void createUserWithNullUsername() throws Exception{
		userStore.createUser(null, "123456", "My biography");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createUserWithEmptyUsername() throws Exception{
		userStore.createUser("", "123456", "My biography");
	}
	
	@Test(expected=IllegalArgumentException.class)
	public void createUserWithWhiteSpaceOnlyUsername() throws Exception{
		userStore.createUser("  \t ", "123456", "My biography");
	}
	
	
	@Test(expected=NullPointerException.class)
	public void createUserWithNullPassword() throws Exception{
		userStore.createUser("alice", null, "My biography");
	}
	
	
	@Test(expected=NullPointerException.class)
	public void getUserWithNullUsername() throws Exception{
		userStore.getUser(null);
	}
	
	
	//-------------------------------------------------------------------------
	
	
	
	@Test(expected=NullPointerException.class)
	public void createPostWithNullType() throws Exception{
		testUser().newPost(
				null, "http://somehwere.com/image.png", toSet("funny", "fails"));
	}
	
	@Test(expected=NullPointerException.class)
	public void createPostWithNullURL() throws Exception{
		testUser().newPost(PostType.IMAGE, null, toSet("funny", "fails"));
	}
	
	@Test(expected=NullPointerException.class)
	public void createPostWithNullTagSet() throws Exception{
		testUser().newPost(PostType.IMAGE, "http://somehwere.com/image.png", null);
	}
	


	//-------------------------------------------------------------------------
	
	
	
	@Test(expected=NullPointerException.class)
	public void serializeNullUser() throws Exception{
		try(OutputStream out = new ByteArrayOutputStream()){
			serializer.serialize((IUser) null, out);
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void serializeNullPost() throws Exception{
		try(OutputStream out = new ByteArrayOutputStream()){
			serializer.serialize((IPost) null, out);
		}
	}
	
	@Test(expected=NullPointerException.class)
	public void serializeNullUserStore() throws Exception{
		try(OutputStream out = new ByteArrayOutputStream()){
			serializer.serialize((IUserStore) null, out);
		}
	}
	
	
	
	@Test(expected=NullPointerException.class)
	public void serializeUserToNullOutputStream() throws Exception{
		serializer.serialize(testUser(), null);
	}
	
	@Test(expected=NullPointerException.class)
	public void serializePostToNullOutputStream() throws Exception{
		IPost p = testUser().newPost(
				PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", toSet("music"));
		serializer.serialize(p, null);
	}
	
	@Test(expected=NullPointerException.class)
	public void serializeUserStoreToNullOutputStream() throws Exception{
		serializer.serialize(userStore, null);
	}
	
	
	
	@Test(expected=NullPointerException.class)
	public void deserializeUserFromNullInputStream() throws Exception{
		serializer.deserializeUser(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void deserializeUsersFromNullInputStream() throws Exception{
		serializer.deserializeUsers(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void deserializePostFromNullInputStream() throws Exception{
		serializer.deserializePost(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void deserializePostsFromNullInputStream() throws Exception{
		serializer.deserializePosts(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void deserializeUserStoreFromNullInputStream() throws Exception{
		serializer.deserializeUserStore(null);
	}
	
	
	
}
