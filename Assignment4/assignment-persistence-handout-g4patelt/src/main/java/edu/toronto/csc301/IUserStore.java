package edu.toronto.csc301;

import java.util.Iterator;


/**
 * A simple DAO.
 */
public interface IUserStore {

	public IUser createUser(String username, String password, String bio) throws Exception;
	public IUser getUser(String username);
	public Iterator<IUser> getAllUsers();
}
