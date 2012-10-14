package Client.Panels.CenterPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JButton;

import Client.Boards.GameBoard;
import Maps.Map;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;


public class GamePanel extends AbstractCenterPanel implements ActionListener
{
	private Map map;
	private GameBoard gameBoard;
	private JButton play;
	
	public GamePanel()
	{
		setLayout(new FormLayout("f:0px:g", "f:30px:n, f:0px:g"));
		gameBoard = new GameBoard();
		play = new JButton("Play");
		play.setActionCommand("play");
		play.addActionListener(this);
		add(play, CC.xy(1, 1));
		add(gameBoard, CC.xy(1, 2));
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

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("play"))
		{
			if (gameBoard.isRunning())
			{
				gameBoard.stop();
				play.setText("Play");
			}
			else
			{
				gameBoard.start();
				play.setText("End");
			}
		}
	}
}
