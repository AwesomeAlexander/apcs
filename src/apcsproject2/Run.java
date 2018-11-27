package apcsproject2;

import java.awt.Color;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
// import java.util.Scanner;
import java.util.List;

import javax.swing.JFrame;

/**
 * Run this file
 */
public class Run {
	public static void main(String[] args) {
		// Code runs from here.
		JFrame window = new JFrame("Strategy Game");
		MainPanel panel = new MainPanel();
		window.setContentPane(panel);
        window.setSize(800,800);
        window.setLocation(150,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);

		startGame(panel.game);
	}

	/**
     * Subroutine for starting the game.
     */
    public static void startGame(GamePanel game) {
		
		String name = "Player";
		// Have user enter name. (Haven't tested)
        // Scanner s = new Scanner(System.in);
		// System.out.println("Please enter your name.");
		// do {name = s.next();} while (name.equals("Player") || name.equals("AI"));
		// s.close();
        
        GamePlayer player = new GamePlayer(name, Color.ORANGE);
        GamePlayer ai = new GamePlayer("AI", Color.BLUE
			/*new Color((float)(Math.random()*256),(float)(Math.random()*256),(float)(Math.random()*256))*/);
		game.players.add(player);
		game.players.add(ai);
			
		// Setting up players
		
		int border = 30, spacing = 5;
		setupSide(game, ai, UnitMaps.getAIMap(), 1, border, border, spacing, spacing);
		setupSide(game, player, UnitMaps.getPlayerMap(), -1, border, game.getHeight()-border, spacing, spacing);
		
		game.listener.player = player;

		game.requestFocusInWindow();
		game.timer.start();
	}
    
    /**
	 * Gives all the Unit types
	 * @return List of Unit Classes
	 */
	static List<Class<? extends Unit>> unitTypes() {
		ArrayList<Class<? extends Unit>> out = new ArrayList<Class<? extends Unit>>();
		out.add(WarriorUnit.class);
		out.add(KamikazeUnit.class);
		out.add(MusketeerUnit.class);
		out.add(BruteUnit.class);
		out.add(CavalryUnit.class);
		out.add(ArtilleryUnit.class);
		return out;
	}
	
	/**
	 * Subroutine for setting up a side's troops based on a map
	 */
	public static void setupSide(GamePanel game, GamePlayer owner, char[][] unitMap, int direction, int placex, int placey, int spacingx, int spacingy) {
		int savex = placex;
		
		// Looping through map
		for (char[] unitLine : unitMap) {
			for (char unitChar : unitLine) {
				
				// For each character in the map...
				
				if (unitChar == ' ') continue; // Spaces don't matter. No need to run through types.
				
				if (unitChar != 'n') // 'n' skips the following loop
				
				// Loop through Unit types, check if character corresponds to any
				for (Class<? extends Unit> type : unitTypes()) {
					try {
						if (unitChar == // Checking if the char is equal to
							// the 'type' class literal's static field TYPE,
							// corresponding to a string, turned to a character (e.g. "W" -> 'w')
							type.getField("TYPE").get(null).toString().toLowerCase().charAt(0)
							) { // If it matches the type,
							game.addUnit(
									type // Create an instance of class, with given parameters
									.getConstructor(GamePanel.class, GamePlayer.class, int.class, int.class)
									.newInstance(game, owner, placex, placey)
									);
							break; // Found correct type! Exit inner loop, move on to next square
						}
					} catch ( // Catching a lot of exceptions
							NoSuchMethodException |
							NoSuchFieldException |
							IllegalAccessException |
							InvocationTargetException |
							InstantiationException
							e) {
						e.printStackTrace();
					}
				}
				
				// Previous method
//				switch (unit) {
//					case 'w': game.addUnit(new SwordsmanUnit(game, owner, placex, placey));break;
//					case 'a': game.addUnit(new MusketeerUnit(game, owner, placex, placey));break;
//					case 'c': game.addUnit(new CavalryUnit(game, owner, placex, placey));break;
//					case 'b': game.addUnit(new BruteUnit(game, owner, placex, placey));break;
//					case 'k': game.addUnit(new KamikazeUnit(game, owner, placex, placey));break;
//					case 'n': break;// adds space
//					case ' ': placex -= spacingx + Unit.SIZE; break;// counteracts
//				}
				
				placex += spacingx + Unit.SIZE;
			}
			placex = savex;
			placey += direction * (spacingy + Unit.SIZE);
		}
	}
}