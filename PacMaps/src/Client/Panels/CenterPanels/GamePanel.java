package Client.Panels.CenterPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import Client.Boards.GameBoard;
import Maps.Map;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;


public class GamePanel extends AbstractCenterPanel implements ActionListener
{
	private Map map;
	private GameBoard gameBoard;
	private JButton play;
	private JTextField scoreText;
	private int score = 0;
	
	public GamePanel()
	{
		setLayout(new FormLayout("f:0px:g", "f:30px:n, f:0px:g, f:50px:n"));
		gameBoard = new GameBoard();
		gameBoard.addActionListener(this);
		play = new JButton("Play");
		play.setActionCommand("play");
		play.addActionListener(this);
		scoreText = new JTextField();
		scoreText.setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Score"));
		scoreText.setText(Integer.toString(score));
		scoreText.setEditable(false);
		scoreText.setHorizontalAlignment(JTextField.HORIZONTAL);
		add(play, CC.xy(1, 1));
		add(gameBoard, CC.xy(1, 2));
		add(scoreText, CC.xy(1, 3));
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
			score = 0;
			scoreText.setText("0");
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
		else if (e.getActionCommand().equals("score"))
		{
			score += 10;
			scoreText.setText(Integer.toString(score));
		}
		else if (e.getActionCommand().equals("win"))
		{
			gameBoard.stop();
			play.setText("Play");
			JOptionPane.showMessageDialog(this, "You win! Go away now.\nScore: " + score, "Congratulations!", JOptionPane.INFORMATION_MESSAGE);
		}
		else if (e.getActionCommand().equals("lose"))
		{
			gameBoard.stop();
			play.setText("Play");
			JOptionPane.showMessageDialog(this, "You Lose! You are a failure. Apologize for playing this game.\nScore: " + score, "...Wow. Just wow.", JOptionPane.INFORMATION_MESSAGE);
		}
	}
}
