package apcsproject2;

import javax.swing.JFrame;

/**
 * Run this file
 */
public class Run {
	public static void main(String[] args) {
		// Code runs from here.
		JFrame window = new JFrame("Plain Panel");
		window.setContentPane( new MainPanel() );
        window.setSize(500,500);
        window.setLocation(150,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setVisible(true);
	}
}