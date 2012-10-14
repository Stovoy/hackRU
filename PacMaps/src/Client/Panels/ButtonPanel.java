package Client.Panels;

import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;

import Client.Client.State;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class ButtonPanel extends JPanel
{
	private JButton previous, next;
	
	public ButtonPanel()
	{
		setLayout(new FormLayout("f:100px:n, f:0px:g, f:100px:n", "f:0px:g"));
		
		previous = new JButton();
		previous.setActionCommand("previous");
		next = new JButton();
		next.setActionCommand("next");
		
		add(previous, CC.xy(1, 1));
		add(next, CC.xy(3, 1));
	}
	
	public void prepare(State state)
	{
		switch (state)
		{
			case Selector:
				previous.setVisible(false);
				next.setVisible(true);
				next.setEnabled(false);
				next.setText("Edit Map");
				return;
			case Editor:
				previous.setVisible(true);
				previous.setText("Select Map");
				next.setVisible(true);
				next.setEnabled(false);
				next.setText("Play Map");
				return;
			case Game:
				previous.setVisible(true);
				previous.setText("Edit Map");
				next.setVisible(false);
				return;
		}		
	}
	
	public void setDone(boolean done)
	{
		next.setEnabled(done);
	}
	
	public void addActionListener(ActionListener listener)
	{
		previous.addActionListener(listener);
		next.addActionListener(listener);
	}
}
