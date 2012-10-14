package Entities;

import java.awt.Image;
import java.awt.event.MouseEvent;

import Maps.Point;

public class PacMan extends Entity
{
	public PacMan(Image image)
	{
		super(image);
		position = new Point(200, 200);
	}

	@Override
	public void tick()
	{
		
	}
	
	public void sendMouseMotion(MouseEvent e)
	{
		
	}
}
