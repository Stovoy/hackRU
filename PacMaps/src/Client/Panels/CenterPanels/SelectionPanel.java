package Client.Panels.CenterPanels;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JTextField;

import API.GoogleMaps;
import API.JSONReaderApi;
import Client.Boards.ImageBoard;
import Maps.Map;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;

public class SelectionPanel extends AbstractCenterPanel
{
	private JTextField selectionText;
	private ImageBoard imageBoard;
	
	private ArrayList<ActionListener> actionListeners;
	
	private Map map;
	
	private boolean isDone = false;
	
	private JTextField populationData;
	
	public SelectionPanel()
	{
		setSize(600, 650);
		setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), "Selection"));
		setLayout(new FormLayout("f:0px:g", "f:30px:n, f:20px:n, f:600px:n, f:50px:n"));
		
		selectionText = new JTextField();
		selectionText.setHorizontalAlignment(JTextField.HORIZONTAL);
		
		imageBoard = new ImageBoard();
		imageBoard.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		
		populationData = new JTextField();
		populationData.setHorizontalAlignment(JTextField.HORIZONTAL);
		populationData.setEditable(false);
		
		add(selectionText, CC.xy(1, 1));
		add(imageBoard, CC.xy(1, 3));
		add(populationData, CC.xy(1, 4));
		
		map = new Map();
		imageBoard.setMap(map);
	}
	
	public boolean isDone()
	{
		return isDone;
	}
	
	private void imageUpdate()
	{
		populationData.setText(JSONReaderApi.getCensusData(selectionText.getText()));
		Image image = GoogleMaps.getImage(selectionText.getText());
		if (image == null) return;
		fireAction(new ActionEvent(this, 1, "done"));
		isDone = true;
		map.clearLines();
		map.setImage(image);
		imageBoard.update();
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

	@Override
	public void addActionListener(ActionListener listener)
	{
		if (actionListeners == null) actionListeners = new ArrayList<ActionListener>();
		actionListeners.add(listener);		
	}

	protected void fireAction(ActionEvent e)
	{
		if (actionListeners == null)
			return;
		for (ActionListener listener : actionListeners)
			listener.actionPerformed(e);
	}

	@Override
	public void sendKeyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ENTER)
			imageUpdate();
	}
}
