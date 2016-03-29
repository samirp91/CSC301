package edu.toronto.csc301;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tweet implements ITweet{

	public static final Pattern hashTagPattern = Pattern.compile("#(\\w+)");
	
	private String username;
	private String text;
	private Set<String> hashTags;
	
	
	public Tweet(String username, String text) {
		setUsername(username);
		setText(text);
	}

	
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		Objects.requireNonNull(username);
		this.username = username;
	}

	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		Objects.requireNonNull(text);
		this.text = text;
		this.hashTags = extractHashTags(text);
	}
	
	
	private Set<String> extractHashTags(String text) {
		Set<String> tags = new HashSet<String>();
		Matcher m = hashTagPattern.matcher(text);
		while(m.find()){
			tags.add(m.group().substring(1));
		}
		return tags;
	}


	public Set<String> getHashTags() {
		return Collections.unmodifiableSet(this.hashTags);
	}

	
	@Override
	public boolean equals(Object o) {
		return o instanceof ITweet &&
				Objects.equals(((ITweet) o).getUsername(), this.getUsername()) &&
				Objects.equals(((ITweet) o).getText(), this.getText());
	}

	@Override
	public String toString() {
		return String.format("@%s : %s", username, text);
	}
}
