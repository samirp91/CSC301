package edu.toronto.csc301.impl;

import java.awt.image.RenderedImage;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.toronto.csc301.IPost;
import edu.toronto.csc301.ISerializer;
import edu.toronto.csc301.IUser;
import edu.toronto.csc301.IUserStore;
import edu.toronto.csc301.Util;


public class Serializer implements ISerializer{
	private IUserStore userstore = new UserStore();
	private Set<IUser> usersSer = new HashSet<>();
	private Set<IUser> usersFound = new HashSet<>();
	@Override
	public void serialize(IUser user, OutputStream output) throws Exception {
		// TODO Auto-generated method stub
		ObjectOutputStream out = new ObjectOutputStream(output);
		
		serializeUser(user, out);
		Set<IUser> temp = new HashSet<IUser>();
		temp.addAll(usersFound);
		Iterator<IUser> i = temp.iterator();		
		while (i.hasNext()){
			IUser u = i.next();
			if (!usersSer.contains(u)){
				serializeUser(u, out);
			}
		}
		
		while (!usersSer.containsAll(usersFound)){
			findUsers(out);
		}
				
	}

	public void findUsers(ObjectOutputStream out) throws Exception {
		// TODO Auto-generated method stub
		Set<IUser> copy = new HashSet<>(usersFound);
		Iterator<IUser> it = copy.iterator();
		while (it.hasNext()){
			IUser user = it.next();
			if (!usersSer.contains(user)){
				serializeUser(user, out);
			}
		}
		
	}

	private void serializeUser(IUser user, ObjectOutputStream out) throws Exception {
		// TODO Auto-generated method stub
		String username = user.getUsername();
		String password = user.getPassword();
		try{
			String u = "User";
//			System.out.println(username + "'s posts");
			usersSer.add(user);
			usersFound.add(user);
			userstore.createUser(username, password);
			LocalDateTime registrationTime = user.getRegistrationTime();
			Iterator<IPost> postArray = user.getPosts();
			Iterator<IPost> likedPosts = user.getLikes();
			out.writeObject(u);
			out.writeObject(username);
			out.writeObject(password);
			out.writeObject(registrationTime);
			serializePosts(postArray, out);
			serializeLikes(likedPosts, out);
		}
		catch (Exception e){
//			e.printStackTrace();
		}
				
	}
	
	

	private void serializeLikes(Iterator<IPost> likedPosts, ObjectOutputStream out) throws Exception {
		while (likedPosts.hasNext()){
			String postType = "Liked";
			IPost post = likedPosts.next();
			String caption = post.getCaption();
			RenderedImage image = post.getImage();
			LocalDateTime postedAt = post.getPostedAt();
//			System.out.println("The current user likes the post " + caption);
			IUser user = post.getPostedBy();
			usersFound.add(user);
			byte[] imageBytes = Util.imageToByteArray(image);
			int size = imageBytes.length;
			out.writeObject(postType);
			out.writeObject(caption);
			out.writeInt(size);
			out.write(imageBytes);
			out.writeObject(postedAt);
			String username = user.getUsername();		
			String password = user.getPassword();
			LocalDateTime registrationTime = user.getRegistrationTime();
			out.writeObject(username);
			out.writeObject(password);
			out.writeObject(registrationTime);			
		}
		
	}

	private void serializePosts(Iterator<IPost> postArray, ObjectOutputStream out) throws Exception {
		while (postArray.hasNext()){
			IPost post = postArray.next();
			String caption = post.getCaption();
			RenderedImage image = post.getImage();
			LocalDateTime postedAt = post.getPostedAt();
			Iterator<IUser> likes = post.getLikes();
			byte[] imageBytes = Util.imageToByteArray(image);
			int size = imageBytes.length;
			String postString = "Post";
			out.writeObject(postString);
			out.writeObject(caption);
//			System.out.println(caption);
			out.writeInt(size);
			out.write(imageBytes);
			out.writeObject(postedAt);
			while (likes.hasNext()){
				IUser user2 = likes.next();
				usersFound.add(user2);
				String username = user2.getUsername();
				String password = user2.getPassword();
				IUser u = userstore.getUser(username);
//				System.out.println(username + " likes above post");
				String likesString = "Likes";
				out.writeObject(likesString);
				LocalDateTime registrationTime = user2.getRegistrationTime();
				out.writeObject(username);
				out.writeObject(password);
				out.writeObject(registrationTime);
			}
		}
		
	}

	@Override
	public IUser deserializeUser(InputStream input) throws Exception {
		if (input == null){
			throw new NullPointerException();
		}
		ObjectInputStream in = new ObjectInputStream(input);
		IUser user = null;
		
		user = deserializeUser2(in, user);
		FileOutputStream fos = new FileOutputStream("t.tmp");
		ObjectOutputStream out = new ObjectOutputStream(fos);
		serialize(user, out);
		return user;
	}
	
	private IUser deserializeUser2(ObjectInputStream in, IUser user) throws Exception {
		// TODO Auto-generated method stub
				String postType = (String) in.readObject();
				String username = (String) in.readObject();
				String password = (String) in.readObject();
				LocalDateTime registrationTime = (LocalDateTime) in.readObject();
				user = new User(username, password);
				user.setRegistrationTime(registrationTime);
				userstore.createUser(username, password).setRegistrationTime(registrationTime);
				try{
					postType = (String) in.readObject();				
				}
				catch (Exception e){
					postType = null;
				}
				if (postType != null){
					deserializePost(in, user, postType);	
				}
			
		user = userstore.getUser(username);
	
		return user;
	}
	
