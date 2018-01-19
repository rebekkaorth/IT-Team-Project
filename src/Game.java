public class Game {

	public Player[] players = new Player[5];

	public int roundCount;
	public int numOfDraws;

	private int playerNumber;

	Deck deck;
	String deckTextFile = "StarCitizenDeck.txt";

	public Game(int numberOfPlayers) {
		roundCount = 0;
		numOfDraws = 0;
		playerNumber = numberOfPlayers;

	}

	public void gameLoop() {
		deck = new Deck(deckTextFile);
		setUpPlayers();

	}

	public void roundLoop () {

	}

	/**
	 * set up 1 human and 4 AI player
	 */
	public void setUpPlayers() {

		for (int i = 0; i < playerNumber; i++) {
			if (i == 0) {
				Human human = new Human("Player");
				players[i] = human;
			}
			if (i == 1) {
				Computer AIplayer1 = new Computer("AIplayer1");
				players[i] = AIplayer1;
			}
			if (i == 2) {
				Computer AIplayer2 = new Computer("AIplayer2");
				players[i] = AIplayer2;
			}
			if (i == 3) {
				Computer AIplayer3 = new Computer("AIplayer3");
				players[i] = AIplayer3;
			}
			if (i == 4) {
				Computer AIplayer4 = new Computer("AIplayer4");
				players[i] = AIplayer4;
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
	public Player compareValue(Player[] playersArray, int category) {
		int winner = 0;
		int max = 0;
		int maxCount = 0;

		//check which player has the highest value of one category
		for (int i = 0; i < 5; i++) {
			if (i == 0) {
				max = this.getCategoryValueOfPlayer(playersArray[i], category);
				winner = i;
				maxCount++;
			} else if (this.getCategoryValueOfPlayer(playersArray[i], category) == max) {
				maxCount++;
			} else if (this.getCategoryValueOfPlayer(playersArray[i], category) > max) {
				max = this.getCategoryValueOfPlayer(playersArray[i], category);
				winner = i;
				maxCount = 1;
			}
		}

		//check if there was draw -> the highest number appears at least twice in the array
		if (maxCount > 1) {
			System.out.println("draw");
		}

		return playersArray[winner];
	}

	/**
	 * div the deck in to 5 part, save it in to player personalDeck
	 */
	public void dealCards() {

		for (int i = 0; i < playerNumber; i++) {

			for (int ia = 0; ia < (int)(deck.deckSize / playerNumber); ia++) {

				players[i].setPersonalDeck(deck.deckArray[(i * (int)(deck.deckSize / playerNumber) + ia)]);

			}
		}

	}

	/**
	 * select a random player as the starting player
	 * 
	 * @return one random player from 5 players
	 */
	public Player selectStartingPlayer() {

		int playerNumber = (int) (Math.random() * 5);

		return players[playerNumber];

	}

	/**
	 * 
	 * @param player
	 *            which player
	 * @return the card left in the deck for the player
	 */
	public int getCardsLeftInDeck(Player player) {
		int cardLeft = 0;

		player.getNumOfCardsInDeck();

		return cardLeft;
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
	public void chechWinner() {
		int lostCount = 0;
		for (int i = 0; i < playerNumber; i++) {
			if (players[i].getNumOfCardsInDeck() == 0) {
				lostCount++;
			}
		}
		if (lostCount == (playerNumber - 1)) {
			System.out.println("Game finish.");
		}

	}

	/**
	 * 
	 * @return the number of draws
	 */
	public int getNumOfDraws() {
		return numOfDraws;
	}

}
