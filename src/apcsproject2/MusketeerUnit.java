package apcsproject2;

/**
 * ArcherUnit
 */
public class MusketeerUnit extends Unit {
	public static String TYPE = "M";

    static int // Unit Stats
        HEALTH = 50,
        SPEED = 2,
        DAMAGE = 10,
        RANGE = 200,
        COOLDOWN = 100;

    public MusketeerUnit(GamePanel panel, GamePlayer owner, int x, int y) {
        super(panel, owner, TYPE, x, y);
        setStats(HEALTH, SPEED, DAMAGE, RANGE, COOLDOWN);
    }

    @Override
    public void attack(Unit other) {
        if (cooldownTimer > 0) return;

        // Fire Projectile
        // System.out.println("Archer "+this+" firing projectile!");
        panel.entities.add(new Projectile(owner, x, y, this.angleto(other)+Math.PI,
        		attackDamage,
        		attackRange/4,
        		10,
        		5,
        		0));
        
        // Reset Cooldown
        cooldownTimer = this.attackCooldown;
    }
}