package apcsproject2;

public class BruteUnit extends Unit {
	public static String TYPE = "B";
	
	static int // Unit Stats
    HEALTH = 150,
    SPEED = 2,
    DAMAGE = 20,
    RANGE = 25,
    COOLDOWN = 200;

	public BruteUnit(GamePanel panel, GamePlayer owner, int x, int y) {
		super(panel, owner, TYPE, x, y);
        setStats(HEALTH, SPEED, DAMAGE, RANGE, COOLDOWN);
	}
}
