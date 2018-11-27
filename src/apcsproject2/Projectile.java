package apcsproject2;

import java.awt.Color;
import java.awt.Graphics;

import utils.BasicEntity;

/**
 * Projectiles
 */
public class Projectile extends BasicEntity implements GameEntity {

    double damage, health, areaOfEffect;
    GamePlayer owner;

    public Projectile(GamePlayer owner, int x, int y, double direction, double damage, double range, double speed, double size, double area) {
        super(x, y, 1.0*speed, direction);
        this.owner = owner;
        this.damage = damage;
        this.health = range;
        this.size = (int) size;
        this.areaOfEffect = area;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        // g.drawOval(x, y, size, size);
        g.fillOval(x, y, size, size);
    }

    @Override
    public void update() {
        this.moveda(speed, direction);
        this.health--;
        
        // System.out.println(this+" has "+health);
    }

    @Override
    public void interact(GameEntity other) {
        if (!(other instanceof Unit)) return;
        Unit o = (Unit) other;
        
        if (o.owner.equals(this.owner)) return;
        
        if (this.areaOfEffect == 0) {
        	// Simple
	        if (this.distanceto(o) <= o.getSize()/2.0) {
	            o.damage(damage);
	            health = 0;
	        }
        } else {
        	// Area of Effect Calculations
        	
        	if (this.distanceto(o) <= o.getSize()/2.0) {
        		this.health = 1;
        	}
        	
        	if (this.health <= 1 && this.distanceto(o) <= o.getSize()/2.0 + this.areaOfEffect) {
        		o.damage(damage);
        	}
        }
    }
}