package apcsproject2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import utils.BasicEntity;

/**
 * Unit
 */
public abstract class Unit extends BasicEntity implements GameEntity {
	
	public static String TYPE;

    static int SIZE = 25;
    static int // Unit Stats - Default
        HEALTH = 100,
        SPEED = 3,
        DAMAGE = 10,
        RANGE = 50,
        COOLDOWN = 20;

    protected GamePlayer owner;
    protected GamePanel panel;
    protected double fullhealth, attackDamage, attackRange, attackCooldown, cooldownTimer, health;
    protected String type;
    protected Point target;

    public Unit(GamePanel panel, GamePlayer owner, String type, int x, int y) {
        super(x, y, SIZE);
        this.owner = owner;
        this.panel = panel;
        this.type = type;
    };

    public void setStats(double health, double speed, double damage, double range, double cooldown) {
        this.fullhealth = health;
        this.speed = speed;
        this.attackDamage = damage;
        this.attackRange = range;
        this.attackCooldown = cooldown;

        this.cooldownTimer = attackCooldown;
        this.health = fullhealth;
    }

    @Override
    public void draw(Graphics g) {

        // Drawing line to target
        if (this.target != null) {
            g.setColor(Color.GRAY);
            g.drawLine(x, y, target.x, target.y);
        }

        // Draws object
        g.setColor(new Color((int)Math.max(0,health/fullhealth*255.99),0,0));
        g.fillRect(x-size/2, y-size/2, size, size);
        g.setColor(owner.color);
        g.drawRect(x-1-size/2, y-1-size/2, size+1, size+1);

        g.drawString(type, x-size/4, y+size/4);
    }

    /**
     * Updates the entity with each tick.
     */
    public void update() {

        if (this.target != null) {
            if (this.distanceto(target.x, target.y) >= this.size/3.0)
                this.moveda(speed, this.angleto(target.x, target.y)+Math.PI);
            else
                this.target = null;
        }

        if (cooldownTimer > 0) cooldownTimer--;
    }
    
    /**
     * Attacks another Unit.
     */
    public void attack(Unit other) {
        other.damage(this.attackDamage);
    }

    public Point setTarget(Point p) {
        return this.target = p;
    }

    /**
     * Causes damage to this entity
     */
    public void damage(double damage) {
        this.health -= damage;
    }
    
    @Override
    public void interact(GameEntity other) {
        if (!(other instanceof Unit)) return;
        Unit o = (Unit) other;
        
        if (this.distanceto(o) <= this.attackRange && !this.owner.equals(o.owner) && cooldownTimer <= 0) {
        	this.attack(o);
        	cooldownTimer = this.attackCooldown; // Reset cooldown
        }
    }
}