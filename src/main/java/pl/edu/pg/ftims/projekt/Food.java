package pl.edu.pg.ftims.projekt;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * The class containes atributes of food and method to appear food on the background.
 *
 */
public class Food extends SBlock {
	private int maxX, maxY;


	private void randomColor() {
		Random random = new Random();

		int r = random.nextInt(256);
		int g = random.nextInt(256);
		int b = random.nextInt(256);

		color = new Color(r, g, b);
	}
	
	private List<int[]> possiblePlace(Snake snake) {
		List<int[]> tableOfPossiblePlace = new ArrayList<int[]>();

		for (int i = 0; i < (maxX - 10) / 20; i++) {
			for (int j = 0; j < (maxY - 10) / 20; j++) { // two loops used because background has got two dimensions, it's square
				boolean flag = true;
				for (int s = 0; s < snake.getLength(); s++) {

					if (snake.getSegments().get(s).getX() == (i * 20 + 10)
							&& snake.getSegments().get(s).getY() == (j * 20 + 10)) {
						flag = false;
					}
				}
				// if any segment of snake or old food has [i*20+10,j*20+10] coordinates, set these numbers into table
				if (flag) {

					int[] tab = new int[2];
					tab[0] = i * 20 + 10;
					tab[1] = j * 20 + 10;
					tableOfPossiblePlace.add(tab);
				}
			}

		}
		return tableOfPossiblePlace;
	}
	
	@Override
	public void draw(Graphics2D graphics2d) {

		graphics2d.setColor(Color.black);
		graphics2d.fillRect(x, y, size, size);
		graphics2d.setColor(color);
		graphics2d.fillRect(x + frameWidth, y + frameWidth, size - 2 * frameWidth, size - 2 * frameWidth);

	}

	public Food(int maxX, int maxY, Snake snake) {
		this.maxX = maxX;
		this.maxY = maxY;
		randLocation(true, snake);
		randomColor();
		size = snake.getSize();
	}

	public void randLocation(boolean ifEat, Snake snake) {
		if (ifEat) {
			int size = possiblePlace(snake).size();
			if (size > 0) {
				Random random = new Random();
				int position = random.nextInt(size);
				setX((possiblePlace(snake)).get(position)[0]);
				setY((possiblePlace(snake)).get(position)[1]);
			}
		}
		randomColor();
	}



}

