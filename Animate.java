import java.awt.*;
import java.util.ArrayList;

public abstract class Animate extends Element {
	protected Point speed; // Pixels to move each time move() is called.
	protected Point bound; // Maximum permissible x, y values.
	private static final int MAX_SPEED = 8;

	public Animate (Point pos, Point dim) {
		super(pos, dim);
		this.speed = new Point(0, 0);
	}

	public void setBounds (int width, int height) {
		bound = new Point(width - dim.x, height - dim.y);
	}

	public boolean collide (ArrayList<Element> env) {
		boolean collide = false;
		for (Element e : env) {
			if (e.getX() < pos.x + dim.x && pos.x < e.getX() + e.getDimX() &&
					pos.y + dim.y - speed.y <= e.getY() && e.getY() <= pos.y + dim.y) {
				pos.y = e.getY() - dim.y;
				collide = true; // We can't break because a upper collide could have happend too
			}
		}
		return collide;
	}

	public void move (ArrayList<Element> env) {
		assert bound != null;
		pos.x += speed.x;
		pos.y += speed.y;        

		//... Bounce the ball off the walls if necessary.
		if (pos.x < 0) { // If at or beyond left side
			pos.x = 0; // Place against edge and
			speed.x = 0;
		} else if (bound.x < pos.x) { // If at or beyond right side
			pos.x = bound.x; // Place against right edge.
			speed.x = 0;
		}

		if (pos.y < 0) {
			pos.y = 0;
			speed.y = 0;
		} else if (pos.y >= bound.y) {
			pos.y = bound.y;
			speed.y = 0;
		} else if (!collide(env) && speed.y < MAX_SPEED) { // Free fall
			++speed.y;
		}
	}
}
