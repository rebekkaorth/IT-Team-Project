
import java.util.LinkedList;
import java.util.List;

public class CommunalPile {

	private List<String> cards = new LinkedList<String>();

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
	public void giveCardsToPile(String card) {
		cards.add(getNumOfCardsInPile(), card);
	}

	/**
	 * get the first card from the Communal Pile
	 * @return
	 */
	public String getCardFormPile() {
		String firstCard = "";
		
		firstCard = cards.get(0);
		
		cards.remove(0);
		
		
		return firstCard;
	}
}
