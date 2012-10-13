package Maps;

import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GoogleMapsAPI 
{	
	public static Image getImage(String location)
	{
		location = location.replaceAll("//s", " ").replace(" ", "+");
		URL url = null;
		try
		{
			url = new URL(String.format("http://maps.googleapis.com/maps/api/staticmap?center=%s&zoom=13&size=600x600&sensor=false", 
					location));
		}
		catch (MalformedURLException e)
		{
			System.err.println("Malformed URL\n");
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
