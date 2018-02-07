package online.dwResources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import commandline.DBConnector;
import commandline.Game;
import online.configuration.TopTrumpsJSONConfiguration;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

//import commandline package - access classes needed to play a game

@Path("/toptrumps") // Resources specified here should be hosted at http://localhost:7777/toptrumps
@Produces(MediaType.APPLICATION_JSON) // This resource returns JSON content
@Consumes(MediaType.APPLICATION_JSON) // This resource can take JSON content as input
/**
 * This is a Dropwizard Resource that specifies what to provide when a user
 * requests a particular URL. In this case, the URLs are associated to the
 * different REST API methods that you will need to expose the game commands
 * to the Web page.
 * 
 * Below are provided some sample methods that illustrate how to create
 * REST API methods in Dropwizard. You will need to replace these with
 * methods that allow a TopTrumps game to be controled from a Web page.
 */
public class TopTrumpsRESTAPI {

	/** A Jackson Object writer. It allows us to turn Java objects
	 * into JSON strings easily. */
	ObjectWriter oWriter = new ObjectMapper().writerWithDefaultPrettyPrinter();

	/**
	 * initialization of the game object
	 */
	Game game;

	/**
	 * Contructor method for the REST API. This is called first. It provides
	 * a TopTrumpsJSONConfiguration from which you can get the location of
	 * the deck file and the number of AI players.
	 * @param conf
	 */
	public TopTrumpsRESTAPI(TopTrumpsJSONConfiguration conf) {

	}

	private void getRoundResult() {
		//set winner of the round
		game.setRoundWinner(game.compareValue(game.getPlayers(), game.getDeck().getCategoryIndex(game.getChosenCategory())));
		game.incRoundCount(1);
	}

	@PUT
	@Path("/startGame")
	public void startGame() {
		int numberOfPlayers = 2 + (int)(Math.random() * ((5 - 2) + 1));
		game = new Game( 3);

		//starting a new game
		game.playGame();
		game.getDeck().shuffleDeck();
		game.setUpPlayers();
		game.dealCards();
		game.selectStartingPlayer();
	}

	@GET
	@Path("/roundCount")
	public String roundCount() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add(Integer.toString(game.getRoundCount()+1));

