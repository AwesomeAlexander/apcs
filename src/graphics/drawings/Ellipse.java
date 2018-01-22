package graphics.drawings;

import java.awt.Color;
import java.awt.Graphics;
import utils.Drawable;

public class Ellipse implements Drawable {
	public Color color;
	public int x,y,width,height;

	public Ellipse(int x,int y) {
		this(x,y,50,50,Color.GREEN);
	}

	public Ellipse(int x,int y,int width,int height,Color c) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.color = c;
	}

	@Override
	public void draw(Graphics g) {
		g.drawOval(this.x, this.y, this.width, this.height);
		g.setColor(this.color);
		g.fillOval(this.x, this.y, this.width, this.height);
	}
}