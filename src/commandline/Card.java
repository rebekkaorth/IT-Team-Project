package commandline;

import java.util.*;

public class Card {

	private String description;
	private int att1, att2, att3, att4, att5;
	private int id;

	/**
	 * Constructor for commandline.Card. Input is a line from the text file
	 * 
	 * @param lineIn
	 */
	public Card(String lineIn) {
		Scanner scan = new Scanner(lineIn);
		while (scan.hasNext()) {
			description = scan.next();
			att1 = scan.nextInt();
			att2 = scan.nextInt();
			att3 = scan.nextInt();
			att4 = scan.nextInt();
			att5 = scan.nextInt();
		}
		scan.close();
	}

	/**
	 * Get Description
	 * 
	 * @return
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Get Attribute values
	 * 
	 * @param attNumber
	 * @return
	 */
	public int getAtt(int attNumber) {
		switch (attNumber) {
		case 0:
			return att1;
		case 1:
			return att2;
		case 2:
			return att3;
		case 3:
			return att4;
		case 4:
			return att5;
		default:
			return 0;
		}
	}

	public int getid() {
		return id;
	}

	/**
	 * Setter for the id
	 * 
	 * @param newid
	 * @return
	 */
	public int setid(int newid) {
		id = newid;
		return id;
	}

	/*
	 * Main method for testing
	 */
	// public static void main(String [] args) {
	// commandline.Card testCard = new commandline.Card("hello 1 2 3 4 5");
	// System.out.println(testCard.description+" "+testCard.att1+"
	// "+testCard.att2+" "+testCard.att3+" "+testCard.att4+" "+testCard.att5);
	// System.err.println(testCard.idCount);
	// }

}
