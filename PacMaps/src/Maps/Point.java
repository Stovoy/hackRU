package Maps;

public class Point
{
	private int x, y;
	
	public Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	public boolean isWithin(Point one, Point two)
	{
		int x1 = one.getX(), y1 = one.getY(), x2 = two.getX(), y2 = two.getY();
		int xMin, xMax;
		int yMin, yMax;
		xMin = Math.min(x1, x2);
		xMax = Math.max(x1, x2);
		yMin = Math.min(y1, y2);
		yMax = Math.max(y1, y2);
		return x >= xMin && x <= xMax && y >= yMin && y <= yMax;
	}
	
	public int getX()
	{
		return x;
	}
	
	public void setX(int x)
	{
		this.x = x;
	}
	
	public int getY()
	{
		return y;
	}
	
	public void setY(int y)
	{
		this.y = y;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;
        Point point = (Point) obj;
        if (point.getX() == x && point.getY() == y) return true;
        return false;
	}
}
