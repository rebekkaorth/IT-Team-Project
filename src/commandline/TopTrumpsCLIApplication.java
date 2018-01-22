package commandline;
import java.util.Scanner;


/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
 	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection
		
		// State
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application
		
		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {

			// ----------------------------------------------------
			// Add your game logic here based on the requirements
			// ----------------------------------------------------
			Scanner scanner = new Scanner(System.in);
			Game game = new Game(4);
			System.out.println("Do you want to play a game or see the statistics?");
			String input = scanner.next();
			if (input.equals("play")) {
				game.playGame();
			} else if (input.equals("statistics")){
				System.out.println("You chose statistics...");
			} else {
				System.out.println("wrong input");
			}
			userWantsToQuit=true;

		}

	}

}
