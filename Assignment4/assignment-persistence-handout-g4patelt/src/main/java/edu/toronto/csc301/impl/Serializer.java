package edu.toronto.csc301.impl;

import java.io.EOFException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Function;

import edu.toronto.csc301.IPost;
import edu.toronto.csc301.IPost.PostType;
import edu.toronto.csc301.ISerializer;
import edu.toronto.csc301.IUser;
import edu.toronto.csc301.IUser.AdFilter;
import edu.toronto.csc301.IUserStore;

public class Serializer implements ISerializer {
	private AdFilter a = (x, y) -> x && y;
	private AdFilter b = (x, y) -> x || y;
	private AdFilter c = (x, y) -> x ^ y;
	private AdFilter d = (x, y) -> x;
	private AdFilter e = (x, y) -> y;
	private AdFilter f = (x, y) -> true;
	private AdFilter g = (x, y) -> false;
	private AdFilter h = (x, y) -> x && (!y);
	private AdFilter i = (x, y) -> x || (!y);
	private AdFilter j = (x, y) -> (!x) && y;
	private AdFilter k = (x, y) -> (!x) || y;
	private AdFilter l = (x, y) -> (!x) && (!y);
	private AdFilter m = (x, y) -> (!x) || (!y);
	private AdFilter n = (x, y) -> (!x) ^ y;
	private AdFilter o = (x, y) -> x ^ (!y);
	private AdFilter p = (x, y) -> (!x) ^ (!y);
	private AdFilter q = (x, y) -> !x;
	private AdFilter r = (x, y) -> !y;
	
	public Serializer(){
		
	}

	@Override
	public void serialize(IUser user, OutputStream output) throws Exception {
		// TODO Auto-generated method stub
		if (user == null){
			throw new NullPointerException();
		}
		ObjectOutputStream out = new ObjectOutputStream(output);
		String username = user.getUsername();
		String password = user.getPassword();
		String bio = user.getBio();
		LocalDateTime registrationTime = user.getRegistrationTime();
		AdFilter adFilter = user.getAdFilter();
		String ad = null;
		if (adFilter == null){
			
		}
		else{
			ad = getAd(adFilter);
		}
		out.writeObject(username);
		out.writeObject(password);
		out.writeObject(bio);
		out.writeObject(registrationTime);
		out.writeObject(ad);
		out.close();		
	}

	@Override
	public void serialize(IPost post, OutputStream output) throws Exception {
		// TODO Auto-generated method stub
		if (post == null){
			throw new NullPointerException();
		}
		PostType type = post.getType();
		String url = post.getURL();
		Set<String> tags = post.getTags();
		LocalDateTime time = post.getPostedAt();
		
		ObjectOutputStream out = new ObjectOutputStream(output);
		out.writeObject(type);
		out.writeObject(url);
		out.writeObject(tags);
		out.writeObject(time);
		out.close();
		
	}
	
	public boolean checkAdFilter(AdFilter a, AdFilter b){
		if (a.displayAd(true, true) == b.displayAd(true, true))
			if (a.displayAd(true, false) == b.displayAd(true, false))
				if (a.displayAd(false, false) == b.displayAd(false, false))
					if (a.displayAd(false, true) == b.displayAd(false, true))
						return true;
		return false;
	}

	@Override
	public void serialize(IUserStore userStore, OutputStream output) throws Exception {
		// TODO Auto-generated method stub
		if (userStore == null){
			throw new NullPointerException();
		}
		ObjectOutputStream out = new ObjectOutputStream(output);
		Iterator<IUser> it = userStore.getAllUsers();
		while (it.hasNext()){
			IUser user = it.next();
			String username = user.getUsername();
			String password = user.getPassword();
			String bio = user.getBio();
			LocalDateTime registrationTime = user.getRegistrationTime();
			AdFilter adFilter = user.getAdFilter();
			out.writeObject(username);
			out.writeObject(password);
			out.writeObject(bio);
			out.writeObject(registrationTime);
			out.writeObject(adFilter);
		}
		out.close();
		
	}

	@Override
	public IUser deserializeUser(InputStream input) throws Exception {
		if (input == null){
			throw new NullPointerException();
		}
		AdFilter ad = null;
		ObjectInputStream in = new ObjectInputStream(input);
		String username = (String) in.readObject();
		String password = (String) in.readObject();
		String bio = (String) in.readObject();
		LocalDateTime registrationTime = (LocalDateTime) in.readObject();
		String adString = (String) in.readObject();
		if (adString == null){
			
		}
		else{
			ad = getAdFilter(adString);
		}
		IUser user = new User(username, password, bio);
		user.setAdFilter(ad);
		user.setRegistrationTime(registrationTime);
		in.close();
		return user;
	}

