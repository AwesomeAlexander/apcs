package apcsproject;

import java.util.Map;

import utils.*;

public class Mine extends Building {
	/***** Variables *****/
	
	private int miningrate,minecooldown;
	
	/***** Constructors *****/
	public Mine(Player p, int x, int y) {
		super(p,x,y);
		this.size = 20;
		
		this.miningrate = run.DEFAULT_SPAWNCOOLDOWN;
		this.minecooldown = this.miningrate;
	}
	
	/***** Methods *****/
	
	@Override
	public String getInfo() {
		return "\n\nThis is a Mine (Building)."
				+ "\nCoordinates: "+this.x+", "+this.y
				+ "\nStats:"
				+ "\n- Health :: "+this.health
				+ "\n- Spawn Rate :: "+this.miningrate
				+ "\nControls:"
				+ "\n- 1 :: Manually mine something"
				+ "\n- 2 :: Increase mining rate by 10%"
				+ "\n- 0 :: Display info & stats";
	}
	
	@Override
	public void setKeys(Map<String, Runnable> keys) {
		keys.put("1",()->{
			System.out.println("Adding $"+run.DEFAULT_MINEPRODUCTION);
			this.owner.addCash(run.DEFAULT_MINEPRODUCTION);
		});
		keys.put("2",()->{
			if (this.miningrate < 50) {
				System.out.println("You've hit minimum spawn rate!");
				return;
			}
			
			this.upgrades++;
			if (!this.owner.removeCash(this.upgrades*5)) this.upgrades--;
			this.miningrate*=0.9;
		});
	}
	
	@Override
	public void move() {
		super.move();
		if (this.minecooldown > 0) {
			this.minecooldown--;
		} else {
			// System.out.println("Mining!");
			this.minecooldown = this.miningrate;
			this.owner.addCash(run.DEFAULT_MINEPRODUCTION);
		}
	}
	
	@Override
	public void draw() {
		Window.out.color("orange");
		Window.out.square(this.x,this.y,this.size);
		Window.out.color(this.owner.getCOLOR());
		Window.out.circle(this.x, this.y, this.size/3.0);
	}
}
