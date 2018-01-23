package graphics.subkiller;

import javax.swing.*;
import java.awt.*;

/**
 * SubKillerScorePanel
 */
public class SubKillerScorePanel extends JPanel {
	static final long serialVersionUID = 42L;
	
	private int score = 0;

	public SubKillerScorePanel() {
		this.setLayout(new GridLayout(1,2));

		// Difficulty Slider
		JPanel difficulty = new JPanel();
		JLabel diffLabel = new JLabel("Difficulty: ");
		JSlider diffSlider = new JSlider(1,5,1);

		diffSlider.setMajorTickSpacing(2);
		diffSlider.setMinorTickSpacing(1);
		diffSlider.setPaintTicks(true);
		diffSlider.setPaintLabels(true);
		diffSlider.setSnapToTicks(true);

		difficulty.add(diffLabel);
		difficulty.add(diffSlider);

		JLabel scoreLabel= new JLabel("Score: "+score);

		this.add(difficulty);
		this.add(scoreLabel);
	}
}