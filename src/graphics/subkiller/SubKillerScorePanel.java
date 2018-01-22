package graphics.subkiller;

import javax.swing.*;
import java.awt.*;

/**
 * SubKillerScorePanel
 */
public class SubKillerScorePanel extends JPanel {
	private int score = 0;

	public SubKillerScorePanel() {
		this.setLayout(new GridLayout(1,2));

		JPanel difficulty = new JPanel();
		JLabel diffLabel = new JLabel("Difficulty: ");
		JSlider diffSlider = new JSlider(1,5,1);

		diffSlider.setMajorTickSpacing(2);
		diffSlider.setMinorTickSpacing(1);

		difficulty.add(diffLabel);
		difficulty.add(diffSlider);
	}
}