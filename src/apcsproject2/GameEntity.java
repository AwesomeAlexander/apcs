package apcsproject2;

import utils.Drawable;

/**
 * Testing
 */
public interface GameEntity extends Drawable {
    public void interact(GameEntity other);
    public void update();
}