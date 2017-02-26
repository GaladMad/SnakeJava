package pl.edu.pg.ftims.projekt;

import javax.swing.JFrame;
import javax.swing.JLabel;
/**
 * The class contains elements needed to create the game window.
 */
public class SnakeFrame extends JFrame {

	private static final long serialVersionUID = 1L;

	private SnakePanel panel;
	private JLabel name, score;
	private String playersName;
	private int playersScore;

	public SnakeFrame() {
		super();
		setSize(445, 500);
		setLocationRelativeTo(null);
		setTitle(">>>Snake java<<<");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setResizable(false);

		// Label
		name = new JLabel("Name: ");
		name.setBounds(20, 430, 150, 20);
		add(name);

		score = new JLabel("Score: ");
		score.setBounds(300, 430, 150, 20);
		add(score);

		// Pamnel
		panel = new SnakePanel();
		panel.setBounds(10, 10, 410, 410);
		add(panel);

		this.setVisible(true);
	}

	public SnakePanel getPanel() {
		return panel;
	}

	public void refreshTheWindow() {
		name.setText("Name: " + playersName);
		score.setText("Score: " + playersScore);
		this.setVisible(true);
	}

	public void setPlayersName(String playersName) {
		this.playersName = playersName;
	}

	public void setPlayersScore(int playersScore) {
		this.playersScore = playersScore;
	}

}
