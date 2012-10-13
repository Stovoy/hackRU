package Maps;

import java.awt.Image;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;

public class GoogleMapsAPI 
{
	public static void main(String[] args)
	{
		String location = "college ave new brunswick";
		location = location.replaceAll("\\s+", " ").replace(" ", "+");
		String url = "http://maps.googleapis.com/maps/api/staticmap?center="+ location + "&zoom=13&size=800x600&sensor=false";
		try 
		{
			saveImage(url, location + ".jpg");
		}
		catch (IOException e) 
		{
			System.err.println("No image found for " + location + ".");
		}
		
	}
	
	public static void saveImage(String imageUrl, String destinationFile) throws IOException {
		URL url = new URL(imageUrl);
		InputStream is = url.openStream();
		OutputStream os = new FileOutputStream(destinationFile);

		byte[] b = new byte[2048];
		int length;

		while ((length = is.read(b)) != -1) {
			os.write(b, 0, length);
		}

		is.close();
		os.close();
	}

}
