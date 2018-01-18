
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.io.*;
public class Deck {

	private Card[] deckArray;
	private static final int deckSize = 40;
	private String att1Name,att2Name,att3Name,att4Name,att5Name;
	
	/*
	 * Constructor. Creates card object and adds to the deck
	 */
	public Deck(String textFile) {
		deckArray = new Card[deckSize];
		int index = 0; //Index for placing new card objects into deck array
		
		try {
			Scanner scan = new Scanner(new File(textFile));
			String headings = scan.nextLine(); //Not currently used
			setCategoryNames(headings);
			while (scan.hasNextLine()) {
				String line = scan.nextLine(); //Line to build card object
				Card newCard = new Card(line);
				deckArray[index] = newCard;
				newCard.setid(index); //Sets the id to an integer between 1 and 40 
				index++;
			}
			scan.close();
		}
		catch(FileNotFoundException e) {
			System.err.println("Input File Not Found");
			e.printStackTrace();
		}
		

	}
	
	/*
	 * Method to shuffle the deckArray
	 */
	public void shuffleDeck() {		
		Random rnd = new Random();
		for(int i=0; i<deckSize; i++) {
			int rndIndex = i+rnd.nextInt(deckSize - i);
			Card rndCard = deckArray[rndIndex];
			deckArray[rndIndex] = deckArray[i];
			deckArray[i] = rndCard;
		}
	}
	
	
	/*
	 * Set the category names
	 */
	public void setCategoryNames(String lineIn) {
		String categoryArray[] = lineIn.split(" ");
		att1Name = categoryArray[0];
		att2Name = categoryArray[1];
		att3Name = categoryArray[2];
		att4Name = categoryArray[3];
		att5Name = categoryArray[4];
		
	}
	
	
	
	/*
	 * Getters for category names
	 */
	
	public String getAtt1Name() {
		return att1Name;
	}
	public String getAtt2Name() {
		return att2Name;
	}
	public String getAtt3Name() {
		return att3Name;
	}
	public String getAtt4Name() {
		return att4Name;
	}
	public String getAtt5Name() {
		return att5Name;
	}
	
	
	/*
	 * Main method for testing
	 */
	public static void main(String [] args) {
		Deck testDeck = new Deck("StarCitizenDeck.txt");
		System.out.println(new File("StarCitizenDeck.txt").getAbsoluteFile());
			for(int i=0; i<deckSize; i++) {
			testDeck.shuffleDeck();
			Card c = testDeck.deckArray[i];
		//	System.out.println(c.getDescription()+" "+c.getAtt1()+" "+c.getAtt2()+" "+c.getAtt3()+" "+c.getAtt4()+" "+c.getAtt5()+" "+c.getid());
		//	System.out.println(testDeck.getAtt1Name()+" "+testDeck.getAtt2Name()+" "+testDeck.getAtt3Name()+" "+testDeck.getAtt4Name()+" "+testDeck.getAtt5Name());
		//System.out.println(c.getid());
			}
	}
}
