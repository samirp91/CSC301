package edu.toronto.csc301.test.cases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.UUID;
import java.util.function.Consumer;

import org.junit.Test;

import edu.toronto.csc301.IPost;
import edu.toronto.csc301.IPost.PostType;
import edu.toronto.csc301.IUser;


/**
 * Test the serialization/deserialization of the IPost implementation.
 * Test the properties only (i.e. not testing relations)
 */
public class PostTests extends AbsTest {
	
	//-------------------------------------------------------------------------
	// Helper methods
	
	private Random random = new Random();
	
	private IPost createTestPost() throws Exception{
		String randomUsername = UUID.randomUUID().toString();
		String randomPassword = UUID.randomUUID().toString();
		IUser u = userStore.createUser(randomUsername, randomPassword, null);
		
		PostType type = random.nextBoolean() ? PostType.IMAGE : PostType.VIDEO; 
		String randomURL = "https://youtu.be/" + random.nextInt();
		Set<String> tags = toSet("art", "funky");
		
		return u.newPost(type, randomURL, tags);
	}
	
	
	//-------------------------------------------------------------------------
	// Sanity tests
	
	
	@Test
	public void createPostWithoutError() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		u.newPost(PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", toSet("music"));
	}
	
	@Test
	public void createPostWithoutError2() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		u.newPost(PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", new HashSet<String>());
	}
	
	@Test
	public void createPostAndCheckType() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		IPost p = u.newPost(PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", toSet("music"));
		assertEquals(PostType.VIDEO, p.getType());
	}
	
	@Test
	public void createPostAndCheckURL() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		IPost p = u.newPost(PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", toSet("music"));
		assertEquals("https://youtu.be/xJZiSkYuhw4", p.getURL());
	}
	
	@Test
	public void createPostAndCheckTags() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		IPost p = u.newPost(PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", toSet("music"));
		assertEquals(toSet("music"), p.getTags());
	}
	
	@Test
	public void createPostAndCheckPostTime() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		IPost p = u.newPost(PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", toSet("music"));
		assertRoughlyEquals(LocalDateTime.now(), p.getPostedAt());
	}
	

	
	// Test the way tags are handled

	@Test(expected=NullPointerException.class)
	public void addNullTag() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		IPost p = u.newPost(PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", toSet("music"));
		p.addTag(null);
	}
	
	@Test(expected=NullPointerException.class)
	public void removeNullTag() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		IPost p = u.newPost(PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", toSet("music"));
		p.removeTag(null);
	}
	
	
	@Test
	public void addNewTag() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		IPost p = u.newPost(PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", toSet("music"));
		p.addTag("rock");
		assertEquals(toSet("music", "rock"), p.getTags());
	}
	
	@Test
	public void addTagThatAlreadyExists() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		IPost p = u.newPost(PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", toSet("music"));
		p.addTag("music");
		assertEquals(toSet("music"), p.getTags());
	}
	
	@Test
	public void removeExistingTag() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		IPost p = u.newPost(PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", toSet("music"));
		p.removeTag("music");
		assertTrue(p.getTags().isEmpty());
	}
	
	@Test
	public void removeNonExistingTag() throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		IPost p = u.newPost(PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", toSet("music"));
		p.removeTag("foo");
		assertEquals(toSet("music"), p.getTags());
	}
	
	
	
	// Make sure that the only way to add/remove tag(s) to/from post(s) is
	// via the addTag and removeTag methods ...
	
	
	private void verifyThatInternalDataStructureIsNotExposed(Consumer<Set<String>> modifyTagSet) throws Exception{
		IUser u = userStore.createUser("alice", "123456", null);
		IPost p = u.newPost(PostType.VIDEO, "https://youtu.be/xJZiSkYuhw4", toSet("music"));
		
		Set<String> tags = p.getTags();
		
		// Try to modify the returned set ...
		try{
			modifyTagSet.accept(tags);
		} catch (Exception e){
			// If getTags returns an immutable set, that's fine. The test should pass.
			return;
		}
		// If the set is mutable, that's fine too.
		// Just make sure that getTags() returns the original set of tags
		assertEquals(toSet("music"), p.getTags());
	}
	
	
	
	@Test
	public void verifyThatInternalDataStructureIsNotExposed() throws Exception{
		verifyThatInternalDataStructureIsNotExposed((tags) -> tags.clear());
	}

	@Test
	public void verifyThatInternalDataStructureIsNotExposed2() throws Exception{
		verifyThatInternalDataStructureIsNotExposed((tags) -> tags.add("foo"));
	}
	
	
	
	
	// Test serialization/deserialization
	
	
	
	@Test
	public void serializationDoesNotThrowAnError() throws Exception{
		IPost p = createTestPost();
		
		ByteArrayOutputStream output = new ByteArrayOutputStream();
		serializer.serialize(p, output);
		output.close();	
	}
	
	@Test
	public void serializationDeserializationDoesNotThrowAnError() throws Exception{
		IPost p = createTestPost();
		serializeDeserialize(p);
	}
	
	@Test
	public void serializeDeserializeDoesNotReturnNull() throws Exception{
		IPost p = createTestPost();
		assertNotNull(serializeDeserialize(p));
		
	}
	
	
	
	@Test
	public void serializeDeserializeAndCheckType() throws Exception{
		IPost p = createTestPost();
		assertEquals(p.getType(), serializeDeserialize(p).getType());
	}
	
	@Test
	public void serializeDeserializeAndCheckURL() throws Exception{
		IPost p = createTestPost();
		assertEquals(p.getURL(), serializeDeserialize(p).getURL());
	}
	
	@Test
	public void serializeDeserializeAndCheckTags() throws Exception{
		IPost p = createTestPost();
		assertEquals(p.getTags(), serializeDeserialize(p).getTags());
	}
	
	@Test
	public void serializeDeserializeAndCheckPostTime() throws Exception{
		IPost p = createTestPost();
		assertEquals(p.getPostedAt(), serializeDeserialize(p).getPostedAt());
	}
	
	
	
	private void testSerializeDeserializePosts(IPost ... posts) throws Exception{
		try(ByteArrayOutputStream output = new ByteArrayOutputStream()){
			for (int i = 0; i < posts.length; i++) {
				serializer.serialize(posts[i], output);
			}
			
			output.close();
			try(ByteArrayInputStream input = new ByteArrayInputStream(output.toByteArray())){
				Iterator<IPost> itr = serializer.deserializePosts(input);
				for (int i = 0; i < posts.length; i++) {
					assertTrue(itr.hasNext());
					assertEquals(posts[i].getURL(), itr.next().getURL());
				}
				assertFalse(itr.hasNext());
			}
		}
	}
	
	@Test
	public void testDeserializePostsWithZeroPosts() throws Exception{
		testSerializeDeserializePosts();
	}
	
	@Test
	public void testDeserializePostsWithOnePost() throws Exception{
		testSerializeDeserializePosts(createTestPost());
	}
	
	@Test
	public void testDeserializePostsWithMultiplePosts() throws Exception{
		testSerializeDeserializePosts(createTestPost(), createTestPost(), createTestPost());
	}
	
	
}
