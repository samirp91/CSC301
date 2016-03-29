package edu.toronto.csc301.impl;

import java.awt.image.RenderedImage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import edu.toronto.csc301.IPost;
import edu.toronto.csc301.IUser;

public class User implements IUser{
	private String name;
	private String password;
	private LocalDateTime registrationTime;
	private ArrayList<IPost> postArray;
	private ArrayList<IPost> likedPosts;
	
	public User(String name, String password){
		if (name == null || password == null ){
			throw new NullPointerException();
		}
		else if (name.trim().equals("") || password.trim().equals("")){
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.password = password;
		this.registrationTime = LocalDateTime.now();
		this.postArray = new ArrayList<>();
		this.likedPosts = new ArrayList<>();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.password;
	}

	@Override
	public void setPassword(String password) {
		// TODO Auto-generated method stub
		this.password = password;
	}

	@Override
	public LocalDateTime getRegistrationTime() {
		// TODO Auto-generated method stub
		return this.registrationTime;
	}

	@Override
	public void setRegistrationTime(LocalDateTime registrationTime) {
		// TODO Auto-generated method stub
		this.registrationTime = registrationTime;
	}

	@Override
	public IPost newPost(RenderedImage image, String caption) {
		IPost post = new Post(image, caption);
		post.setPostedBy(this);
		postArray.add(post);
		return post;
	}

	@Override
	public Iterator<IPost> getPosts() {
		// TODO Auto-generated method stub
		return this.postArray.iterator();
	}

	@Override
	public void like(IPost post) {
		// TODO Auto-generated method stub
		Iterator<IPost> likedPosts = this.getLikes();
		while(likedPosts.hasNext()){
			if (post == likedPosts.next()){
				return;
			}
		}
		this.likedPosts.add(post);
		post.addLike(this);
	}

	@Override
	public void unlike(IPost post) {
		// TODO Auto-generated method stub
		Iterator<IPost> likedPosts = this.getLikes();
		boolean postRemoved = true;
		while(likedPosts.hasNext()){
			if (post == likedPosts.next()){
				postRemoved = false;
			}
		}
		if (postRemoved){
			return;
		}
		this.likedPosts.remove(post);
		post.removeLike(this);
	}

	@Override
	public Iterator<IPost> getLikes() {
		// TODO Auto-generated method stub
		return this.likedPosts.iterator();
	}

}
