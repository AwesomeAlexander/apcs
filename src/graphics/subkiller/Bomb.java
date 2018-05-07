package graphics.subkiller;

import utils.BasicEntity;
import utils.Drawable;
import utils.Moveable;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Bomb
 */
public class Bomb extends BasicEntity implements Moveable, Drawable {

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