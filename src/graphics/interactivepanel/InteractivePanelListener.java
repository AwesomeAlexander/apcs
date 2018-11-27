package graphics.interactivepanel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import graphics.drawings.*;

public class InteractivePanelListener implements MouseListener,MouseMotionListener {

	InteractivePanel panel;
	Rect drawThis;
	int startX,startY;

	public InteractivePanelListener(InteractivePanel generator) {
		this.panel = generator;
		this.panel.addMouseListener(this);
		this.panel.addMouseMotionListener(this);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.drawThis = new Rect(this.startX = e.getX(), this.startY = e.getY(), 0, 0, e.isShiftDown() ? Color.BLUE : Color.RED);
		panel.addDrawnObj(this.drawThis);
		panel.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// Updating x, y, width, and height using new x and y dragged mouse positions
		// Enables dragging anywhere instead of just down to the right
		this.drawThis.x = Math.min(this.startX, e.getX());
		this.drawThis.y = Math.min(this.startY, e.getY());
		this.drawThis.width = Math.max(this.startX, e.getX())	-	this.drawThis.x;
		this.drawThis.height = Math.max(this.startY, e.getY())	-	this.drawThis.y;
		this.panel.repaint();
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// Futureproofing, removing old undrawn object of width/height 0
		if (this.drawThis.x == e.getX() && this.drawThis.y == e.getY()) {
			this.panel.removeDrawnObj(this.drawThis);
			this.drawThis = null;
			this.startX = -1;
			this.startY = -1;
		}
	}

	@Override public void mouseMoved(MouseEvent e) {}
	@Override public void mouseClicked(MouseEvent e) {}
	@Override public void mouseEntered(MouseEvent e) {}
	@Override public void mouseExited(MouseEvent e) {}
}