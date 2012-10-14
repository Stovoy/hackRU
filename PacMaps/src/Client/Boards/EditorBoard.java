package Client.Boards;

import java.awt.Graphics;
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
    	for (Line line : map.getLines())
    		g.drawLine(line.getStart().getX(), line.getStart().getY(), line.getEnd().getX(), line.getEnd().getY());
    	drawIntersections(g);
    }
    
    private void drawIntersections(Graphics g)
    {
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
        		g.drawOval(intersect.getX()-3, intersect.getY()-3, 6, 6);    			
    		}
    }

	@Override
	public void setMap(Map map)
	{
		this.map = map;
		updateUI();
	}
}
