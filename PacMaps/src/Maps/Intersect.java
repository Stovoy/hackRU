package Maps;

public class Intersect
{
	private Point intersectPoint;
	private Line line;
	private int position;
	
	public Intersect(Point intersectPoint, Line line, int position)
	{
		this.intersectPoint = intersectPoint;
		this.line = line;
		this.position = position;
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
	
	public int getPosition()
	{
		return position;
	}
	
	public void setPosition(int position)
	{
		this.position = position;
	}
}
