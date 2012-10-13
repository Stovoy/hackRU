package Client.GUI.Panels;

import javax.swing.JButton;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class ButtonPanel extends JPanel
{
	private JButton previous, next;
	public ButtonPanel()
	{
		setLayout(new FormLayout("f:100px:n, f:0px:g, f:100px:n", "f:0px:g"));
		previous = new JButton("Previous");
		next = new JButton("Next");
		add(previous, CC.xy(1, 1));
		add(next, CC.xy(3, 1));
	}
}
