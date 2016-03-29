package edu.toronto.csc301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;

public class DataLoader implements IDataLoader{
	String line;
	String full[];
	String user;
	String text;
	Tweet tweet;
	BufferedReader reader;
	Set<String> currentHashTags;
	
	public DataLoader(){
		
	}

	public Iterator<ITweet> iterator(InputStream data) throws IOException {
		TweetStreamReadingIterator it = new TweetStreamReadingIterator(data);
		return it;
	}

	public Iterator<ITweet> iterator(InputStream data, Set<String> hashtags) throws IOException {
		if (hashtags == null){
			throw new NullPointerException();
		}
		if (hashtags.isEmpty()){
			throw new IllegalArgumentException();
		}
		TweetStreamReadingIterator it = new TweetStreamReadingIterator(data);
		
		Predicate<ITweet> p = (tw) -> (tw.getHashTags().containsAll(hashtags));
		TweetFilteringIterator it2 = new TweetFilteringIterator(it, p);
		return it2;

			
	}
	

}
