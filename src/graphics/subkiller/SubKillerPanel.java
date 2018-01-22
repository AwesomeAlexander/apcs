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

	// Objects
	Boat boat;
	ArrayList<Bomb> bombs;
	ArrayList<Submarine> subs;

	public SubKillerPanel() {
		super();

		// Listener stuff
		SubKillerListener listener = new SubKillerListener(this);
		this.addKeyListener(listener);
		this.addFocusListener(listener);
		this.timer = new Timer(30, listener); // Fires every 30ms

		// Menu stuff & UI
		JMenuBar menuBar = new JMenuBar();
		JMenu subkillerMenu = new JMenu("Sub Killer");
		JMenu optionsMenu = new JMenu("Options");
		JMenuItem about = new JMenuItem("About"); about.addActionListener(listener);
		JMenuItem quit = new JMenuItem("Quit"); quit.addActionListener(listener);
		JMenuItem restart = new JMenuItem("Restart"); restart.addActionListener(listener);
		
		subkillerMenu.add(about);
		subkillerMenu.addSeparator();
		subkillerMenu.add(quit);
		subkillerMenu.add(restart);

		menuBar.add(subkillerMenu);
		menuBar.add(optionsMenu);

		this.add(menuBar);

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