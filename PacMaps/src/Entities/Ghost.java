package Entities;

import java.awt.Image;
import java.util.Random;

import Maps.Intersect;
import Maps.Line;
import Maps.Point;

public abstract class Ghost extends Entity
{
	protected Image one, two;
	private int turnTicks = 0;
	
	public Ghost(Line line)
	{
		super(line);
		distance = new Random().nextInt(32);
		this.position = line.getStart().increment(angle, distance).add(new Point(-6, -6));
	}
	
	protected void move()
	{
		Point newPosition;
		final int speed = 2;
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
		if (++turnTicks == 20)
		{
			turnTicks = 0;
			Intersect[] intersects = getIntersects();
			Line newLine = null;
			Point intersectingPoint = null;
			for (Intersect intersect : intersects)
			{
				Random random = new Random();
				if (random.nextInt(2) == 1)
				{
					moveForward = random.nextInt(2) == 0;
					newLine = intersect.getLine();
					intersectingPoint = intersect.getIntersect();
					break;
				}
			}
			if (newLine != null && !line.equals(newLine))
			{
				line = newLine;
				angle = line.getAngle();
				distance = (int) intersectingPoint.distance(line.getStart());
			}
		}
	}
}
