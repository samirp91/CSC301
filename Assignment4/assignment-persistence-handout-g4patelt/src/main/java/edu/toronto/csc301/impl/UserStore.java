package edu.toronto.csc301.impl;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import edu.toronto.csc301.IUser;
import edu.toronto.csc301.IUserStore;

public class UserStore implements IUserStore {
	private Set<IUser> users;
	
	public UserStore(){
		users = new HashSet<>();
	}

	@Override
	public IUser createUser(String username, String password, String bio) throws Exception {
		for (IUser u : users){
			if (u.getUsername().equals(username)){
				throw new IllegalArgumentException();
			}
		}
		if (username == null || password == null){
			throw new NullPointerException();
		}
		if (username.equals("") || username.contains(" ")){
			throw new IllegalArgumentException();
		}
		IUser newUser = new User(username, password, bio);
		this.users.add(newUser);
		return newUser;
	}

	@Override
	public IUser getUser(String username) {
		// TODO Auto-generated method stub
		if (username == null){
			throw new NullPointerException();
		}
		for (IUser u : users){
			if (u.getUsername().equals(username)){
				return u;
			}
		}
		return null;
	}

	@Override
	public Iterator<IUser> getAllUsers() {
		// TODO Auto-generated method stub
		return users.iterator();
	}

}
