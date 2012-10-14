package Client.Panels.CenterPanels;

import Client.Boards.EditorBoard;
import Maps.Map;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.FormLayout;


public class EditorPanel extends AbstractCenterPanel
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
	}

	@Override
	public Map getMap()
	{
		return map;
	}
}
