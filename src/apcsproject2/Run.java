package apcsproject2;

import java.awt.Color;
// import java.util.Scanner;

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
	 * Subroutine for setting up a side's troops based on a map
	 */
	public static void setupSide(GamePanel game, GamePlayer owner, char[][] unitMap, int direction, int placex, int placey, int spacingx, int spacingy) {
		int savex = placex;
		for (char[] unitLine : unitMap) {
			for (char unit : unitLine) {
				switch (unit) {
					case 'w': game.addUnit(new WarriorUnit(game, owner, placex, placey));break;
					case 'a': game.addUnit(new ArcherUnit(game, owner, placex, placey));break;
					case 'c': game.addUnit(new CavalryUnit(game, owner, placex, placey));break;
					case 'n': break;// adds space
					case ' ': placex -= spacingx + Unit.SIZE; break;// counteracts
				}
				placex += spacingx + Unit.SIZE;
			}
			placex = savex;
			placey += direction * (spacingy + Unit.SIZE);
		}
	}
}