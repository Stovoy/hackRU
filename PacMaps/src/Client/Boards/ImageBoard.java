package Client.Boards;

import java.awt.Graphics;
import java.awt.Image;

import Maps.Map;

public class ImageBoard extends AbstractBoard
{
    private Map map;
    
    @Override
    public void update()
    {
    	repaint();
    }
    
    @Override
    public void paintComponent(Graphics g) 
    {
    	if (map == null) return;
        super.paintComponent(g);
        int xOffset = (getWidth()-600)/2;
        if (xOffset < 0) xOffset = 0;
        int yOffset = (getHeight()-600)/2;
        if (yOffset < 0) yOffset = 0;
        drawImage(g, xOffset, yOffset);
    }
    
    private void drawImage(Graphics g, int xOffset, int yOffset)
    {
        g.drawImage(map.getImage(), xOffset, yOffset, null);
    }
    
    private void drawLines(Graphics g, int xOffset, int yOffset)
    {
    	
    }

    @Override
	public void setMap(Map map)
	{
    	this.map = map;
	}
}
