import java.util.ArrayList;

/**
 * The Player class represents the players who play the game Top Trumps.
 * Each player has a name and a personalDeck of cards.
 * They receive cards from/ give cards to the Game class.
 * It enables players to choose a category from the first card in their deck.
 */
public abstract class Player {
    private String playerName;
    private ArrayList <Card> personalDeck;
    private int numOfCardsInDeck;
    private int numOfRoundsWon;

    /**
     * Constructor of the Player class. It initialises the ArrayList personalDeck.
     * It also sets the number of cards in the ArrayList + the number of rounds won to 0.
     * @param playerName
     */
    public Player(String playerName) {
        this.playerName = playerName;
        this.personalDeck = new ArrayList<>();
        this.numOfCardsInDeck = personalDeck.size();
        this.numOfRoundsWon = 0;
    }

    /**
     * Returns the name of the player
     * @return this.playerName
     */
    public String getPlayerName () {
        return this.playerName;
    }

    /**
     * Add one card to the personalDeck of the player - called when game is started
     * @param card
     */
    public void setPersonalDeck(Card card) {
        personalDeck.add(card);
        numOfCardsInDeck++;
    }

    /**
     * Adds one card to the personalDeck of the player - called when player wins a round
     * @param card
     */
    public void receiveCard (Card card) {
        personalDeck.add(card);
        numOfRoundsWon++;
    }

    /**
     * Removes the first card in the deck from the personalDeck and returns it
     * @return firstCardInDeck
     */
    public Card loseCard () {
        Card firstCard = personalDeck.get(0);
        personalDeck.remove(0);
        numOfCardsInDeck--;
        return firstCard;
    }

    /**
     * Returns the first card in the personalDeck
     * @return firstCardInDeck
     */
    public Card getFirstCard () {
        Card firstCardInDeck = personalDeck.get(0);
        return firstCardInDeck;
    }

    /**
     * Returns the number of cards left in deck
     * @return numOfCardsInDeck
     */
    public int getNumOfCardsInDeck() {
        return numOfCardsInDeck;
    }

    /**
     * Returns the number of rounds won
     * @return numOfRoundsWon
     */
    public int getNumOfRoundsWon() {
        return numOfRoundsWon;
    }

    /**
     * Player can choose a category from the first card in his deck
     * @return categoryName
     */
    abstract String chooseCategory();
}
