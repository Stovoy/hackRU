package Items;

import java.awt.Image;

import Maps.Line;
import Maps.Point;

public class Pellet extends Item
{
	public Pellet(Image image, Line line, int distance)
	{
		super(image, line);
		position = line.getStart().increment(line.getAngle(), distance).add(new Point(-6, -6));
	}
}
