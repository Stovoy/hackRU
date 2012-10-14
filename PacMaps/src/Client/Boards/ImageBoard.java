package Client.Boards;

import java.awt.Graphics;

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
        drawImage(g);
    }
    
    private void drawImage(Graphics g)
    {
        g.drawImage(map.getImage(), 0, 0, null);
    }

    @Override
	public void setMap(Map map)
	{
    	this.map = map;
	}
}
