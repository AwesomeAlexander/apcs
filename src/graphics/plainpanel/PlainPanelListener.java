package graphics.plainpanel;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import utils.Drawable;
import graphics.drawings.*;

public class PlainPanelListener extends MouseAdapter {

	private FramedPanel panel;
	Drawable obj;
	Point offset;

	public PlainPanelListener(FramedPanel generator) {
		this.panel = generator;
		this.panel.addMouseListener(this);
		this.panel.addMouseMotionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Color c = e.isAltDown() ? Color.BLUE : Color.RED;
		Drawable d = e.isShiftDown() ? new Ellipse(e.getX(), e.getY(), 30, 30, c) : new Rect(e.getX(), e.getY(), 30, 30, c);

		this.panel.addDrawnObj(d);
		
		if(e.isControlDown()) {
			this.panel.clearDrawn();
		}

		this.panel.repaint();
		
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		
	}
}