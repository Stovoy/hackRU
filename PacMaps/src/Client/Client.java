package Client;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.HashMap;

import javax.swing.JApplet;
import javax.swing.JFrame;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Client.Layout.TogglePanel;
import Client.Panels.ControlPanel;
import Client.Panels.CenterPanels.AbstractCenterPanel;
import Client.Panels.CenterPanels.EditorPanel;
import Client.Panels.CenterPanels.GamePanel;
import Client.Panels.CenterPanels.SelectionPanel;
import Maps.Map;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;

public class Client extends JApplet implements ActionListener, KeyListener
{
	private ControlPanel controlPanel;
	
	private State state;
	
	private HashMap<State, AbstractCenterPanel> stateToCenterPanel;
	
	private TogglePanel panel;
	
	public static void main(String args[]) throws UnsupportedLookAndFeelException
	{
	    Component applet = new Client();
	    JFrame frame = new JFrame("PacMaps");
	    frame.getContentPane().add(applet);
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.pack();
	    frame.setSize(620, 860);
	    frame.setResizable(false);
	}
	
	public Client() throws UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
		setLayout(new FormLayout("f:0px:g", "f:0px:g"));
		getContentPane().setBackground(new Color(244, 244, 244));

		controlPanel = new ControlPanel();
		stateToCenterPanel = new HashMap<State, AbstractCenterPanel>();

		initializePanels();

		prepare(State.Selector);

		addKeyListenersToAllChildren(this);
		controlPanel.addActionListener(this);
	}
	
	private void initializePanels()
	{
		stateToCenterPanel.put(State.Selector, new SelectionPanel());
		stateToCenterPanel.put(State.Editor, new EditorPanel());
		stateToCenterPanel.put(State.Game, new GamePanel());
	}
	
	private void prepare(State newState)
	{
		AbstractCenterPanel centerPanel = getCenterPanel(newState);
		if (state != null) panel.setGrowing(centerPanel);
		else
		{
			panel = new TogglePanel(controlPanel, centerPanel, TogglePanel.Position.Top, TogglePanel.State.Opened);
			add(panel, CC.xy(1, 1));
		}
		centerPanel.addActionListener(this);
		state = newState;
		controlPanel.prepare(state);
		controlPanel.setDone(centerPanel.isDone());
	}
	
	private AbstractCenterPanel getCenterPanel(State state)
	{
		return stateToCenterPanel.get(state);
	}
	
	private AbstractCenterPanel getCurrentCenterPanel()
	{
		return getCenterPanel(state);
	}
	
	private void setDone(boolean done)
	{
		controlPanel.setDone(done);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("previous"))
		{
			State newState = null;
			if (state == State.Game) newState = State.Editor;
			else if (state == State.Editor)
			{
				Map map = getCurrentCenterPanel().getMap();
				map.clearLines();
				newState = State.Selector;
			}
			prepare(newState);
		}
		else if (e.getActionCommand().equals("next"))
		{
			State newState = null;
			if (state == State.Selector) newState = State.Editor;
			else if (state == State.Editor) newState = State.Game;
			Map map = getCurrentCenterPanel().getMap();
			prepare(newState);
			getCurrentCenterPanel().setMap(map);
		}
		else if (e.getActionCommand().equals("done"))
		{
			setDone(true);
		}
		else if (e.getActionCommand().equals("!done"))
		{
			setDone(false);
		}
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		getCenterPanel(state).sendKeyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}
	
	public void addKeyListenersToAllChildren(Component component)
	{
		if (component instanceof Container)
		{
			Container container = (Container)component;
			for (Component child : container.getComponents())
				addKeyListenersToAllChildren(child);
		}
		component.addKeyListener(this);
	}
	
	public enum State
	{
		Selector,
		Editor,
		Game
	}
}
