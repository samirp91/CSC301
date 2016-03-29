package edu.toronto.csc301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.function.Consumer;

public class TweetStreamReadingIterator implements Iterator<ITweet> {
	String line;
	BufferedReader reader;
	String full[];
	String text;
	String user;
	ITweet tweet;
	
	public TweetStreamReadingIterator(InputStream data){
		this.reader = new BufferedReader(new InputStreamReader(data));
		
	}
	public boolean hasNext() {
		try {
		    line = reader.readLine();
		    if (line != null) {
		    	return true;
		    }
		    else {
		    	reader.close();
		    	return false;
		    }
		}
		catch (IOException e) {
		    throw new RuntimeException(e);
		}
	}

	public ITweet next() {
		full = line.split(" : ");
		if (full.length > 1){
	    	user = full[0].substring(1).trim();
	    	text = full[1].trim();
	    	tweet = new Tweet(user, text);
			return tweet;
		}
		else{
			return new Tweet("", line);
		}
	}

	public void remove() {
		// TODO Auto-generated method stub
		
	}

	public void forEachRemaining(Consumer<? super ITweet> action) {
		// TODO Auto-generated method stub
		
	}

}
