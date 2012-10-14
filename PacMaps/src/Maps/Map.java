package Maps;

import java.awt.Image;
import java.util.ArrayList;

public class Map
{
	private Image image;
	private ArrayList<Line> lines;
	private Line firstLine;
	
	public Map()
	{
		image = null;
		lines = new ArrayList<Line>();
		firstLine = null;
	}
	
	public void setImage(Image image)
	{
		this.image = image;
	}
	
	public Image getImage()
	{
		return image;
	}
	
	public void addLine(Line line)
	{
		if (firstLine == null)
			firstLine = line;
		lines.add(line);
	}
	
	public void addLine(Point start, Point end)
	{
		addLine(new Line(start, end));
	}
	
	public void addLine(int x1, int y1, int x2, int y2)
	{
		addLine(new Point(x1, y1), new Point(x2, y2));
	}
	
	public Line[] getLines()
	{
		Line[] lines = new Line[this.lines.size()];
		for (int i = 0; i < lines.length; ++i)
			lines[i] = this.lines.get(i);
		return lines;
	}
	
	public void clearLines()
	{
		firstLine = null;
		lines.clear();
	}

	public void removeLine(Line line)
	{
		lines.remove(line);
		if (lines.size() == 0)
			firstLine = null;
	}

	public Line getFirstLine()
	{
		return firstLine;
	}

	public void clearIntersects()
	{
		for (Line line : lines)
			line.clearIntersects();
	}

	public void addIntersect(Point intersectPoint, Line first, Line second)
	{
		first.addIntersect(intersectPoint, second);
		second.addIntersect(intersectPoint, first);
	}
}
