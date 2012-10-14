package Client.Panels.CenterPanels;

import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import Client.Boards.GameBoard;
import Maps.Map;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;


public class GamePanel extends AbstractCenterPanel
{
	private Map map;
	private GameBoard gameBoard;
	
	public GamePanel()
	{
		setLayout(new FormLayout("f:0px:g", "f:0px:g"));
		gameBoard = new GameBoard();
		add(gameBoard, CC.xy(1, 1));
	}

	@Override
	public void setMap(Map map)
	{
		this.map = map;
		gameBoard.setMap(map);
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
