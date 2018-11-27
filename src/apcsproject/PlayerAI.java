package apcsproject;

public class PlayerAI extends Player {
	
	String[] controls;
	
	public PlayerAI(int x,int y,String color) {
		super(x,y,run.PLAYER_STARTINGAMOUNT,color); // Integer.MAX_VALUE moneyz? nah
		
		this.controls = new String[] {
				"1",
				"2",
				"3",
				"4",
				"5",
				"6",
				"7",
				"8",
				"9"};
	}
	
	@Override
	public void userInteract() {
		
		if (Math.random() > 0.0125) return;
		
		// Selects a random object and does 3 random actions
		this.selected = this.objects.get((int)(Math.random()*this.objects.size()));
		this.selected.userInteract();
		String ctrl = this.controls[(int)(Math.random()*this.controls.length)];
		if (this.keys.get(ctrl) != null) this.keys.get(ctrl).run();
		
		// Randomly attacks
		if (Math.random() < 0.01) {
			Player target = run.players.get((int)(Math.random()*run.players.size()));
			this.units.forEach((Unit u)->{u.track(target.getX(),target.getY());});
		}
	}
	
	@Override
	public boolean removeCash(double amount) {
		if (amount == 0) return true;
		if (amount > this.money) {
			//System.out.println("You don't have enough money for that!");
			return false;
		}
		this.money -= amount;
		//System.out.println("You just spent $"+amount+", you have $"+this.money+" left.");
		return true;
	}
}
