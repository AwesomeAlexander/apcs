package graphics.plainpanel;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.util.ArrayList;
import utils.Drawable;

public class FramedPanel extends JPanel {
	private Color color;
	ArrayList<Drawable> drawnObjects;
	
	public FramedPanel(Color c) {
		this.color = c;
		this.drawnObjects = new ArrayList<Drawable>();
	}
	
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		g.setColor(this.color);
		g.fillRect(0, 0, this.getWidth(), this.getHeight());
		g.setColor(Color.WHITE);
		g.fillRect(this.getWidth()/20, this.getHeight()/20, this.getWidth()-this.getWidth()/20*2, this.getHeight()-this.getHeight()/20*2);
		g.setColor(Color.BLACK);
		g.drawRect(this.getWidth()/20, this.getHeight()/20, this.getWidth()-this.getWidth()/20*2, this.getHeight()-this.getHeight()/20*2);

		for (Drawable j : this.drawnObjects) {
			j.draw(g);
		}
	}

	public void addDrawnObj(Drawable obj) {
		this.drawnObjects.add(obj);
	}

	public void clearDrawn() {
		this.drawnObjects = new ArrayList<Drawable>();
	}
}
