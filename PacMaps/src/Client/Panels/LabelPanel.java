package Client.Panels;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Client.Client.State;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class LabelPanel extends JPanel
{
	private JLabel label;
	public LabelPanel()
	{
		setLayout(new FormLayout("f:0px:g", "f:0px:g"));
		
		label = new JLabel();
		label.setHorizontalAlignment(JLabel.CENTER);
		label.setVerticalAlignment(JLabel.CENTER);
		
		add(label, CC.xy(1, 1));
		
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Description"));
	}
	
	public void prepare(State state)
	{
		switch (state)
		{
			case Selector:
				label.setText("Select your location! It will soon be inhabited by ghosts and yummy pellets.");
				return;
			case Editor:
				label.setText("Edit your map! Place lines according to the roads, or however you please!");
				return;
			case Game:
				label.setText("Play your map, but watch out! The ghosts are lonely and will do anything to get you to their dreary home.");
				return;
		}
	}
}
