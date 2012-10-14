package Client.Panels.CenterPanels;

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
}
