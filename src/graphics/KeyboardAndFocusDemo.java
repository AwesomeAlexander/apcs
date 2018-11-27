package graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


/**
 * This program demonstrates Focus events and Key events.  A colored square
 * is drawn on the panel.  By pressing the arrow keys, the user can move
 * the square up, down, left, or right.  By pressing the keys
 * R, G, B, or K, the user can change the color of the square to red,
 * green, blue, or black, respectively. The panel changes appearance when 
 * it has the input focus; a cyan-colored border is drawn around it.  
 * When it does not have the input focus, the message "Click to Activate" 
 * is displayed and the border is gray.  The panel should have focus
 * whenever the program window is active.
 * This class contains a main() routine so that it can be run as a program
 */
public class KeyboardAndFocusDemo extends JPanel implements KeyListener/*, FocusListener*/ {
	static final long serialVersionUID = 42L;


    /**
     * The main program just opens a window that shows an object of type
     * KeyboardAndFocusDemo.  Note that it should request focus for the panel.
     * This has to be done after the window is made visible for it to have
     * any effect.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Keyboard and Focus Demo");
        KeyboardAndFocusDemo content = new KeyboardAndFocusDemo();
        window.setContentPane( content );
        window.setSize(400,400);
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		window.setVisible(true);
		content.requestFocusInWindow();
    }

    private static final int SQUARE_SIZE = 50;  // Length of side of square.
    private Color squareColor;  // The color of the square.
	private int squareTop, squareLeft;  // Coordinates corner of square.

    /**
     * The constructor sets the initial position and color of the square
     * and registers itself to act as a listener for Key, Focus, and 
     * Mouse events.
     */
    public KeyboardAndFocusDemo() {

        squareTop = 100;  // Initial position of top-left corner of square.
        squareLeft = 100;
        squareColor = Color.RED;  // Initial color of square.

		setBackground(Color.WHITE);
		
		this.addKeyListener(this);


    } // end constructor


    /**
     * Draws a border, square, and message in the panel.  The message and
     * the color of  the border depend on whether or not the pane has
     * the input focus.
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
		
        int width = getSize().width;  // Width of the panel.
		int height = getSize().height; // Height of the panel.
		g.setColor(this.isFocusOwner() ? Color.CYAN : Color.GRAY); // Color depending on focus
        g.drawRect(0,0,width-1,height-1);
        g.drawRect(1,1,width-3,height-3);
        g.drawRect(2,2,width-5,height-5);

		//*** Draw the square.
		
		g.setColor(this.squareColor);
		g.fillRect(this.squareLeft,this.squareTop,KeyboardAndFocusDemo.SQUARE_SIZE,KeyboardAndFocusDemo.SQUARE_SIZE);

        /* Print a message that depends on whether the panel has the focus. */

        g.setColor(Color.MAGENTA);
        if (this.isFocusOwner()) {
            g.drawString("Arrow Keys Move Square",7,20);
            g.drawString("K, R, G, B Change Color",7,40);
        } else {
			g.drawString("Click to activate",7,20);
		}

	}  // end paintComponent()
	
	private Color getColor(char c) {
		switch (c) {
			case 'k': return Color.BLACK;
			case 'r': return Color.RED;
			case 'g': return Color.GREEN;
			case 'b': return Color.BLUE;
			default: return null;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		this.squareColor = getColor(e.getKeyChar())==null ? this.squareColor : getColor(e.getKeyChar());
		repaint();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP) {
			this.squareTop-=5;
		} else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
			this.squareTop+=5;
		} else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
			this.squareLeft-=5;
		} else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
			this.squareLeft+=5;
		}

		repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Nothing
	}
} // end class KeyboardAndFocusDemo