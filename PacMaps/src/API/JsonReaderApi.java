package API;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;

public class JSONReaderApi 
{
	public static String getCensusData(String location) 
	{
	    BufferedReader reader = null;
	    try 
	    {
	        URL url = new URL("http://api.usatoday.com/open/census/pop?keypat=NJ&keyname=statePostal&sumlevid=4&api_key=fsmfpbabjaygm9qcw57yv76t");
	        reader = new BufferedReader(new InputStreamReader(url.openStream()));
	        StringBuffer buffer = new StringBuffer();
	        int read;
	        char[] chars = new char[1024];
	        while ((read = reader.read(chars)) != -1)
	            buffer.append(chars, 0, read); 

	        String string = buffer.toString().toLowerCase();
	        location = location.toLowerCase();
	        int index = string.indexOf(location);
	        if (index == -1)
	    		return "No population data found. Enter a city in NJ.";
	        string = string.substring(index);
	        index = string.indexOf(": ");
	        string = string.substring(index + 3);
	        index = string.indexOf("\"");
	        string = string.substring(0, index);
	        return "The population of " + UppercaseFirstLetters(location) + " is " + string + " people.";
	    }
	    catch (Exception e) { }
	    finally
	    {
	        if (reader != null) 
		        try
				{
					reader.close();
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
	    }
		return "No population data found. Enter a city in NJ.";
	}
	
	private static String UppercaseFirstLetters(String string) 
	{
	    boolean previousWasWhiteSpace = true;
	    char[] chars = string.toCharArray();
	    for (int i = 0; i < chars.length; i++) 
	    {
	        if (Character.isLetter(chars[i])) 
	        {
	            if (previousWasWhiteSpace) 
	                chars[i] = Character.toUpperCase(chars[i]);    
	            previousWasWhiteSpace = false;
	        }
	        else
	        	previousWasWhiteSpace = Character.isWhitespace(chars[i]);
	    }
	    return new String(chars);
	}
}
