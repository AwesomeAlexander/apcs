package graphics.simplepaint;

import java.util.ArrayList;

import java.awt.*;
import javax.swing.*;

import graphics.drawings.Line;

/**
 * SimplePaintPanel
 */
public class SimplePaintPanel extends JPanel {
	static final long serialVersionUID = 42L;

	Point prev;
	boolean dragging;
	ArrayList<Line> lines;
	Color color;

	public SimplePaintPanel() {

		// Black background
		this.setBackground(Color.BLACK);

		// 3 pixel gray border
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

		// Line array
		this.lines = new ArrayList<Line>();

		// Default Drawing Color
		this.color = Color.WHITE;
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Fill with background color (black).
        
		for (Line l : this.lines) l.draw(g);
    }
}