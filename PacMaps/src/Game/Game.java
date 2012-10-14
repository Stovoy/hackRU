package Game;

import java.awt.Image;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import Client.Boards.GameBoard;
import Entities.Blinky;
import Entities.Clyde;
import Entities.Ghost;
import Entities.Inky;
import Entities.PacMan;
import Entities.Pinky;
import Items.Pellet;
import Maps.Line;
import Maps.Point;

public class Game extends TimerTask
{
    private PacMan pacMan;
    private MouseEvent mouseEvent;
    private Ghost[] ghosts;
    private ArrayList<Pellet> pellets;
  
    private GameBoard gameBoard;
    
    public void initialize()
    {
    	makePacMan();
    	makeGhosts();
    	makePellets();
    	gameBoard.update();
    }
    
    private void makePacMan()
    { 
		pacMan = new PacMan(gameBoard.getMap().getFirstLine());
    }
    
    private void makeGhosts()
    {
    	ghosts = new Ghost[4];
    	ghosts[0] = new Blinky(getSpawnLine());
    	ghosts[1] = new Clyde(getSpawnLine());
    	ghosts[2] = new Inky(getSpawnLine());
    	ghosts[3] = new Pinky(getSpawnLine());
    }
    
    private Line getSpawnLine()
    {
    	Line[] lines = gameBoard.getMap().getLines();
    	Random random = new Random();
    	int randomInt = random.nextInt(lines.length);
		if (lines[randomInt].equals(pacMan.getLine()))
			return lines[randomInt].getIntersects()[0].getLine();
		else
			return lines[randomInt];
    }
    
    private void makePellets()
    {
    	pellets = new ArrayList<Pellet>();

    	final int pelletDistance = 10;
    	
    	for (Line line : gameBoard.getMap().getLines())
    	{
	    	int position = 0;
	    	while (position < line.getDistance())
	    	{
		    	
		    	Image pelletImage = null;
				try
				{
					pelletImage = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("pellet.png"));
				}
				catch (IOException e) { e.printStackTrace(); }
		    	pellets.add(new Pellet(pelletImage, line, position));
		    	position += pelletDistance;
	    	}
    	}
    }
    
	@Override
	public void run()
	{
		if (mouseEvent == null)
			return;
		pacMan.setMouse(new Point(mouseEvent.getX(), mouseEvent.getY()));
		pacMan.tick();
		for (Ghost ghost : ghosts)
			ghost.tick();
		
		ArrayList<Pellet> removeList = new ArrayList<Pellet>();
		for (Pellet pellet : pellets)
		{
			if (pellet.getPosition().distance(pacMan.getPosition()) < 10)
				removeList.add(pellet);
		}
		for (Pellet pellet : removeList)
		{
			pellets.remove(pellet);
		}

		gameBoard.update();
	}

	public ImageData[] getImages()
	{
		ImageData[] images = new ImageData[1 + pellets.size() + ghosts.length];
		images[0] = new ImageData(pacMan.getImage(), pacMan.getPosition());
		int count = 1;
		for (Pellet pellet : pellets)
			images[count++] = new ImageData(pellet.getImage(), pellet.getPosition());
		for (Ghost ghost : ghosts)
			images[count++] = new ImageData(ghost.getImage(), ghost.getPosition());
		return images;
	}
	
	public void setMouseEvent(MouseEvent e)
	{
		if (pacMan == null)
			return;
		if (e == null)
			pacMan.setFollowing(false);
		else
		{
			pacMan.setFollowing(true);
			this.mouseEvent = e;
		}
	}

	public void setGameBoard(GameBoard gameBoard)
	{
		this.gameBoard = gameBoard;
	}
}
