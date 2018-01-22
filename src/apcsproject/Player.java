package apcsproject;

import java.util.*;
import utils.*;

public class Player extends BasicWindowObject {
	
	/***** Variables *****/
	
	// Default Access so other classes in this package can make edits to these
	Map<String, Runnable> keys;
	ArrayList<GameObject> objects;
	ArrayList<Unit> selectedGroup;
	
	protected ArrayList<Unit> units;
	protected ArrayList<Building> buildings;
	protected GameObject selected;
	protected int money;
	
	private int keyCooldown;
	private boolean clicking;
	private int xWhenClicked,yWhenClicked;
	
	/***** Constructors *****/
	
	public Player() {this(Window.width()/2,Window.height()/2,run.PLAYER_STARTINGAMOUNT,"red");}
	public Player(int x,int y,int startingAmount,String color) {
		super(x,y);
		this.objects = new ArrayList<GameObject>();
		this.units = new ArrayList<Unit>();
		this.buildings = new ArrayList<Building>();
		this.keys = new HashMap<String,Runnable>();
		this.money = startingAmount;
		this.color = color;
		this.selected = null;
		this.keyCooldown = 0;
		
		this.clicking = false;
		this.xWhenClicked = this.x;
		this.yWhenClicked = this.y;
		
		this.selectedGroup = new ArrayList<Unit>();
		
		// Initial Base
		this.makeBuilding(new Base(this,this.x,this.y));
		
		
	}
	
	/***** Methods *****/

	/**
	 * Makes a Unit at Base b, with optional cost
	 * @param b:Base - the Base it spawns from
	 * @return - the Unit it made
	 */
	public Unit makeUnit(Base b) {return this.makeUnit(b, run.DEFAULT_UNITCOST);}
	public Unit makeUnit(Base b,int cost) {
		if (!this.removeCash(cost)) return null;
		
		Unit u = new Unit(this,b);
		this.units.add(u);
		this.objects.add(u);
		run.objects.add(u);
		return u;
	}
	
	/**
	 * Makes a Building
	 * @param b:Building - what to make
	 * @return - the Building made
	 */
	public Building makeBuilding(Building b) {
		this.buildings.add(b);
		this.objects.add(b);
		run.objects.add(b);
		return b;
	}
	
	@Override
	public void update() {
		
		this.userInteract();
		
		super.update(); // Move then Draw
		
	}
	
	@Override
	public void draw() {
		
		// Separated for graphical
		for (Unit u : this.units) u.draw();
		for (Unit u : this.selectedGroup) {
			Window.out.color("white");
			Window.out.circle(u.getX(), u.getY(), u.getSIZE()/5.0);
		}
		for (Building b : this.buildings) b.draw();
		
		// TODO Let player move view around, moving everyone else oppositely
	}

	@Override
	public void move() {
		// Separated to avoid concurrent modification - buildings doing stuff to units
		for (Unit u : this.units) u.move();
		for (int i=0;i<this.buildings.size();i++) {
			Building b = this.buildings.get(i);
			b.move();
			if (b.health <= 0) {
				this.kill(b);
				i--;
			}
		}
		
		
	}
	
	@Override
	public void userInteract() {
		
		// Keyboard Detection
		
		if (this.keyCooldown > 0) {
			this.keyCooldown--;
			return;
		}
		
		for (String s : this.keys.keySet()) if (Window.key.pressed(s) && this.keys.get(s) != null) {
			this.keys.get(s).run();
			this.keyCooldown = run.DEFAULT_ACTIONCOOLDOWN;
			break;
		}
		
		// Mouse Logic
		
		// If it's logged to be NOT clicking and it's actually clicking,
		// update clicking status and initial position
		if (!this.clicking && Window.mouse.clicked()) {
			this.clicking = true;
			this.xWhenClicked = Window.mouse.getX();
			this.yWhenClicked = Window.mouse.getY();
		}
		
		// If it is logged to be NOT clicking, don't continue
		if (!this.clicking) return;
		// it MUST be logged to be clicking from this point on
		
		// If it isn't actually clicking, update
		if (!Window.mouse.clicked()) {
			this.clicking = false;
			return;
		}
		
		// Testing whether the position differs from when initially clicked
		if (!(Math.abs(this.xWhenClicked - Window.mouse.getX()) > run.PLAYER_SELECTDISTANCE &&
			Math.abs(this.yWhenClicked - Window.mouse.getY()) > run.PLAYER_SELECTDISTANCE)) {
			// Single Object Selection
			for (GameObject o : this.objects) {
				if (o.isTouched()) {
					this.selected = o;
					this.selected.userInteract();
					System.out.println(this.selected.getInfo());
				}
			}
		} else {
			// Unit Group Selection
			for (Unit u : this.units) {
				// Tests whether the coordinates are in the middle. I hope Math.min and Math.max aren't too expensive...
				if (Math.min(Math.max(Math.min(this.xWhenClicked, Window.mouse.getX()),  u.getX()  ),Math.max(this.xWhenClicked, Window.mouse.getX())) == u.getX() &&
					Math.min(Math.max(Math.min(this.yWhenClicked, Window.mouse.getY()),  u.getY()  ),Math.max(this.yWhenClicked, Window.mouse.getY())) == u.getY()) {
					// Stopping duplicates
					if (!this.selectedGroup.contains(u))
						this.selectedGroup.add(u);
				} else {
					this.selectedGroup.remove(u);
				}
			}
			
			Window.out.color("light gray");
			Window.out.rectangle((int)((this.xWhenClicked+Window.mouse.getX())/2), (int)((this.yWhenClicked+Window.mouse.getY())/2),
					Math.abs(this.xWhenClicked - Window.mouse.getX()), Math.abs(this.yWhenClicked - Window.mouse.getY()));
		}
	}
	
	public void kill(GameObject g) {
		this.objects.remove(g);
		run.objects.remove(g);
		
		if (g instanceof Unit) this.units.remove(g);
		else if (g instanceof Building) this.buildings.remove(g);
		else System.out.println("Ya dun goofed. This message should never appear.");
	}
	
	// Cash Functions
	public int getCash() {return this.money;}
	public void addCash(int amount) {this.money += amount;}
	
	/**
	 * Attempts to remove cash, returns false if not completed (not enough money)
	 * @param amount
	 * @return
	 */
	public boolean removeCash(double amount) {
		if (amount == 0) return true;
		if (amount > this.money) {
			System.out.println("You don't have enough money for that!");
			return false;
		}
		this.money -= amount;
		System.out.println("You just spent $"+amount+", you have $"+this.money+" left.");
		return true;
	}
	
}
