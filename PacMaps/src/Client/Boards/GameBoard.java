package Client.Boards;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Area;
import java.util.ArrayList;
import java.util.Timer;

import Audio.Audio;
import Game.Game;
import Game.ImageData;
import Maps.Intersect;
import Maps.Line;
import Maps.Map;

public class GameBoard extends AbstractBoard implements MouseMotionListener
{
    private Map map;
    private Game game;
    private boolean running = false;
    private ArrayList<ActionListener> actionListeners;
    
    public GameBoard()
    {
    	this.addMouseMotionListener(this);
    	this.actionListeners = new ArrayList<ActionListener>();
    	game = new Game();
    }
    
    public void start()
    {
		Audio.playBeginning();
    	game = new Game();
    	for (ActionListener actionListener : actionListeners)
    		game.addActionListener(actionListener);
    	game.setGameBoard(this);
    	game.initialize();
    	new Timer().scheduleAtFixedRate(game, 0, 33);
    	running = true;
    }

	public void stop()
	{
		game.cancel();
		running = false;
	}

	@Override
	public void update()
	{
		updateUI();
	}
	
    @Override
    public void paintComponent(Graphics g) 
    {
    	if (map == null) return;
        super.paintComponent(g);
        drawImage(g);
        drawLines(g);
        if (running)
        	drawGame(g);
    }
    
    private void drawImage(Graphics g)
    {
        g.drawImage(map.getImage(), 0, 0, null);
    }

	private void drawLines(Graphics g)
    {
		Graphics2D graphics = (Graphics2D)g;
		ArrayList<Line> linesToAdd = new ArrayList<Line>();
		linesToAdd.add(map.getFirstLine());
    	Area area = createArea(null, linesToAdd, null);
    	g.setColor(Color.BLACK);
		graphics.fill(area);
		graphics.setStroke(new BasicStroke(1));
		g.setColor(new Color(0, 51, 255));
		graphics.setStroke(new BasicStroke(2));
		graphics.draw(area);
    }
	
	private void drawGame(Graphics g)
	{
		for (ImageData data: game.getImages())
			g.drawImage(data.image, data.point.getX(), data.point.getY(), null);
	}
    
    private Area createArea(Area area, ArrayList<Line> linesToProcess, ArrayList<Line> finishedLines)
    {
    	if (linesToProcess == null)
    		linesToProcess = new ArrayList<Line>();
    	if (finishedLines == null)
    		finishedLines = new ArrayList<Line>();
    	ArrayList<Line> newLinesToProcess = new ArrayList<Line>();
    	
    	if (area == null)
    	{
    		area = new Area();
    		area.add(new Area(createPolygon(linesToProcess.get(0))));
        	finishedLines.add(linesToProcess.get(0));
    		for (Intersect deepIntersect : linesToProcess.get(0).getIntersects())
    		{
    			Line deepLine = deepIntersect.getLine();
    			if (!finishedLines.contains(deepLine))
    				newLinesToProcess.add(deepLine);
    		}
    	}
    	
    	for (Line currentLine : linesToProcess)
    	{
    		if (finishedLines.contains(currentLine)) continue;
    		area.add(new Area(createPolygon(currentLine)));
			for (Intersect intersect : currentLine.getIntersects())
			{
				Line line = intersect.getLine();
				if (finishedLines.contains(line)) continue;
	    		area.add(new Area(createPolygon(line)));
	    		finishedLines.add(line);
	    		for (Intersect deepIntersect : line.getIntersects())
	    		{
	    			Line deepLine = deepIntersect.getLine();
	    			if (!finishedLines.contains(deepLine))
	    				newLinesToProcess.add(deepLine);
	    		}
			}
    	}
    	if (newLinesToProcess.size() == 0)
    		return area;
    	else
    		return createArea(area, newLinesToProcess, finishedLines);
    }
	
    private Polygon createPolygon(Line line)
    {
    	Polygon polygon = new Polygon();
		float angle1 = (float) (line.getAngle() + Math.PI/2);
		float angle2 = (float) (line.getAngle() - Math.PI/2);

		final int w = 5;
		
		int x, y;
		
		x = (int) (Math.cos(angle1) * w + line.getStart().getX());
		y = (int) (Math.sin(angle1) * w + line.getStart().getY());
		polygon.addPoint(x, y);
		
		x = (int) (Math.cos(angle2) * w + line.getStart().getX());
		y = (int) (Math.sin(angle2) * w + line.getStart().getY());
		polygon.addPoint(x, y);
		
		x = (int) (Math.cos(angle2) * w + line.getEnd().getX());
		y = (int) (Math.sin(angle2) * w + line.getEnd().getY());
		polygon.addPoint(x, y);
		
		x = (int) (Math.cos(angle1) * w + line.getEnd().getX());
		y = (int) (Math.sin(angle1) * w + line.getEnd().getY());
		polygon.addPoint(x, y);
		
		return polygon;		
    }

	public boolean isRunning()
	{
		return running;
	}
	
	@Override
	public void setMap(Map map)
	{
		this.map = map;
		updateUI();
	}
	
	public Map getMap()
	{
		return map;
	}

	@Override
	public void mouseDragged(MouseEvent e)
	{
		game.setMouseEvent(e);
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		game.setMouseEvent(null);
	}

	public void addActionListener(ActionListener actionListener)
	{
		actionListeners.add(actionListener);
	}
}
