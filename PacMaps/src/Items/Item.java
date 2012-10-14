package Items;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import org.imgscalr.Scalr;

import Maps.Line;
import Maps.Point;

public abstract class Item
{
	protected Line line;
	protected int distance;
	
	protected Point position;
	
	public Image image;
	
	public Item(Image image, Line line)
	{
		this.image = Scalr.resize((BufferedImage) image, 12, (BufferedImageOp[])null);
		this.line = line;

		float angle = line.getAngle();
		this.position = line.getStart().increment(angle, 4).add(new Point(-6, -6));
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public void setLine(Line line)
	{
		this.line = line;
	}
	
	public Point getPosition() 
	{
		return position;
	}
}
