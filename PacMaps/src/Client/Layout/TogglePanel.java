package Client.Layout;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import com.jgoodies.forms.factories.CC;
import com.jgoodies.forms.layout.CellConstraints;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.RowSpec;
import com.jgoodies.forms.layout.Sizes;

public class TogglePanel extends JPanel implements ActionListener
{
	private Component shrinking;
	private JScrollPane growing;
	private Position position;
	private FormLayout layout;
	private boolean shrunk;
	
	public TogglePanel(Component shrinking, Component growing, Position position, State state)
	{
		this.shrinking = shrinking;
		this.growing = new JScrollPane(growing);
		this.position = position;
		this.shrunk = true;	
		shrinking.setSize(0, 0);

		layout = new FormLayout();
		setLayout(layout);
		
		ToggleBar toggleBar = new ToggleBar(position);
		
		switch (position)
		{
			case Left:
				layout.appendRow(RowSpec.decode("f:0px:g"));
				layout.appendColumn(new ColumnSpec(ColumnSpec.FILL, Sizes.pixel(0), ColumnSpec.NO_GROW));
				layout.appendColumn(new ColumnSpec(ColumnSpec.FILL, Sizes.pixel(20), ColumnSpec.NO_GROW));
				layout.appendColumn(new ColumnSpec(ColumnSpec.FILL, Sizes.pixel(growing.getPreferredSize().width), ColumnSpec.DEFAULT_GROW));
				add(shrinking, CC.xy(1,  1));
				add(toggleBar, CC.xy(2,  1));
				add(this.growing, CC.xy(3, 1));
				break;
			case Right:
				layout.appendRow(RowSpec.decode("f:0px:g"));
				layout.appendColumn(new ColumnSpec(ColumnSpec.FILL, Sizes.pixel(growing.getPreferredSize().width), ColumnSpec.DEFAULT_GROW));
				layout.appendColumn(new ColumnSpec(ColumnSpec.FILL, Sizes.pixel(20), ColumnSpec.NO_GROW));
				layout.appendColumn(new ColumnSpec(ColumnSpec.FILL, Sizes.pixel(0), ColumnSpec.NO_GROW));
				add(this.growing, CC.xy(1, 1));
				add(toggleBar, CC.xy(2,  1));
				add(shrinking, CC.xy(3,  1));
				break;
			case Top:
				layout.appendColumn(ColumnSpec.decode("f:0px:g"));
				layout.appendRow(new RowSpec(RowSpec.FILL, Sizes.pixel(0), RowSpec.NO_GROW));
				layout.appendRow(new RowSpec(RowSpec.FILL, Sizes.pixel(20), RowSpec.NO_GROW));
				layout.appendRow(new RowSpec(RowSpec.FILL, Sizes.pixel(growing.getPreferredSize().width), RowSpec.DEFAULT_GROW));
				add(shrinking, CC.xy(1,  1));
				add(toggleBar, CC.xy(1,  2));
				add(this.growing, CC.xy(1, 3));
				break;
			case Bottom:
				layout.appendColumn(ColumnSpec.decode("f:0px:g"));
				layout.appendRow(new RowSpec(RowSpec.FILL, Sizes.pixel(growing.getPreferredSize().width), RowSpec.DEFAULT_GROW));
				layout.appendRow(new RowSpec(RowSpec.FILL, Sizes.pixel(20), RowSpec.NO_GROW));
				layout.appendRow(new RowSpec(RowSpec.FILL, Sizes.pixel(0), RowSpec.NO_GROW));
				add(this.growing, CC.xy(1, 1));
				add(toggleBar, CC.xy(1,  2));
				add(shrinking, CC.xy(1,  3));
				break;
			default:
				break;
		}
		toggleBar.addActionListener(this);
		if (state == State.Opened)
		{
			toggle(1);
			toggleBar.reverseText();
		}
	}
	
	public void setGrowing(Component newGrowing)
	{
		CellConstraints constraints = layout.getConstraints(growing);
		remove(growing);
		this.growing = new JScrollPane(newGrowing);
		add(growing, constraints);
	}

	@Override
	public void actionPerformed(ActionEvent e) 
	{
		if (e.getActionCommand().equals("toggle"))
		{
			toggle(20);
		}
	}
	
	private void toggle(int speed)
	{
		switch (position)
		{
			case Left:
				animate(ColumnSpec.class, 1, 20);
				break;
			case Right:
				animate(ColumnSpec.class, 3, 20);
				break;
			case Top:
				animate(RowSpec.class, 1, 20);
				break;
			case Bottom:
				animate(RowSpec.class, 3, 20);
				break;
			default:
				break;
		}
		shrunk = !shrunk;
		shrinking.setSize(shrunk ? new Dimension(0, 0) : shrinking.getPreferredSize());
	}
	
	private void animate(final Class<?> clazz, final int index, final int speed)
	{
		Dimension startDimensions = shrunk ? new Dimension(0, 0) : shrinking.getPreferredSize();
		Dimension endDimensions = shrunk ? shrinking.getPreferredSize() : new Dimension(0, 0);

		final int start, end;
		if (position == Position.Left || position == Position.Right)
		{
			start = startDimensions.width;
			end = endDimensions.width;
		}
		else
		{
			start = startDimensions.height;
			end = endDimensions.height;
		}
		
		final JComponent component = this;
		Timer timer = new Timer();
		timer.scheduleAtFixedRate(new TimerTask() 
		{
			int max = speed;
			int count = 0;
			float rate = (end-start)/max;
	        public void run() 
	        {
	        	if (++count == max)
	        		this.cancel();
	        	int value = (int) (rate*count + start);
	        	if (clazz == ColumnSpec.class)
	    			layout.setColumnSpec(index, new ColumnSpec(ColumnSpec.FILL, Sizes.pixel(value), ColumnSpec.NO_GROW));
	    		else
	    			layout.setRowSpec(index, new RowSpec(RowSpec.FILL, Sizes.pixel(value), RowSpec.NO_GROW));
	        	component.updateUI();
	        }
		}, 0, 5);
	}
	
	public enum Position
	{
		Left,
		Right,
		Top,
		Bottom
	}
	
	public enum State
	{
		Opened,
		Closed
	}
}
