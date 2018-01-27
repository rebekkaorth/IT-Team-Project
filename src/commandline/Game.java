package commandline;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Game {

	public ArrayList<Player> players = new ArrayList<Player>();

	public String chosenCategory;
	private int roundCount;
	private HashMap<String, Integer> roundsWon;

	private int numOfDraws;

	private int numPlayers;
	private boolean isDraw;
	private boolean writeGameLogsToFile;

	private LogWriter logger;

	private CommunalPile communalPile;
	private Deck deck;
	private String deckTextFile = "StarCitizenDeck.txt";

	private Player activePlayer;
	private Player gameWinner;

	public Game(int numberOfPlayers, boolean writeGameLogsToFile) {
		this.roundCount = 0;
		this.roundsWon = new HashMap<>();
		this.numOfDraws = 0;
		this.isDraw = false;
		this.numPlayers = numberOfPlayers;
		this.writeGameLogsToFile = writeGameLogsToFile;
	}

	public void playGame() {
		if (writeGameLogsToFile == true) { logger = new LogWriter(); }
		deck = new Deck(deckTextFile);
		communalPile = new CommunalPile();
		if (writeGameLogsToFile == true) { logger.writeDeckInfo(deck); }

		deck.shuffleDeck();
		if (writeGameLogsToFile == true) { logger.writeDeckInfo(deck); }

		this.setUpPlayers(numPlayers);
		this.dealCards();
		if (writeGameLogsToFile == true) { logger.writePlayerDeckInfo(deck, this); }

		activePlayer = this.selectStartingPlayer();

		System.out.printf(
				"%n--------------------------%n------- TOP TRUMPS -------%n--------------------------%n-------- NEW GAME --------%n--------------------------%n%n");

		while (players.size() > 1) {
			System.out.printf(
					"%n--------------------------%n---- ROUNDS NUMBER: %d ----%n-- NUMBER OF PLAYERS: %d --%n--------------------------%n%n",
					roundCount+1, players.size());
			for (int i = 0; i < players.size(); i++) {
				System.out.printf("%s: %d cards%n", players.get(i).getPlayerName(),
						players.get(i).getNumOfCardsInDeck());
			}
			this.roundLoop();
			roundCount++;
			this.updatePlayer();
			promptEnterKey();
		}

		gameWinner = players.get(0);
		roundsWon.put(gameWinner.getPlayerName(), gameWinner.getNumOfRoundsWon());
		if (writeGameLogsToFile == true) {
			logger.writeWinner(gameWinner.getPlayerName());
			logger.closeFileHandler();
		}
		System.out.printf("%n---- THE GAME WINNER IS %s ----%n", gameWinner.getPlayerName());
		System.out.printf(
				"%n%n---------------------------%n------ GAME FINISHED ------%n---------------------------%n%n");

		System.out.println("Human Player rounds won: "+roundsWon.get("Human Player")+" AI 1: "+
				roundsWon.get("AI Player 1")+ " AI 2: "+roundsWon.get("AI Player 2")+ " AI 3: "
				+roundsWon.get("AI Player 3")+
				" AI 4: "+roundsWon.get("AI Player 4"));
		System.out.println("Num draws: "+numOfDraws);
		System.out.println("Round count: "+roundCount);

		writeToDatabase();
	}

	public void roundLoop() {
		Player roundWinner;
		if (writeGameLogsToFile == true) { logger.writeCurrentCards(deck, this); }
		System.out.printf("%nThe active player is: %s%n", activePlayer.getPlayerName());
		chosenCategory = activePlayer.chooseCategory(deck.getCategoryArray());
		System.out.printf("%n%s chooses category %S on card %S%n", activePlayer, chosenCategory,
				activePlayer.getCardAtIndex(0).getDescription());
		if (writeGameLogsToFile == true) { logger.writeCategoryValues(deck, this); }
		roundWinner = this.compareValue(players, deck.getCategoryIndex(chosenCategory));
		if (!isDraw) {
			System.out.printf("%n---- THE ROUND WINNER IS: %s with card %S ---- %n", roundWinner,
					roundWinner.getCardAtIndex(0).getDescription());

		}

		if (isDraw) {
			System.out.printf("%n---- DRAW ----%n");
			numOfDraws++;
			for (int i = 0; i < players.size(); i++) {
				communalPile.giveCardsToPile(players.get(i).loseCard());
			}

			if (writeGameLogsToFile == true) { logger.writeCommunalPile(deck, communalPile); }
			isDraw = false;

		} else {
			for (int i = 0; i < players.size(); i++) {
				roundWinner.receiveCard(players.get(i).loseCard());
				while (communalPile.getNumOfCardsInPile() > 0) {
					if (writeGameLogsToFile == true) { logger.writeCommunalPile(deck, communalPile); }
					roundWinner.receiveCard(communalPile.getCardFormPile());
				}
			}
			if (writeGameLogsToFile == true) { logger.writePlayerDeckInfo(deck, this); }
			activePlayer = roundWinner;
			activePlayer.increaseNumOfRoundsWon();
		}
	}

	/**
	 * sets up player array list set up 1 human and 4 AI player
	 */
	public void setUpPlayers(int numPlayers) { // change this method; use a for-loop to put AIs in the ArrayList

		for (int i = 0; i < numPlayers; i++) {
			if (i == 0) {
				Human human = new Human("Human Player");
				players.add(human);
			}
			if (i == 1) {
				Computer AIplayer1 = new Computer("AI Player 1");
				players.add(AIplayer1);
			}
			if (i == 2) {
				Computer AIplayer2 = new Computer("AI Player 2");
				players.add(AIplayer2);
			}
			if (i == 3) {
				Computer AIplayer3 = new Computer("AI Player 3");
				players.add(AIplayer3);
			}
			if (i == 4) {
				Computer AIplayer4 = new Computer("AI Player 4");
				players.add(AIplayer4);
			}

		}
	}

	// Alternative method to compare cards. This would return null, if there was a draw.
	// This would however imply, that the roundloop gets re-written.
	/*
	private Player compare() {

		boolean draw = false;
		int categoryInt = deck.getCategoryIndex(chosenCategory);
		int winner = 0;
		int max = 0;

		for (int i = 0; i < players.size(); i++) {

			if (players.get(i).getFirstCard().getAtt(categoryInt) > max) {

				max = players.get(i).getFirstCard().getAtt(categoryInt);
				winner = i;
				draw = false;

			}

			else if (players.get(i).getFirstCard().getAtt(categoryInt) == max) {

				draw = true;

			}

		}

		if (draw == false) {

			return players.get(winner);

		} else {

			return null;

		}

	}
	*/

	/**
	 * it will return the winner of the turn (from player1 - 5)
	 *
	 * @param playersArray
	 *            value of the card from player1
	 * @param category
	 *            value of the card from player
	 * @return the winner of the round, draw if the return value is 0
	 */
	public Player compareValue(ArrayList<Player> playersArray, int category) {
		int winner = 0;
		int max = 0;
		int maxCount = 0;

		// check which player has the highest value of one category
		for (int i = 0; i < players.size(); i++) {
			if (i == 0) {
				max = this.getCategoryValueOfPlayer(playersArray.get(i), category);
				winner = i;
				maxCount++;
			} else if (this.getCategoryValueOfPlayer(playersArray.get(i), category) == max) {
				maxCount++;
			} else if (this.getCategoryValueOfPlayer(playersArray.get(i), category) > max) {
				max = this.getCategoryValueOfPlayer(playersArray.get(i), category);
				winner = i;
				maxCount = 1;
			}
		}

		// check if there was draw -> the highest number appears at least twice in the
		// array
		if (maxCount > 1) {
			isDraw = true;
		}

		return playersArray.get(winner);
	}

	/**
	 * div the deck in to 5 part, save it in to player personalDeck
	 */
	public void dealCards() {

		for (int i = 0; i < players.size(); i++) {

			for (int ia = 0; ia < (int) (deck.deckSize / numPlayers); ia++) {

				players.get(i).setPersonalDeck(deck.deckArray[(i * (int) (deck.deckSize / numPlayers) + ia)]);

			}
		}

	}

	/**
	 * select a random player as the starting player
	 *
	 * @return one random player from x players
	 */
	public Player selectStartingPlayer() {

		int playerNumber = (int) (Math.random() * (numPlayers)); // returns value between 0 and numPlayers exclusive
		return players.get(playerNumber);

	}

	/**
	 *
	 * @param player
	 *            which player
	 * @param category
	 *            the index of category chosen
	 * @return the value the category of the first card in this player
	 */
	public int getCategoryValueOfPlayer(Player player, int category) {
		int value;

		value = player.getFirstCard().getAtt(category);

		return value;
	}

	/**
	 * update the players list. only remain the player who have card/cards on their
	 * hand
	 * 
	 * 
	 */
	public void updatePlayer() {
		int i = 0;
		while (i < players.size()){
			if (players.get(i).getNumOfCardsInDeck() == 0) {
				System.out.printf("%n---- %s is out of the game ----%n", players.get(i).getPlayerName());
				roundsWon.put(players.get(i).getPlayerName(), players.get(i).getNumOfRoundsWon()); // remember the rounds this player has won
				players.remove(i);
			}
			else i++;
		}
	}


	private void writeToDatabase() {
		DBConnector dB = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
		dB.connect();

		if (numPlayers == 2) {
			dB.writeToDB(gameWinner.getPlayerName(), numOfDraws, roundCount, roundsWon.get("Human Player"),
					roundsWon.get("AI Player 1"));
		} else if (numPlayers == 3) {
			dB.writeToDB(gameWinner.getPlayerName(), numOfDraws, roundCount, roundsWon.get("Human Player"),
					roundsWon.get("AI Player 1"), roundsWon.get("AI Player 2"));
		} else if (numPlayers == 4) {
			dB.writeToDB(gameWinner.getPlayerName(), numOfDraws, roundCount, roundsWon.get("Human Player"),
					roundsWon.get("AI Player 1"), roundsWon.get("AI Player 2"), roundsWon.get("AI Player 3"));
		} else if (numPlayers == 5) {
			dB.writeToDB(gameWinner.getPlayerName(), numOfDraws, roundCount, roundsWon.get("Human Player"),
					roundsWon.get("AI Player 1"), roundsWon.get("AI Player 2"), roundsWon.get("AI Player 3"),
					roundsWon.get("AI Player 4"));
		}

		dB.closeConnection();
	}

	private void promptEnterKey() {
		if (players.get(0).getPlayerName().equals("Human Player")) {
			System.out.printf(
					"%n ---------------------------%n| Press \"ENTER\" to continue |%n ---------------------------%n");
			Scanner scanner = new Scanner(System.in);
			scanner.nextLine();
		}
	}

	// main method for testing
	public static void main(String[] args) {

		Game game1 = new Game(5, false);
		game1.playGame();

	}
}
