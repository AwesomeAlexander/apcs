package apcsproject2;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import graphics.drawings.Rect;

public class GameListener implements ActionListener, MouseListener, MouseMotionListener, KeyListener, FocusListener {

    GamePanel panel;
    GamePlayer player;
    List<Unit> selected;
    Point onclick;
    Rect selectionSquare;
    boolean spaceDown;

	public GameListener(GamePanel generator) {
		this.panel = generator;
		panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addKeyListener(this);
        panel.addFocusListener(this);

        this.selected = new ArrayList<Unit>();
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Assume it's timer firing for game tick, but only update if unpaused
        if (!panel.paused) panel.update();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // System.out.println("Clicked!");

        boolean selectedNew = false;
        for (Unit u : player.units) {
            if (u.distanceto(e.getX(), e.getY()) < u.getSize()*2.0/3) {
                (this.selected = new ArrayList<Unit>()).add(u);
                selectedNew = true;
                break;
            }
        }

        if (!selectedNew && this.selected != null) // Player clicked a spot without a unit - move selected units there
            panel.setTargets(this.selected, e.getX(),e.getY());
    }

    @Override
    public void mousePressed(MouseEvent e) {
        onclick = new Point(e.getX(), e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        selectionSquare = null;
        onclick = null;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        panel.unpause();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        panel.pause();
    }

    @Override
    public void mouseDragged(MouseEvent e) {

        if (spaceDown && selectionSquare == null) {
            // Relative targeting
            panel.setTargetsRelative(this.selected, (e.getX()-onclick.x), (e.getY()-onclick.y));
        } else {
            // If there is a significant difference in position, make selectionSquare
            if (Math.abs(e.getX() - onclick.x) > 5 && Math.abs(e.getY() - onclick.y) > 5) {
                selectionSquare = new Rect(
                    Math.min(e.getX(), onclick.x),
                    Math.min(e.getY(), onclick.y),
                    Math.abs(e.getX() - onclick.x),
                    Math.abs(e.getY() - onclick.y),
                    Color.GRAY);
            }

            if (selectionSquare != null) {
            	// Manual foreach loop, as its causing errors - didn't fix
                for (Iterator<Unit> iter = player.units.iterator();iter.hasNext();) {
                	Unit u = iter.next();
                    if (true // Coords in selection square
                    	&& u.getX() >= selectionSquare.x
                        && u.getX() <= selectionSquare.x+selectionSquare.width
                        && u.getY() >= selectionSquare.y
                        && u.getY() <= selectionSquare.y+selectionSquare.height) {
                    	// Add if it isn't already
                        if (!this.selected.contains(u)) this.selected.add(u);
                    } else { // If not in square, remove
                        this.selected.remove(u);
                    }
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
    	switch (e.getKeyCode()) {
    	case KeyEvent.VK_SPACE:
    		spaceDown = true;
    		break;
    	case KeyEvent.VK_UP:
    		panel.setTargetsRelativeTarget(this.selected, 0, -5);
    		break;
    	case KeyEvent.VK_DOWN:
    		panel.setTargetsRelativeTarget(this.selected, 0, 5);
    		break;
    	case KeyEvent.VK_LEFT:
    		panel.setTargetsRelativeTarget(this.selected, -5, 0);
    		break;
    	case KeyEvent.VK_RIGHT:
    		panel.setTargetsRelativeTarget(this.selected, 5, 0);
    		break;
    	case KeyEvent.VK_S:
    		panel.setTargetsRelative(this.selected, 0, 0);
    		break;
    	case KeyEvent.VK_A:
    		this.selected = player.units;
    		break;
    	case KeyEvent.VK_D:
    		this.selected = new ArrayList<Unit>();
    		break;
    	}
        //if (e.getKeyCode() == KeyEvent.VK_SPACE) spaceDown = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == e.VK_SPACE) spaceDown = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) {
        	if (panel.paused) panel.unpause();
    		else panel.pause();
        }
    }

    @Override
	public void focusGained(FocusEvent e) {
		panel.unpause();
	}

	@Override
	public void focusLost(FocusEvent e) {
        panel.pause();
	}
}