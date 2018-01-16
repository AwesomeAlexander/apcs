package graphics.rectanglepanel;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;

import utils.Drawable;
import graphics.drawings.*;

public class RectanglePanelListener extends MouseAdapter {

	private FramedPanel panel;
	Rect selectedObj;
	Point offset;

	public RectanglePanelListener(FramedPanel generator) {
		this.panel = generator;
		this.panel.addMouseListener(this);
		this.panel.addMouseMotionListener(this);
	}

	@Override
	public void mouseClicked(MouseEvent e) {

		this.panel.addDrawnObj(new Rect(e.getX(), e.getY(), 30, 30, e.isAltDown() ? Color.BLUE : Color.RED));
		
		if(e.isControlDown()) {
			this.panel.clearDrawn();
		}

		this.panel.repaint();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		if (this.selectedObj != null) return;
		Rect temp = this.panel.containsPoint(e.getPoint());
		if (temp != null) {
			this.selectedObj = temp;
			this.offset = new Point(e.getX()-temp.x, e.getY()-temp.y);
		}
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.selectedObj.x = e.getX() - this.offset.x;
		this.selectedObj.y = e.getY() - this.offset.y;
		this.panel.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		this.selectedObj = null;
	}
}