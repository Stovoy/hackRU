package Client.GUI.Panels;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class SelectionPanel extends CenterPanel implements ActionListener
{
	private JTextField selectionText;
	private JPanel image;
	
	public SelectionPanel()
	{
		setSize(600, 650);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Selection"));
		setLayout(new FormLayout("f:0px:g", "f:30px:n, f:20px:n, f:600px:n"));
		selectionText = new JTextField();
		selectionText.addActionListener(this);
		image = new JPanel();
		image.setBorder(BorderFactory.createLineBorder(Color.WHITE));
		image.setBackground(Color.RED);
		
		add(selectionText, CC.xy(1, 1));
		add(image, CC.xy(1, 3));
	}

	@Override
	public void cleanup()
	{
		// TODO Auto-generated method stub
	}

	@Override
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == selectionText)
		{
			selectionText.getText();
		}
	}
}
