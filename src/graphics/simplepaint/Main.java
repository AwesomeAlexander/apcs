package graphics.simplepaint;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Main
 */
public class Main {

	public static void main(String[] args) {
		JFrame window = new JFrame("Simple Paint");
        JPanel content = new SimplePaintContainer();
        window.setContentPane(content);
        window.setSize(700,380);
        window.setLocation(100,100);
        window.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		window.setVisible(true);
		
	}
}