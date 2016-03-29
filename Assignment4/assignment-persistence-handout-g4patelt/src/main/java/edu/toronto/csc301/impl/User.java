package edu.toronto.csc301.impl;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import edu.toronto.csc301.IPost;
import edu.toronto.csc301.IPost.PostType;
import edu.toronto.csc301.IUser;

public class User implements IUser{
	
	private String username;
	private String password;
	private String bio;
	private LocalDateTime registrationTime;
	private AdFilter filter;
	
	public User(String username, String password, String bio){
		if (username == null || password == null){
			throw new NullPointerException();
		}
		if (username.equals("") || username.contains(" ")){
			throw new IllegalArgumentException();
		}
		this.username = username;
		this.password = password;
		this.bio = bio;
		this.registrationTime = LocalDateTime.now();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.username;
	}

	@Override
	public void setUsername(String username) {
		this.username = username;
		
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
	public String getBio() {
		// TODO Auto-generated method stub
		return this.bio;
	}

	@Override
	public void setBio(String bio) {
		// TODO Auto-generated method stub
		this.bio = bio;
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
	public IPost newPost(PostType type, String url, Set<String> tags){
		IPost p = new Post(type, url, tags);
		return p;
	}

	@Override
	public AdFilter getAdFilter() {
		// TODO Auto-generated method stub
		return this.filter;
	}

	@Override
	public void setAdFilter(AdFilter filter) {
		// TODO Auto-generated method stub
		this.filter = filter;
	}

}
