package Client.Panels;

import java.awt.event.ActionListener;

import javax.swing.JPanel;

import Client.Client.State;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class ControlPanel extends JPanel
{
	private LabelPanel label;
	private ButtonPanel buttons;
	
	public ControlPanel()
	{
		setLayout(new FormLayout("f:0px:g", "f:50px:n, f:30px:n"));
		
		label = new LabelPanel();
		buttons = new ButtonPanel();
		
		add(label, CC.xy(1, 1));
		add(buttons, CC.xy(1, 2));
	}
	
	public void prepare(State state)
	{
		label.prepare(state);
		buttons.prepare(state);
	}
	
	public void done()
	{
		buttons.done();
	}
	
	public void addActionListener(ActionListener listener)
	{
		buttons.addActionListener(listener);
	}
}
