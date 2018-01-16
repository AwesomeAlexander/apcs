package graphics.interactivepanel;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame("Stuffs");
        window.setSize(400, 600);
        window.setLocation(500, 300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
		InteractivePanel content = new InteractivePanel();
		/*InteractivePanelListener listener = */new InteractivePanelListener(content);
        window.setContentPane(content);
    
        window.setVisible(true);
	}
}