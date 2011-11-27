import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Gui extends JPanel {
	private Field field; // The bouncing ball panel

	/** Creates a panel with the controls and bouncing ball display. */
	public Gui() {
		//... Create components
		field = new Field();        
		JButton startButton = new JButton("Start");        
		JButton stopButton  = new JButton("Stop");
		JButton leftButton  = new JButton("Left");
		JButton rightButton  = new JButton("Right");
		JButton upButton  = new JButton("Up");
		JButton downButton  = new JButton("Down");

		/* +===============+----+----------+
		 * |  ___________  |    | +-+   ^  |
		 * *O| LISTENERS |O*    | | |  ( ) |
		 *   |___________|      | +-+   V  |
		 *       \   /          +----------+
		 */
		field.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0), // 0 mean no modification (like control or alt)
				"left");
		field.getActionMap().put("left", new SpeedAction(-1, 0));
		field.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0),
				"right");
		field.getActionMap().put("right", new SpeedAction(1, 0));
		field.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0),
				"up");
		field.getActionMap().put("up", new SpeedAction(0, -1));
		field.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0),
				"down");
		field.getActionMap().put("down", new SpeedAction(0, 1));
		field.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(
				KeyStroke.getKeyStroke(KeyEvent.VK_SPACE, 0),
				"start");
		field.getActionMap().put("start", new StartAction(true));
		leftButton.addActionListener(new SpeedAction(-1, 0));
		rightButton.addActionListener(new SpeedAction(1, 0));
		upButton.addActionListener(new SpeedAction(0, -1));
		downButton.addActionListener(new SpeedAction(0, 1));
		startButton.addActionListener(new StartAction(true));

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
		this.add(field       , BorderLayout.CENTER);
	}


	class SpeedAction extends AbstractAction {
		private Point speed;
		public SpeedAction (int x, int y) {
			speed = new Point(x, y);
		}
		public void actionPerformed(ActionEvent e) {
			field.addSpeed(speed.x, speed.y);
		}
	}
	class StartAction extends AbstractAction {
		boolean start;
		public StartAction (boolean start) {
			this.start = start;
		}
		public void actionPerformed(ActionEvent e) {
			field.setAnimation(start);
			start = !start;
			((JButton) e.getSource()).setText(start ? "Start" : "Stop");
		}
	}

	public static void main(String[] args) {
		JFrame win = new JFrame("Bouncing Ball Demo");
		win.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		win.setContentPane(new Gui());

		win.pack();
		win.setVisible(true); 
	}

}
