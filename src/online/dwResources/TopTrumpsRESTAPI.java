package online.dwResources;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import commandline.Game;
import online.configuration.TopTrumpsJSONConfiguration;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.ArrayList;
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
		int numberOfPlayers = 2 + (int)(Math.random() * ((5 - 2) + 1));
		game = new Game(5);

		//starting a new game
		game.playGame();
		System.out.println("new game started");
		game.getDeck().shuffleDeck();
		game.setUpPlayers(5);
		game.dealCards();
		game.selectStartingPlayer();

	}

	@GET
	@Path("/roundCount")
	public String roundCount() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add(Integer.toString(game.getRoundCount()+1));
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path("/numOfPlayers")
	public String numOfPlayers() throws IOException {

		List<String> listOfWords = new ArrayList<String>();
		listOfWords.add(Integer.toString(game.getPlayers().size()));

		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
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
		String listAsJSONString = oWriter.writeValueAsString(listOfWords);

		return listAsJSONString;
	}

	@GET
	@Path("/catValue")
	public String catValue() throws IOException {

		List<String> listOfWords = new ArrayList<>();
		for(int i=0; i<game.getDeck().getCategoryArray().length; i++) {
			listOfWords.add(Integer.toString(game.getCategoryValueOfPlayer(game.getPlayers().get(i), game.getDeck().getCategoryIndex(game.getChosenCategory()))));
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
		listOfWords.add(game.getPlayers().get(0).getFirstCard().getDescription());
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
	@Path("/numCardsInComPIle")
	public String numCardsInComPIle() throws IOException {
		List<String> listOfWords = new ArrayList<>();
		listOfWords.add(Integer.toString(game.getCommunalPile().getNumOfCardsInPile()));

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
	public String draw(@QueryParam("draw") String Draw) throws IOException {
		game.setNumOfDraws(1);
		for (int i = 0; i < game.getPlayers().size(); i++) {
			game.getCommunalPile().giveCardsToPile(game.getPlayers().get(i).loseCard());
		}
		game.setDraw(false);

		return "draw";
	}

	@GET
	@Path("/roundEnded")
	/**
	 *
	 * @param Draw - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String roundEnded() throws IOException {
		for (int i = 0; i < game.getPlayers().size(); i++) {
			game.getRoundWinner().receiveCard(game.getPlayers().get(i).loseCard());
			while (game.getCommunalPile().getNumOfCardsInPile() > 0) {
				game.getRoundWinner().receiveCard(game.getCommunalPile().getCardFormPile());
			}
		}
		game.setActivePlayer(game.getRoundWinner());
		game.getActivePlayer().increaseNumOfRoundsWon();

		return "round ended";
	}


	@GET
	@Path("/playerChoosesCat")
	/**
	 *
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String playerChoosesCategory(@QueryParam("Category") String Category) throws IOException {
		game.setChosenCategory(Category);
		return "player chose category";
	}


	@GET
	@Path("/categoryChosen")
	/**
	 *
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String categoryChosen(@QueryParam("Category") String Category) throws IOException {
		game.setChosenCategory(game.getActivePlayer().chooseCategory(game.getDeck().getCategoryArray()));

		return "category";
	}

	@GET
	@Path("/setRoundWinner")
	/**
	 *
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String setRoundWinner(@QueryParam("roundWinner") String roundWinner) throws IOException {
		game.setRoundWinner(game.compareValue(game.getPlayers(), game.getDeck().getCategoryIndex(game.getChosenCategory())));
		System.out.println(game.getRoundWinner());
		return "round winner";
	}

	@GET
	@Path("/setRoundCount")
	/**
	 *
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String setRoundCount(@QueryParam("roundCount") String roundCount) throws IOException {
		game.setRoundCount(1);
		return "round count";
	}

	@GET
	@Path("/updatePlayer")
	/**
	 *
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String updatePlayer(@QueryParam("updatePlayer") String updatePlayer) throws IOException {
		game.updatePlayer();
		return "player updated";
	}

	@GET
	@Path("/setGameWinner")
	/**
	 *
	 * @param Word - A word
	 * @return - A String
	 * @throws IOException
	 */
	public String setGameWinner (@QueryParam("gameWinner") String gameWinner) throws IOException {
		game.setGameWinner(game.getPlayers().get(0));
		game.getRoundsWon().put(game.getGameWinner().getPlayerName(), game.getGameWinner().getNumOfRoundsWon());

		return "game winner";
	}

	@GET
	@Path("/writeDatabase")
	public String catChosen(@QueryParam("writeDatabase") String writeDatabase) throws IOException {
		game.writeToDatabase();
		return "written to DB";
	}
}
