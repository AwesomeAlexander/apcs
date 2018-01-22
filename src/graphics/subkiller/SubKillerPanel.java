package graphics.subkiller;

import javax.swing.Timer;

import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * SubKillerPanel
 */
public class SubKillerPanel extends JPanel {
	static final long serialVersionUID = 42L;

	Timer timer;

	// Objects
	Boat boat;
	ArrayList<Bomb> bombs;
	ArrayList<Submarine> subs;

	public SubKillerPanel() {
		super();

		SubKillerListener listener = new SubKillerListener(this);
		this.addKeyListener(listener);
		this.addFocusListener(listener);

		this.timer = new Timer(30, listener);

		this.boat = new Boat((int)(this.getWidth()/2),(int)(this.getHeight()/7.5));
		this.bombs = new ArrayList<Bomb>();
		this.subs = new ArrayList<Submarine>();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // White background

		this.boat.draw(g);
		for (Bomb b : this.bombs) b.draw(g);
		for (Submarine s : this.subs) s.draw(g);
	}
}