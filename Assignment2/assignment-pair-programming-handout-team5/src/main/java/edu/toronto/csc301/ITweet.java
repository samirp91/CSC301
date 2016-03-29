package edu.toronto.csc301;

import java.util.Set;

public interface ITweet {

	public String getUsername();
	
	/**
	 * @throws NullPointerException If the given username is null.
	 * @throws IllegalArgumentException If the given username contains
	 *         an illegal character (i.e. Any non-alphanumeric character,
	 *         other than underscore).
	 */
	public void setUsername(String username);
	
	public String getText();
	
	/**
	 * @throws NullPointerException If the given text is null.
	 * @throws IllegalArgumentException If the given text contains only 
	 *         white spaces.
	 */
	public void setText(String text);
	
	/**
	 * NOTE: For the purpose of this assignment, hash-tags can contain
	 * only alphanumeric characters (i.e. English letters and digits) and
	 * underscore.
	 */
	public Set<String> getHashTags();
	
}
