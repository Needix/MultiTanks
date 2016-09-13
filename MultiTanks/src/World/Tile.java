package World;

import java.awt.Color;
import java.awt.Point;

public class Tile {
	private Point pos; public Point getPosition() { return pos; }
						public int getX() { return pos.x; } public void setX(int x) { pos.x = x; }
						public int getY() { return pos.y; } public void setY(int y) { pos.y = y; }
	private Color color; public Color getColor() { return color; }
	
	public Tile(int x, int y, Color c) {
		this.pos = new Point(x,y);
		color = c;
	}
	public Tile(Point p, Color c) {
		this.pos = p;
		this.color = c;
	}
}
