package apcsproject2;

import java.awt.Point;

/**
 * CavalryUnit
 */
public class CavalryUnit extends Unit {

    static int // Unit Stats
        HEALTH = 75,
        SPEED = 5,
        DAMAGE = 20,
        RANGE = 100,
        COOLDOWN = 40;
        
    int powerup, powerupCooldown;

    public CavalryUnit(GamePanel panel, GamePlayer owner, int x, int y) {
        super(panel, owner, "C", x, y);
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
        }
    }

    @Override
    public void attack(Unit other) {

        if (cooldownTimer > 0) return;

        // Cavalry charge 'attack' - powerup
        // System.out.println("Cavalry "+this+" charging!");
        this.target = new Point(other.getX(), other.getY());
        this.speed = SPEED * 1.5;
        this.powerupCooldown = powerup;

        // Attack
        if (this.distanceto(other) < this.attackRange/10) other.damage(this.attackDamage);
        
        // Reset Cooldown
        cooldownTimer = this.attackCooldown;
    }
}