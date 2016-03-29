package edu.toronto.csc301;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.Set;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.io.BufferedReader;

public class TweetFilteringIterator implements Iterator<ITweet> {
	Iterator<ITweet> it;
	private Predicate<ITweet> hashtags;
	
	
	public TweetFilteringIterator(Iterator<ITweet> it, Predicate<ITweet> hashtags){
		this.it = it;
		this.hashtags = hashtags;
		//this.reader = new BufferedReader(new InputStreamReader(data));
	}

	public boolean hasNext() {
		while (it.hasNext()){
			Iterator<ITweet> copy = it;
			ITweet line = copy.next();
			if (hashtags.test(line)){
				return true;
			}
		}
		return false;
	}

	public ITweet next() {
	    return (ITweet) it.next();
	}

	public void remove() {
		// TODO Auto-generated method stub
		
	}

	public void forEachRemaining(Consumer<? super ITweet> action) {
		// TODO Auto-generated method stub
		
	}
}
