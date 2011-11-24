import java.awt.*;

public class Ball {
	final static int DIAMETER = 21;

	private Point pos; // coordinates upper left

	private Point speed; // Pixels to move each time move() is called.

	private Point bound; // Maximum permissible x, y values.

	public Ball(int x, int y, int v_x, int v_y) {
		pos = new Point(x, y);
		speed = new Point(v_x, v_y);
	}

	public void setBounds(int width, int height) {
		bound = new Point(width - DIAMETER, height - DIAMETER);
	}

	public void addSpeed (int x, int y) {
		speed.x += x;
		speed.y += y;
	}

	public void move() {
		pos.x += speed.x;
		pos.y += speed.y;        

		//... Bounce the ball off the walls if necessary.
		if (pos.x < 0) { // If at or beyond left side
			pos.x = 0; // Place against edge and
			speed.x = -speed.x; // reverse direction.
		} else if (bound.x < pos.x) { // If at or beyond right side
			pos.x = bound.x; // Place against right edge.
			speed.x = -speed.x;  // Reverse direction.
		}

		if (pos.y < 0) {
			pos.y = 0;
			speed.y = -speed.y;
		} else if (pos.y > bound.y) {
			pos.y = bound.y;
			speed.y = -speed.y;
		}
	}

	public void draw(Graphics g) {
		g.fillOval(pos.x, pos.y, DIAMETER, DIAMETER);
	}

	public int  getDiameter() {
		return DIAMETER;
	}
	public int  getX() {
		return pos.x;
	}
	public int  getY() {
		return pos.y;
	}

	public void setPosition(int x, int y) {
		pos.x = x;
		pos.y = y;
	}
}

