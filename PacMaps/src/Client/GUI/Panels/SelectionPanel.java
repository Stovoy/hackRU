package Client.GUI.Panels;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import Maps.GoogleMapsAPI;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class SelectionPanel extends CenterPanel implements KeyListener
{
	private JTextField selectionText;
	private ImagePanel imagePanel;
	
	public SelectionPanel()
	{
		setSize(600, 650);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Selection"));
		setLayout(new FormLayout("f:0px:g", "f:30px:n, f:20px:n, f:600px:n"));
		
		selectionText = new JTextField();
		selectionText.addKeyListener(this);
		
		imagePanel = new ImagePanel();
		imagePanel.setBorder(BorderFactory.createLineBorder(Color.WHITE));

		addKeyListener(this);
		
		add(selectionText, CC.xy(1, 1));
		add(imagePanel, CC.xy(1, 3));
	}

	@Override
	public void cleanup()
	{
		// TODO Auto-generated method stub
	}
	
	private void imageUpdate(String text)
	{
		Image image = GoogleMapsAPI.getImage(text);
		if (image == null)
			return;
		imagePanel.setImage(image);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			imageUpdate(selectionText.getText());
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}
}
