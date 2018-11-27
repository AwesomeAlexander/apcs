package apcsproject2;

/**
 * ArcherUnit
 */
public class ArtilleryUnit extends Unit {
	public static String TYPE = "A";

    static double // Unit Stats
        HEALTH = 25,
        SPEED = 1.5,
        DAMAGE = 10,
        RANGE = 500,
        COOLDOWN = 250;

    public ArtilleryUnit(GamePanel panel, GamePlayer owner, int x, int y) {
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
        		7.5,
        		15,
        		40));
        
        // Reset Cooldown
        cooldownTimer = this.attackCooldown;
    }
}