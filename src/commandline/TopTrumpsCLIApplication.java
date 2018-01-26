package commandline;

import java.util.*;
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
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile=true; // Command line selection

		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			Scanner scan = new Scanner(System.in);
			String input = scan.next().toLowerCase();
			boolean userInput = false;
			while(!userInput){
				System.out.println("Do you want to play a game or see statistics?");
				if(input.equals("play") || input.equals("statistics") || input.equals("quit")){
					userInput = true;
				}
				else{
					System.out.println("Input not recognised, please enter either: play, statistics, or quit");
				}

			}
			if(input.equals("play")){
				Game game = new Game(5,writeGameLogsToFile);
				game.playGame();
			}
			else if(input.equals("statistics")){
				Game game = new Game(5, writeGameLogsToFile);
			// Get info from DB	game.showData();
			}
			else if(input.equals("quit")){
				userWantsToQuit = true;
			}
		}
		System.exit(0);
	}

}
