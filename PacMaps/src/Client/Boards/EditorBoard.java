package Client.Boards;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.util.ArrayList;
import java.util.HashMap;

import Maps.Line;
import Maps.Map;
import Maps.Point;

public class EditorBoard extends AbstractBoard
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
    
    private void drawLines(Graphics g)
    {
		Graphics2D graphics = (Graphics2D)g;
    	for (Line line : map.getLines())
    	{
    		if (line.equals(map.getFirstLine()))
    			g.setColor(Color.GREEN);
    		else
        		g.setColor(Color.BLACK);
    		Polygon polygon = createPolygon(line);
    		graphics.fillPolygon(polygon);
    		g.setColor(Color.WHITE);
    		graphics.drawPolygon(polygon);
    	}
    	calculateIntersections(g);
    }
    
    private Polygon createPolygon(Line line)
    {
    	Polygon polygon = new Polygon();
		float angle1 = (float) (line.getAngle() + Math.PI/2);
		float angle2 = (float) (line.getAngle() - Math.PI/2);

		final int w = 5;
		
		int x, y;
		
		x = (int) (Math.cos(angle1)*w + line.getStart().getX());
		y = (int) (Math.sin(angle1)*w + line.getStart().getY());
		polygon.addPoint(x, y);
		
		x = (int) (Math.cos(angle2)*w + line.getStart().getX());
		y = (int) (Math.sin(angle2)*w + line.getStart().getY());
		polygon.addPoint(x, y);
		
		x = (int) (Math.cos(angle2)*w + line.getEnd().getX());
		y = (int) (Math.sin(angle2)*w + line.getEnd().getY());
		polygon.addPoint(x, y);
		
		x = (int) (Math.cos(angle1)*w + line.getEnd().getX());
		y = (int) (Math.sin(angle1)*w + line.getEnd().getY());
		polygon.addPoint(x, y);
		
		return polygon;		
    }
    
    private void calculateIntersections(Graphics g)
    {
    	map.clearIntersects();
    	if (map.getLines().length < 2) return;
    	HashMap<Line, ArrayList<Line>> calculated = new HashMap<Line, ArrayList<Line>>();
    	for (Line first : map.getLines())
    		for (Line second : map.getLines())
    		{
    			if (first.equals(second)) continue;
    			
    			if (calculated.get(second) != null && calculated.get(second).contains(first)) continue;

    			ArrayList<Line> lines;
    			lines = calculated.get(first);
    			if (lines == null)
				{
					lines = new ArrayList<Line>();
					calculated.put(first, lines);
				}
    			lines.add(second);
    			lines = calculated.get(second);
    			if (lines == null)
				{
					lines = new ArrayList<Line>();
					calculated.put(second, lines);
				}
    			lines.add(first);
    			
    			Point intersect;
    			if (Math.abs(first.getAngle()) - Math.PI/2 < Math.PI/4)
    				intersect = second.intersects(first);
    			else
    				intersect = first.intersects(second);
    			if (intersect == null) continue;
    			map.addIntersect(intersect, first, second); 			
    		}
    }

	@Override
	public void setMap(Map map)
	{
		this.map = map;
		updateUI();
	}
}
