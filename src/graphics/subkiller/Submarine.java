package graphics.subkiller;

import utils.BasicObject;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Submarine
 */
public class Submarine extends BasicObject {

	private boolean direcHorz; // 'goRight'
	private int destVert; // Where to go vertically
	private int ybound;

	// public Submarine() {super();}
	public Submarine(SubKillerPanel parent,int x,int y,int ybound) {
		super(x, y, 25, Color.BLACK);
		this.direcHorz = true;
		this.destVert = this.x;
		this.parent = parent;
		this.ybound = ybound;
		this.sx = 10;
		this.sy = 5;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.x,this.y,this.size*2,this.size);
	}

	@Override
	public void move() {
		// Moves randomly left/right and up/down
		if ((!direcHorz&&this.x<=0) || (direcHorz&&this.x>=parent.getWidth()) || Math.random() < 0.04) direcHorz = !direcHorz;
		if (Math.random() < 0.04) destVert = (int)(ybound+Math.random()*(parent.getHeight()-ybound));
		this.movexy(
			(int)(Math.random()*this.sx*(direcHorz ? 1 : -1)),
			(int)(this.sy*(this.y <= destVert ? 1 : -1)));
	}
}