	public IUser deserializePost(ObjectInputStream in, IUser user, String postType) throws Exception {
		IPost post = null;
		IUser userMain = null;
		Iterator<IUser> itUMain = userstore.getAllUsers();
		while (itUMain.hasNext()){
			userMain = itUMain.next();
		}
		while (true) {
			if (postType.equals("User")){
				String username = (String) in.readObject();
				String password = (String) in.readObject();
				LocalDateTime registrationTime = (LocalDateTime) in.readObject();
				try {
					userstore.createUser(username, password).setRegistrationTime(registrationTime);
				}
				catch (Exception e){
					
				}
				
				boolean found = false;
				Iterator<IUser> itU = userstore.getAllUsers();
				while (itU.hasNext()){
					userMain = itU.next();
					if (userMain.getUsername().equals(username)){
						found = true;
						break;
					}
				}
				
				if (!found){
					System.out.println("Something went wrong");
					break;
				}
				
				try {
					postType = (String) in.readObject();
				}
				catch (Exception e){
					break;
				}
			}
			
			else if (postType.equals("Post")){
				String caption = (String) in.readObject();
				int size = in.readInt();
				byte[] imageBytes = new byte[size];
				in.read(imageBytes);
				RenderedImage image = Util.byteArrayToImage(imageBytes);
				LocalDateTime postedAt = (LocalDateTime) in.readObject();
				
				Iterator<IUser> i = userstore.getAllUsers();
				IUser poster = null;
				boolean userFound = false;
				while (i.hasNext()){
					poster = i.next();
					if (poster.getUsername().equals(userMain.getUsername())){
						userFound = true;
						break;
					}
				}
				
				if (!userFound){
					System.out.println("User not found");
					break;
				}
				Iterator<IPost> itP = poster.getPosts();
				IPost currentPost = null;
				boolean alreadyPosted = false;
				while (itP.hasNext()){
					currentPost = itP.next();
					if (currentPost.getCaption().equals(caption)){
						alreadyPosted = true;
						break;
					}
				}
				
				post = new Post(image, caption);
				if (!alreadyPosted){
					poster.newPost(image, caption).setPostedAt(postedAt);
				}
				try {
					postType = (String) in.readObject();
				}
				catch (Exception e){
					break;
				}
			}
			
			else if (postType.equals("Likes")){
				String username = (String) in.readObject();
				String password = (String) in.readObject();
				LocalDateTime registrationTime = (LocalDateTime) in.readObject();
				try {
					userstore.createUser(username, password).setRegistrationTime(registrationTime);
				}
				catch (Exception e){
					
				}
				Iterator<IUser> itU = userstore.getAllUsers();
				IUser liker = null;
				boolean found = false;
				while (itU.hasNext()){
					liker = itU.next();
					if (liker.getUsername().equals(username)){
						found = true;
						break;
					}
				}
				
				itU = userstore.getAllUsers();
				IUser poster = null;
				boolean posterFound = false;
				while (itU.hasNext()){
					poster = itU.next();
					if (poster.getUsername().equals(userMain.getUsername())){
						posterFound = true;
						break;
					}
				}
				
				if (!posterFound){
					System.out.println("Poster wasn't found");
					break;
				}
				
				Iterator<IPost> itP = poster.getPosts();
				IPost postToLike = null;
				boolean postFound = false;
				while (itP.hasNext()){
					postToLike = itP.next();
					if (postToLike.getCaption().equals(post.getCaption())){
						postFound = true;
						break;
					}
				}
				
				if (!postFound){
					System.out.println("Post wasn't found");
					break;
				}
				
				if (!found){
					System.out.println("Something went wrong in the likes section");
					break;
				}
				
				liker.like(postToLike);//Maybe need to find the post manually before liking it. Probably not though
				
				try {
					postType = (String) in.readObject();
				}
				catch (Exception e){
					break;
				}
			}
			
			else if (postType.equals("Liked")){
				String caption = (String) in.readObject();
				int size = in.readInt();
				byte[] imageBytes = new byte[size];
				in.read(imageBytes);
				RenderedImage image = Util.byteArrayToImage(imageBytes);
				LocalDateTime postedAt = (LocalDateTime) in.readObject();
				String username = (String) in.readObject();
				String password = (String) in.readObject();
				LocalDateTime registrationTime = (LocalDateTime) in.readObject();
				
				try{
					userstore.createUser(username, password).setRegistrationTime(registrationTime);
				}
				
				catch (Exception e){
					
				}
				
				Iterator<IUser> itU = userstore.getAllUsers();
				IUser poster = null;
				boolean foundUser = false;
				while (itU.hasNext()){
					poster = itU.next();
					if (poster.getUsername().equals(username)){
						foundUser = true;
						break;
					}
				}
				
				if (!foundUser){
					System.out.println("User wasn't found in liked");
					break;
				}
				
				itU = userstore.getAllUsers();
				IUser liker = null;
				boolean foundLiker = false;
				while (itU.hasNext()){
					liker = itU.next();
					if (liker.getUsername().equals(userMain.getUsername())){
						foundLiker = true;
						break;
					}
				}
				
				if (!foundLiker){
					System.out.println("Didn't find the liker");
					break;
				}
				
				Iterator<IPost> itP = poster.getPosts();
				IPost postToLike = null;
				boolean postFound = false;
				while (itP.hasNext()){
					postToLike = itP.next();
					if (postToLike.getCaption().equals(caption)){
						postFound = true;
						break;
					}
				}
				
				if (postFound){
					liker.like(postToLike);
				}
				else{
					postToLike = poster.newPost(image, caption);
					postToLike.setPostedAt(postedAt);
					liker.like(postToLike);
				}
				
				try {
					postType = (String) in.readObject();
				}
				catch (Exception e){
					break;
				}
			}
			
			else{
				System.out.println("Shouldn't come here");
				break;
			}
		}
		return user;
	}
	

}