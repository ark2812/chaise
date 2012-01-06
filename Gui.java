import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui extends JPanel {
	private Field field; // The bouncing ball panel

	/** Creates a panel with the controls and bouncing ball display. */
	public Gui () {
		//... Create components
		field = new Field();        
		JButton startButton = new JButton("Pause");        
		JButton leftButton  = new JButton("Left");
		JButton rightButton  = new JButton("Right");
		JButton upButton  = new JButton("Jump");
		JButton downButton  = new JButton("Stop");

		/* +===============+----+----------+
		 * |  ___________  |    | +-+   ^  |
		 * *O| LISTENERS |O*    | | |  ( ) |
		 *   |___________|      | +-+   V  |
		 *       \   /          +----------+
		 */
		StartAction start = new StartAction(false);
		field.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), // 0 mean no modification (like control or alt)
				"left");
		field.getActionMap().put("left", new SpeedAction(-1));
		field.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
				"right");
		field.getActionMap().put("right", new SpeedAction(1));
		field.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
				"up");
		field.getActionMap().put("up", new JumpAction());
		field.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
				"down");
		field.getActionMap().put("down", new SpeedAction(0));
		startButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_P, 0),
				"start");
		startButton.getActionMap().put("start", start);
		leftButton.addActionListener(new SpeedAction(-1));
		rightButton.addActionListener(new SpeedAction(1));
		upButton.addActionListener(new JumpAction());
		downButton.addActionListener(new SpeedAction(0));
		startButton.addActionListener(start);

		//... Layout inner panel with two buttons horizontally
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new FlowLayout());
		JPanel speedPanel = new JPanel();
		speedPanel.setLayout(new BorderLayout());
		buttonPanel.add(speedPanel);
		speedPanel.add(leftButton, BorderLayout.WEST);
		speedPanel.add(rightButton, BorderLayout.EAST);
		speedPanel.add(upButton, BorderLayout.NORTH);
		speedPanel.add(downButton, BorderLayout.SOUTH);
		speedPanel.add(startButton, BorderLayout.CENTER);

		//... Layout outer panel with button panel above bouncing ball
		this.setLayout(new BorderLayout());
		this.add(buttonPanel, BorderLayout.NORTH);
		this.add(field, BorderLayout.CENTER);
		field.setAnimation(true);
	}


	class JumpAction extends AbstractAction {
		public void actionPerformed (ActionEvent e) {
			field.jump();
		}
	}
	class SpeedAction extends AbstractAction {
		private int factor;
		public SpeedAction (int factor) {
			this.factor = factor;
		}
		public void actionPerformed (ActionEvent e) {
			field.move(factor);
		}
	}
	class StartAction extends AbstractAction {
		boolean start;
		public StartAction (boolean start) {
			this.start = start;
		}
		public void actionPerformed (ActionEvent e) {
			field.setAnimation(start);
			start = !start;
			((JButton) e.getSource()).setText(start ? "Resume" : "Pause");
		}
	}

	public static void main (String[] args) {
		JFrame win = new JFrame("Chaise");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		win.setContentPane(new Gui());

		win.pack();
		win.setVisible(true); 
	}

}
