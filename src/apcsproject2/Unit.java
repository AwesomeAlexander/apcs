package apcsproject2;

import java.awt.Graphics;

import utils.Moveable;

/**
 * Unit
 */
public class Unit extends GameEntity implements Moveable {
    protected GamePlayer owner;
    protected int damage;

    public Unit() {
        // TODO: make constructer, add variables, etc
    };

    public void draw(Graphics g) {
        // Draws object
    }

    public void move() {
        // Moves around
    }

    @Override
    public void interact(GameEntity other) {
        // Interacts with other entities
    }
}