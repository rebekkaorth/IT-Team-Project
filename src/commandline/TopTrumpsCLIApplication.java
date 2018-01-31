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
			System.out.println("Do you want to play a game or see statistics?");
			Scanner scan = new Scanner(System.in);
			String input = scan.next().toLowerCase();
			boolean userInput = false;
			while(!userInput){
				if(input.equals("play") || input.equals("statistics") || input.equals("quit")){
					userInput = true;
				}
				else{
					System.out.println("Input not recognised, please enter either: play, statistics, or quit");
					break;
				}

			}
			if(input.equals("play")){
				Game game = new Game(5, writeGameLogsToFile);
				game.playGame();
			}
			else if(input.equals("statistics")){
				DBConnector dBStats = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
				dBStats.connect();
				HashMap statistics = dBStats.readFromDB();
				printStatistics (statistics);
				dBStats.closeConnection();
			}
			else if(input.equals("quit")){
				userWantsToQuit = true;
			}
		}
		System.exit(0);
	}

	private static void printStatistics (HashMap statistics){
		StringBuilder createStats = new StringBuilder("%n--------------------------%n------- TOP TRUMPS -------%n--------------------------%n-------- STATISTICS --------%n--------------------------%n%n\");\n");
		createStats.append(statistics.toString());
		String stats = createStats.toString();
		System.out.println(stats);
	}

}
