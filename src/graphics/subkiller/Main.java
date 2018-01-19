package graphics.subkiller;

import javax.swing.JFrame;

/**
 * Main
 */
public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame("Stuffs");
        window.setSize(400, 600);
        window.setLocation(500, 300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
		SubKillerPanel content = new SubKillerPanel();
        window.setContentPane(content);
    
		window.setVisible(true);
		window.setResizable(false); // don't want resizeability
	}
}