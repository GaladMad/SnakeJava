package pl.edu.pg.ftims.projekt;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * The abstract class is a pattern to create classes, such as: Background, Segment and Food.
 *
 */
public abstract class SBlock {
	protected Color color;
	protected int x;
	protected int y;
	protected int size;
	protected final int frameWidth = 2;
	
	public abstract void draw(Graphics2D graphics2d);

	public int getX() {
		return x;
	}

	public void setX(int centerX) {
		this.x = centerX;
	}

	public int getY() {
		return y;
	}

	public void setY(int centerY) {
		this.y = centerY;
	}
}
