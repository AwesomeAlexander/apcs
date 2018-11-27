package apcsproject;

import java.util.Map;
import utils.*;

public class Unit extends GameObject {
	/***** Variables *****/
	
	private String type;
	private boolean tracking;
	private int trackingX,trackingY;
	private int upgrades;
	
	/***** Constructors *****/
	public Unit(Player p,Base b) {
		super(p,(int)(b.getX() + (Math.random()-0.5)*run.DEFAULT_SPAWNRADIUS), (int)(b.getY() + (Math.random()-0.5)*run.DEFAULT_SPAWNRADIUS));
		this.type = "Regular";
		
		this.health = 25;
		this.speed = 2;
		this.damage = 1;
		
		this.tracking = false;
		this.trackingX = 0;
		this.trackingY = 0;
		
		this.upgrades = 0;
	}
	
	/***** Methods *****/

	@Override
	public void draw() {
		Window.out.color(this.owner.getCOLOR());
		Window.out.circle(this.x, this.y, this.size = this.health/2);
	}

	@Override
	public void move() {
		if (this.tracking && Math.abs(this.x - this.trackingX) > 2 && Math.abs(this.y - this.trackingY) > 2) {
			this.moveda(this.speed, this.direction = this.angleto(trackingX, trackingY)+Math.PI);
		}
	}
	
	@Override
	public String getInfo() {
		return "\n\nThis is a "+this.type+" Unit."
				+ "\nCoordinates: "+this.x+", "+this.y
				+ "\nStats:"
				+ "\n- Health :: "+this.health
				+ "\n- Speed :: "+this.speed
				+ "\n- Damage :: "+this.damage
				+ "\nControls:"
				+ "\n- WASD :: Moves unit"
				+ "\n- 1 :: Increase the health by 10%"
				+ "\n- 2 :: Increase speed by 10%"
				+ "\n- 3 :: Increase damage by 10%"
//				+ "\n- 4 :: Nothing (yet)" // TODO
//				+ "\n- 5 :: Nothing (yet)" // TODO
//				+ "\n- 6 :: Nothing (yet)" // TODO
				+ "\n- 7 :: Make a Mine"
				+ "\n- 8 :: Make a Wall"
				+ "\n- 9 :: Make a Base"
				+ "\n- 0 :: Display info & stats";
	}
	
	public void setKeys(Map<String,Runnable> keys) {
		keys.put("1",()->{
			if (this.health > 50) {
				System.out.println("You've hit max health!");
				return;
			}
			
			this.upgrades++;
			if (!this.owner.removeCash(this.upgrades*10)) this.upgrades--;
			this.health*=1.1;
		});
		keys.put("2",()->{
			if (this.speed > 50) {
				System.out.println("You've hit max speed!");
				return;
			}

			this.upgrades++;
			if (!this.owner.removeCash(this.upgrades*10)) this.upgrades--;
			this.speed*=1.1;
		});
		keys.put("3",()->{
			if (this.damage > 25) {
				System.out.println("You've hit max damage!");
				return;
			}

			this.upgrades++;
			if (!this.owner.removeCash(this.upgrades*10)) this.upgrades--;
			this.damage*=1.1;
		});
		
		keys.put("7",()->{
			if (this.owner.removeCash(100))
				this.owner.makeBuilding(new Mine(this.owner,this.x,this.y));
		});
		keys.put("8",()->{
			if (this.owner.removeCash(50))
				this.owner.makeBuilding(new Wall(this.owner,this.x,this.y));
		});
		keys.put("9",()->{
			if (this.owner.removeCash(200))
				this.owner.makeBuilding(new Base(this.owner,this.x,this.y));
		});
		
		keys.put("w",()->{movexy(0, (int)-this.speed);this.tracking = false;});
		keys.put("a",()->{movexy((int)-this.speed, 0);this.tracking = false;});
		keys.put("s",()->{movexy(0, (int)this.speed);this.tracking = false;});
		keys.put("d",()->{movexy((int)this.speed, 0);this.tracking = false;});
	}

	public void track(int x, int y) {
		this.tracking = true;
		this.trackingX = x;
		this.trackingY = y;
	}
}
