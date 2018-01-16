package graphics.plainpanel;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import utils.Drawable;
import graphics.drawings.*;

public class PlainPanelListener extends MouseAdapter {

	private FramedPanel panel;

	public PlainPanelListener(FramedPanel generator) {
		this.panel = generator;
		this.panel.addMouseListener(this);
		this.panel.addMouseMotionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

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
	public void mouseDragged(MouseEvent e) {
		// Nothing
	}
}