public class Game {

	public Player[] players = new Player[5];

	public int roundCount;
	public int numOfDraws;

	private int playernumber = 5;

	Deck deck;
	String DeckTextFile = "StarCitizenDeck.txt";

	// Player[] players = new Player[5];

	public Game() {
		roundCount = 0;
		numOfDraws = 0;

	}

	public void game() {
		deck = new Deck("DeckTextFile");
		setUpPlayers();

	}

	/**
	 * set up 1 human and 4 AI player
	 */
	public void setUpPlayers() {

		for (int i = 0; i < playernumber; i++) {
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
	 * @param player1value
	 *            value of the card from player1
	 * @param player2value
	 *            value of the card from player1
	 * @param player3value
	 *            value of the card from player1
	 * @param player4value
	 *            value of the card from player1
	 * @param player5value
	 *            value of the card from player1
	 * @return the winner of the round, draw if the return value is 0
	 */
	public Player compareValue(Player[] playersarray, int Categroy) {
		int winner = 0;
		int max = 0;
		int maxcount = 0;

		for (int i = 0; i < 5; i++) {
			if (i == 0) {
				max = getCategoryValueOfPlayer(playersarray[i], Categroy);
				winner = i;
				maxcount++;
			} else if (getCategoryValueOfPlayer(playersarray[i], Categroy) > max) {
				max = getCategoryValueOfPlayer(playersarray[i], Categroy);
				winner = i;
				maxcount = 1;
			} else if (getCategoryValueOfPlayer(playersarray[i], Categroy) == max) {
				maxcount++;
			}

		}

		if (maxcount > 1) {
			System.out.println("Drow");
		}

		return playersarray[winner];
	}

	/**
	 * div the deck in to 5 part, save it in to player personalDeck
	 */
	public void dealCards() {

		for (int i = 0; i < playernumber; i++) {

			for (int ia = 0; ia < (int)(deck.deckSize / playernumber); ia++) {

				players[i].setPersonalDeck(deck.deckArray[(i * (int)(deck.deckSize / playernumber) + ia)]);

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
	 *            the index of category choosen
	 * @return the value the category of the first card in this player
	 */
	public int getCategoryValueOfPlayer(Player player, int category) {
		int value = 0;

		value = player.getFirstCard().getAtt(category); // change

		return value;
	}

	/**
	 * 
	 */
	public void chechWinner() {
		int lostCount = 0;
		for (int i = 0; i < playernumber; i++) {
			if (players[i].getNumOfCardsInDeck() == 0) {
				lostCount++;
			}
		}

		if (lostCount == (playernumber - 1)) {
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
