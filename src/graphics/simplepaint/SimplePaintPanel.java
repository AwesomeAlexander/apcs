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
		super(); // Class act as drawing area

		// (Mouse) Listener mechanics
		SimplePaintListener listener = new SimplePaintListener(this);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);

		// Black background
		this.setBackground(Color.BLACK);

		// 3 pixel gray border
		this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));

		// Line array
		this.lines = new ArrayList<Line>();
	}

	public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Fill with background color (white).
        
		for (Line l : this.lines) l.draw(g);
    }
}