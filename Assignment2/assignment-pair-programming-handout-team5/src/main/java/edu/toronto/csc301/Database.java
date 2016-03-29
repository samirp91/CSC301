package edu.toronto.csc301;

import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Database implements IDatabase{
	
	private List<ITweet> db; // linked list of tweets?

	public void loadTweets(List<ITweet> tweets) {
		db = tweets;	
	}

	public int getTotalNumberOfTweets() {
		return db.size();
	}

	public int getNumberOfUniqueUsers() {
		Set<String> unique = new HashSet<String>();
		
		for (int i = 0; i < db.size(); i++) {
			unique.add(db.get(i).getUsername());
		}

		return unique.size();
	}

	public int getNumberOfUniqueHashTags() {
		Set<String> uniqueTags = new HashSet<String>();
		
		for (int i = 0; i < db.size(); i++) {
			uniqueTags.addAll(db.get(i).getHashTags());
		}
		
		return uniqueTags.size();
	}

	public double getAverageNumberOfHashTagsPerTweet() {
		int totalHashTags = 0;
		
		for (int i = 0; i < db.size(); i++) {
			totalHashTags += db.get(i).getHashTags().size();
		}
		
		if (getTotalNumberOfTweets() == 0) {
			return 0;
		} else {
			return (double) totalHashTags/getTotalNumberOfTweets();
		}
	}

	public double getAverageNumberOfTweetsPerUser() {
		Set<String> uniqueUsers = new HashSet<String>();
		for (int i = 0; i < db.size(); i++) {
			uniqueUsers.add(db.get(i).getUsername());
		}
		
		if (uniqueUsers.size() == 0) {
			return 0;
		} else {
			return (double) getTotalNumberOfTweets()/uniqueUsers.size();
		}
	}

	public String getMostPopularHashTag() {
		Map<String, Integer> frequencyList = new HashMap<String, Integer>();
		
		// ...?!
		for (int i = 0; i < db.size(); i++) {
			for (String hash: db.get(i).getHashTags()) {
				if (!(frequencyList.containsKey(hash))) { // not in list yet
					frequencyList.put(hash, 1); // first entry of hashtag
				} else {
					frequencyList.put(hash, (frequencyList.get(hash) + 1));
				}
			}
		}
		
		String mostPop = null;
		
		if (!frequencyList.isEmpty()) {
			// iterate through mapping and find the one whose val = largest val
			int largestVal = Collections.max(frequencyList.values());
			for (Map.Entry<String, Integer> entry : frequencyList.entrySet()) {
				if (entry.getValue() == largestVal) {
					mostPop = entry.getKey();
				}
			}		
		}
		
		return mostPop;
	}

}
