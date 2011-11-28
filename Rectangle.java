import java.awt.*;

public class Rectangle extends Element {
	public static final int BLOCKWIDTH = 25;
	public Rectangle (Point pos, Point dim) {
		super(pos, dim);
	}
	public void draw (Graphics g) {
		g.fillRect(pos.x, pos.y, dim.x, dim.y);
	}
}
