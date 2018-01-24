package graphics.subkiller;

import javax.swing.*;
import java.awt.*;

/**
 * Main
 */
public class Main {
	public static void main(String[] args) {
		JFrame window = new JFrame("SubKiller");
        window.setSize(400, 600);
        window.setLocation(500, 300);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		// Total container
		JPanel container = new JPanel();
		container.setLayout(new BorderLayout());

		// Main Content
		SubKillerPanel content = new SubKillerPanel();
		
		// Menu stuff & UI
		JMenuBar menuBar = new JMenuBar();
		JMenu subkillerMenu = new JMenu("Sub Killer");
		JMenu optionsMenu = new JMenu("Options");
		JMenuItem about = new JMenuItem("About"); about.addActionListener(content.listener);
		JMenuItem quit = new JMenuItem("Quit"); quit.addActionListener(content.listener);
		JMenuItem restart = new JMenuItem("Restart"); restart.addActionListener(content.listener);
		
		subkillerMenu.add(about);
		subkillerMenu.addSeparator();
		subkillerMenu.add(quit);
		subkillerMenu.add(restart);

		menuBar.add(subkillerMenu);
		menuBar.add(optionsMenu);

		// Slider below
		SubKillerScorePanel scorePanel = new SubKillerScorePanel();
		// scorePanel.addActionListener(content.listener); // TODO: FIX THIS

		// Adding stuffs to window
		container.add(menuBar,BorderLayout.NORTH);
		container.add(scorePanel,BorderLayout.SOUTH);
		container.add(content,BorderLayout.CENTER);
		window.setContentPane(container);
    
		window.setVisible(true);
		window.setResizable(false); // don't want resizeability
	}
}