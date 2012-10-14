package Client.Boards;

import java.awt.Graphics;
import java.awt.Image;

import Maps.Map;

public class ImageBoard extends AbstractBoard
{
    private Image image;
    
    public void setImage(Image image)
    {
    	this.image = image;
    }

    @Override
    public void paintComponent(Graphics g) 
    {
        super.paintComponent(g);
        int widthDifference = (getWidth()-600)/2;
        if (widthDifference < 0) widthDifference = 0;
        int heightDifference = (getHeight()-600)/2;
        if (heightDifference < 0) heightDifference = 0;
        g.drawImage(image, widthDifference, heightDifference, null);
    }

    @Override
	public void setMap(Map map)
	{
		image = map.getImage();
		updateUI();
	}
}
