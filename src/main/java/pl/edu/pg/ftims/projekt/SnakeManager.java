package pl.edu.pg.ftims.projekt;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;

import pl.edu.pg.ftims.projekt.Snake.Course;

/**
 * The class manages the game. It is responsible for operating the events, communication with a player,
 * and supporting the file with results of the game.
 *
 */
public class SnakeManager implements KeyListener {

	private boolean nextGame;
	private boolean continueGame;
	private int speed;
	private Snake.Course order;
	private HashMap<String, String> mapOfscores;
	private ArrayList<String> names;

	public SnakeManager() {
		nextGame = true;
		continueGame = true;
		speed = 200;
		order = Course.East;
	}

	public boolean isNextGame() {
		return nextGame;
	}

	public void setNextGame(boolean nextGame) {
		this.nextGame = nextGame;
	}

	public boolean isContinueGame() {
		return continueGame;
	}

	public void setContinueGame(boolean continueGame) {
		this.continueGame = continueGame;
	}

	public Snake.Course getOrder() {
		return order;
	}

	public void setOrder(Snake.Course order) {
		this.order = order;
	}

	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub

		switch (e.getKeyCode()) {

		case KeyEvent.VK_UP:
			order = Course.North;
			break;

		case KeyEvent.VK_DOWN:
			order = Course.South;
			break;

		case KeyEvent.VK_LEFT:
			order = Course.West;
			break;

		case KeyEvent.VK_RIGHT:
			order = Course.East;
			break;

		case KeyEvent.VK_ESCAPE:
			continueGame = false;
			break;
		default:
		}
	}

	public int getSpeed() {
		return speed;
	}

	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void inputPlayersName(Player player) throws ToLongNameException{
		String user = JOptionPane.showInputDialog("Enter your name", "player");

		if (user != null) {
			
			if(user.length()>17){
				throw new ToLongNameException();
			}
			else{
				player.setName(user);
			}
		}
	}

	public void showScoreMessage(Player player) {
		JOptionPane.showMessageDialog(null, "Your score is: " + player.getScore());
	}

	public void showPauseWindow() {
		int result = JOptionPane.showConfirmDialog(null, "Do you want to continue the game?", null,
				JOptionPane.YES_NO_OPTION);

		if (result == 0) {
			continueGame = true;
		} else {
			continueGame = false;
		}

	}

	public void showPlayAgainWindow(SnakeFrame frame) {
		int result = JOptionPane.showConfirmDialog(null, "Do you want to play again?", null, JOptionPane.YES_NO_OPTION);
		continueGame = true;

		if (result == 0) {
			nextGame = true;
			frame.dispose();
		} else {
			nextGame = false;
		}
	}

	public void showScoresWindow(SnakeFrame frame) {
		int result = JOptionPane.showConfirmDialog(null, "Do you want to see other scores?", null,
				JOptionPane.YES_NO_OPTION);

		if (result == 0) {
			readScores();
		}
	}

	///////////////////////////// files operation
	private void readScores() {
		
		prepareFile();
	
		String text = "PLAYER: SCORE\n";
		
		for (int i=0; i<mapOfscores.size(); i++){
			text+=names.get(i)+": "+mapOfscores.get(names.get(i))+"\n";
		}
		
		JOptionPane.showMessageDialog(null, text);
	}
	
	public void writeScores(Player player){
		
		PrintWriter recording;
		try {
			recording = new PrintWriter(new FileWriter("scores.txt", true));
			recording.println(player.getName() + ";" + player.getScore());
			recording.close();
			prepareFile();
		}
		catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.err.println("The file does not exist!");
		}
	}
		
	private void prepareFile(){
		Path path = Paths.get("scores.txt");
		ArrayList<String> linesOfFile = new ArrayList<String>();
		String[] nameAndscore = new String[2];
		mapOfscores = new HashMap<String, String>();
		
		try {
			linesOfFile = (ArrayList<String>) Files.readAllLines(path, StandardCharsets.UTF_8); // read all lines in the same time
			
			for(int i=0; i<linesOfFile.size();i++){
				nameAndscore = linesOfFile.get(i).split(";");
				mapOfscores.put(nameAndscore[0], nameAndscore[1]);
			}
			
			names = new ArrayList<String>(mapOfscores.keySet());
			
		} catch (IOException ex) {
			ex.printStackTrace();
			System.err.println("The file does not exist!");
		}
	}
}
