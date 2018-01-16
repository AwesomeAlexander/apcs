package apcsproject;

import utils.*;

/**
 * A basic building that can be built from a 
 * @author Alexander Ng
 *
 */
public abstract class Building extends GameObject {
	/***** Variables *****/
	
	/***** Constructors *****/
	
	public Building(Player p, int x, int y) {
		super(p,x,y);
		this.health = run.DEFAULT_BUILDINGHEALTH;
	}
	
	/***** Methods *****/

	@Override
	public void move() {
		// This object doesn't move.
	}

}
