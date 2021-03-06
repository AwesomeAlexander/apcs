package graphics.subkiller;

import java.awt.event.*;

import javax.swing.JOptionPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 * SubKillerListener
 */
public class SubKillerListener implements FocusListener,KeyListener,MouseListener,ActionListener,ChangeListener {
	static final long serialVersionUID = 42L;
	
	SubKillerPanel panel;

	public SubKillerListener(SubKillerPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String str = e.getActionCommand();
		if (str != null) {
			System.out.println("hofweoihwefoih");
			if (str.equals("About")) JOptionPane.showMessageDialog(this.panel, "This is a Sub Killer game! Kill dem subs to win.");
			else if (str.equals("Quit")) System.exit(0);
			else if (str.equals("Restart")) this.panel.restart();
		}

		// Performs based upon timer, updates game mechanics
		this.panel.update();
	}

	@Override
	public void stateChanged(ChangeEvent e) {
		// TODO: this
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
		this.panel.requestFocusInWindow();
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