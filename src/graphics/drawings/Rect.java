package graphics.drawings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import utils.Drawable;

public class Rect extends Rectangle implements Drawable {
	static final long serialVersionUID = 42L;
	
	public Color color;

	public Rect(int x,int y) {
		super(x,y,50,50);
		this.color = Color.GREEN;
	}

	public Rect(int x,int y,int width,int height,Color c) {
		super(x,y,width,height);
		this.color = c;
	}

	@Override
	public void draw(Graphics g) {
		// g.setColor(Color.BLACK);
		// g.drawRect(this.x-1, this.y-1, this.width+1, this.height+1);
		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.width, this.height);
	}

	public boolean containsPoint(Point p) {
		return (this.x <= p.x && this.x+this.width >= p.x) &&
				(this.y <= p.y && this.y+this.height >= p.y);
	}
}