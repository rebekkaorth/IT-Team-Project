package online.dwResources;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import online.configuration.TopTrumpsJSONConfiguration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;

//import commandline package - access classes needed to play a game
import commandline.*;

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

		int numberOfPlayers = (int) Math.floor((Math.random()*5)+2);
		game = new Game(numberOfPlayers, false);

		//starting a new game
		game.playGame();
		System.out.println("new game started");
		game.getDeck().shuffleDeck();
		game.setUpPlayers(numberOfPlayers);
		game.dealCards();
		game.selectStartingPlayer();
	}

	@GET
	@Path("/roundCount")
	public String roundCount() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add(Integer.toString(game.getRoundCount()));
		listOfWords.add(Integer.toString(game.getPlayers().size()));

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path("/numOfPlayer")
	public String numOfPlayers() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add(Integer.toString(game.getPlayers().size()));

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path("/getNumOfCardsForEachPlayer")
	public String getNumOfCardsForEachPlayer() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		for(int i=0; i<game.getPlayers().size(); i++) {
			listOfWords.add(Integer.toString(game.getPlayers().get(i).getNumOfCardsInDeck()));
		}

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path ("/activePlayer")
	public String activePlayer() throws IOException {
		List<String> listOfWords = new ArrayList<>();
		listOfWords.add(game.getActivePlayer().getPlayerName());

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path ("/getChosenCategory")
	public String getChosenCategory() throws IOException {
		List<String> listOfWords = new ArrayList<>();
		listOfWords.add(game.getChosenCategory());

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}


	@GET
	@Path ("/getFirstCardDescription")
	public String getFirstCardDescription() throws IOException {
		List<String> listOfWords = new ArrayList<>();
		listOfWords.add(game.getActivePlayer().getCardAtIndex(0).getDescription());

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}


	@GET
	@Path("/getRoundWinner")
	public String getRoundWinner() throws IOException {
		List<String> listOfWords = new ArrayList<>();
		listOfWords.add(game.getRoundWinner().getPlayerName());

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path("/drawOccurred")
	public String drawOccurred() throws IOException {
		List<String> listOfWords = new ArrayList<>();
		listOfWords.add(Boolean.toString(game.isDraw()));

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path("/gameFinished")
	public String gameFinished() throws IOException {

		List<String> listOfWords = new ArrayList<>();
		for(int i=0; i<game.getPlayers().size(); i++) {
			listOfWords.add(Integer.toString(game.getRoundsWon().get(game.getPlayers().get(i).getPlayerName())));
		}
		listOfWords.add(Integer.toString(game.getNumOfDraws()));
		listOfWords.add(Integer.toString(game.getRoundCount()));


		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path("/showStatistics")
	public String showStatistics() throws IOException {
		//needs to be adjusted
		return "";
	}


	//send from HTML

	@GET
	@Path("/draw")
	/**
	 *
	 * @param Draw - A word
	 * @return - A String
	 * @throws IOException
	 */
	public void draw(@QueryParam("draw") String Draw) throws IOException {
		game.setNumOfDraws(1);
		for (int i = 0; i < game.getPlayers().size(); i++) {
			game.getCommunalPile().giveCardsToPile(game.getPlayers().get(i).loseCard());
		}
		game.setDraw(false);
	}

	@GET
	@Path("/roundEnded")
	/**
	 *
	 * @param Draw - A word
	 * @return - A String
	 * @throws IOException
	 */
	public void roundEnded(@QueryParam("round") String Round) throws IOException {
		for (int i = 0; i < game.getPlayers().size(); i++) {
			game.getRoundWinner().receiveCard(game.getPlayers().get(i).loseCard());
			while (game.getCommunalPile().getNumOfCardsInPile() > 0) {
				game.getRoundWinner().receiveCard(game.getCommunalPile().getCardFormPile());
			}
		}
		game.setActivePlayer(game.getRoundWinner());
		game.getActivePlayer().increaseNumOfRoundsWon();
	}


	@GET
	@Path("/categoryChosen")
	/**
	 *
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public void categoryChosen(@QueryParam("Category") String Category) throws IOException {
		game.setChosenCategory(game.getActivePlayer().chooseCategory(game.getDeck().getCategoryArray()));
	}

	@GET
	@Path("/setRoundWinner")
	/**
	 *
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public void setRoundWinner(@QueryParam("roundWinner") String roundWinner) throws IOException {
		game.setRoundWinner(game.compareValue(game.getPlayers(), game.getDeck().getCategoryIndex(game.getChosenCategory())));
	}

	@GET
	@Path("/setRoundCount")
	/**
	 *
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public void setRoundCount(@QueryParam("roundCount") String roundCount) throws IOException {
		game.setRoundCount(1);
	}

	@GET
	@Path("/updatePlayer")
	/**
	 *
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public void updatePlayer(@QueryParam("updatePlayer") String updatePlayer) throws IOException {
		game.updatePlayer();
	}

	@GET
	@Path("/setGameWinner")
	/**
	 *
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public void setGameWinner (@QueryParam("gameWinner") String gameWinner) throws IOException {
		game.setGameWinner(game.getPlayers().get(0));
		game.getRoundsWon().put(game.getGameWinner().getPlayerName(), game.getGameWinner().getNumOfRoundsWon());
	}

	@GET
	@Path("/writeDatabase")
	public void catChosen(@QueryParam("writeDatabase") String writeDatabase) throws IOException {
		game.writeToDatabase();
	}
}
