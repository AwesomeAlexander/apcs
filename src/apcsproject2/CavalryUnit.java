package apcsproject2;

import java.awt.Point;

/**
 * CavalryUnit
 */
public class CavalryUnit extends Unit {
	public static String TYPE = "C";

    static int // Unit Stats
        HEALTH = 75,
        SPEED = 5,
        DAMAGE = 25,
        RANGE = 100,
        COOLDOWN = 40;
        
    int powerup, powerupCooldown;

    public CavalryUnit(GamePanel panel, GamePlayer owner, int x, int y) {
        super(panel, owner, TYPE, x, y);
        setStats(HEALTH, SPEED, DAMAGE, RANGE, COOLDOWN);

        this.powerup = COOLDOWN/2;
        this.powerupCooldown = powerup;
    }

    @Override
    public void update() {
        super.update(); // Unit Update

        if (powerupCooldown > 0) powerupCooldown--;
        else {
            this.speed = SPEED;
            this.attackDamage = DAMAGE;
        }
    }

    @Override
    public void attack(Unit other) {

        // Cavalry charge 'attack' - powerup
        // System.out.println("Cavalry "+this+" charging!");
        this.target = new Point(other.getX(), other.getY());
        this.speed = SPEED * 1.5;
        this.attackDamage = DAMAGE * 1.5;
        this.powerupCooldown = powerup;

        // Attack
        if (this.distanceto(other) < this.attackRange/4) other.damage(this.attackDamage);
    }
}