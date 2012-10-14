package Maps;

public class Intersect
{
	private Point intersectPoint;
	private Line line;
	
	public Intersect(Point intersectPoint, Line line)
	{
		this.intersectPoint = intersectPoint;
		this.line = line;
	}

	public Point getIntersect()
	{
		return intersectPoint;
	}
	
	public void setIntersect(Point intersect)
	{
		this.intersectPoint = intersect;
	}
	
	public Line getLine()
	{
		return line;
	}
	
	public void setLine(Line line)
	{
		this.line = line;
	}
}