	@Override
	public Iterator<IUser> deserializeUsers(InputStream input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null){
			throw new NullPointerException();
		}
		ArrayList<IUser> usersArray = new ArrayList<>();
		try{
			ObjectInputStream in = new ObjectInputStream(input);
			while (true){
				AdFilter ad = null;
				String username = (String) in.readObject();
				String password = (String) in.readObject();
				String bio = (String) in.readObject();
				LocalDateTime registrationTime = (LocalDateTime) in.readObject();
				String adString = (String) in.readObject();
				if (adString == null){
					
				}
				else{
					ad = getAdFilter(adString);
				}
				IUser user = new User(username, password, bio);
				user.setAdFilter(ad);
				user.setRegistrationTime(registrationTime);
				usersArray.add(user);
				in = new ObjectInputStream(input);
			}
		}
		catch (EOFException e){
			
		}
		return usersArray.iterator();
	}

	@Override
	public IPost deserializePost(InputStream input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null){
			throw new NullPointerException();
		}
		ObjectInputStream in = new ObjectInputStream(input);
		PostType type = (PostType) in.readObject();
		String url = (String) in.readObject();
		Set<String> tags = (Set<String>) in.readObject();
		LocalDateTime time = (LocalDateTime) in.readObject();
		in.close();
		IPost post = new Post(type, url, tags);
		post.setPostedAt(time);
		return post;
	}

	@Override
	public Iterator<IPost> deserializePosts(InputStream input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null){
			throw new NullPointerException();
		}
		ArrayList<IPost> postsArray = new ArrayList<>();
		try{
			ObjectInputStream in = new ObjectInputStream(input);
			while (true){
				PostType type = (PostType) in.readObject();
				String url = (String) in.readObject();
				Set<String> tags = (Set<String>) in.readObject();
				LocalDateTime time = (LocalDateTime) in.readObject();
				in.close();
				IPost post = new Post(type, url, tags);
				post.setPostedAt(time);
				postsArray.add(post);
				in = new ObjectInputStream(input);
			}
		}
		catch (EOFException e){
			
		}
		return postsArray.iterator();
	}

	@Override
	public IUserStore deserializeUserStore(InputStream input) throws Exception {
		// TODO Auto-generated method stub
		if (input == null){
			throw new NullPointerException();
		}
		IUserStore userStore = new UserStore();
		ObjectInputStream in = new ObjectInputStream(input);
		try{
		while (true){
			String username = (String) in.readObject();
			String password = (String) in.readObject();
			String bio = (String) in.readObject();
			LocalDateTime registrationTime = (LocalDateTime) in.readObject();
			String adFilter = (String) in.readObject();
			userStore.createUser(username, password, bio);
		}
		}
		catch (EOFException e){
			
		}
		return userStore;
	}
	
	private String getAd(AdFilter adFilter) {
		if (checkAdFilter(adFilter, a))
			return "a";
		if (checkAdFilter(adFilter, b))
			return  "b";
		if (checkAdFilter(adFilter, c))
			return "c";
		if (checkAdFilter(adFilter, d))
			return"d";
		if (checkAdFilter(adFilter, e))
			return"e";
		if (checkAdFilter(adFilter, f))
			return "f";
		if (checkAdFilter(adFilter, g))
			return "g";
		if (checkAdFilter(adFilter, h))
			return "h";
		if (checkAdFilter(adFilter, i))
			return "i";
		if (checkAdFilter(adFilter, j))
			return "j";
		if (checkAdFilter(adFilter, k))
			return "k";
		if (checkAdFilter(adFilter, l))
			return "l";
		if (checkAdFilter(adFilter, m))
			return "m";
		if (checkAdFilter(adFilter, n))
			return "n";
		if (checkAdFilter(adFilter, o))
			return "o";
		if (checkAdFilter(adFilter, p))
			return "p";
		if (checkAdFilter(adFilter, q))
			return "q";
		if (checkAdFilter(adFilter, r))
			return "r";
		return null;
	}
	
	private AdFilter getAdFilter(String adString) {
		if (adString.equals("a"))
			return a;
		if (adString.equals("b"))
			return b;
		if (adString.equals("c"))
			return c;
		if (adString.equals("d"))
			return d;
		if (adString.equals("e"))
			return e;
		if (adString.equals("f"))
			return f;
		if (adString.equals("g"))
			return g;
		if (adString.equals("h"))
			return h;
		if (adString.equals("i"))
			return i;
		if (adString.equals("j"))
			return j;
		if (adString.equals("k"))
			return k;
		if (adString.equals("l"))
			return l;
		if (adString.equals("m"))
			return m;
		if (adString.equals("n"))
			return n;
		if (adString.equals("o"))
			return o;
		if (adString.equals("p"))
			return m;
		if (adString.equals("q"))
			return q;
		if (adString.equals("r"))
			return r;
		return null;
	}

}
