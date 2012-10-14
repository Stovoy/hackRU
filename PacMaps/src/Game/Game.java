package Game;

import java.io.IOException;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import Entities.PacMan;

public class Game extends TimerTask
{
    private PacMan pacMan;
//    private Ghost[] ghosts;
  
	@Override
	public void run()
	{
		try
		{
			pacMan = new PacMan(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("pacmid.png")));
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	public ImageData[] getImages()
	{
		ImageData[] images = new ImageData[1];
		images[0] = new ImageData(pacMan.getImage(), pacMan.getPosition());
		return images;
	}
}
