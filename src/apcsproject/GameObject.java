package apcsproject;

import java.util.HashMap;
import java.util.Map;
import utils.*;

public abstract class GameObject extends BasicObject {
	
	/***** Variables *****/
	
	protected int upgrades;
	protected int health;
	protected double damage;
	protected Player owner;
	
	/***** Constructors *****/
	
	public GameObject(Player p,int x,int y) {
		super(x,y);
		
		this.owner = p;
		this.upgrades = 0;
		this.health = 10;
		this.damage = 0;
	}
	
	/***** Methods *****/
	
	public abstract String getInfo();
	public abstract void setKeys(Map<String,Runnable> keys);
	
	@Override
	public void userInteract() {
		this.owner.keys = new HashMap<String,Runnable>();
		
		// Default Keys
		this.owner.keys.put("0",()->{System.out.println(this.getInfo());});
		this.owner.keys.put("m",()->{System.out.println("You currently have $"+this.owner.getCash());});
		this.owner.keys.put("space",()->{
			this.owner.selectedGroup.forEach((Unit u)->{
				u.track(Window.mouse.getX(),Window.mouse.getY());
			});
		});
		
		this.setKeys(this.owner.keys);
	}
	
	@Override
	public void update() {
		if (this.health < 0) this.owner.kill(this);
		else super.update();
	}
	
	public void damage(GameObject o) {
		o.takeDamage(this.damage);
	}
	public void takeDamage(double damage) {
		this.health -= damage;
	}
	
	public boolean isTouched() {
		return this.isTouched(Window.mouse.getX(),Window.mouse.getY());
	}
	public boolean isTouched(int x,int y) {
		return this.distanceto(x, y) < this.size;
	}
	
	public Player getOwner() {return this.owner;}
}
