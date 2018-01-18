public class Game {
	
public String[] players;
public String[] allPlayers;

public int roundCount;
public int numberOfDraws;
	private int playernumber =5;
	
	
	public Game() {
		roundCount = 0;
		numberOfDraws =0;
		setUpPlayers();
		
		
	}
	
	/**
	 * set up 1 human and 4 AI player
	 */
	public void setUpPlayers(){
		
		players = new String[playernumber];
		for(int i=0; i<playernumber; i++) {
			if(i==0) {
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
	 * @param player1value value of the card from player1
	 * @param player2value value of the card from player1
	 * @param player3value value of the card from player1
	 * @param player4value value of the card from player1
	 * @param player5value value of the card from player1
	 * @return the winner of the round, draw if the return value is 0
	 */
	public int roundWinner(int player1value,int player2value,int player3value,int player4value,int player5value) {
		int winner =0;
		int max = 0;
		int[] value = {player1value, player2value, player3value, player4value, player5value};
		for(int i=0; i<5; i++) {
			if(max == 0) {
				max = value[i];
				winner = i+1;
			}else {
				if(value[i]>max) {
					max = value[i];
					winner = i+1;
				}
			}
		}
		
		return winner;
	}
	
	/**
	 * div the deck in to 5 part, save it in to player personalDeck
	 */
	public dealCards() {
		
		
		
	}
	
	/**
	 * 
	 */
	public void upDatePlayers() {
		int lostCount = 0;
		for(int i=0; i<playernumber; i++) {
			if(players[i].card==0) {
			
				players[i] = "empty";
				lostCount++;
			}
		}
		
		if(lostCount ==( playernumber -1)) {
			System.out.println("Game finish.");
		}
		
		
	}
	
	

}
