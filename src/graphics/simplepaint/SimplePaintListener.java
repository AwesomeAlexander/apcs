package graphics.simplepaint;

import java.awt.event.*;
import java.awt.Color;
import java.awt.Point;
import javax.swing.BorderFactory;
import javax.swing.JColorChooser;

import java.util.ArrayList;
import graphics.drawings.Line;

/**
 * SimplePaintListener
 */
public class SimplePaintListener extends MouseAdapter implements ActionListener, KeyListener, FocusListener {

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
		switch (action) { // Non-color actions
			case "QUIT": System.exit(0);
			case "CLEAR": clear();break;
			case "UNDO": removeLast();break;
			case "CUSTOM":
				panel.color = JColorChooser.showDialog(panel, "Choose a Custom Color to draw with!", panel.color);
				if (panel.color==null) panel.color = Color.WHITE;
				break;
			default:
				System.out.println("Got an unhandled''"+action+"'' command.");
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (!e.isMetaDown()) return;
		switch (e.getKeyChar()) {
			case 'w': System.exit(0);
			case 'z': removeLast(e.isShiftDown() ? 5 : 1);break;
			case 'p': clear();break;
			default:
				break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Nothing
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Nothing
	}

	@Override
    public void mousePressed(MouseEvent e) {
		panel.requestFocusInWindow();
		if (panel.dragging) return;
		panel.prev = new Point(e.getX(),e.getY());
    }

	@Override
    public void mouseReleased(MouseEvent e) {
		if (!panel.dragging) return;
		panel.dragging = false;
        panel.prev = null;
    }

	@Override
    public void mouseDragged(MouseEvent e) {
        panel.dragging = true;

        if (panel.prev != null)
		    panel.lines.add(new Line(panel.prev.x,panel.prev.y,e.getX(),e.getY(),panel.color));
        panel.prev = new Point(e.getX(),e.getY());
        
		panel.repaint();
	}

	@Override
	public void focusGained(FocusEvent e) {
		panel.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
	}

	@Override
	public void focusLost(FocusEvent e) {
		panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 6));
	}

	// Utils

	void clear() {
		panel.lines = new ArrayList<Line>();
		panel.repaint();
	}

	void removeLast() {removeLast(1);}
	void removeLast(int amount) {
		for (int i=0;i<amount;i++) {
			if (panel.lines.size() == 0) return;
			panel.lines.remove(panel.lines.size()-1);
		}
		panel.repaint();
	}
	
	Color getColor(String thecolor) {
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