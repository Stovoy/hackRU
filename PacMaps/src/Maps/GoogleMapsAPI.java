package Maps;

import java.awt.Image;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class GoogleMapsAPI 
{
	public Image GetImage(String location)
	{
		String fixedLocation = location.replaceAll("\\s+", " ").replace(" ", "+");
		Image image = null;
		try 
		{
		    URL url = new URL(String.format("http://maps.googleapis.com/maps/api/staticmap?center=%s&zoom=13&size=800x600", fixedLocation));
		    image = ImageIO.read(url);
		} 
		catch (IOException e) 
		{
			System.err.println("No image found for " + location + ".");
		}
		return image;
	}
}
