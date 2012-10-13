package Client.GUI.Panels.CenterPanels;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.BorderFactory;
import javax.swing.JProgressBar;
import javax.swing.JTextField;

import Client.GUI.Panels.ImagePanel;
import Maps.GoogleMapsAPI;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class SelectionPanel extends CenterPanel implements KeyListener, MouseWheelListener
{
	private JTextField selectionText;
	private ImagePanel imagePanel;
	private JProgressBar workingBar;
	private int zoom = 13;
	
	public SelectionPanel()
	{
		setSize(600, 650);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Selection"));
		setLayout(new FormLayout("f:0px:g", "f:30px:n, f:20px:n, f:600px:n"));
		
		selectionText = new JTextField();
		selectionText.addKeyListener(this);
		addMouseWheelListener(this);
		
		imagePanel = new ImagePanel();
		imagePanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		addKeyListener(this);
		
		add(selectionText, CC.xy(1, 1));
		add(imagePanel, CC.xy(1, 3));
	}

	@Override
	public void cleanup()
	{
		// TODO Auto-generated method stub
	}
	
	private void imageUpdate()
	{
		Image image = GoogleMapsAPI.getImage(selectionText.getText(), zoom);
		if (image == null)
			return;
		imagePanel.setImage(image);
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			imageUpdate();
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e)
	{
		int notches = e.getWheelRotation();
		zoom -= notches;
		imageUpdate();
	}
}
