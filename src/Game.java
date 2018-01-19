public class Game {

	public Player[] players;
	public Player[] allPlayers;

	public int roundCount;
	public int numOfDraws;

	private int playernumber = 5;

	Deck deck;

	public Game() {
		roundCount = 0;
		numOfDraws = 0;
		

	}

	
	
	public void game() {
		deck  = new Deck();
		setUpPlayers();
		
		
	}
	
	
	
	/**
	 * set up 1 human and 4 AI player
	 */
	public void setUpPlayers() {

		players = new Player[playernumber];
		for (int i = 0; i < playernumber; i++) {
			if (i == 0) {
				players[i] = "human";
				allPlayers[i] = "human";
			} else {
				allPlayers[i] = "AI " + i;
				players[i] = "AI " + i;
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
	public int round(int player1value, int player2value, int player3value, int player4value, int player5value) {
		int winner = 0;
		int max = 0;
		int[] value = { player1value, player2value, player3value, player4value, player5value };
		for (int i = 0; i < 5; i++) {
			if (max == 0) {
				max = value[i];
				winner = i + 1;
			} else {
				if (value[i] > max) {
					max = value[i];
					winner = i + 1;
				}
			}
		}

		return winner;
	}

	/**
	 * div the deck in to 5 part, save it in to player personalDeck
	 */
	public void dealCards() {

		for (int i = 0; i < 5; i++) {

			for (int ia = 1; ia < 9; ia++) {

				player[i].setPersonalDeck = deck.deckArry[(i * 8 + ia)];

			}
		}

	}

	/**
	 * select a random player as the starting player
	 * 
	 * @return one random player from 5 players
	 */
	public int selectStartingPlayer() {

		int playerNumber = (int) (Math.random() * 5);

		return playerNumber;
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

	//??
	public void chooseCategory() {

	}

	/**
	 * 
	 * @param player which player
	 * @param category the index of category choosen
	 * @return the value the category of the first card in this player 
	 */
	public int getCategoryValueOfPlayer(Player player, int category) {
		int value = 0;

		value = player.getFirstCard.getAtt(category); // card class need change

		return value;
	}

	/**
	 * 
	 */
	public void upDatePlayers() {
		int lostCount = 0;
		for (int i = 0; i < playernumber; i++) {
			if (players[i].card == 0) {

				players[i] = "empty";
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
