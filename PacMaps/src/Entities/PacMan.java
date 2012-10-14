package Entities;

import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

import Maps.Intersect;
import Maps.Line;
import Maps.Point;

public class PacMan extends Entity
{
	private Point mouse;
	private int distance;
	private boolean moveForward = true;
	
	public PacMan(Image image, Line line)
	{
		super(image, line);
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
		move();
	}
	
	private void move()
	{
		Point newPosition;
		if (moveForward)
		{
			distance += 5;
			newPosition = line.getStart().increment(angle, distance).add(new Point(-6, -6));
		}
		else
		{
			distance -= 5;
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
		if (true)
		{
			if (closestIntersectingLine != line)
			{
				angle = bestAngle;
				line = closestIntersectingLine;
				distance = (int) intersectingPoint.distance(line.getStart());
			}
			else if (backwardsIsBest == moveForward && minimumAngleDifference < Math.PI/64)
			{
				moveForward = !moveForward;	
			}
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
}
