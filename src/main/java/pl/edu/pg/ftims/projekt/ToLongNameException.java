package pl.edu.pg.ftims.projekt;
/**
 * The exception occurs when the name of the player is too long.
 */
public class ToLongNameException extends Exception{
	
	
	private static final long serialVersionUID = 1L;

	public ToLongNameException(){
		System.err.println("The name is too long");
	}

}
