package apcsproject;

import java.util.Map;

import utils.*;

public class Wall extends Building {
	/***** Variables *****/
	
	/***** Constructors *****/
	public Wall(Player p, int x, int y) {
		super(p,x,y);
		this.size = 15;
	}
	
	/***** Methods *****/
	
	@Override
	public String getInfo() {
		return "\n\nThis is a Wall (Building)."
				+ "\nCoordinates: "+this.x+", "+this.y
				+ "\nStats:"
				+ "\n- Health :: "+this.health
				+ "\nControls:"
				+ "\n- 1 :: Increase health by 10%"
				+ "\n- 2 :: Increase health by 50%"
				+ "\n- 3 :: Increase health by 100%"
				+ "\n- 0 :: Display info & stats";
	}
	
	@Override
	public void setKeys(Map<String, Runnable> keys) {
		keys.put("1",()->{
			if (this.health > 500) {
				System.out.println("You've hit max health!");
				return;
			}
			
			this.upgrades++;
			if (!this.owner.removeCash(this.upgrades*10)) this.upgrades--;
			this.health*=1.1;
		});
		keys.put("2",()->{
			if (this.health > 500) {
				System.out.println("You've hit max health!");
				return;
			}
			this.upgrades+=5;
			if (!this.owner.removeCash(this.upgrades*10)) this.upgrades-=5;
			this.health*=1.5;
		});
		keys.put("3",()->{
			if (this.health > 500) {
				System.out.println("You've hit max health!");
				return;
			}
			this.upgrades+=10;
			if (!this.owner.removeCash(this.upgrades*10)) this.upgrades-=10;
			this.health*=2;
		});
	}
	
	@Override
	public void draw() {
		Window.out.color("grey");
		Window.out.square(this.x,this.y,this.size);
		Window.out.color(this.owner.getCOLOR());
		Window.out.square(this.x, this.y,(int)(this.size/2.5));
	}
}
