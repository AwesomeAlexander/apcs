package apcsproject2;

import utils.BasicEntity;
import utils.Drawable;

/**
 * GameEntity
 */
public abstract class GameEntity extends BasicEntity implements Drawable {
    public abstract void interact(GameEntity other);
}