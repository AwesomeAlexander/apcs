package graphics.rectanglepanel;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import graphics.drawings.*;
import utils.Drawable;

public class RectanglePanel extends JPanel {
	static final long serialVersionUID = 42L;

	public RectanglePanel() {
		super();
		new RectanglePanelListener(this);
	}
	
	ArrayList<Rect> drawnObjects = new ArrayList<Rect>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (Rect d : this.drawnObjects) {
			d.draw(g);
		}
	}
	
	public void addDrawnObj(Rect obj) {
		this.drawnObjects.add(obj);
	}

	public void removeDrawnObj(Drawable obj) {
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