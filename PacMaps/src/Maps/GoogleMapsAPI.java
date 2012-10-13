package Maps;

import java.awt.Image;

public class GoogleMapsAPI 
{
	public Image GetImage(String location)
	{
		Image image = null;
		try 
		    URL url = new URL("http://www.yahoo.com/image_to_read.jpg");
		    image = ImageIO.read(url);
		} catch (IOException e) {
		}
		"http://maps.googleapis.com/maps/api/staticmap?center=New+York,NY&zoom=13&size=800x600";
	}
}
