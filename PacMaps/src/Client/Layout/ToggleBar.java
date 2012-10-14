package Client.Layout;

import java.awt.Font;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;

public class ToggleBar extends JPanel implements ActionListener
{
	private JButton toggleButton;
	
	public ToggleBar(TogglePanel.Position position)
	{
		FormLayout layout = new FormLayout();
		setBorder(BorderFactory.createEtchedBorder());
		setLayout(layout);
		
		String text = "";
		switch (position)
		{
			case Left:
				text = ">";
				break;
			case Right:
				text = "<";
				break;
			case Top:
				text = "v";
				break;
			case Bottom:
				text = "^";
				break;
			default:
				break;
		}
		toggleButton = new JButton(text);
		toggleButton.setFocusPainted(false);
		toggleButton.setMargin(new Insets(0, 0, 0, 0));
		toggleButton.setFont(new Font("serif", Font.PLAIN, 10));
		toggleButton.setActionCommand("toggle");
		toggleButton.addActionListener(this);
		
		switch (position)
		{
			case Left:
			case Right:
				layout.appendRow(RowSpec.decode("f:40px:g"));
				layout.appendColumn(ColumnSpec.decode("f:15px:n"));
				break;
			case Top:
			case Bottom:
				layout.appendColumn(ColumnSpec.decode("f:40px:g"));
				layout.appendRow(RowSpec.decode("f:15px:n"));
				break;
			default:
				break;			
		}
		add(toggleButton, CC.xy(1, 1));
		
	}
	
	public void addActionListener(ActionListener listener)
	{
		toggleButton.addActionListener(listener);
	}
	
	public void reverseText()
	{
		char text = toggleButton.getText().charAt(0);
		switch (text)
		{
			case '<':
				text = '>';
				break;
			case '>':
				text = '<';
				break;
			case '^':
				text = 'v';
				break;
			case 'v':
				text = '^';
				break;
			default:
				break;
		}
		toggleButton.setText(text + "");
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("toggle"))
			reverseText();
	}
	
	public enum Orientation
	{
		Horizontal,
		Vertical
	}
}
