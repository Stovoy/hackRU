package Client.Panels.CenterPanels;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Stack;

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
	
	private Stack<Line> lineHistory;
	
	private boolean isDone = false;
	private boolean isDragging = false;
	
	public EditorPanel()
	{
		setLayout(new FormLayout("f:0px:g", "f:0px:g"));
		
		editorBoard = new EditorBoard();
		
		lineHistory = new Stack<Line>();
		
		add(editorBoard, CC.xy(1, 1));
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
		if (e.getY() > 600 || e.getX() > 600) return;
		isDragging = true;
		line = new Line(new Point(e.getX(), e.getY()), new Point(e.getX(), e.getY()));
		lineHistory.push(line);
		map.addLine(line);
		editorBoard.update();
		if (map.getLines().length > 1)
		{
			fireAction(new ActionEvent(this, 1, "done"));
			isDone = true;
		}
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		if (!isDragging) return;
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

	@Override
	public void addActionListener(ActionListener listener)
	{
		if (actionListeners == null) actionListeners = new ArrayList<ActionListener>();
		actionListeners.add(listener);
	}
	
	protected void fireAction(ActionEvent e)
	{
		for (ActionListener listener : actionListeners)
			listener.actionPerformed(e);
	}

	@Override
	public boolean isDone()
	{
		return isDone;
	}

	@Override
	public void sendKeyPressed(KeyEvent e)
	{
		if (e.getKeyCode() == KeyEvent.VK_ESCAPE)
		{
			if (lineHistory.size() == 0) return;
			map.removeLine(lineHistory.pop());
			editorBoard.update();
			if (lineHistory.size() <= 1) fireAction(new ActionEvent(this, 1, "!done"));		
		}
	}
}
