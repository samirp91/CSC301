package edu.toronto.csc301;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface IDataLoader {

	public List<ITweet> load(InputStream data) throws IOException;
}
