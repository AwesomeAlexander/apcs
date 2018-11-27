package apcsproject2;

import java.awt.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * GamePlayer
 */
public class GamePlayer {

    String name;
    Color color;

    List<Unit> units;

    public GamePlayer(String name) {this(name,Color.WHITE);}
    public GamePlayer(String name, Color color) {
        this.name = name;
        this.color = color;
        this.units = new ArrayList<Unit>();
    }
}