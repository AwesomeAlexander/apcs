package apcsproject2;

/**
 * ArcherUnit
 */
public class WarriorUnit extends Unit {

    public WarriorUnit(GamePanel panel, GamePlayer owner, int x, int y) {
        super(panel, owner, "W", x, y);
        setStats(HEALTH, SPEED, DAMAGE, RANGE, COOLDOWN);
    }
}