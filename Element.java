import java.awt.*;

public abstract class Element {
	protected Point pos; // coordinates upper left
	protected Point dim;

	public Element (Point pos, Point dim) {
		this.pos = new Point(pos);
		this.dim = new Point(dim);
	}

	abstract public void draw (Graphics g);

	public int  getX () {
		return pos.x;
	}

	public int  getY () {
		return pos.y;
	}

	public int getDimX () {
		return dim.x;
	}

}
