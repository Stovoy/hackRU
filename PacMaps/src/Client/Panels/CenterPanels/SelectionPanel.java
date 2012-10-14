package Client.Panels.CenterPanels;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import API.GoogleMaps;
import Client.Boards.ImageBoard;
import Maps.Map;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class SelectionPanel extends AbstractCenterPanel implements KeyListener, MouseWheelListener
{
	private JTextField selectionText;
	private ImageBoard imageBoard;
	private int zoom = 13;
	
	private Map map;
	
	public SelectionPanel()
	{
		setSize(600, 650);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Selection"));
		setLayout(new FormLayout("f:0px:g", "f:30px:n, f:20px:n, f:600px:n"));
		
		selectionText = new JTextField();
		selectionText.addKeyListener(this);
		addMouseWheelListener(this);
		
		imageBoard = new ImageBoard();
		imageBoard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

		addKeyListener(this);
		
		add(selectionText, CC.xy(1, 1));
		add(imageBoard, CC.xy(1, 3));
		
		map = new Map();
		imageBoard.setMap(map);
	}
	
	private void imageUpdate()
	{
		Image image = GoogleMaps.getImage(selectionText.getText(), zoom);
		if (image == null) return;
		map.setImage(image);
		imageBoard.update();
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

	@Override
	public void setMap(Map map)
	{
		this.map = map;
	}

	@Override
	public Map getMap()
	{
		return map;
	}
}
