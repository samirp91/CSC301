package edu.toronto.csc301;

import java.util.HashSet;
import java.util.Set;

public class Tweet implements ITweet {
	
	public static final int MAX_USERNAME_LENGTH = 32; 
	public static final int MAX_TEXT_LENGTH = 140;
	
	private String username;
	private String text;
	
	public Tweet(String username, String text){
		setUsername(username);
		setText(text);
	}

	public String getUsername() {
		return username;
	}

	/**
	 * @throws NullPointerException If the given username is null.
	 * @throws IllegalArgumentException If the given username contains
	 *         an illegal character (i.e. Any non-alphanumeric character,
	 *         other than underscore).
	 */
	public void setUsername(String username) {
		if ((username.length() > MAX_USERNAME_LENGTH) // too long
				|| !(username.matches("[a-zA-Z0-9_]*")) // or not alphanum or _
				|| (username.equals(""))) { // empty
			throw new IllegalArgumentException();
		} else {
			this.username = username;
		}
	}

	public String getText() {
		return text;
	}

	/**
	 * @throws NullPointerException If the given text is null.
	 * @throws IllegalArgumentException If the given text is empty
	 */
	public void setText(String text) {
		if (text.trim().equals("") // empty or whitespace only
				|| (text.length() > MAX_TEXT_LENGTH)) { 
			throw new IllegalArgumentException();
		} else {
			this.text = text;
		}
	}

	public Set<String> getHashTags() {
		String text = this.getText();
		Set<String> hashTags = new HashSet<String>();
		if (!text.contains("#") || text.contains("# ")){
			return hashTags;
		}
		String[] split = text.split("#");
		String[] finalSplit;
		for (int i = 0; i < split.length; i++){
			if (split.length > 1 && i == 0){
				i++;
			}
			split[i] = split[i].trim();
			if (split[i].contains(",")){
				finalSplit = split[i].split(",");
				hashTags.add(finalSplit[0]);
			}
			else if (split[i].contains(" ")){
				finalSplit = split[i].split(" ");
				hashTags.add(finalSplit[0]);
			}
			else if (split[i].contains("!")){
				finalSplit = split[i].split("!");
				hashTags.add(finalSplit[0]);
			}
			else if (split[i].matches(".*\\.")){
				finalSplit = split[i].split("[.]");
				hashTags.add(finalSplit[0]);
			}
			else if (split[i].contains("-")){
				finalSplit = split[i].split("-");
				hashTags.add(finalSplit[0]);
			}
			else{
				hashTags.add(split[i]);
			}
		
		}
		return hashTags;
	}
	

	@Override
	public boolean equals(Object obj){
		return obj.hashCode() == this.hashCode();
	}
	
	@Override
	public int hashCode() {
	    int hash = 3;
	    hash = 53 * hash + (this.getUsername() != null ? this.getUsername().hashCode() : 0);
	    hash = 53 * hash + (this.getText() != null ? this.getText().hashCode() : 0);
	    return hash;
	}
	
}
