package Client.Panels.CenterPanels;

import java.awt.event.ActionEvent;

import javax.swing.JPanel;

import Maps.Map;

public abstract class AbstractCenterPanel extends JPanel
{
	public abstract void setMap(Map map);
	public abstract Map getMap();
}
