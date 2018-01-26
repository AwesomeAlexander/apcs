package graphics.simplepaint;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.SpringLayout;

/**
 * SimplePaintContainer
 */
public class SimplePaintContainer extends JPanel {
	static final long serialVersionUID = 42L;

	SimplePaintPanel drawingSpace;
	SimplePaintListener listener;
	JMenuBar menu;
	JPanel buttonPanel;

	public SimplePaintContainer() {
		super();

		// Object declaration

		this.menu = new JMenuBar();
		this.drawingSpace = new SimplePaintPanel();
		this.buttonPanel = new JPanel();
		this.listener = new SimplePaintListener(this.drawingSpace);

		// Menu Stuffs

		JMenu simplepaint = new JMenu("Simple Paint");
		JMenu edit = new JMenu("Edit");

		simplepaint.add(new JMenuItem("Quit")).addActionListener(listener);
		edit.add(new JMenuItem("Clear")).addActionListener(listener);
		edit.add(new JMenuItem("Undo")).addActionListener(listener);

		menu.add(simplepaint);
		menu.add(edit);
		
		// Button Stuffs

		buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
		for (String s : "Red, Green, Blue, Cyan, Magenta, Yellow, Custom, Clear".split(", "))
			buttonPanel.add(new JButton(s) {
				static final long serialVersionUID = 42L;
				{
					setSize(200,100);
					setMaximumSize(getSize());
					setAlignmentX(Component.CENTER_ALIGNMENT);
					addActionListener(listener);
				}
			});

		// Listener registration

		this.drawingSpace.addMouseListener(this.listener);
		this.drawingSpace.addMouseMotionListener(this.listener);

		// Adding components to master container

		this.setLayout(new BorderLayout());
		this.add(this.menu, BorderLayout.NORTH);
		this.add(this.drawingSpace, BorderLayout.CENTER);
		this.add(this.buttonPanel, BorderLayout.EAST);
	}
}