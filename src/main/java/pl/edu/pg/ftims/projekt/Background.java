package pl.edu.pg.ftims.projekt;

import java.awt.Color;
import java.awt.Graphics2D;
/**
 * The class contains atributes of background.
 *
 */

public class Background extends SBlock {
	
	public Background(int x, int y) {
		this.x = x;
		this.y = y;
		this.size = 400;
		color = Color.white;
	}
	
	@Override
	public void draw(Graphics2D graphics2d) {
		graphics2d.setColor(Color.black);
		graphics2d.fillRect(x, y, size, size);
		graphics2d.setColor(color);
		graphics2d.fillRect(x + frameWidth, y + frameWidth, size - 2 * frameWidth, size - 2 * frameWidth);
	}
	
	public int getSize(){
		return size;
	}

}
