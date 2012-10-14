package Game;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.TimerTask;

import javax.imageio.ImageIO;

import Client.Boards.GameBoard;
import Entities.PacMan;
import Maps.Point;

public class Game extends TimerTask
{
    private PacMan pacMan;
    private MouseEvent mouseEvent;
//    private Ghost[] ghosts;
  
    private GameBoard gameBoard;
    
    public void initialize()
    {
    	makePacMan();
    }
    
    private void makePacMan()
    { 
		try
		{
			pacMan = new PacMan(ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("pacmid.png")), gameBoard.getMap().getFirstLine());
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
    }
    
	@Override
	public void run()
	{
		if (mouseEvent == null)
			return;
		pacMan.setMouse(new Point(mouseEvent.getX(), mouseEvent.getY()));
		pacMan.tick();

		gameBoard.update();
	}

	public ImageData[] getImages()
	{
		ImageData[] images = new ImageData[1];
		images[0] = new ImageData(pacMan.getImage(), pacMan.getPosition());
		return images;
	}
	
	public void setMouseEvent(MouseEvent e)
	{
		this.mouseEvent = e;
	}

	public void setGameBoard(GameBoard gameBoard)
	{
		this.gameBoard = gameBoard;
	}
}
