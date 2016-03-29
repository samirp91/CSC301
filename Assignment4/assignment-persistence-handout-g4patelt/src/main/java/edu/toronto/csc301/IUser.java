package edu.toronto.csc301;

import java.time.LocalDateTime;
import java.util.Set;

public interface IUser {
	
	

	// Properties
	
	public String getUsername();
	public void setUsername(String username);
	
	public String getPassword();
	public void setPassword(String password);
	
	public String getBio();
	public void setBio(String bio);

	public LocalDateTime getRegistrationTime();
	public void setRegistrationTime(LocalDateTime registrationTime);
	
	
	// Users can create posts 
	
	public IPost newPost(IPost.PostType type, String url, Set<String> tags);
	
	
	//-------------------------------------------------------------------------
	
	// AdFilter is, essentially, a function that takes two booleans and returns a boolean
	@FunctionalInterface
	public static interface AdFilter {
		public boolean displayAd(boolean userIsOnMobileDevice, boolean adIsSponsored);
	}
	
	public AdFilter getAdFilter();
	public void setAdFilter(AdFilter filter);
	
	
	//-------------------------------------------------------------------------
	
}
