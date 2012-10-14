package Client;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;

import javax.swing.JApplet;
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

public class Client extends JApplet implements ActionListener
{
	private ControlPanel controlPanel;
	
	private State state;
	
	private HashMap<State, AbstractCenterPanel> stateToCenterPanel;
	
	private TogglePanel panel;
	
	public Client() throws UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
		setLayout(new FormLayout("f:0px:g", "f:0px:g"));
		getContentPane().setBackground(new Color(244, 244, 244));

		controlPanel = new ControlPanel();
		stateToCenterPanel = new HashMap<State, AbstractCenterPanel>();

		initializePanels();

		prepare(State.Selector);
		
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
		if (state != null) panel.setGrowing(getCenterPanel(newState));
		else
		{
			panel = new TogglePanel(controlPanel, getCenterPanel(newState), TogglePanel.Position.Top, TogglePanel.State.Opened);
			add(panel, CC.xy(1, 1));
		}
		state = newState;
		controlPanel.prepare(state);
	}
	
	private AbstractCenterPanel getCenterPanel(State state)
	{
		return stateToCenterPanel.get(state);
	}
	
	private AbstractCenterPanel getCurrentCenterPanel()
	{
		return getCenterPanel(state);
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getActionCommand().equals("previous"))
		{
			State newState = null;
			if (state == State.Game) newState = State.Editor;
			else if (state == State.Editor) newState = State.Selector;
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
	}
	
	public enum State
	{
		Selector,
		Editor,
		Game
	}
}
