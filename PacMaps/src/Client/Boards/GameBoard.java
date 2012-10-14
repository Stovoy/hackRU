package Client.Boards;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.HashMap;

import Maps.Intersect;
import Maps.Line;
import Maps.Map;
import Maps.Point;

public class GameBoard extends AbstractBoard
{
    private Map map;

	@Override
	public void update()
	{
		updateUI();
	}
	
    @Override
    public void paintComponent(Graphics g) 
    {
    	if (map == null) return;
        super.paintComponent(g);
        drawImage(g);
        drawLines(g);
    }
    
    private void drawImage(Graphics g)
    {
        g.drawImage(map.getImage(), 0, 0, null);
    }
    
    private Area createArea(Area area, ArrayList<Line> linesToProcess, ArrayList<Line> finishedLines)
    {
    	if (linesToProcess == null)
    		linesToProcess = new ArrayList<Line>();
    	if (finishedLines == null)
    		finishedLines = new ArrayList<Line>();
    	ArrayList<Line> newLinesToProcess = new ArrayList<Line>();
    	
    	if (area == null)
    	{
    		area = new Area();
    		area.add(new Area(createPolygon(linesToProcess.get(0))));
        	finishedLines.add(linesToProcess.get(0));
    		for (Intersect deepIntersect : linesToProcess.get(0).getIntersects())
    		{
    			Line deepLine = deepIntersect.getLine();
    			if (!finishedLines.contains(deepLine))
    				newLinesToProcess.add(deepLine);
    		}
    	}
    	
    	for (Line currentLine : linesToProcess)
    	{
    		if (finishedLines.contains(currentLine)) continue;
    		area.add(new Area(createPolygon(currentLine)));
			for (Intersect intersect : currentLine.getIntersects())
			{
				Line line = intersect.getLine();
				if (finishedLines.contains(line)) continue;
	    		area.add(new Area(createPolygon(line)));
	    		finishedLines.add(line);
	    		for (Intersect deepIntersect : line.getIntersects())
	    		{
	    			Line deepLine = deepIntersect.getLine();
	    			if (!finishedLines.contains(deepLine))
	    				newLinesToProcess.add(deepLine);
	    		}
			}
    	}
    	if (newLinesToProcess.size() == 0)
    		return area;
    	else
    		return createArea(area, newLinesToProcess, finishedLines);
    }

	private void drawLines(Graphics g)
    {
		Graphics2D graphics = (Graphics2D)g;
		ArrayList<Line> linesToAdd = new ArrayList<Line>();
		linesToAdd.add(map.getFirstLine());
    	Area area = createArea(null, linesToAdd, null);
    	g.setColor(Color.BLACK);
		graphics.fill(area);
		graphics.setStroke(new BasicStroke(1));
		g.setColor(new Color(0, 51, 255));
		graphics.setStroke(new BasicStroke(2));
		graphics.draw(area);
    }
	
    private Polygon createPolygon(Line line)
    {
    	Polygon polygon = new Polygon();
		float angle1 = (float) (line.getAngle() + Math.PI/2);
		float angle2 = (float) (line.getAngle() - Math.PI/2);

		final int w = 5;
		
		int x, y;
		
		x = (int) (Math.cos(angle1) * w + line.getStart().getX());
		y = (int) (Math.sin(angle1) * w + line.getStart().getY());
		polygon.addPoint(x, y);
		
		x = (int) (Math.cos(angle2) * w + line.getStart().getX());
		y = (int) (Math.sin(angle2) * w + line.getStart().getY());
		polygon.addPoint(x, y);
		
		x = (int) (Math.cos(angle2) * w + line.getEnd().getX());
		y = (int) (Math.sin(angle2) * w + line.getEnd().getY());
		polygon.addPoint(x, y);
		
		x = (int) (Math.cos(angle1) * w + line.getEnd().getX());
		y = (int) (Math.sin(angle1) * w + line.getEnd().getY());
		polygon.addPoint(x, y);
		
		return polygon;		
    }

	@Override
	public void setMap(Map map)
	{
		this.map = map;
		updateUI();
	}
}
