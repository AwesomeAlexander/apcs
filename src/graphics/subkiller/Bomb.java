package graphics.subkiller;

import utils.BasicObject;
import java.awt.Graphics;

/**
 * Bomb
 */
public class Bomb extends BasicObject {

	public Bomb() {super();}
	public Bomb(int x,int y) {super(x, y);}

	@Override
	public void draw(Graphics g) {
		
	}

	@Override
	public void move() {
		this.acceleratexy(0,2); // accelerates downwards at a rate of 2
		this.movexy();
	}
}