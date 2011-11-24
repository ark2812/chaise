import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class Field extends JPanel {
	private Ball ball         = new Ball(0, 0, 2, 3);

	private int   interval  = 35; // Milliseconds between updates.
	private Timer timer; // Timer fires to anmimate one step.

	/** Set panel size and creates timer. */
	public Field() {
		setPreferredSize(new Dimension(200, 80));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		timer = new Timer(interval, new TimerAction());
	}

	/** Turn animation on or off.
	 *@param turnOnOff Specifies state of animation.
	 */
	public void setAnimation(boolean turnOnOff) {
		if (turnOnOff) {
			timer.start(); // start animation by starting the timer.
		} else {
			timer.stop(); // stop timer
		}
	}

	public void addSpeed (int x, int y) {
		ball.addSpeed(x, y);
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g); // Paint background, border
		ball.draw(g); // Draw the ball.
	}

	class TimerAction implements ActionListener {
		/** ActionListener of the timer.  Each time this is called,
		 *  the ball's position is updated, creating the appearance of
		 *  movement.
		 *@param e This ActionEvent parameter is unused.
		 */
		public void actionPerformed(ActionEvent e) {
			ball.setBounds(getWidth(), getHeight());
			ball.move(); // Move the ball.
			repaint(); // Repaint indirectly calls paintComponent.
		}
	}
}

