package API;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;

public class JsonReaderApi {
	public static String readJsonUrl() throws Exception {
	    BufferedReader reader = null;
	    try {
	        URL url = new URL("http://api.usatoday.com/open/census/pop?keypat=NJ&keyname=statePostal&sumlevid=4&api_key=fsmfpbabjaygm9qcw57yv76t");
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        return buffer.toString();
	    } finally {
	        if (reader != null)
	            reader.close();
	    }
	}
}
