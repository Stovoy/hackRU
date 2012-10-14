package Client.Panels.CenterPanels;

import javax.swing.JPanel;

import Maps.Map;

public abstract class AbstractCenterPanel extends JPanel
{
	public abstract void setMap(Map map);
	public abstract Map getMap();
}
