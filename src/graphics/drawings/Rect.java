package graphics.drawings;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import utils.Drawable;

public class Rect extends Rectangle implements Drawable {
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
		g.drawRect(this.x, this.y, this.width, this.height);
		g.setColor(this.color);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
}