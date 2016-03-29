package edu.toronto.csc301;

import java.io.InputStream;
import java.io.OutputStream;
import java.util.Iterator;

public interface ISerializer {

	public void serialize(IUser user, OutputStream output) throws Exception;
	public void serialize(IPost post, OutputStream output) throws Exception;
	public void serialize(IUserStore userStore, OutputStream output) throws Exception;
	
	public IUser deserializeUser(InputStream input) throws Exception;
	public Iterator<IUser> deserializeUsers(InputStream input) throws Exception;
	
	public IPost deserializePost(InputStream input) throws Exception;
	public Iterator<IPost> deserializePosts(InputStream input) throws Exception;
	
	public IUserStore deserializeUserStore(InputStream input) throws Exception;
}
