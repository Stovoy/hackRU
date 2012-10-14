package Client.Panels.CenterPanels;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

import Maps.Map;

public abstract class AbstractCenterPanel extends JPanel
{
	public abstract void setMap(Map map);
	public abstract Map getMap();
	public abstract void addActionListener(ActionListener listener);
	public abstract boolean isDone();
	public abstract void sendKeyPressed(KeyEvent e);
}
