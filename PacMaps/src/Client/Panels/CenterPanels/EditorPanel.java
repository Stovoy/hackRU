package Client.Panels.CenterPanels;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Client.Boards.EditorBoard;
import Maps.Map;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;


public class EditorPanel extends AbstractCenterPanel implements MouseListener, MouseMotionListener
{
	private Map map;
	private EditorBoard editorBoard;
	
	public EditorPanel()
	{
		setLayout(new FormLayout("f:0px:g", "f:0px:g"));
		editorBoard = new EditorBoard();
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
		map.addLine(e.getX(), e.getY(), e.getX()+10, e.getY());
		editorBoard.update();
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
	}

	@Override
	public void mouseReleased(MouseEvent e)
	{
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
	}
}
