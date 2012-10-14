package Client.Panels.CenterPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;

import Client.Boards.EditorBoard;
import Maps.Line;
import Maps.Map;
import Maps.Point;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;


public class EditorPanel extends AbstractCenterPanel implements MouseListener, MouseMotionListener
{
	private Map map;
	private EditorBoard editorBoard;
	private Line line = null;
	private ArrayList<ActionListener> actionListeners;
	
	public EditorPanel()
	{
		setLayout(new FormLayout("f:0px:g", "f:0px:g"));
		editorBoard = new EditorBoard();
		add(editorBoard, CC.xy(1, 1));
		actionListeners = new ArrayList<ActionListener>();
	}

	@Override
	public void setMap(Map map)
	{
		this.map = map;
		editorBoard.setMap(map);
		editorBoard.addMouseListener(this);
		editorBoard.addMouseMotionListener(this);
	}

	@Override
	public Map getMap()
	{
		return map;
	}

	@Override
	public void mouseClicked(MouseEvent e)
	{
	}

	@Override
	public void mouseEntered(MouseEvent e)
	{
	}

	@Override
	public void mouseExited(MouseEvent e)
	{
	}

	@Override
	public void mousePressed(MouseEvent e)
	{
		line = new Line(new Point(e.getX(), e.getY()), new Point(e.getX(), e.getY()));
		map.addLine(line);
		editorBoard.update();
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		int x, y;
		x = Math.max(Math.min(e.getX(), 600), 0);
		y = Math.max(Math.min(e.getY(), 600), 0);
		line.setEnd(new Point(x, y));
		editorBoard.update();
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
	}

	protected void addActionListener(ActionListener listener)
	{
		actionListeners.add(listener);
	}
	
	protected void fireAction(ActionEvent e)
	{
		for (ActionListener listener : actionListeners)
			listener.actionPerformed(e);
	}
}
