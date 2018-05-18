package apcsproject2;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import javax.swing.Timer;

import javax.swing.BorderFactory;
import javax.swing.JPanel;

/**
 * GamePanel - Graphical Panel in which all the game is played
 */
public class GamePanel extends JPanel {
    static final long serialVersionUID = 42L;
    static final int TICKDELAY = 50;

    List<GameEntity> entities;
    List<GamePlayer> players;
    GameListener listener;
    Timer timer;
    int tick;
    boolean paused;

    public GamePanel() {
        super();
        this.listener = new GameListener(this);
        this.timer = new Timer(TICKDELAY, listener);

        this.setBackground(Color.GREEN);

        this.entities = new ArrayList<GameEntity>();
        this.players = new ArrayList<GamePlayer>();

        this.tick = 0;
        this.paused = false;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (listener.selectionSquare != null) listener.selectionSquare.draw(g);

        for (GameEntity ge : entities) {
            ge.draw(g);
        }
    }

    /**
     * Updates the game every tick
     */
    public void update() {
        tick++;

        // System.out.println("Updating, on tick "+tick);

        for (int i=0;i<entities.size();i++) {
            GameEntity ge = entities.get(i);

            ge.update();

            for (int j=0;j<entities.size();j++) { // can't be foreach - archerunit adds projectiles
                ge.interact(entities.get(j));
            }

            if ((ge instanceof Unit && ((Unit)ge).health <= 0) || (ge instanceof Projectile && ((Projectile)ge).health <= 0))
                entities.remove(i--);
        }

        // Check game end
        for (GamePlayer p : this.players) if (p.units.size() == 0) {
            if (p.name == "AI") System.out.println("You WON!!!!!");
            else System.out.println("You LOST!!!!!");
            System.exit(0);
        }

        this.repaint();
    }

    public void addUnit(Unit unit) {
        unit.owner.units.add(unit);
        this.entities.add(unit);
    }

    public void removeUnit(Unit unit) {
        unit.owner.units.remove(unit);
        this.entities.remove(unit);
    }

    public void pause() {
        this.paused = true;
        this.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 6));
        repaint();
    }

    public void unpause() {
        this.paused = false;
        this.setBorder(BorderFactory.createLineBorder(Color.GRAY, 3));
        repaint();
    }
}