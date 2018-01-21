package utils;

import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

import javax.swing.JPanel;

/**
 * A basic abstract object with some basic constructors and basic methods.
 * Intended for use in conjunction with the {@link javax.swing} elements
 * 
 * @author Alexander Ng
 *
 */
public abstract class BasicObject {

	// Variables
	protected int x, y, size, r, b, g, sx, sy;
	protected double speed, direction;
	protected Color color;
	protected JPanel parent;

	// Constructors

	/**
	 * Basic Constructor
	 */
	public BasicObject() {

	}

	/**
	 * Basic Constructor
	 * 
	 * @param x
	 *            - initial x position, Integer
	 * @param y
	 *            - initial y position, Integer
	 */
	public BasicObject(int x, int y) {
		this(x,y,10);
	}

	/**
	 * Basic Constructor
	 * 
	 * @param x
	 *            - initial x position, Integer
	 * @param y
	 *            - initial y position, Integer
	 * @param size
	 *            - initial size, Integer
	 */
	public BasicObject(int x, int y, int size) {
		this.x = x;
		this.y = y;
		this.size = size;
	}

	/**
	 * Basic Constructor
	 * 
	 * @param x
	 *            - initial x position, Integer
	 * @param y
	 *            - initial y position, Integer
	 * @param speed
	 *            - initial speed, double
	 */
	public BasicObject(int x, int y, double speed) {
		this.x = x;
		this.y = y;
		this.speed = speed;
	}

