package pl.edu.pg.ftims.projekt;

import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;
/**
 * The SnakePanel contains elements, such as: background, snake and food,
 * and methods of using these elements.
 */
public class SnakePanel extends JPanel {
	
	private static final long serialVersionUID = 1L;
	private int x, y;
	private int sizePanel;
	private Background background;
	private Snake snake;
	private Food food;
	private SBlock[] blocks;


	public SnakePanel() {
		x = 10;
		y = 10;
		background = new Background(x, y);
		sizePanel = background.getSize();
		snake = new Snake(x + sizePanel, y + sizePanel, sizePanel / 20);
		food = new Food(x + sizePanel, y + sizePanel, snake);
		
		blocks = new SBlock[2];
		blocks[0] = background;
		blocks[1] = food;
		
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;

		for(SBlock x : blocks){
            x.draw(g2d);
    	}
		snake.draw(g2d);
	}

	public Snake getSnake() {
		return snake;
	}

	public Food getFood() {
		return food;
	}

	public void refresh(Food food, Snake snake) {
		this.repaint();
	}

	public void setSize(int size) {
		this.sizePanel = size;
	}

	public int getPanelSize() {
		return sizePanel;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
}
