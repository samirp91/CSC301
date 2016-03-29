package edu.toronto.csc301;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class DataLoader implements IDataLoader{

	public List<ITweet> load(InputStream data) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(data));
        String line;
        List<ITweet> temp = new ArrayList<ITweet>();
        while ((line = reader.readLine()) != null) {
        	if (line.equals("")){
        		continue;
        	}
        	String [] info = line.substring(1).trim().split(":");
        	Tweet tw = new Tweet(info[0].trim(), info[1].trim());
        	temp.add(tw);
        	
        }
        reader.close();
        
		return temp;
	}

}
