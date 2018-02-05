package commandline;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameTest {

    private Game testGame;

    @Before
    public void beforeEachTest(){
        testGame = new Game(3);
    }


   /* @Test
    public void setRoundCount() {

        //Test if roundcount==10 before and after setting the roundcount
        assertNotEquals(10, testGame.getRoundCount());
        testGame.setRoundCount(10);
        assertEquals(10, testGame.getRoundCount());
        assertNotEquals(0,testGame.getRoundCount());
    }

    @Test
    public void setNumOfDraws() {

        //Test is NumOfDraws==2 before and after setting NumOfDraws
        assertNotEquals(2,testGame.getNumOfDraws());
        testGame.setNumOfDraws(2);
        assertEquals(2, testGame.getNumOfDraws());
    }*/

    @Test
    public void setDraw() {

        //Test if isDraw returns true before and after setting
        assertNotEquals(true, testGame.isDraw());
        testGame.setDraw(true);
        assertEquals(true, testGame.isDraw());
    }

    @Test
    public void setActivePlayer() {

            testGame.setUpPlayers(5);
            assertNull(testGame.getActivePlayer()); //Active player should be null as no active player has been set yet
        //Set active player to AI Player 2 and test
            testGame.setActivePlayer(testGame.getPlayers().get(2));
            assertEquals("AI Player 2",testGame.getActivePlayer().getPlayerName());
            assertNotEquals("Human Player", testGame.getActivePlayer().getPlayerName());


    }

    @Test
    public void setGameWinner() {
        testGame.setUpPlayers(5); //Set up player to test
        assertNull(testGame.getGameWinner()); //Initially, getGameWinner should return null - no winner set
        //Set winner to human player and test
        testGame.setGameWinner(testGame.getPlayers().get(0));
        assertEquals("Human Player", testGame.getGameWinner().getPlayerName());
        assertNotEquals("AI Player 2", testGame.getGameWinner().getPlayerName());
    }

    @Test
    public void setChosenCategory() {
        assertNull(testGame.getChosenCategory()); //Should be null initially - no category chosen yet
        //Set chosen category and test
        testGame.setChosenCategory("testCategory");
        assertEquals("testCategory", testGame.getChosenCategory());
        assertNotEquals("Happiness", testGame.getChosenCategory());
    }


    @Test
    public void setRoundWinner() {
        assertNull(testGame.getRoundWinner()); //Should be null initially - no winner chosen yet
        //Set round winner to human player and test
        testGame.setUpPlayers(5);
        testGame.setRoundWinner(testGame.getPlayers().get(0));

        assertEquals("Human Player", testGame.getRoundWinner().getPlayerName());
        assertNotEquals("AI Player 2", testGame.getRoundWinner().getPlayerName());
    }


    @Test
    public void setUpPlayers() {
        testGame.setUpPlayers(5);
        assertEquals(5, testGame.getPlayers().size());
        assertFalse(testGame.getPlayers().isEmpty());
        assertEquals("Human Player", testGame.getPlayers().get(0).getPlayerName());

    }

    @Test
    public void compareValue() {
        //Set up player objects with a personal deck
        //Add these player objects to a Player ArrayList
        //Use the compareValue method to return a winner
        testGame.setUpPlayers(3);

        //Set up card objects.
        //Different values except for value 3 (on cards 1 and 2), which allows a test for a draw
        Card card1 = new Card("card1 1 2 10 4 5");
        Card card2 = new Card("card2 2 1 10 9 8");
        Card card3 = new Card("card3 0 8 9 0 2");

        //Set personal decks
        ArrayList<Player> playersArray = testGame.getPlayers();
        playersArray.get(0).setPersonalDeck(card1);
        playersArray.get(1).setPersonalDeck(card2);
        playersArray.get(2).setPersonalDeck(card3);


        //test compareValue method for category

        //Printouts for debugging
        for(int i=0; i<playersArray.size(); i++){
           System.out.println(playersArray.get(i).getPlayerName()+": "+testGame.getCategoryValueOfPlayer(playersArray.get(i),1)) ;
        }

        //test statement. Should evaluate to AI Player 2 (see printouts for confirmation)
        assertEquals("AI Player 2", testGame.compareValue(playersArray,1).getPlayerName());

        //Test if draw logic is working. First call compare value on a category with
        //a draw (category 3, index 2 in this case). Calling the isDraw method should
        //return true
        testGame.compareValue(playersArray, 2);
        assertTrue(testGame.isDraw());


    }

    @Test
    public void dealCards() {
    }

    @Test
    public void selectStartingPlayer() {
    }

    @Test
    public void getCategoryValueOfPlayer() {
        //To test this, need to set up a player object, and a personal deck with at least 1 card object
        Human testHuman = new Human("Bob");
        Card card1 = new Card("card1 1 2 3 4 5");
        testHuman.setPersonalDeck(card1);

        //Test getCategoryValueOfPlayer method with testHuman as input
        assertEquals(1,testGame.getCategoryValueOfPlayer(testHuman, 0));
        assertNotEquals(10, testGame.getCategoryValueOfPlayer(testHuman, 2));
    }

    @Test
    public void updatePlayer() {
    }

    @Test
    public void writeToDatabase() {
    }
}