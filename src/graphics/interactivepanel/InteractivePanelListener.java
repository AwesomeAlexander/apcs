package graphics.interactivepanel;

import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import graphics.drawings.*;

public class InteractivePanelListener implements MouseListener,MouseMotionListener {

	InteractivePanel panel;
	Rect drawThis;

	public InteractivePanelListener(InteractivePanel generator) {
		this.panel = generator;
		this.panel.addMouseListener(this);
		this.panel.addMouseMotionListener(this);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		this.drawThis = new Rect(e.getX(), e.getY(), 50, 50, Color.RED);
		panel.addDrawnObj(this.drawThis);
		panel.repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		this.drawThis.width = e.getX()-this.drawThis.x;
		this.drawThis.height = e.getY()-this.drawThis.y;
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// Nothing
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// Nothing
	}

	@Override
	public void mouseReleased(MouseEvent e) {
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