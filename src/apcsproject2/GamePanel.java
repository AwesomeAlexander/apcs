package apcsproject2;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
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

        // ForEach is causing errors, so replaced with manual foreach
        // doesn't seem to help
//        for (GameEntity ge : entities) {
//        	// System.out.println(ge);
//            ge.draw(g);
//        }
        for (Iterator<GameEntity> ge = entities.iterator();ge.hasNext();) {
        	ge.next().draw(g);
        }
    }

    /**
     * Updates the game every tick
     */
    public void update() {
    	
        tick++;// System.out.println("Updating, on tick "+tick);
        
        // System.out.println(listener.selected); // Debugging, printing selected troops

        for (int i=0;i<entities.size();i++) {
            GameEntity ge = entities.get(i);

            ge.update();

            for (int j=0;j<entities.size();j++) { // can't be foreach - archerunit adds projectiles
                ge.interact(entities.get(j));
            }

            if ((ge instanceof Unit && ((Unit)ge).health <= 0) || (ge instanceof Projectile && ((Projectile)ge).health <= 0)) {
            	GameEntity ent = entities.remove(i--);
            	if (ent instanceof Unit) ((Unit)ent).owner.units.remove(ent);
            }
       
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
        this.listener.selected.remove(unit);
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
    
    public void setTargetsRelative(List<Unit> units, int addX, int addY) {
    	for (Unit u : units) {
            u.target = new Point( // Point offset from their original position
                u.getX() + addX,
                u.getY() + addY
                );
        }
    }
    
    public void setTargetsRelativeTarget(List<Unit> units, int addX, int addY) {
    	for (Unit u : units) {
    		if (u.target == null) u.target = new Point(u.getX(),u.getY());
            u.target = new Point( // Point offset from target
                u.target.x + addX,
                u.target.y + addY
                );
        }
    }
    
    public void setTargets(List<Unit> units, int x, int y) {
    	for (Unit u : units) u.target = new Point(x,y); // Absolute set
    }
    
}