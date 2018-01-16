package apcsproject;

import utils.*;
import java.util.*;

public class run {

	// Variables
	public static final int // Constants
		PLAYER_STARTINGAMOUNT = 1000000,
		PLAYER_SELECTDISTANCE = 5,
		DEFAULT_SPAWNRADIUS = 50,
		DEFAULT_UNITCOST = 10,
		DEFAULT_MINEPRODUCTION = 1,
		DEFAULT_ACTIONCOOLDOWN = 5,//25,
		DEFAULT_BUILDINGHEALTH = 100,
		DEFAULT_SPAWNCOOLDOWN = 500,
		WINDOW_SIZE = 800,
		WINDOW_FRAMERATE = 100;
	public static int frameNum = 0;
	public static ArrayList<GameObject> objects;
	public static ArrayList<Player> players;
	
	public static void main(String[] args) {
		Window.size(WINDOW_SIZE, WINDOW_SIZE);
		Window.setFrameRate(WINDOW_FRAMERATE);
		
		objects = new ArrayList<GameObject>();
		players = new ArrayList<Player>();
		
		// Main player
		players.add(new Player());
		
		// AIs
		int awai = 100;
		players.add(new PlayerAI(awai,awai,"blue"));
		players.add(new PlayerAI(Window.width()-awai,awai,"green"));
		players.add(new PlayerAI(awai,Window.height()-awai,"magenta"));
		players.add(new PlayerAI(Window.width()-awai,Window.height()-awai,"yellow"));
	
		
		while (true) {
			Window.frame();frameNum++;
			Window.out.background("light green");
			
			updateObjects();
			
			// Regular for loop to avoid concurrent modification
			for (int i=0;i<players.size();i++) {
				Player p = players.get(i);
				p.update();
				if (p.objects.size() == 0) {
					players.remove(p);
					System.out.println("Player "+p.getCOLOR()+" just lost!");
					if (i == 0) System.exit(0); // Lose if main player died
					i--;
				}
			}
			
			if (players.size() == 0) {
				System.out.println("You won! :D Good job!");
				System.exit(0);
			}
		}
	}
	
	/**
	 * Game Computation & Object Interaction
	 */
	public static void updateObjects() {
		for (GameObject i : objects) {
			for (GameObject j : objects) {
				if (i == j) continue; // Not itself
				if (i.distanceto(j) < i.getSIZE()) {
					if (i instanceof Unit) {
						i.moveda(i.getSIZE()/10.0, i.angleto(j));
						if (!i.getOwner().equals(j.getOwner())) i.damage(j);
					}
					
				}
			}
		}
	}
}
