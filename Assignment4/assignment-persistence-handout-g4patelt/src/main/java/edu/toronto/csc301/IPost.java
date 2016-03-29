package edu.toronto.csc301;

import java.time.LocalDateTime;
import java.util.Set;

public interface IPost {

	enum PostType {IMAGE, VIDEO};
	
	public PostType getType();
	public void setType(PostType type);
	
	public String getURL();
	public void setURL(String url);
	
	public Set<String> getTags();
	public void addTag(String tag);
	public void removeTag(String tag);
	
	public LocalDateTime getPostedAt();
	public void setPostedAt(LocalDateTime time);
	
	
	
}
