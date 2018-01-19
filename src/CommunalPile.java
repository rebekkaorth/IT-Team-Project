
import java.util.LinkedList;
import java.util.List;

public class CommunalPile {

	private List<Card> cards = new LinkedList<Card>();

	public CommunalPile() {

	}

	/**
	 * 
	 * @return number of the cards in the communal pile
	 */
	public int getNumOfCardsInPile() {
		int numCards = 0;

		numCards = cards.size();

		return numCards;

	}
	
	/**
	 * add the card to the end of the list in Communal Pile
	 * @param card
	 */
	public void giveCardsToPile(Card card) {

		cards.add(getNumOfCardsInPile(), card);
	}

	/**
	 * get the first card from the Communal Pile
	 * @return
	 */
	public Card getCardFormPile() {
		Card firstCard;
		
		firstCard = cards.get(0);
		
		cards.remove(0);
		
		
		return firstCard;
	}
}
