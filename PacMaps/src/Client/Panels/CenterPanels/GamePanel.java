package Client.Panels.CenterPanels;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import Maps.Map;


public class GamePanel extends AbstractCenterPanel
{
	private Map map;
	
	public GamePanel()
	{
		setSize(800, 600);
	}

	@Override
	public void setMap(Map map)
	{
		this.map = map;
	}

	@Override
	public Map getMap()
	{
		return map;
	}

	@Override
	public void addActionListener(ActionListener listener) { }

	@Override
	public boolean isDone()
	{
		return false;
	}

	@Override
	public void sendKeyPressed(KeyEvent e)
	{		
	}
}
