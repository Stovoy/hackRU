package Client.GUI;

import java.awt.Color;
import java.util.HashMap;

import javax.swing.JApplet;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import Client.GUI.Layout.TogglePanel;
import Client.GUI.Panels.ControlPanel;
import Client.GUI.Panels.CenterPanels.CenterPanel;
import Client.GUI.Panels.CenterPanels.EditorPanel;
import Client.GUI.Panels.CenterPanels.GamePanel;
import Client.GUI.Panels.CenterPanels.SelectionPanel;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.looks.plastic.Plastic3DLookAndFeel;

public class Client extends JApplet
{
	private ControlPanel controlPanel;
	
	private State state;
	
	private HashMap<State, CenterPanel> stateToCenterPanel;
	
	private TogglePanel panel;
	
	public Client() throws UnsupportedLookAndFeelException
	{
		UIManager.setLookAndFeel(new Plastic3DLookAndFeel());
		setLayout(new FormLayout("f:0px:g", "f:0px:g"));
		getContentPane().setBackground(new Color(244, 244, 244));

		controlPanel = new ControlPanel();
		stateToCenterPanel = new HashMap<State, CenterPanel>();

		initializePanels();

		prepare(State.Selection);
	}
	
	private void initializePanels()
	{
		stateToCenterPanel.put(State.Selection, new SelectionPanel());
		stateToCenterPanel.put(State.Editor, new EditorPanel());
		stateToCenterPanel.put(State.Game, new GamePanel());
	}
	
	private void prepare(State newState)
	{
		if (state != null) cleanup(state);
		else
		{
			panel = new TogglePanel(controlPanel, getCenterPanel(newState), TogglePanel.Position.Top, TogglePanel.State.Opened);
			add(panel, CC.xy(1, 1));
		}
		state = newState;
		controlPanel.prepare(state);

		CenterPanel current = getCurrentCenterPanel();
		if (panel.getGrowing() != current)
			panel.setGrowing(current);
	}
	
	private void cleanup(State state)
	{
		getCenterPanel(state).cleanup();
	}
	
	private CenterPanel getCenterPanel(State state)
	{
		return stateToCenterPanel.get(state);
	}
	
	private CenterPanel getCurrentCenterPanel()
	{
		return getCenterPanel(state);
	}
	
	public enum State
	{
		Selection,
		Editor,
		Game
	}
}
