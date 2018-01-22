package graphics.simplepaint;

import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D.Double;
import java.util.ArrayList;

import javax.swing.*;


public class SimplePaint extends JPanel implements MouseListener, MouseMotionListener {
    static final long serialVersionUID = 42L;
    
    /**
     * This main routine allows this class to be run as a program.
     */
    public static void main(String[] args) {
        JFrame window = new JFrame("Simple Paint");
        SimplePaint content = new SimplePaint();
        window.setContentPane(content);
        window.setSize(700,380);
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        window.setVisible(true);

    }

    /**
     * Some constants to represent the color selected by the user.
     */
    private final static int BLACK = 0,
            RED = 1,     
            GREEN = 2,   
            BLUE = 3, 
            CYAN = 4,   
            MAGENTA = 5,
            YELLOW = 6;

    private int currentColor = BLACK;  // The currently selected drawing color,
                                       //   coded as one of the above constants.

    /* The following variables are used when the user is sketching a
         curve while dragging a mouse. */

    private Point prev;
    private boolean dragging;
    
	class Line extends Double {
        static final long serialVersionUID = 42L;

        Color c;
		public Line(int x1,int y1,int x2,int y2,Color c) {
			super();
            this.setLine(x1, y1, x2, y2);
            this.c = c;
		}
		public void draw(Graphics g) {
            g.setColor(c);
			g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
		}
	}
	ArrayList<Line> lines;
    

    /**
     * Constructor for SimplePaintPanel class sets the background color to be
     * white and sets it to listen for mouse events on itself.
     */
    SimplePaint() {
		this.setBackground(Color.WHITE);
		
		this.lines = new ArrayList<Line>();

		this.addMouseListener(this);
		this.addMouseMotionListener(this);
	}

    public void paintComponent(Graphics g) {
        super.paintComponent(g);  // Fill with background color (white).

        int width = getWidth();    // Width of the panel.
        int height = getHeight();  // Height of the panel.

        int colorSpacing = (height - 56) / 7;

        g.setColor(Color.GRAY);
        g.drawRect(0, 0, width-1, height-1);  // one rectangle for each pixel 
        g.drawRect(1, 1, width-3, height-3);
        g.drawRect(2, 2, width-5, height-5);

        /* Draw a 56-pixel wide gray rectangle along the right edge of the panel.
             The color palette and Clear button will be drawn on top of this.
             (This covers some of the same area as the border I just drew. */

        g.fillRect(width - 56, 0, 56, height);

        /* Draw the "Clear button" as a 50-by-50 white rectangle in the lower right
             corner of the panel, allowing for a 3-pixel border. */

        g.setColor(Color.WHITE);
        g.fillRect(width-53,  height-53, 50, 50);
        g.setColor(Color.BLACK);
        g.drawRect(width-53, height-53, 49, 49);
        g.drawString("CLEAR", width-48, height-23); 

        /* Draw the seven color rectangles. */

        g.setColor(Color.BLACK);
        g.fillRect(width-53, 3 + 0*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.RED);
        g.fillRect(width-53, 3 + 1*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.GREEN);
        g.fillRect(width-53, 3 + 2*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.BLUE);
        g.fillRect(width-53, 3 + 3*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.CYAN);
        g.fillRect(width-53, 3 + 4*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.MAGENTA);
        g.fillRect(width-53, 3 + 5*colorSpacing, 50, colorSpacing-3);
        g.setColor(Color.YELLOW);
        g.fillRect(width-53, 3 + 6*colorSpacing, 50, colorSpacing-3);

        /* Draw a 2-pixel white border around the color rectangle
             of the current drawing color. */

        g.setColor(Color.WHITE);
        g.drawRect(width-55, 1 + currentColor*colorSpacing, 53, colorSpacing);
        g.drawRect(width-54, 2 + currentColor*colorSpacing, 51, colorSpacing-2);
 
        
		for (Line l : this.lines) {
			l.draw(g);
		}

        
    } // end paintComponent()


    private void changeColor(int y) {
        int colorSpacing = (getHeight() - 56) / 7;  // Space for one color rectangle.
        int newColor = y / colorSpacing;       // Which color number was clicked?
        // System.out.println("y: " + y + " colorSpacing: " + colorSpacing + " newColor: " + newColor);

        if (newColor < 0 || newColor > 6)      // Make sure the color number is valid.
            return;

        currentColor = newColor;
    }

    private Color getColor(int c) {
        switch (c) {
            case BLACK: return Color.BLACK;
            case RED: return Color.RED;
            case GREEN: return Color.GREEN;
            case BLUE: return Color.BLUE;
            case CYAN: return Color.CYAN;
            case MAGENTA: return Color.MAGENTA;
            case YELLOW: return Color.YELLOW;
            default:
                System.out.println("You Failed at colors!");
                return null;
        }
    }


    /**
     * This is called when the user presses the mouse anywhere in the panel.  
     * There are three possible responses, depending on where the user clicked:  
     * Change the current color, clear the drawing, or start drawing a curve.  
     * (Or do nothing if user clicks on the border.)
     */
    public void mousePressed(MouseEvent evt) {
        int x = evt.getX();   // x-coordinate where the user clicked.
        int y = evt.getY();   // y-coordinate where the user clicked.

        int width = getWidth();    // Width of the panel.
        int height = getHeight();  // Height of the panel.

        if (dragging == true) return; // The only real use for this variable...

        if (x > width - 53) {
            if (y > height - 53) {
            			//  Clicked on "CLEAR".
					  this.lines = new ArrayList<Line>();
					  // this.lines.clear();
            } else {
                changeColor(y);
			}
			repaint();
        } else if (x > 3 && x < width - 56 && y > 3 && y < height - 3) {
            // Save x,y
            this.prev = new Point(x,y);
        }

    } // end mousePressed()


    /**
     * Called whenever the user releases the mouse button. If the user was drawing 
     * a curve, the curve is done, so we should set dragging to false
     */
    public void mouseReleased(MouseEvent evt) {
        if (dragging == false)
        		return; // Nothing to do because the user isn't drawing.
        dragging = false;;
        this.prev = null;
    }


    /**
     * Called whenever the user moves the mouse while a mouse button is held down.  
     * If the user is drawing, draw a line segment from the previous mouse location 
     * to the current mouse location, and set up prevX and prevY for the next call.  
     * Note that in case the user drags outside of the drawing area, the values of
     * x and y are "clamped" to lie within this area.  This avoids drawing on the color 
     * palette or clear button.
     */
    public void mouseDragged(MouseEvent evt) {
        dragging = true;

        int x = Math.max(3,Math.min(getWidth()-56,evt.getX()));
		int y = Math.max(3,Math.min(getHeight()-3,evt.getY()));
		
        if (this.prev != null)
		    this.lines.add(new Line(this.prev.x,this.prev.y,x,y,this.getColor(this.currentColor)));
        this.prev = new Point(x,y);
        
        repaint();
    } // end mouseDragged()


    public void mouseEntered(MouseEvent evt) { }   // Some empty routines.
    public void mouseExited(MouseEvent evt) { }    //    (Required by the MouseListener
    public void mouseClicked(MouseEvent evt) { }   //    and MouseMotionListener
    public void mouseMoved(MouseEvent evt) { }     //    interfaces).



} // end class SimplePaint