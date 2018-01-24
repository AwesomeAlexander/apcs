package graphics.simplepaint;

import java.awt.event.*;

import graphics.drawings.Line;

import java.awt.Point;

/**
 * SimplePaintListener
 */
public class SimplePaintListener extends MouseAdapter implements ActionListener {

	SimplePaintPanel panel;

	public SimplePaintListener(SimplePaintPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO: buttons and menu listeneing
	}

    public void mousePressed(MouseEvent e) {
		if (panel.dragging) return;
		panel.prev = new Point(e.getX(),e.getY());
    }

    public void mouseReleased(MouseEvent e) {
		if (!panel.dragging) return;
		panel.dragging = false;
        panel.prev = null;
    }

    public void mouseDragged(MouseEvent e) {
        panel.dragging = true;

        if (panel.prev != null)
		    panel.lines.add(new Line(panel.prev.x,panel.prev.y,e.getX(),e.getY(),panel.color));
        panel.prev = new Point(e.getX(),e.getY());
        
        panel.repaint();
    }
}