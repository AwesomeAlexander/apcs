package graphics.drawingpanel;

import java.awt.*;
import javax.swing.*;
import graphics.drawings.*;

public class DrawingPanel extends JPanel {
    static final long serialVersionUID = 42L;
	
	int counter = 0;
	Rect[] rectangles = new Rect[20000];

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setFont(new Font("Serif", Font.BOLD, 14));
        g.drawString("The System called paintComponent " + counter + " times.", 10, 10);

        int x = (int)(Math.random()*300);
		int y = (int)(Math.random()*300);
		rectangles[counter] = new Rect(x, y);
		counter++;

        for (int i=0; i<rectangles.length && rectangles[i] != null; i++) {
			rectangles[i].draw(g);
		}
    }
}