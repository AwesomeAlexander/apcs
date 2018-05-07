package apcsproject2;

import java.awt.*;
import javax.swing.*;

public class MainPanel extends JPanel {
    static final long serialVersionUID = 42L;
    
    public MainPanel() {
        // Set current object's layout
		this.setLayout(new BorderLayout());
		
		GamePanel game = new GamePanel();
        SidePanel side = new SidePanel();
        
        this.add(game,BorderLayout.CENTER);
        this.add(side,BorderLayout.EAST);
    }
}