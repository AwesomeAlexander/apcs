package graphics.simplepaint;

import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JColorChooser;

import graphics.drawings.Line;

import java.awt.Color;
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
		String action = e.getActionCommand().toUpperCase();

		if (getColor(action) != null) {
			panel.color = getColor(action);
			return;
		}
		switch (action) {
			case "QUIT": System.exit(0);
			case "UNDO":
				panel.lines.remove(panel.lines.size()-1);
				panel.repaint();break;
			case "CLEAR":
				panel.lines = new ArrayList<Line>();
				panel.repaint();break;
			case "CUSTOM":
				panel.color = JColorChooser.showDialog(panel, "Choose a Custom Color to draw with!", panel.color);
				if (panel.color==null) panel.color = Color.WHITE;
				break;
			default:
				System.out.println("Got an unhandled''"+action+"'' command.");
		}
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
	
	public Color getColor(String thecolor) {
		switch (thecolor.toUpperCase()) {
			case "RED": return Color.RED;
			case "GREEN": return Color.GREEN;
			case "BLUE": return Color.BLUE;
			case "CYAN": return Color.CYAN;
			case "MAGENTA": return Color.MAGENTA;
			case "YELLOW": return Color.YELLOW;
			default: return null;
		}
	}
}