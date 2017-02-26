package pl.edu.pg.ftims.projekt;

import java.io.IOException;

import javax.swing.JOptionPane;

/**
 * The game based on the classic game 'Snake'.
 * @author Kamil
 * @version 1.0 - 19.02.2017 r.
 */
public class StartSnake 
{
    public static void main( String[] args ) throws IOException
    {
    	SnakeManager manager = new SnakeManager();
    		
    	while (manager.isNextGame()){
    		SnakeFrame frame = new SnakeFrame();
        	Player player = new Player();
        	frame.addKeyListener(manager);
    		
        	boolean isException = true;
        	do{
        		try {
    				manager.inputPlayersName(player);
    				isException = false;
    			} catch (ToLongNameException e) {
    				// TODO Auto-generated catch block
    				e.printStackTrace();
    				JOptionPane.showMessageDialog(null, "Your name is too long, enter a shorter name!" );
    			}
        	}while(isException==true);
    		
    		frame.setPlayersName(player.getName());
    		
    		while (frame.getPanel().getSnake().ifAlive() && manager.isContinueGame())
{

    			frame.setPlayersScore(player.getScore());
    			frame.refreshTheWindow();
    			frame.getPanel().getSnake().setCourse(manager.getOrder());
    			frame.getPanel().getSnake().moveSnake();
    			if(frame.getPanel().getSnake().eatAndGrow(frame.getPanel().getFood())){
    				frame.getPanel().getFood().randLocation(true, frame.getPanel().getSnake()); // if snake eats food, the food coordinates change, and the snake grows
    			}
    			
    			
    			frame.getPanel().refresh(frame.getPanel().getFood(), frame.getPanel().getSnake());
    			
    			try {
    			    Thread.sleep(manager.getSpeed());
    			} catch(InterruptedException ex) {
    			    Thread.currentThread().interrupt();
    			}
    			
    			player.setScore(frame.getPanel().getSnake().getLength());
    			
    			if(manager.isContinueGame()==false){
    				manager.showPauseWindow();
    			}
    				
    		}

    		manager.writeScores(player); //write score to file "scores.txt"
    		manager.showScoreMessage(player);
    		manager.showScoresWindow(frame);
    		manager.showPlayAgainWindow(frame); //if you want, you can play one more time
		}
	}
}
