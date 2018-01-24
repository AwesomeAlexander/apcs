package graphics.subkiller;

import javax.swing.Timer;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.*;

/**
 * SubKillerthis
 */
public class SubKillerPanel extends JPanel {
	static final long serialVersionUID = 42L;

	Timer timer;
	SubKillerListener listener;

	// Objects
	Boat boat;
	ArrayList<Bomb> bombs;
	ArrayList<Submarine> subs;

	int subSpeed,ybound;

	public SubKillerPanel() {
		super();

		// Listener stuff
		this.listener = new SubKillerListener(this);
		this.addKeyListener(listener);
		this.addFocusListener(listener);
		this.timer = new Timer(30, listener); // Fires every 30ms

		this.timer.start();

		this.setBackground(Color.BLUE);

		this.restart();

	}

	@Override
	protected void paintComponent(Graphics g) {

		this.boat.draw(g);
		for (Bomb b : this.bombs) b.draw(g);
		for (Submarine s : this.subs) s.draw(g);
	}

	public void update() { // Updates game per tick
		// Make sub on random
		if (Math.random() < 0.001) {
			this.subs.add(new Submarine(this,(int)(Math.random()*this.getWidth()),(int)(ybound+Math.random()*(this.getHeight()-ybound)),ybound));
		}

		// Updating objects
		for (int i=0;i<this.bombs.size();i++) { // Bomb Updates
			Bomb b = this.bombs.get(i);
			b.move();
			if (b.getX() > this.getHeight()) { // Bomb Deletion
				this.bombs.remove(b);
				i--; // Resets iterator
			}
		}
		for (int i=0;i<this.subs.size();i++) { // Sub Updates
			Submarine s = this.subs.get(i);
			s.move();s.setSpeed(this.subSpeed,(int)Math.ceil(this.subSpeed/2));
			if (s.getExplosionStatus() >= 15) { // Sub Deletion
				this.subs.remove(s);
				i--;
			}
			// Detecting Sub-Bomb collision
			for (int j=0;s.getExplosionStatus() < 1 && j< this.bombs.size();j++) {
				Bomb b = this.bombs.get(j);
				if (s.distanceto(b) < s.getSize()) {
					s.explode();
					this.bombs.remove(b);
					break;
				}
			}
		}

		this.repaint();
	}

	public void restart() {
		// Variable Initiation
		this.boat = new Boat((int)(this.getWidth()/2),(int)(this.getHeight()/7.5),Color.RED);
		this.bombs = new ArrayList<Bomb>();
		this.subs = new ArrayList<Submarine>();

		this.ybound = 100;

		this.subs.add(new Submarine(this,(int)(Math.random()*this.getWidth()),(int)(ybound+Math.random()*(this.getHeight()-ybound)),ybound));

		this.subSpeed = 5;
	}
}