		return oWriter.writeValueAsString(listOfWords);
	}

	@GET
	@Path("/numOfPlayers")
	public String numOfPlayers() throws IOException {
		return oWriter.writeValueAsString(Integer.toString(game.getPlayers().size()));
	}

	@GET
	@Path("/cardCatNames")
	public String cardCatNames() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		for (int i=0; i<game.getDeck().getCategoryArray().length; i++) {
			listOfWords.add(game.getDeck().getAttName(i));
		}

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path("/getFirstCardValues")
	public String getFirstCardValues() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		for (int i=0; i<game.getDeck().getCategoryArray().length; i++) {
			listOfWords.add(Integer.toString(game.getPlayers().get(0).getFirstCard().getAtt(i)));
		}

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}


	@GET
	@Path("/getNumOfCardsForEachPlayer")
	public String getNumOfCardsForEachPlayer() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add(Integer.toString(game.getPlayers().size()));
		for(int i=0; i<game.getPlayers().size(); i++) {
			listOfWords.add(Integer.toString(game.getPlayers().get(i).getNumOfCardsInDeck()));
		}

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path("/namesOfPlayers")
	public String namesOfPlayers() throws IOException {

		List<String> listOfWords = new ArrayList<>();
		listOfWords.add(Integer.toString(game.getPlayers().size()));
		for(int i=0; i<game.getPlayers().size(); i++) {
			listOfWords.add(game.getPlayers().get(i).getPlayerName());
		}
		listOfWords.add(game.getActivePlayer().getPlayerName());
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	//names and number of cards sent together
	@GET
	@Path("/playerNamesAndNumOfCards")
	public String playerNamesAndNumOfCards() throws IOException {

		ArrayList<String> playersAndCards = new ArrayList<>();
		for(int i=0; i<game.getPlayers().size(); i++) {
			playersAndCards.add(game.getPlayers().get(i).getPlayerName());
			playersAndCards.add(Integer.toString(game.getPlayers().get(i).getNumOfCardsInDeck()));
		}
		String json = new ObjectMapper().writeValueAsString(playersAndCards);
		return json;
	}

	@GET
	@Path("/catValuesOfPlayers")
	public String catValuesOfPlayers() throws IOException {

		List<String> listOfWords = new ArrayList<>();
		for(int i=0; i<game.getPlayers().size(); i++) {
			listOfWords.add(Integer.toString(game.getCategoryValueOfPlayer(game.getPlayers().get(i), game.getDeck().getCategoryIndex(game.getChosenCategory()))));
		}
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		//deal cards according to game rules - round winner gets cards or communal pile if isDraw is true
		if (game.isDraw()) {
			game.incNumOfDraws(1);
			for (int i = 0; i < game.getPlayers().size(); i++) {
				game.getCommunalPile().giveCardsToPile(game.getPlayers().get(i).loseCard());
			}
			game.setDraw(false);
		} else {
			for (int i = 0; i < game.getPlayers().size(); i++) {
				game.getRoundWinner().receiveCard(game.getPlayers().get(i).loseCard());

				while (game.getCommunalPile().getNumOfCardsInPile() > 0) {
					game.getRoundWinner().receiveCard(game.getCommunalPile().getCardFormPile());
				}
			}
			game.setActivePlayer(game.getRoundWinner());
			game.getActivePlayer().increaseNumOfRoundsWon();
		}

		game.updatePlayer();

		if(game.getPlayers().size() < 2) {
			game.setGameWinner(game.getPlayers().get(0));
		}

		return listAsJSONString;
	}

	@GET
	@Path ("/activePlayer")
	public String activePlayer() throws IOException {
		return oWriter.writeValueAsString(game.getActivePlayer().getPlayerName());
	}

	/*@GET
	@Path ("/drawOccurred")
	public String drawOccurred() throws IOException {
		return oWriter.writeValueAsString(game.isDraw());
	}*/

	@PUT
	@Path ("/getAIchosenCategory")
	public String getAIchosenCategory() throws IOException {
		game.setChosenCategory(game.getActivePlayer().chooseCategory(game.getDeck().getCategoryArray()));
		String chosenCat = game.getChosenCategory();
		getRoundResult();
		
		return oWriter.writeValueAsString(chosenCat);
	}

	@PUT
	@Path("/humanPlayerChosenCategory")
	/**
	 * Here is an example of how to read parameters provided in an HTML Get request.
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String humanPlayerChosenCategory(@QueryParam("category") String category) throws IOException {
		game.setChosenCategory(category);
		getRoundResult();
		return oWriter.writeValueAsString(category);
	}


	@GET
	@Path ("/getFirstCardDescription")
	public String getFirstCardDescription() throws IOException {
		return oWriter.writeValueAsString(game.getPlayers().get(0).getFirstCard().getDescription());
	}


	@GET
	@Path("/getRoundWinner")
	public String getRoundWinner() throws IOException {
		if (null != game.getRoundWinner()){
		return oWriter.writeValueAsString(game.getRoundWinner().getPlayerName());
		}
		else {
			return oWriter.writeValueAsString("none");
		}
	}

	/**
	 *
	 * @return
	 * @throws IOException
	 */
	@GET
	@Path("/numCardsInComPIle")
	public String numCardsInComPIle() throws IOException {
		List<String> listOfWords = new ArrayList<>();
		listOfWords.add(Integer.toString(game.getCommunalPile().getNumOfCardsInPile()));

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path("/getGameWinner")
	/**
	 *
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String getGameWinner () throws IOException {
		return oWriter.writeValueAsString(game.getGameWinner().getPlayerName());
	}
	
	@GET
	@Path("/getTotalGame")
	public String getTotalGame() throws IOException {

		DBConnector dBStats = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
		dBStats.connect();
		HashMap statistics = dBStats.readFromDB();
		String str = (String) statistics.get("Number of games");
		dBStats.closeConnection();

		return oWriter.writeValueAsString(str);
	}
	
	@GET
	@Path("/getAverageDraw")
	public String getAverageDraw() throws IOException {

		DBConnector dBStats = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
		dBStats.connect();
		HashMap statistics = dBStats.readFromDB();
		String str = (String) statistics.get("Avg. number of draws");
		dBStats.closeConnection();

		return oWriter.writeValueAsString(str);
	}
	
	@GET
	@Path("/getHighestNumberOfRound")
	public String getHighestNumberOfRound() throws IOException {

		DBConnector dBStats = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
		dBStats.connect();
		HashMap statistics = dBStats.readFromDB();
		String str = (String) statistics.get("Max. number of rounds");
		dBStats.closeConnection();

		return oWriter.writeValueAsString(str);
	}
	
	@GET
	@Path("/getHumanWin")
	public String getHumanWin() throws IOException {

		DBConnector dBStats = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
		dBStats.connect();
		HashMap statistics = dBStats.readFromDB();
		String str = (String) statistics.get("Games won by human");
		dBStats.closeConnection();

		return oWriter.writeValueAsString(str);
	}
	
	@GET
	@Path("/getAIWin")
	public String getAIWin() throws IOException {

		DBConnector dBStats = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
		dBStats.connect();
		HashMap statistics = dBStats.readFromDB();
		String str = (String) statistics.get("Games won by AI");
		dBStats.closeConnection();

		return oWriter.writeValueAsString(str);
	}
	
	
}