	/**
	 * Basic Constructor
	 * 
	 * @param x
	 *            - initial x position, Integer
	 * @param y
	 *            - initial y position, Integer
	 * @param speed
	 *            - initial speed, double
	 * @param direction
	 *            - initial direction, double
	 */
	public BasicObject(int x, int y, double speed, double direction) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.direction = direction;
	}
	
	/**
	 * Basic Constructor
	 * 
	 * @param x
	 *            - initial x position, Integer
	 * @param y
	 *            - initial y position, Integer
	 * @param speed
	 *            - initial speed, double
	 * @param size
	 *            - initial size, Integer
	 */
	public BasicObject(int x, int y, double speed, int size) {
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.size = size;
	}

	/**
	 * Basic Constructor
	 * 
	 * @param x
	 *            - initial x position, Integer
	 * @param y
	 *            - initial y position, Integer
	 * @param sx
	 *            - initial x speed, Integer
	 * @param sy
	 *            - initial y speed, Integer
	 */
	public BasicObject(int x, int y, int sx, int sy) {
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = sy;
	}

	/**
	 * Basic Constructor
	 * 
	 * @param x
	 *            - initial x position, Integer
	 * @param y
	 *            - initial y position, Integer
	 * @param sx
	 *            - initial x speed, Integer
	 * @param sy
	 *            - initial y speed, Integer
	 * @param size
	 *            - initial size, Integer
	 */
	public BasicObject(int x, int y, int sx, int sy, int size) {
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = sy;
		this.size = size;
	}

	/**
	 * Basic Constructor
	 * 
	 * @param x
	 *            - initial x position, Integer
	 * @param y
	 *            - initial y position, Integer
	 * @param sx
	 *            - initial x speed, Integer
	 * @param sy
	 *            - initial y speed, Integer
	 * @param color
	 *            - initial color, String
	 */
	public BasicObject(int x, int y, int sx, int sy, Color color) {
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = sy;
		this.color = color;
	}

	/**
	 * Basic Constructor
	 * 
	 * @param x
	 *            - initial x position, Integer
	 * @param y
	 *            - initial y position, Integer
	 * @param sx
	 *            - initial x speed, Integer
	 * @param sy
	 *            - initial y speed, Integer
	 * @param r
	 *            - initial red value, Integer 0-255
	 * @param b
	 *            - initial blue value, Integer 0-255
	 * @param g
	 *            - initial green value, Integer 0-255
	 */
	public BasicObject(int x, int y, int sx, int sy, int r, int b, int g) {
		this.x = x;
		this.y = y;
		this.sx = sx;
		this.sy = sy;
		this.r = r;
		this.b = b;
		this.g = g;
	}

	// Abstract Methods

	/**
	 * Draws the object
	 */
	public abstract void draw(Graphics g);

	/**
	 * Moves the object
	 */
	public abstract void move();

	// Methods

	/**
	 * Updates the object, Defaults move and draw
	 */
	public void update() {
		this.move();
		this.parent.repaint();
	}

	/**
	 * Interaction with the user
	 */
	public void userInteract() {

	}

	// Movement

	/**
	 * Basic Move function based on this object's speed x and speed y
	 */
	public void movexy() {
		this.movexy((int) this.sx, (int) this.sy);
	}

	/**
	 * Basic Move function based on this object's speed x and speed y
	 */
	public void moveda() {
		this.moveda(this.speed, this.direction);
	}

	/**
	 * Basic Move function based on x and y
	 * 
	 * @param xmove
	 *            - movement in the x direction
	 * @param ymove
	 *            - movement in the y direction
	 */
	public void movexy(int xmove, int ymove) {
		this.x += xmove;
		this.y += ymove;
	}

	/**
	 * Moves this object given distance and angle
	 * 
	 * @param distance
	 *            - distance you want it to move
	 * @param angle
	 *            - What angle (in radians) to move.
	 */
	public void moveda(double distance, double angle) {
		this.x += (int) (distance * Math.cos(angle));
		this.y += (int) (distance * Math.sin(angle));
	}

	/**
	 * Accelerates object, increasing speed x and y
	 * 
	 * @param xAccel
	 *            - How much to accelerate in x
	 * @param yAccel
	 *            - How much to accelerate in y
	 */
	public void acceleratexy(int xAccel, int yAccel) {
		this.sx += xAccel;
		this.sy += yAccel;
	}

	/**
	 * Accelerates object, increasing speed
	 * 
	 * @param acceleration
	 *            - How much to accelerate
	 */
	public void accelerateda(int acceleration) {
		this.speed += acceleration;
	}

	// Getters

	/**
	 * Calculates angle from this object to the coordinates passed in
	 * 
	 * @param x
	 *            - x coordinate
	 * @param y
	 *            - y coordinate
	 * @return - Angle in radians from these coordinates
	 */
	public double angleto(int x, int y) {
		return Math.atan2(this.y - y,this.x - x);
	}

	/**
	 * Calculates angle from this object to the other object
	 * 
	 * @param other
	 *            - the other object
	 * @return - Angle in radians from this object
	 */
	public double angleto(BasicObject other) {
		return Math.atan2(this.y - other.y, this.x - other.x);
	}

	/**
	 * Calculates distance from this object to the coordinates passed in
	 * 
	 * @param x
	 *            - x coordinate
	 * @param y
	 *            - y coordinate
	 * @return - Distance from these coordinates
	 */
	public double distanceto(int x, int y) {
		return Math.sqrt(Math.pow(this.x - x, 2) + Math.pow(this.y - y, 2));
	}

	/**
	 * Calculates distance from this object to the other object
	 * 
	 * @param other
	 *            - the other object
	 * @return - Distance from this object
	 */
	public double distanceto(BasicObject other) {
		return Math.sqrt(Math.pow(this.x - other.x, 2) + Math.pow(this.y - other.y, 2));
	}
	
	/**
	 * Gives the closest of an ArrayList of BasicObjects
	 * @param list - ArrayList of basic objects
	 * @return - the closest object
	 */
	public BasicObject closest(ArrayList<BasicObject> list) {
		BasicObject closest = list.get(0);
		for (BasicObject thing : list) {
			if (this.distanceto(thing) < this.distanceto(closest)) {
				closest = thing;
			}
		}
		return closest;
	}
	
	/**
	 * Gives the closest of an Array of BasicObjects
	 * @param list - Array of basic objects
	 * @return - the closest object
	 */
	public BasicObject closest(BasicObject[] list) {
		BasicObject closest = list[0];
		for (BasicObject thing : list) {
			if (this.distanceto(thing) < this.distanceto(closest)) {
				closest = thing;
			}
		}
		return closest;
	}

	public final int getY() {
		return this.y;
	}
	
	public final int getX() {
		return this.x;
	}

	public int getSX() {
		return this.sx;
	}

	public int getSY() {
		return this.sy;
	}

	public double getSpeed() {
		return this.speed;
	}

	public double getDirection() {
		return this.direction;
	}

	public int getSize() {
		return this.size;
	}

	public int getR() {
		return this.r;
	}

	public int getG() {
		return this.g;
	}

	public int getB() {
		return this.b;
	}

	public Color getColor() {
		return this.color;
	}
}