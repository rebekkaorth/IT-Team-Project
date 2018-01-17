import java.util.*;

public class Card {

	private String description;
	private int att1,att2,att3,att4,att5;
	private int id;
	
	/*
	 * Constructor for Card. Input is a line from the text file
	 */
	public Card(String lineIn) {
		Scanner scan = new Scanner(lineIn);
		while(scan.hasNext()) {
			description = scan.next();
			att1 = scan.nextInt();
			att2 = scan.nextInt();
			att3 = scan.nextInt();
			att4 = scan.nextInt();
			att5 = scan.nextInt();			
		}
		scan.close();
	}
	
	/*
	 * Getters
	 */
	public String getDescription() {
		return description;
	}
	public int getAtt1() {
		return att1;
	}
	public int getAtt2() {
		return att2;
	}
	public int getAtt3() {
		return att3;
	}
	public int getAtt4() {
		return att4;
	}
	public int getAtt5() {
		return att5;
	}
	public int getid() {
		return id;
	}
	
	/*
	 * Setter for the id
	 */
	public int setid(int newid) {
		id = newid;
		return id;
	}
	
/*
 * Main method for testing
 */
//	public static void main(String [] args) {
//		Card testCard = new Card("hello 1 2 3 4 5");
//		System.out.println(testCard.description+" "+testCard.att1+" 
//		"+testCard.att2+" "+testCard.att3+" "+testCard.att4+" "+testCard.att5);
//		System.err.println(testCard.idCount);
//	}
	
	
}
