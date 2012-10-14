package API;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GoogleMaps 
{	
	public static Image getImage(String location, int zoom)
	{
		location = location.replaceAll("//s", " ").replace(" ", "+");
		URL url = null;
		try
		{
			url = new URL(String.format("http://maps.googleapis.com/maps/api/staticmap?center=%s&zoom=%d&size=600x600&sensor=false&key=AIzaSyDIDjeZzpV81g3NBXwaMUb3L0cPw2lE3Bc", 
					location, zoom));
		}
		catch (MalformedURLException e)
		{
			System.err.println("Malformed URL");
		}
		
		Image image = null;
		try 
		{
		    image = ImageIO.read(url);
		}
		catch (IOException e) 
		{
		}
		return image;
	}

}
