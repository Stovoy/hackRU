package Entities;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;

import org.imgscalr.Scalr;

import Maps.Line;
import Maps.Point;

public abstract class Entity
{
	protected Line line;
	protected float angle;
	
	protected Point position;
	
	public Image image;
	
	public Entity(Image image)
	{
		this.image = Scalr.resize((BufferedImage) image, 20, (BufferedImageOp[])null);
	}
	
	public Image getImage()
	{
		AffineTransform tx = AffineTransform.getRotateInstance(angle, 32, 32);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		return op.filter((BufferedImage)image, null);
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
