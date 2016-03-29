package edu.toronto.csc301.impl;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import edu.toronto.csc301.IPost;

public class Post implements IPost{
	
	private PostType type;
	private String url;
	private LocalDateTime time;
	private HashSet<String> tags;
	
	public Post(PostType type, String url, Set<String> tags){
		if (url == null || type == null){
			throw new NullPointerException();
		}
		this.type = type;
		this.url = url;
		this.tags = new HashSet<String>(tags);
		this.time = LocalDateTime.now();
	}
	
	@Override
	public PostType getType() {
		// TODO Auto-generated method stub
		return this.type;
	}

	@Override
	public void setType(PostType type) {
		// TODO Auto-generated method stub
		this.type = type;
	}

	@Override
	public String getURL() {
		// TODO Auto-generated method stub
		return this.url;
	}

	@Override
	public void setURL(String url) {
		// TODO Auto-generated method stub
		this.url = url;
	}

	@Override
	public Set<String> getTags() {
		// TODO Auto-generated method stub
		return new HashSet<String>(this.tags);
	}

	@Override
	public void addTag(String tag) {
		// TODO Auto-generated method stub
		if (tag == null){
			throw new NullPointerException();
		}
		this.tags.add(tag);
	}

	@Override
	public void removeTag(String tag) {
		// TODO Auto-generated method stub
		if (tag == null){
			throw new NullPointerException();
		}
		this.tags.remove(tag);
		
	}

	@Override
	public LocalDateTime getPostedAt() {
		// TODO Auto-generated method stub
		return this.time;
	}

	@Override
	public void setPostedAt(LocalDateTime time) {
		// TODO Auto-generated method stub
		this.time = time;
	}

}
