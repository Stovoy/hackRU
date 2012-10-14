package Game;

import java.awt.Image;
import java.util.TimerTask;

import Entities.PacMan;

public class Game extends TimerTask
{
    private PacMan pacMan;
//    private Ghost[] ghosts;
  
	@Override
	public void run()
	{
		pacMan = new PacMan();
	}

	public Image[] getImages()
	{
		return pacMan.getImage();
	}
}
