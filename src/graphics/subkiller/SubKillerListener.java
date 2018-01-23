package graphics.subkiller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
// import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

/**
 * SubKillerListener
 */
public class SubKillerListener implements FocusListener,KeyListener,MouseListener,/*MouseMotionListener,*/ActionListener {
	static final long serialVersionUID = 42L;
	
	SubKillerPanel panel;

	public SubKillerListener(SubKillerPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		
		if (str != null) {
			if (str.equals("About")) JOptionPane.showMessageDialog(this.panel, "This is a Sub Killer game! Kill dem subs to win.");
			else if (str.equals("Quit")) System.exit(0);
			else if (str.equals("Restart")) this.panel.restart();
		}

		// Performs based upon timer, updates game mechanics
		this.panel.update();
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
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
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