package graphics.plainpanel;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import utils.Drawable;
import graphics.drawings.*;

public class PlainPanelListener implements MouseListener {

	FramedPanel panel;

	public PlainPanelListener(FramedPanel generator) {
		this.panel = generator;
		this.panel.addMouseListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("u did a thing");

		int x = e.getX();
		int y = e.getY();
		Color c; 
		Drawable d;
		
		if(e.isMetaDown()) {
			c = Color.RED;
			d = new Ellipse(x, y, 30, 30, c);
		}
		else if(e.isAltDown()) {
			c = Color.BLUE;
			d = new Ellipse(x, y, 30, 30, c);
		}
		else if(e.isShiftDown()) {
			c = Color.BLUE;
			d = new Rect(x, y, 30, 30, c);
		}
		else{
			c = Color.RED;
			d = new Rect(x, y, 30, 30, c);
		}

		this.panel.addDrawnObj(d);
		
		if(e.isControlDown()) {
			this.panel.clearDrawn();
		}

		this.panel.repaint();
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Nothing
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// Nothing
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// Nothing
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// Nothing
	}
}