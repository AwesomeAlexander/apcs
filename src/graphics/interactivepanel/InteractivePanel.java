package graphics.interactivepanel;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import utils.Drawable;

public class InteractivePanel extends JPanel {
	static final long serialVersionUID = 42L;
	
	int counter = 0;
	ArrayList<Drawable> drawnObjects = new ArrayList<Drawable>();

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // g.setFont(new Font("Serif", Font.BOLD, 14));
        g.drawString("The System called paintComponent " + counter + " times.", 10, 10);

        for (Drawable d : this.drawnObjects) {
			d.draw(g);
		}
	}
	
	public void addDrawnObj(Drawable obj) {
		this.drawnObjects.add(obj);
		counter++;
	}

	public void removeDrawnObj(Drawable obj) {
		this.drawnObjects.remove(obj);
		counter--;
	}
}