package Entities;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.util.ArrayList;

import org.imgscalr.Scalr;

import Maps.Intersect;
import Maps.Line;
import Maps.Point;

public abstract class Entity
{
	protected Line line;
	protected float angle;
	protected int distance;
	protected boolean moveForward = true;
	
	protected Point position;
	
	public Image image;
	
	public Entity(Line line)
	{
		this.line = line;
		this.angle = line.getAngle();
		this.position = line.getStart().increment(angle, 4).add(new Point(-6, -6));
	}
	
	public Entity(Image image, Line line)
	{
		this.image = Scalr.resize((BufferedImage) image, 12, (BufferedImageOp[])null);
		this.line = line;

		this.angle = line.getAngle();
		this.position = line.getStart().increment(angle, 4).add(new Point(-6, -6));
	}
	
	public Image getImage()
	{
		AffineTransform tx = AffineTransform.getRotateInstance(angle, 6, 6);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		return op.filter((BufferedImage)image, null);
	}
	
	public Intersect[] getIntersects()
	{
		ArrayList<Intersect> intersecting = new ArrayList<Intersect>();
		for (Intersect intersect : line.getIntersects())
		{
			Point intersectPoint = intersect.getIntersect();
			if ((int) intersectPoint.distance(position) < 10)
				intersecting.add(intersect);
		}
		Intersect[] intersectingArray = new Intersect[intersecting.size()];
		for (int i = 0; i < intersecting.size(); ++i)
			intersectingArray[i] = intersecting.get(i);
		return intersectingArray;
	}
	
	public float getAngle()
	{
		return angle;
	}
	
	public void setLine(Line line)
	{
		this.line = line;
		this.angle = line.getAngle();
	}
	
	public abstract void tick();
	
	public Point getPosition() 
	{
		return position;
	}
}
