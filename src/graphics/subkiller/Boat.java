package graphics.subkiller;

import utils.BasicEntity;
import utils.Drawable;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Boat
 */
public class Boat extends BasicEntity implements Drawable {

	// public Boat() {super();}
	public Boat(int x,int y,Color c) {super(x, y, 25, c);}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.size, this.size/2);
	}
}