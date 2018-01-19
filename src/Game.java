import java.util.ArrayList;

public class Game {

	public ArrayList<Player> players = new ArrayList<Player>();

	public int roundCount;
	public int numOfDraws;

	private int playerNumber;
	private boolean isDraw;

	private LogWriter logger;

	CommunalPile communalPile;
	Deck deck;
	String deckTextFile = "StarCitizenDeck.txt";

	private Player activePlayer;

	public Game(int numberOfPlayers) {
		roundCount = 0;
		numOfDraws = 0;
		playerNumber = numberOfPlayers;
		isDraw = false;
	}

	public void playGame() {
		logger  = new LogWriter();
		deck = new Deck(deckTextFile);
		communalPile = new CommunalPile();
		String winner;
		this.setUpPlayers();
		this.dealCards();

		activePlayer = this.selectStartingPlayer();

		while(players.size() > 1) {
			this.roundLoop();
			roundCount++;
			this.updatePlayer();
		}

		winner = players.get(0).getPlayerName();
	}

	public void roundLoop () {
		String chosenCategory;
		Player roundWinner;

		chosenCategory = activePlayer.chooseCategory(deck.getCategoryArray());
		System.out.println("The chosen category is: " + chosenCategory);

		roundWinner = this.compareValue(players, deck.getCategoryIndex(chosenCategory));

		if(isDraw) {
			numOfDraws++;
			for (int i=0; i<players.size(); i++) {
				communalPile.giveCardsToPile(players.get(i).loseCard());
			}
			isDraw = false;
		} else {
			for (int i=0; i<players.size(); i++) {
				roundWinner.receiveCard(players.get(i).loseCard());
				while(communalPile.getNumOfCardsInPile() > 0) {
					roundWinner.receiveCard(communalPile.getCardFormPile());
				}
			}
			activePlayer = roundWinner;
		}
	}

	/**
	 * set up 1 human and 4 AI player
	 */
	public void setUpPlayers() {

		for (int i = 0; i < players.size(); i++) {
			if (i == 0) {
				Human human = new Human("Player");
				players.add(human);
			}
			if (i == 1) {
				Computer AIplayer1 = new Computer("AIplayer1");
				players.add(AIplayer1);
			}
			if (i == 2) {
				Computer AIplayer2 = new Computer("AIplayer2");
				players.add(AIplayer2);
			}
			if (i == 3) {
				Computer AIplayer3 = new Computer("AIplayer3");
				players.add(AIplayer3);
			}
			if (i == 4) {
				Computer AIplayer4 = new Computer("AIplayer4");
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
		for (int i = 0; i < 5; i++) {
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
		}

		return playersArray.get(winner);
	}

	/**
	 * div the deck in to 5 part, save it in to player personalDeck
	 */
	public void dealCards() {

		for (int i = 0; i < players.size(); i++) {

			for (int ia = 0; ia < (int)(deck.deckSize / playerNumber); ia++) {

				players.get(i).setPersonalDeck(deck.deckArray[(i * (int)(deck.deckSize / playerNumber) + ia)]);

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

		return players.get(playerNumber);

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
	public boolean chechGameEnd() {
		int lostCount = 0;
		for (int i = 0; i < playerNumber; i++) {
			if (players.get(i).getNumOfCardsInDeck() == 0) {
				lostCount++;
			}
		}
		if (lostCount == (playerNumber - 1)) {
			
			
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

	public void updatePlayer() {
		for (int i=0; i< players.size(); i++) {
			if (players.get(i).getNumOfCardsInDeck() == 0) {
				players.remove(i);
				i--;
			}
		}
	}

}
