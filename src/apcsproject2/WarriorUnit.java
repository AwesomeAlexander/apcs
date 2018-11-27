package apcsproject2;

import java.awt.Point;

/**
 * ArcherUnit
 */
public class WarriorUnit extends Unit {
	public static String TYPE = "W";

    public WarriorUnit(GamePanel panel, GamePlayer owner, int x, int y) {
        super(panel, owner, TYPE, x, y);
        setStats(HEALTH, SPEED, DAMAGE, RANGE, COOLDOWN);
    }
    
    @Override
    public void interact(GameEntity other) {
        if (!(other instanceof Unit)) return;
        Unit o = (Unit) other;
        
        if (this.distanceto(o) <= this.attackRange && !this.owner.equals(o.owner)) {
        	this.attack(o);
        }
    }
    
    @Override
    public void attack(Unit other) {

        // Targets unit
    	this.target = new Point(other.getX(), other.getY());

        // Attack
        if (this.distanceto(other) < this.attackRange/2 && cooldownTimer <= 0) {
        	other.damage(this.attackDamage);
        	cooldownTimer = this.attackCooldown; // Reset cooldown
        }
    }
}