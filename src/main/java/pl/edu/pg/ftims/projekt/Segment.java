package pl.edu.pg.ftims.projekt;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * The class contains atributes of segments of snake.
 *
 */
public class Segment extends SBlock{
	
	public Segment(int x, int y) {
		this.x = x;
		this.y = y;
		this.size = 20;
		color = Color.green;
	}

	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.setColor(Color.black);
		graphics2d.fillRect(x, y, size, size);
		graphics2d.setColor(color);
		graphics2d.fillRect(x + frameWidth, y + frameWidth, size - 2 * frameWidth, size - 2 * frameWidth);
	}
}
