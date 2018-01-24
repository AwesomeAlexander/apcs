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
	private int explosionFrameNumber;

	// public Submarine() {super();}
	public Submarine(SubKillerPanel parent,int x,int y,int ybound) {
		super(x, y, 30, Color.BLACK);
		this.direcHorz = true;
		this.destVert = this.x;
		this.parent = parent;
		this.ybound = ybound;
		this.sx = 5;
		this.sy = 3;
		this.explosionFrameNumber = 0;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(this.color);
		g.fillOval(this.x-this.size,this.y-this.size/2,this.size*2,this.size);

		if (this.explosionFrameNumber > 0) {
			// Draws an "explosion" that grows in size as the number of
			// frames since the initial explosion.
		g.setColor(Color.YELLOW);
		g.fillOval(this.x - 4*explosionFrameNumber,
				this.y - 2*explosionFrameNumber,
				8*explosionFrameNumber,
				4*explosionFrameNumber);
		g.setColor(Color.RED);
		g.fillOval(this.x - 2*explosionFrameNumber,
				this.y - explosionFrameNumber/2,
				4*explosionFrameNumber,
				explosionFrameNumber);
		}
	}

	@Override
	public void move() {
		if (this.explosionFrameNumber > 0) {
			this.explosionFrameNumber++;
			return;
		}
		// Moves randomly left/right and up/down
		if ((!direcHorz&&this.x<=0) || (direcHorz&&this.x>=parent.getWidth()) || Math.random() < 0.04) direcHorz = !direcHorz;
		if (Math.random() < 0.01) destVert = (int)(ybound+Math.random()*(parent.getHeight()-ybound));
		this.movexy(
			(int)(Math.random()*this.sx*(direcHorz ? 1 : -1)),
			(int)(this.sy*(this.distanceto(this.x,destVert) <= 4 ? 0 : this.y <= destVert ? 1 : -1)));
	}

	public void setSpeed(int sx,int sy) {this.sx=sx;this.sy=sy;}
	public void setSpeed(int sx) {this.sx=sx;}
	public void explode() {this.explosionFrameNumber = 1;}
	public int getExplosionStatus() {return this.explosionFrameNumber;}
}