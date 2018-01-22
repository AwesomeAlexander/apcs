package graphics.subkiller;

import utils.BasicObject;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Bomb
 */
public class Bomb extends BasicObject {

	// public Bomb() {super();}
	public Bomb(int x,int y) {super(x, y, 10, Color.BLACK);}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.x, this.y, this.size, this.size);
	}

	@Override
	public void move() {
		this.acceleratexy(0,2); // accelerates downwards at a rate of 2
		this.movexy();
	}
}