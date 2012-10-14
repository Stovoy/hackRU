package Entities;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.imgscalr.Scalr;

import Maps.Intersect;
import Maps.Line;
import Maps.Point;

public class PacMan extends Entity
{
	private Point mouse;
	
	private Image opened, mid, closed;
	
	private boolean opening = true;
	
	private int count = 0;
	
	private boolean following = false;
	
	public PacMan(Line line)
	{
		super(line);
		try
		{
			opened = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("pacopened.png"));
			opened = Scalr.resize((BufferedImage) opened, 12, (BufferedImageOp[])null);
			mid = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("pacmid.png"));
			mid = Scalr.resize((BufferedImage) mid, 12, (BufferedImageOp[])null);
			closed = ImageIO.read(Thread.currentThread().getContextClassLoader().getResourceAsStream("pacclosed.png"));
			closed = Scalr.resize((BufferedImage) closed, 12, (BufferedImageOp[])null);
		}
		catch (IOException e) { e.printStackTrace(); }
		image = closed;
		distance = 4;
	}
	
	@Override
	public Image getImage()
	{
		AffineTransform tx = AffineTransform.getRotateInstance(moveForward ? angle : reverseAngle(angle), 6, 6);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		return op.filter((BufferedImage)image, null);
	}
	
	@Override
	public void tick()
	{
		if (mouse == null) return;
		if (++count % 3 == 0)
		{
			count = 0;
			if (image.equals(closed))
			{
				image = mid;
				opening = true;
			}
			else if (image.equals(mid))
			{
				if (opening)
					image = opened;
				else
					image = closed;
			}
			else
			{
				image = mid;
				opening = false;
			}
		}
		move();		
	}
	
	private void move()
	{
		Point newPosition;
		final int speed = 3;
		if (moveForward)
		{
			distance += speed;
			newPosition = line.getStart().increment(angle, distance).add(new Point(-6, -6));
		}
		else
		{
			distance -= speed;
			newPosition = line.getStart().increment(angle, distance).add(new Point(-6, -6));
			
		}
		if (moveForward && distance < line.getDistance() || !moveForward && distance > 0)
			position = newPosition;
		else
		{
			if (moveForward)
			{
				distance = 4;
				position = line.getStart().increment(angle, 4).add(new Point(-6, -6));
			}
			else
			{
				distance = (int)(line.getDistance()-4);
				position = line.getStart().increment(angle, (int)(line.getDistance()-4)).add(new Point(-6, -6));
				
			}
		}
		if (!following) return;
		boolean backwardsIsBest = false;
		Intersect[] intersects = getIntersects();
		float mouseAngle = getMouseToPacManAngle();
		float minimumAngleDifference = Math.abs(mouseAngle - angle);
		float bestAngle = 0;
		float backwards = (float) Math.abs(mouseAngle - reverseAngle(angle));
		if (backwards < minimumAngleDifference)
		{
			minimumAngleDifference = backwards;
			backwardsIsBest = true;
		}
		Point intersectingPoint = null;
		Line closestIntersectingLine = line;
		for (Intersect intersect : intersects)
		{
			Line intersectingLine = intersect.getLine();
			float newAngleDifference;
			float lineAngle;
			
			lineAngle = intersectingLine.getAngle();
			newAngleDifference = Math.abs(mouseAngle - lineAngle);
			if (newAngleDifference < minimumAngleDifference)
			{
				bestAngle = lineAngle;
				minimumAngleDifference = newAngleDifference;
				closestIntersectingLine = intersectingLine;
				intersectingPoint = intersect.getIntersect();
				moveForward = true;
			}
			
			newAngleDifference = Math.abs(mouseAngle - reverseAngle(lineAngle));
			if (newAngleDifference < minimumAngleDifference)
			{
				bestAngle = lineAngle;
				minimumAngleDifference = newAngleDifference;
				closestIntersectingLine = intersectingLine;
				intersectingPoint = intersect.getIntersect();
				moveForward = false;
			}
		}
		if (closestIntersectingLine != line)
		{
			angle = bestAngle;
			line = closestIntersectingLine;
			distance = (int) intersectingPoint.distance(line.getStart());
		}
		else if (backwardsIsBest == moveForward && minimumAngleDifference < Math.PI/32)
		{
			moveForward = !moveForward;	
		}
	}
	
	private float reverseAngle(float in)
	{
		float out;
		if (in + Math.PI > Math.PI)
			out = (float) (in - Math.PI);
		else
			out = (float) (in + Math.PI);
		return out;
	}
	
	private float getMouseToPacManAngle()
	{ 
		return (float) Math.atan2(mouse.getY()-position.getY(), mouse.getX()-position.getX());
	}

	public void setMouse(Point point)
	{
		mouse = point;
	}

	public Line getLine()
	{
		return line;
	}

	public boolean isFollowing()
	{
		return following;
	}

	public void setFollowing(boolean following)
	{
		this.following = following;
	}
}
