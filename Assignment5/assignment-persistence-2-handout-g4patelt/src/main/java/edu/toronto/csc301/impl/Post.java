package edu.toronto.csc301.impl;

import java.awt.image.RenderedImage;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Iterator;

import edu.toronto.csc301.IPost;
import edu.toronto.csc301.IUser;

public class Post implements IPost{
	private RenderedImage image;
	private String caption;
	private ArrayList<IUser> likes;
	LocalDateTime postedAt;
	private IUser poster;
	
	public Post(RenderedImage image, String caption){
		if (image == null && caption == null){
			throw new IllegalArgumentException();
		}
		this.image = image;
		this.caption = caption;
		this.postedAt = LocalDateTime.now();
		this.likes = new ArrayList<>();
	}

	@Override
	public RenderedImage getImage() {
		// TODO Auto-generated method stub
		return this.image;
	}

	@Override
	public void setImage(RenderedImage profilePic) {
		// TODO Auto-generated method stub
		this.image = profilePic;
	}

	@Override
	public String getCaption() {
		// TODO Auto-generated method stub
		return this.caption;
	}

	@Override
	public void setCaption(String caption) {
		// TODO Auto-generated method stub
		this.caption = caption;
	}

	@Override
	public IUser getPostedBy() {
		// TODO Auto-generated method stub
		return this.poster;
	}

	@Override
	public void setPostedBy(IUser user) {
		// TODO Auto-generated method stub
		this.poster = user;
	}

	@Override
	public LocalDateTime getPostedAt() {
		// TODO Auto-generated method stub
		return this.postedAt;
	}

	@Override
	public void setPostedAt(LocalDateTime time) {
		// TODO Auto-generated method stub
		this.postedAt = time;
	}

	@Override
	public Iterator<IUser> getLikes() {
		// TODO Auto-generated method stub
		return this.likes.iterator();
	}

	@Override
	public void addLike(IUser user) {
		// TODO Auto-generated method stub
		Iterator<IUser> likedUsers = this.getLikes();
		while (likedUsers.hasNext()){
			if (likedUsers.next() == user){
				return;
			}
		}
		this.likes.add(user);
		user.like(this);
		
	}

	@Override
	public void removeLike(IUser user) {
		// TODO Auto-generated method stub
		Iterator<IUser> likedUsers = this.getLikes();
		boolean userRemoved = true;
		while (likedUsers.hasNext()){
			if (likedUsers.next() == user){
				userRemoved = false;
				break;
			}
			
		}
		if (userRemoved){
			return;
		}
		this.likes.remove(user);
		user.unlike(this);
	}

}
