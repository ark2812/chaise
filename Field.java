import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.ArrayList;

public class Field extends JPanel {
	public static final Point DIM = new Point(800, 450);
	private Ball ball;
	private ArrayList<Element> map;

	private int interval  = 35; // Milliseconds between updates.
	private Timer timer; // Timer fires to anmimate one step.
	boolean loaded = false;

	public Field () {
		setPreferredSize(new Dimension(DIM.x, DIM.y));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
		timer = new Timer(interval, new TimerAction());

		ball = new Ball(new Point(20, DIM.y - Ball.DIAMETER));

		/* +-----------+
		 * | ___       |
		 * |      ____ |
		 * |           |
		 * |  __       |
		 * +-----------+
		 */
		map = new ArrayList<Element>();
		map.add(new Rectangle(new Point(50, 50), new Point(25, 4*Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(50, 200), new Point(25, 4*Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(50, 350), new Point(100, 2*Rectangle.BLOCKWIDTH)));

		map.add(new Rectangle(new Point(200, 150), new Point(50, Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(200, 225), new Point(50, 3*Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(200, 325), new Point(50, 3*Rectangle.BLOCKWIDTH)));

		map.add(new Rectangle(new Point(300, 150), new Point(25, 4*Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(300, 300), new Point(25, 4*Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(325, 200), new Point(25, 3*Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(350, 325), new Point(50, 3*Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(375, 175), new Point(25, 5*Rectangle.BLOCKWIDTH)));

		map.add(new Rectangle(new Point(450, 200), new Point(50, 5*Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(525, 175), new Point(25, 6*Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(475, 320), new Point(75, 3*Rectangle.BLOCKWIDTH)));

		map.add(new Rectangle(new Point(600, 200), new Point(50, 3*Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(600, 325), new Point(50, 4*Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(650, 250), new Point(50, 3*Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(675, 200), new Point(50, 2*Rectangle.BLOCKWIDTH)));
		map.add(new Rectangle(new Point(700, 325), new Point(50, 2*Rectangle.BLOCKWIDTH)));
	}

	/** Turn animation on or off.
	 * @param turnOnOff Specifies state of animation.
	 */
	public void setAnimation (boolean state) {
		if (state) {
			timer.start();
		} else {
			timer.stop();
		}
	}

	public void move (int factor) {
		ball.move(factor);
	}
	public void jump () {
		ball.jump();
	}
	public void paintComponent (Graphics g) {
		super.paintComponent(g); // Paint background, border
		for (Element e : map) {
			e.draw(g);
		}
		ball.draw(g);
	}

	class TimerAction implements ActionListener {
		/** ActionListener of the timer.  Each time this is called,
		 *  the ball's position is updated, creating the appearance of
		 *  movement.
		 *@param e This ActionEvent parameter is unused.
		 */
		public void actionPerformed(ActionEvent e) {
			if (loaded) {
				ball.setBounds(getWidth(), getHeight());
				ball.move(map);
				repaint(); // Repaint indirectly calls paintComponent.
			} else {
				if (0 < getWidth() && 0 < getHeight())
					loaded = true; // Else setBound is called with getWidth() == 0 because the window is not loaded yet
			}
		}
	}
}
