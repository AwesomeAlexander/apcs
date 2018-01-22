package graphics.subkiller;

import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;

/**
 * SubKillerPanel
 */
public class SubKillerPanel extends JPanel {
	static final long serialVersionUID = 42L;

	Timer timer;
	SubKillerListener listener;

	// Objects
	Boat boat;
	ArrayList<Bomb> bombs;
	ArrayList<Submarine> subs;

	public SubKillerPanel() {
		super();

		// Listener stuff
		this.listener = new SubKillerListener(this);
		this.addKeyListener(listener);
		this.addFocusListener(listener);
		this.timer = new Timer(30, listener); // Fires every 30ms

		this.restart();

	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g); // White background

		this.boat.draw(g);
		for (Bomb b : this.bombs) b.draw(g);
		for (Submarine s : this.subs) s.draw(g);
	}

	private void restart() {
		// Variable Initiation
		this.boat = new Boat((int)(this.getWidth()/2),(int)(this.getHeight()/7.5),Color.RED);
		this.bombs = new ArrayList<Bomb>();
		this.subs = new ArrayList<Submarine>();

		// TODO: scorepanel stuff
	}
}