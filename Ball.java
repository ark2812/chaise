import java.awt.*;

public class Ball extends Animate {
	public static final int DIAMETER = 21;
	private static final int X_SPEED = 4;
	private static final int JUMP_SPEED = -8;

	public Ball (Point pos) {
		super(pos, new Point(DIAMETER, DIAMETER));
	}

	public void draw (Graphics g) {
		g.fillOval(pos.x, pos.y, DIAMETER, DIAMETER);
	}

	public void move (int factor) {
		speed.x = factor * X_SPEED;
	}

	public void jump () {
		speed.y = JUMP_SPEED;
	}
}
