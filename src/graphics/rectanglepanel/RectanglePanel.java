package graphics.plainpanel;

import javax.swing.*;
import java.awt.*;

// This is the specification for the custom container panel 
// that will be added to the JFrame object 'window'
public class RectanglePanel extends JPanel {

	public RectanglePanel() {
		// Set current object's layout
		this.setLayout(new BorderLayout());
		
		FramedPanel upper = new FramedPanel(Color.RED);
		new RectanglePanelListener(upper);
		
		// south will hold the other 2 JPanel objects
		// (no need to create custom JPanel class for this)
		JPanel lower = new JPanel();
		lower.setLayout(new GridLayout(2,1));
		
		// A JPanel object is a container that can hold other containers 
		// so 'south' will contain 2 other JPanel objects.
		JPanel lowerUP = new JPanel();
		JPanel lowerDOWN = new JPanel();
		
		// Set correct layout
		lowerUP.setLayout(new GridLayout(1,2));
		lowerDOWN.setLayout(new GridLayout(1,1));
		
		// Syntax: container.add(component)
		lowerUP.add(new JButton("Previous"));
		lowerUP.add(new JButton("Next"));
		
		lowerDOWN.add(new JButton("Submit"));
		
		lower.add(lowerUP);
		lower.add(lowerDOWN);

		this.add(upper,BorderLayout.CENTER);
		this.add(lower,BorderLayout.SOUTH);
	}

}