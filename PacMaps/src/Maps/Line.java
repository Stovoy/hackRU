package Maps;

import java.util.ArrayList;

public class Line
{
	private Point start;
	private Point end;
	private ArrayList<Intersect> intersects;
	
	public Line(Point start, Point end)
	{
		this.start = start;
		this.end = end;
		this.intersects = new ArrayList<Intersect>();
	}
	
	public Point intersects(Line line)
	{
		float m1 = getSlope();
		float m2 = line.getSlope();
		if (m1 == m2 || m1 == Float.NaN || m2 == Float.NaN) return null;
		float b1 = getYIntersect(m1);
		float b2 = line.getYIntersect(m2);
		int x = Math.round((b2 - b1) / (m1 - m2));
		int y = Math.round(m1*x + b1);
		if (x < 0 || x > 600 || y < 0 || y > 600) return null;
		Point intersection = new Point(x, y);
		if (!intersection.isWithin(start, end) || !intersection.isWithin(line.start, line.end)) return null;
		return intersection;
	}
	
	public Point getMidpoint()
	{
		int x = start.getX() + end.getX();
		int y = start.getY() + end.getY();
		return new Point(x, y);
	}
	
	public float getAngle()
	{
		return (float)Math.atan2(end.getY() - start.getY(), end.getX() - start.getX());
	}
	
	public float getSlope()
	{
		float dX = end.getX() - start.getX();
		if (dX == 0) return Float.NaN;
		float dY = end.getY() - start.getY();
		return dY / dX;
	}
	
	private float getYIntersect(float slope)
	{
		return start.getY() - slope * start.getX();
	}
	
	public Point getStart()
	{
		return start;
	}
	
	public void setStart(Point start)
	{
		this.start = start;
	}
	
	public Point getEnd()
	{
		return end;
	}
	
	public void setEnd(Point end)
	{
		this.end = end;
	}
	
	@Override 
	public boolean equals(Object obj)
	{
		if (obj == null) return false;
        if (!this.getClass().equals(obj.getClass())) return false;
		Line line = (Line)obj;
		if (line.start.equals(start) && line.end.equals(end)) return true;
		return false;
	}

	public void addIntersect(Point intersectPoint, Line line)
	{
		intersects.add(new Intersect(intersectPoint, line, (int) line.getStart().distance(intersectPoint)));
	}
	
	public void clearIntersects()
	{
		intersects.clear();
	}

	public float getDistance()
	{
		return (float) Math.sqrt(Math.pow(end.getX()-start.getX(), 2) + Math.pow(end.getY()-start.getY(), 2));
	}

	public Intersect[] getIntersects()
	{
		Intersect[] intersectsArray = new Intersect[intersects.size()];
		for (int i = 0; i < intersects.size(); ++i)
			intersectsArray[i] = intersects.get(i);
		return intersectsArray;
	}
}
