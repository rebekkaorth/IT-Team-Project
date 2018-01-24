package commandline;

import java.util.ArrayList;

public class CommunalPile {

	private ArrayList<Card> cards = new ArrayList<Card>();

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
	 * 
	 * @param card 
	 */
	public void giveCardsToPile(Card card) {

		cards.add(getNumOfCardsInPile(), card);

	}

	/**
	 * get the first card from the Communal Pile and delete it from communalPile
	 * 
	 * @return the first card in the communal Pile
	 */
	public Card getCardFormPile() {

		Card firstCard;

		firstCard = cards.get(0);

		cards.remove(0);

		return firstCard;
	}
}