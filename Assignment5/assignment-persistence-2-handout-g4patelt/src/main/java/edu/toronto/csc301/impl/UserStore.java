package edu.toronto.csc301.impl;

import java.util.ArrayList;
import java.util.Iterator;

import edu.toronto.csc301.IUser;
import edu.toronto.csc301.IUserStore;

public class UserStore implements IUserStore {
	
	private ArrayList<IUser> users;
	public UserStore(){
		this.users = new ArrayList<>();
	}

	@Override
	public IUser createUser(String username, String password) throws Exception {
		// TODO Auto-generated method stub
		Iterator<IUser> users = getAllUsers();
		while (users.hasNext()){
			IUser user = users.next();
			if (user.getUsername().equals(username)){
				throw new IllegalArgumentException();
			}
		}
		IUser user = new User(username, password);
		this.users.add(user);
		return user;
	}

	@Override
	public IUser getUser(String username) {
		if (username == null){
			throw new NullPointerException();
		}
		Iterator<IUser> users = getAllUsers();
		while (users.hasNext()){
			IUser user = users.next();
			if (user.getUsername().equals(username)){
				return user;
			}
		}
		return null;
	}

	@Override
	public Iterator<IUser> getAllUsers() {
		// TODO Auto-generated method stub
		return this.users.iterator();
	}

}
