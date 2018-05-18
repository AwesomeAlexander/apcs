package apcsproject2;

import java.awt.Color;
import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;

import graphics.drawings.Rect;

public class GameListener implements ActionListener, MouseListener, MouseMotionListener, KeyListener, FocusListener {

    GamePanel panel;
    GamePlayer player;
    List<Unit> selected; // TODO: print to debug
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
            for (Unit u : this.selected)
                u.setTarget(new Point(e.getX(),e.getY()));
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
            // Relative targetting
            for (Unit u : this.selected) {
                u.target = new Point( // Point offset from their original position
                    u.getX() + (e.getX()-onclick.x),
                    u.getY() + (e.getY()-onclick.y)
                    );
            }
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
                for (Unit u : player.units) {
                    if (u.getX() >= selectionSquare.x
                        && u.getX() <= selectionSquare.x+selectionSquare.width
                        && u.getY() >= selectionSquare.y
                        && u.getY() <= selectionSquare.y+selectionSquare.height)
                        this.selected.add(u);
                    else
                        this.selected.remove(u);
                }
            }
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_SPACE) spaceDown = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        spaceDown = false;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        
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