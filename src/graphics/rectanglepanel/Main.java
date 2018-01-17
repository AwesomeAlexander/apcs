package graphics.rectanglepanel;

import javax.swing.JFrame;

public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame("Rectangle Panel");
		window.setContentPane( new RectanglePanel() );
        window.setSize(500,500);
        window.setLocation(150,100);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }
    
} 