package commandline;

import java.util.ArrayList;
import java.util.Scanner;

public class Game {

	public ArrayList<Player> players = new ArrayList<Player>();

	public int roundCount;
	public int numOfDraws;
	public String chosenCategory;

	private int numPlayers;
	private boolean isDraw;
	private boolean writeGameLogsToFile;

	private LogWriter logger;

	private CommunalPile communalPile;
	private Deck deck;
	private String deckTextFile = "StarCitizenDeck.txt";

	private Player activePlayer;


	public Game(int numberOfPlayers, boolean writeGameLogsToFile) {
		this.roundCount = 0;
		this.numOfDraws = 0;
		this.isDraw = false;
		this.numPlayers = numberOfPlayers;
		this.writeGameLogsToFile = writeGameLogsToFile;
	}

	public void playGame() {
		logger  = new LogWriter();
		deck = new Deck(deckTextFile);
		communalPile = new CommunalPile();
		logger.writeDeckInfo(deck);

		deck.shuffleDeck();
		logger.writeDeckInfo(deck);

		this.setUpPlayers(numPlayers);
		this.dealCards();
		logger.writePlayerDeckInfo(deck,this);

		activePlayer = this.selectStartingPlayer();

		System.out.printf("%n--------------------------%n------- TOP TRUMPS -------%n--------------------------%n-------- NEW GAME --------%n--------------------------%n%n");

		while(players.size() > 1) {
			System.out.printf("%n--------------------------%n---- ROUNDS PLAYED: %d ----%n-- NUMBER OF PLAYERS: %d --%n--------------------------%n%n", roundCount ,players.size());
			for (int i=0; i<players.size(); i++){
				System.out.printf("%s: %d cards%n", players.get(i).getPlayerName(), players.get(i).getNumOfCardsInDeck() );
			}
			this.roundLoop();
			roundCount++;
			this.updatePlayer();
			promptEnterKey();
		}

		Player gameWinner = players.get(0);
		logger.writeWinner(gameWinner.getPlayerName());
		logger.closeFileHandler();
		System.out.printf("%n---- THE GAME WINNER IS %s ----%n", gameWinner.getPlayerName());
		System.out.printf("%n%n---------------------------%n------ GAME FINISHED ------%n---------------------------%n%n");

		//writeToDatabase(gameWinner.getPlayerName(), numOfDraws, roundCount, ); we need to insert the count for human and ai rounds
	}

	public void roundLoop () {
		Player roundWinner;

		logger.writeCurrentCards(deck,this);

		System.out.printf("%nThe active player is: %s%n", activePlayer.getPlayerName());

		chosenCategory = activePlayer.chooseCategory(deck.getCategoryArray());
		System.out.printf("%n%s chooses category %S on card %S%n", activePlayer, chosenCategory, activePlayer.getCardAtIndex(0).getDescription());
		logger.writeCategoryValues(deck,this);

		roundWinner = this.compareValue(players, deck.getCategoryIndex(chosenCategory));
		if (!isDraw) {
			System.out.printf("%n---- THE ROUND WINNER IS: %s with card %S ---- %n", roundWinner, roundWinner.getCardAtIndex(0).getDescription());
		}

		if(isDraw) {
			System.out.printf("%n---- DRAW ----%n");
			numOfDraws++;
			for (int i=0; i<players.size(); i++) {
				communalPile.giveCardsToPile(players.get(i).loseCard());
			}

			logger.writeCommunalPile(deck,communalPile);
			isDraw = false;

		} else {
			for (int i=0; i<players.size(); i++) {
				roundWinner.receiveCard(players.get(i).loseCard());
				while(communalPile.getNumOfCardsInPile() > 0) {
					logger.writeCommunalPile(deck,communalPile);
					roundWinner.receiveCard(communalPile.getCardFormPile());
				}
			}
			logger.writePlayerDeckInfo(deck,this);
			activePlayer = roundWinner;
		}
	}

	/**
	 * sets up player array list
	 * set up 1 human and 4 AI player
	 */
	public void setUpPlayers(int numPlayers) { //change this method; use a for-loop to put AIs in the ArrayList

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

		//check which player has the highest value of one category
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

		//check if there was draw -> the highest number appears at least twice in the array
		if (maxCount > 1) {
			isDraw=true;
		}

		return playersArray.get(winner);
	}

	/**
	 * div the deck in to 5 part, save it in to player personalDeck
	 */
	public void dealCards() {

		for (int i = 0; i < players.size(); i++) {

			for (int ia = 0; ia < (int)(deck.deckSize / numPlayers); ia++) {

				players.get(i).setPersonalDeck(deck.deckArray[(i * (int)(deck.deckSize / numPlayers) + ia)]);

			}
		}

	}

	/**
	 * select a random player as the starting player
	 *
	 * @return one random player from x players
	 */
	public Player selectStartingPlayer() {

		int playerNumber = (int)(Math.random()*(numPlayers)); //returns value between 0 and numPlayers exclusive
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
	 *
	 * @return the number of draws
	 */
	public int getNumOfDraws() {//we need this for DB or can we directly access the variable???
		return numOfDraws;
	}

	/**
	 * update the players list. only remain the player who have card/cards on their
	 * hand
	 * 
	 * 
	 */
	public void updatePlayer() {

		for (int i=0; i<players.size(); i++){
			if (players.get(i).getNumOfCardsInDeck() == 0) {
				System.out.printf("%n---- %s is out of the game ----%n", players.get(i).getPlayerName());
				players.remove(i);
				i = 0;
			}
		}
	}

	public void writeToDatabase(String winner, int draws, int rounds, int humanRounds, int ai1Rounds, int ai2Rounds, int ai3Rounds, int ai4Rounds){
		DBConnector dB = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
		dB.connect();
		dB.writeToDB(winner, draws, rounds, humanRounds, ai1Rounds, ai2Rounds, ai3Rounds, ai4Rounds);
		dB.closeConnection();
	}

	public void promptEnterKey(){
		if (players.get(0).getPlayerName().equals("Human Player")){
		System.out.printf("%n ---------------------------%n| Press \"ENTER\" to continue |%n ---------------------------%n");
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
