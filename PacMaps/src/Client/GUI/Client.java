package Client.GUI;

import java.util.HashMap;

import javax.swing.JApplet;

import Client.GUI.Panels.ButtonPanel;
import Client.GUI.Panels.CenterPanel;
import Client.GUI.Panels.EditorPanel;
import Client.GUI.Panels.GamePanel;
import Client.GUI.Panels.LabelPanel;
import Client.GUI.Panels.SelectionPanel;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class Client extends JApplet
{
	private LabelPanel label;
	private ButtonPanel buttons;
	
	private State state;
	
	private HashMap<State, CenterPanel> stateToCenterPanel;
	
	public Client()
	{
		setSize(800, 800);
		setLayout(new FormLayout("f:20px:n, f:600px:n, f:20px:n", "f:15px:n, f:50px:n, f:20px:n, f:650px:n, f:20px:n, f:30px:n, f:15px:n"));
		
		label = new LabelPanel();
		buttons = new ButtonPanel();
		
		add(label, CC.xyw(1, 2, 3));
		add(buttons, CC.xyw(1, 6, 3));
		
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
		state = newState;
		label.prepare(state);
		add(getCurrentCenterPanel(), CC.xy(2, 4));
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
