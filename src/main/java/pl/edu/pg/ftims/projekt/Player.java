package pl.edu.pg.ftims.projekt;

/**
 * The class containing data such as name and score of player.
 */
public class Player {
	private String name;
	private int score;

	public Player() {
		name = "player";
		score = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score - 3;
	}
}

