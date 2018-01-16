package graphics.rectanglepanel;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JPanel;
import java.util.ArrayList;
import utils.Drawable;
import graphics.drawings.*;

public class FramedPanel extends JPanel {
	private Color color;
	ArrayList<Rect> drawnObjects; 
	
	public FramedPanel(Color c) {
		this.color = c;
		this.drawnObjects = new ArrayList<Rect>();
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

		for (Rect j : this.drawnObjects) {
			j.draw(g);
		}
	}

	public void addDrawnObj(Rect obj) {
		this.drawnObjects.add(obj);
	}
	public void removeDrawnObj(Rect obj) {
		this.drawnObjects.remove(obj);
	}

	public void clearDrawn() {
		this.drawnObjects = new ArrayList<Rect>();
	}

	public Rect containsPoint(Point p) {
		for (Rect r : this.drawnObjects) {
			if (r.containsPoint(p)) return r;
		}
		return null;
	}
}
