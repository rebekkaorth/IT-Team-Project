package commandline;

import java.util.ArrayList;

public class Game {

	public ArrayList<Player> players = new ArrayList<Player>();

	public int roundCount;
	public int numOfDraws;
	public String chosenCategory;

	private int numPlayers;
	private boolean isDraw;

	private LogWriter logger;

	CommunalPile communalPile;
	Deck deck;
	String deckTextFile = "StarCitizenDeck.txt";

	private Player activePlayer;

	public Game(int numberOfPlayers) {
		roundCount = 0;
		numOfDraws = 0;
		numPlayers = numberOfPlayers;
		isDraw = false;
	}

	public void playGame() {
		logger  = new LogWriter();
		deck = new Deck(deckTextFile);
		logger.writeDeckInfo(deck);
		deck.shuffleDeck();
		logger.writeDeckInfo(deck);
		communalPile = new CommunalPile();
		String gameWinner;
		this.setUpPlayers(numPlayers);
		this.dealCards();
		logger.writePlayerDeckInfo(deck,this);

		activePlayer = this.selectStartingPlayer();

		while(players.size() > 1) {
			System.out.println("Num players: " + players.size());
			for (int i=0; i<players.size(); i++){
				System.out.println("Player " + players.get(i).getPlayerName()+" has " + players.get(i).getNumOfCardsInDeck() + "cards left.");
			}
			this.roundLoop();
			roundCount++;
			this.updatePlayer();
		}


		gameWinner = players.get(0).getPlayerName(); //still throws exception; there is sth wrong with the loop logic I think
		logger.writeWinner(gameWinner);
		logger.closeFileHandler();
		System.out.println("Game winner is " + gameWinner);
		System.out.println("GAME FINISHED");
	}

	public void roundLoop () {
		Player roundWinner;
		logger.writeCurrentCards(deck,this);
		System.out.println("The active player is: " + activePlayer.getPlayerName());
		chosenCategory = activePlayer.chooseCategory(deck.getCategoryArray());
		System.out.println("The chosen category is: " + chosenCategory);
		logger.writeCategoryValues(deck,this);

		roundWinner = this.compareValue(players, deck.getCategoryIndex(chosenCategory));
		System.out.println(("The round winner is: " +roundWinner));

		if(isDraw) {
			System.out.println(("Draw occurred"));
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
	public void setUpPlayers(int numPlayers) {

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
			System.out.println("draw");
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
		System.out.println("The starting player is: " + players.get(playerNumber).getPlayerName());
		return players.get(playerNumber);

	}

	/**
	 *
	 * @param player
	 *            which player
	 * @return the card left in the deck for the player
	 */
	public int getCardsLeftInDeck(Player player) { //do we need this method?
		int cardsLeft = 0;

		player.getNumOfCardsInDeck();

		return cardsLeft;
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
	 */
	public boolean checkGameEnd() { //do we need this method?
		int lostCount = 0;
		for (int i = 0; i < numPlayers; i++) {
			if (players.get(i).getNumOfCardsInDeck() == 0) {
				lostCount++;
			}
		}
		if (lostCount == (numPlayers - 1)) {


			System.out.println("Game finish.");
			return false;
		}
		return true;
	}

	/**
	 *
	 * @return the number of draws
	 */
	public int getNumOfDraws() {
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
				System.out.println("This player will be removed: "+players.get(i));
				players.remove(i);
				i = 0;
			}
		}
	}
	// main method for testing
	public static void main(String[] args) {

		Game game1 = new Game(4);
		game1.playGame();

	}
}
