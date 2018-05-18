package apcsproject2;

import java.awt.BorderLayout;
import javax.swing.JPanel;

public class MainPanel extends JPanel {
    static final long serialVersionUID = 42L;
    
    GamePanel game;
    SidePanel side;

    public MainPanel() {
        // Set current object's layout
		this.setLayout(new BorderLayout());
		
		this.game = new GamePanel();
        this.side = new SidePanel();
        
        this.add(game,BorderLayout.CENTER);
        // this.add(side,BorderLayout.EAST);
        // Sidepanel unnecessary atm
    }
}