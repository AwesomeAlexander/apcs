package graphics.subkiller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
// import java.awt.event.MouseListener;
// import java.awt.event.MouseMotionListener;

/**
 * SubKillerListener
 */
public class SubKillerListener implements FocusListener,KeyListener,/*MouseListener,MouseMotionListener,*/ActionListener {

	SubKillerPanel panel;

	public SubKillerListener(SubKillerPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// Listens on timer
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void focusGained(FocusEvent e) {
		this.panel.timer.start();
		this.panel.repaint();
	}

	@Override
	public void focusLost(FocusEvent e) {
		this.panel.timer.stop();
		this.panel.repaint();
	}
}