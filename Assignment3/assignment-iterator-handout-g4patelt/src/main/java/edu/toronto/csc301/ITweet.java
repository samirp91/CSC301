package edu.toronto.csc301;

import java.util.Set;

public interface ITweet {

	public String getUsername();
	public void setUsername(String username);
	public String getText();
	public void setText(String text);
	public Set<String> getHashTags();
	
}
