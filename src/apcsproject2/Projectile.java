package apcsproject2;

import java.awt.Color;
import java.awt.Graphics;

import utils.BasicEntity;

/**
 * Projectile fired by an ArcherUnit
 */
public class Projectile extends BasicEntity implements GameEntity {

    static final int SPEED = 10, DISTANCE = 10;

    double damage, health;
    GamePlayer owner;

    public Projectile(GamePlayer owner, int x, int y, double direction, double damage, double range) {
        super(x, y, 1.0*SPEED, direction);
        this.owner = owner;
        this.damage = damage;
        this.health = range;
    }

    @Override
    public void draw(Graphics g) {
        g.setColor(Color.BLACK);
        g.drawOval(x, y, 5, 5);
    }

    @Override
    public void update() {
        this.moveda(speed, direction);
        this.health--;
    }

    @Override
    public void interact(GameEntity other) {
        if (!(other instanceof Unit)) return;
        Unit o = (Unit) other;
        
        if (this.distanceto(o) <= DISTANCE && !this.owner.equals(o.owner)) {
            o.damage(damage);
            health = 0;
        }
    }
}