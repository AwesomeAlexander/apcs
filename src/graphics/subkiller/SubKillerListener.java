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
		panel.boat.move();
		for (Bomb b : panel.bombs) b.move();
		for (Submarine s : panel.subs) s.move();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// NOTE: Change to acceleratexy? would make boat movement less 'blocky'
		switch (e.getKeyCode()) {
			case KeyEvent.VK_A:;
			case KeyEvent.VK_LEFT:
				this.panel.boat.movexy(-10,0);
				break;
			case KeyEvent.VK_D:;
			case KeyEvent.VK_RIGHT:
				this.panel.boat.movexy(10,0);
				break;
			case KeyEvent.VK_S:;
			case KeyEvent.VK_DOWN:;
			case KeyEvent.VK_SPACE:
				this.panel.bombs.add(new Bomb(
					this.panel.boat.getX(),
					this.panel.boat.getY()));
				break;
			default:
				break;
		}
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