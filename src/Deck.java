
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;

public class Deck {

	protected Card[] deckArray;
	protected static final int deckSize = 40;
	private String att1Name, att2Name, att3Name, att4Name, att5Name;
	private String[] categoryArray;
	/**
	 * Constructor. Creates card object and adds to the deck
	 * 
	 * @param textFile
	 */
	public Deck(String textFile) {
		deckArray = new Card[deckSize];
		int index = 0; // Index for placing new card objects into deck array

		try {
			Scanner scan = new Scanner(new File(textFile));
			String headings = scan.nextLine();
			setCategoryNames(headings);
			while (scan.hasNextLine()) {
				String line = scan.nextLine(); // Line to build card object
				Card newCard = new Card(line);
				deckArray[index] = newCard;
				newCard.setid(index); // Sets the id to an integer between 1 and 40
				index++;
			}
			scan.close();
		} catch (FileNotFoundException e) {
			System.err.println("Input File Not Found");
			e.printStackTrace();
		}

	}

	/**
	 * Method to shuffle the deckArray
	 */
	public void shuffleDeck() {

		Random rnd = new Random();

		for (int i = 0; i < deckSize; i++) {
			int rndIndex = i + rnd.nextInt(deckSize - i);
			Card rndCard = deckArray[rndIndex];
			deckArray[rndIndex] = deckArray[i];
			deckArray[i] = rndCard;
		}

		// Collections.shuffle(Arrays.asList(deckArray));

	}

	/**
	 * Set the category names
	 * 
	 * @param lineIn
	 */
	public void setCategoryNames(String lineIn) {
		categoryArray = lineIn.split(" ");
		att1Name = categoryArray[0];
		att2Name = categoryArray[1];
		att3Name = categoryArray[2];
		att4Name = categoryArray[3];
		att5Name = categoryArray[4];

	}

	/**
	 * Getters for category names
	 * 
	 * @param attNumber
	 * @return
	 */

	public String getAtt(int attNumber) {
		switch (attNumber) {
		case 0:
			return att1Name;
		case 1:
			return att2Name;
		case 2:
			return att3Name;
		case 4:
			return att4Name;
		case 5:
			return att5Name;
		default:
			return "Category does not exist";
		}
	}

	/**
	 * Getter for whole category name array
	 * @return
	 */
	public String[] getCategoryArray(){
		return categoryArray;
	}


	public int getCategoryIndex (String categoryName) {
		int index = 0;

		for(int i=0; i<this.getCategoryArray().length; i++) {
			if (this.getCategoryArray()[i].equals(categoryName)) {
				index = i;
			}
		}

		return index;
	}

	/**
	 * Main method for testing
	 * 
	 * @param args
	 */
	/*
	public static void main(String[] args) {
	 /*
		Deck testDeck = new Deck("StarCitizenDeck.txt");
		System.out.println(new File("StarCitizenDeck.txt").getAbsoluteFile());
		for (int i = 0; i < deckSize; i++) {
			testDeck.shuffleDeck();
			Card c = testDeck.deckArray[i];
			// System.out.println(testDeck.getAttName(0)+" "+testDeck.getAttName(1)+"
			// "+testDeck.getAttName(2)+" "+testDeck.getAttName(3)+"
			// "+testDeck.getAttName(4));
			// System.out.println(c.getid());
		}
	} */
}
