package commandline;
import java.util.*;
/**
 * Top Trumps command line application
 */
public class TopTrumpsCLIApplication {

	/**
	 * This main method is called by TopTrumps.java when the user specifies that they want to run in
	 * command line mode. The contents of args[0] is whether we should write game logs to a file.
	 *
	 * @param args
	 */
	public static void main(String[] args) {

		boolean writeGameLogsToFile = false; // Should we write game logs to file?
		boolean userWantsToQuit = false; // flag to check whether the user wants to quit the application

		if (args[0].equalsIgnoreCase("true")) writeGameLogsToFile = true; // Command line selection

		// Loop until the user wants to exit the game
		while (!userWantsToQuit) {
			System.out.println(String.format("Do you want to%n%nPLAY A GAME%30s%nSEE STATISTICS%33s%nQUIT%37s", "-- enter \"play\" --", "-- enter \"statistics\" --", "-- enter \"quit\" --"));
			Scanner scan = new Scanner(System.in);
			String input = scan.next().toLowerCase();
			boolean userInput = false;
			while (!userInput) {
				if (input.equals("play") || input.equals("statistics") || input.equals("quit")) {
					userInput = true;
				} else {
					System.out.println("Input not recognised, please enter either: play, statistics, or quit");
					break;
				}

			}

			if (input.equals("play")) {

				// initialise logwriter
				LogWriter logger = null;

				// initialise new game
				Game game = new Game(5);

				System.out.printf(
						"%n--------------------------%n------- TOP TRUMPS -------%n--------------------------%n-------- NEW GAME --------%n--------------------------%n%n");

				// initialise game variables
				if (writeGameLogsToFile) { logger = new LogWriter(); }
				game.playGame();

				if (writeGameLogsToFile) { logger.writeDeckInfo(game.getDeck()); }

				game.getDeck().shuffleDeck();
				if (writeGameLogsToFile) { logger.writeDeckInfo(game.getDeck()); }

				game.setUpPlayers(game.getNumPlayers());
				game.dealCards();

				if (writeGameLogsToFile) { logger.writePlayerDeckInfo(game.getDeck(), game); }
				game.selectStartingPlayer();

				// main game loop
				while (game.getPlayers().size() > 1) {
					System.out.printf("%n--------------------------%n---- ROUNDS NUMBER: %d ----%n-- NUMBER OF PLAYERS: %d --%n--------------------------%n%n",
							game.getRoundCount() + 1, game.getPlayers().size());

					for (int i = 0; i < game.getPlayers().size(); i++) {
						System.out.printf("%s: %d cards%n", game.getPlayers().get(i).getPlayerName(),
								game.getPlayers().get(i).getNumOfCardsInDeck());
					}


					/*
					Round
					 */
					if (writeGameLogsToFile) { logger.writeCurrentCards(game.getDeck(), game); }

					System.out.printf("%nThe active player is: %s%n", game.getActivePlayer().getPlayerName());

					game.setChosenCategory(game.getActivePlayer().chooseCategory(game.getDeck().getCategoryArray()));

					System.out.printf("%n%s chooses category %S on card %S%n", game.getActivePlayer(), game.getChosenCategory(),
							game.getActivePlayer().getCardAtIndex(0).getDescription());

					if (writeGameLogsToFile) { logger.writeCategoryValues(game.getDeck(), game); }

					//set winner of the round
					game.setRoundWinner(game.compareValue(game.getPlayers(), game.getDeck().getCategoryIndex(game.getChosenCategory())));

					if (!game.isDraw()) {
						System.out.printf("%n---- THE ROUND WINNER IS: %s with card %S ---- %n", game.getRoundWinner(),
								game.getRoundWinner().getCardAtIndex(0).getDescription());
					}

					if (game.isDraw()) {
						System.out.printf("%n---- DRAW ----%n");

						game.incNumOfDraws(1);

						for (int i = 0; i < game.getPlayers().size(); i++) {
							game.getCommunalPile().giveCardsToPile(game.getPlayers().get(i).loseCard());
						}

						if (writeGameLogsToFile) {
							logger.writeCommunalPile(game.getDeck(), game.getCommunalPile());
						}
						game.setDraw(false);

					} else {
						for (int i = 0; i < game.getPlayers().size(); i++) {
							game.getRoundWinner().receiveCard(game.getPlayers().get(i).loseCard());

							while (game.getCommunalPile().getNumOfCardsInPile() > 0) {
								if (writeGameLogsToFile) {
									logger.writeCommunalPile(game.getDeck(), game.getCommunalPile());
								}
								game.getRoundWinner().receiveCard(game.getCommunalPile().getCardFormPile());
							}
						}
						if (writeGameLogsToFile) {
							logger.writePlayerDeckInfo(game.getDeck(), game);
						}
						game.setActivePlayer(game.getRoundWinner());
						game.getActivePlayer().increaseNumOfRoundsWon();
					}

					game.incRoundCount(1);
					game.updatePlayer();

					//prompt ENTER
					if (game.getPlayers().get(0).getPlayerName().equals("Human Player")) {
						System.out.printf(
								"%n ---------------------------%n| Press \"ENTER\" to continue |%n ---------------------------%n");
						Scanner scanner = new Scanner(System.in);
						scanner.nextLine();
					}
				}

				game.setGameWinner(game.getPlayers().get(0));
				game.getRoundsWon().put(game.getGameWinner().getPlayerName(), game.getGameWinner().getNumOfRoundsWon());

				if (writeGameLogsToFile == true) {
					logger.writeWinner(game.getGameWinner().getPlayerName());
					logger.closeFileHandler();
				}

				System.out.printf("%n---- THE GAME WINNER IS %s ----%n", game.getGameWinner().getPlayerName());
				System.out.printf(
						"%n%n---------------------------%n------ GAME FINISHED ------%n---------------------------%n%n");

				//stats about the finished game
				System.out.printf("Human Player rounds won: %s%nAI Player 1 rounds won: %s%nAI Player 2 rounds won: %s%nAI Player 3 rounds won: %s%nAI Player 4 rounds won: %s%n%n", game.getRoundsWon().get("Human Player"), game.getRoundsWon().get("AI Player 1"), game.getRoundsWon().get("AI Player 2"), game.getRoundsWon().get("AI Player 3"), game.getRoundsWon().get("AI Player 4"));
				System.out.printf("-- Num draws: %d --%n", game.getNumOfDraws());
				System.out.printf("-- Round count: %d --%n", game.getRoundCount());

				// write game statistics to database
				game.writeToDatabase();

			} else if (input.equals("statistics")) {
				DBConnector dBStats = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
				dBStats.connect();
				HashMap statistics = dBStats.readFromDB();
				printStatistics(statistics);
				dBStats.closeConnection();

			} else if (input.equals("quit")) {
				userWantsToQuit = true;
				}
		}

		System.exit(0);
	}


	private static void printStatistics (HashMap statistics){
		String statsString = String.format("%n--------------------------%n------- TOP TRUMPS -------%n--------------------------%n------- STATISTICS -------%n--------------------------%n%n");
		StringBuilder createStats = new StringBuilder(statsString);
		
		createStats.append(String.format("--- Number of games played: %s ---%n%n", statistics.get("Number of games")));
		createStats.append(String.format("--- Max. number of rounds: %s ---%n%n", statistics.get("Max. number of rounds")));
		createStats.append(String.format("--- Avg. number of draws: %s ---%n%n", statistics.get("Avg. number of draws")));
		createStats.append(String.format("--- Games won by human player: %s ---%n%n", statistics.get("Games won by human")));
		createStats.append(String.format("--- Games won by AI players: %s ---%n%n", statistics.get("Games won by AI")));
		
		System.out.println(createStats.toString());
	}


}
