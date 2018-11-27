package graphics.drawingpanel;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame("Stuffs");
        window.setSize(400, 600);
        window.setLocation(500, 300);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
        DrawingPanel content = new DrawingPanel();
        window.setContentPane(content);
    
        window.setVisible(true);
	}
}