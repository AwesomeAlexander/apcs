package apcsproject2;

import java.awt.Point;

/**
 * ArcherUnit
 */
public class KamikazeUnit extends Unit {
	public static String TYPE = "K";
	
	static int // Unit Stats
	    HEALTH = 50,
	    SPEED = 3,
	    DAMAGE = 60,
	    RANGE = 50,
	    COOLDOWN = 1;

    public KamikazeUnit(GamePanel panel, GamePlayer owner, int x, int y) {
        super(panel, owner, TYPE, x, y);
        setStats(HEALTH, SPEED, DAMAGE, RANGE, COOLDOWN);
    }
    
    @Override
    public void update() {
    	super.update();
    	
    	if (this.health < this.fullhealth/10) {
    		cooldownTimer = 0;
    		this.health = 0;
    	}
    	else {
    		cooldownTimer = 1;
    	}
    }
    
    @Override
    public void interact(GameEntity other) {
        if (!(other instanceof Unit)) return;
        Unit o = (Unit) other;
        
        if (cooldownTimer == 0 && this.distanceto(o) <= this.attackRange) {
        	this.attack(o);
        }
    }
}