package apcsproject;

import java.util.Map;

import utils.*;

public class Base extends Building {
	/***** Variables *****/
	
	private int spawncooldown,spawnprice,cooldowncount;
	
	/***** Constructors *****/
	public Base(Player p, int x, int y) {
		super(p,x,y);
		this.size = 20;
		
		this.spawncooldown = run.DEFAULT_SPAWNCOOLDOWN;
		this.spawnprice = run.DEFAULT_UNITCOST;
		this.cooldowncount = this.spawncooldown;
	}
	
	/***** Methods *****/
	
	@Override
	public String getInfo() {
		return "\n\nThis is a Base (Building)."
				+ "\nCoordinates: "+this.x+", "+this.y
				+ "\nStats:"
				+ "\n- Health :: "+this.health
				+ "\n- Spawn Rate :: "+this.spawncooldown
				+ "\nControls:"
				+ "\n- 1 :: Manually spawn a unit"
				+ "\n- 2 :: Increase automatic spawn rate by 10%"
//				+ "\n- 3 :: Nothing (yet)" // TODO
//				+ "\n- 4 :: Nothing (yet)" // TODO
//				+ "\n- 5 :: Nothing (yet)" // TODO
//				+ "\n- 6 :: Nothing (yet)" // TODO
//				+ "\n- 7 :: Nothing (yet)" // TODO
//				+ "\n- 8 :: Nothing (yet)" // TODO
//				+ "\n- 9 :: Nothing (yet)" // TODO
				+ "\n- 0 :: Display info & stats";
	}
	
	@Override
	public void setKeys(Map<String, Runnable> keys) {
		keys.put("1",()->{this.owner.makeUnit(this,this.spawnprice);});
		keys.put("2",()->{
			if (this.spawncooldown < 100) {
				System.out.println("You've hit minimum spawn rate!");
				return;
			}
			
			this.upgrades++;
			if (!this.owner.removeCash(this.upgrades*10)) this.upgrades--;
			this.spawncooldown*=0.9;
		});
	}
	
	@Override
	public void move() {
		super.move();
		if (this.cooldowncount > 0) {
			this.cooldowncount--;
		} else {
			// System.out.println("Spawning!");
			this.cooldowncount = this.spawncooldown;
			this.owner.makeUnit(this,0);
		}
	}
	
	@Override
	public void draw() {
		Window.out.color("gray");
		Window.out.square(this.x,this.y,this.size);
		Window.out.color(this.owner.getCOLOR());
		Window.out.circle(this.x, this.y, this.size/3.0);
	}
}
