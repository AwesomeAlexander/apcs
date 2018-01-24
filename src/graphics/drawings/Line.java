package graphics.drawings;

import java.awt.geom.Line2D.Double;
import java.awt.Color;
import java.awt.Graphics;

import utils.Drawable;

public class Line extends Double implements Drawable {
	static final long serialVersionUID = 42L;

	Color c;
	public Line(int x1,int y1,int x2,int y2,Color c) {
		super();
		this.setLine(x1, y1, x2, y2);
		this.c = c;
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(c);
		g.drawLine((int)x1, (int)y1, (int)x2, (int)y2);
	}
}