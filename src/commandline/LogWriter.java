package commandline;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/*
 * This class is responsible for writing a detailed log of the program's operations
 * when it is run in command line mode, in order to facilitate debugging.
 * It produces an output file called toptrumps.log in the same directory as the program is run in.
 *
 * OPEN ISSUES:
 * 1. The output methods will have to be amended using input parameters
 * 2. We need to ask if the output format is OK like that (especially the "INFO" tag
 * (a possible solution would be using a StringBuilder I guess for logging)).
 */

public class LogWriter {

    /*
     * Instance variables of LogWriter class consisting of
     * a line separator String as well as Logger [[and FileHandler]] objects.
     */
    private static final Logger topTrumpsLogger = Logger.getLogger("TopTrumpsLog"); //name correct?
    private final String lineSeparator = "/n---------------------------------------------/n";

    /*
     * Constructor, should only be called when program is run in command line mode.
     * @try to instantiate FileHandler and SimpleFormatter objects
     * @catch security exception se
     * @catch IOException ioe (file cannot be found etc.)
     */
    public LogWriter() {

        try {

            // instantiate the FileHandler and define file storage location
            FileHandler logFileHandler = new FileHandler("toptrumps.log");
            topTrumpsLogger.addHandler(logFileHandler);

            // needed to format the output as log
            SimpleFormatter formatter = new SimpleFormatter();
            logFileHandler.setFormatter(formatter);

            /*
             * This line will ultimately disable the log output to the console.
             * I am leaving it in for debugging purposes atm.
             * topTrumpsLogger.setUseParentHandlers(false);
             */

            // FOR TESTING ONLY
            topTrumpsLogger.info("This is the first line.");
        }

        catch (SecurityException se) {
            se.printStackTrace();
        }

        catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /* TO DO: change Deck class accordingly
     * Write the contents of the current deck (initial OR shuffled) to the log
     * @param Deck the (initial) deck of cards
     */
    public void writeDeckInfo(Deck deck) {

        topTrumpsLogger.info(String.format("The current deck:/n%s" + deck.getCategoryArray(), lineSeparator));

    }

    /* TO DO: change Deck class accordingly
     * Write the contents of a player's deck to the log
     * @param Player the specific player
     */
    public void writePlayerDeckInfo(Player player) {

        topTrumpsLogger.info(String.format("The deck of player" + player.getPlayerName() + ":/n%s", lineSeparator));

    }

    /* TO DO: does that mean the cards? Would be smart to "copy" the player function for getting the first card
     * Write the contents of the communal pile to the log
     * @param CommunalPile the communal pile
     */
    public void writeCommunalPile(CommunalPile communalPile) {

        topTrumpsLogger.info(String.format("Content of the communal pile:/n%s", lineSeparator));

    }

    /* TO DO: write a loop for appending the individual values
     * Write the contents of the current cards in play to the log
     * @param Player the top card of the player's current deck
     */
    public void writeCurrentCards(Player player) {

        topTrumpsLogger.info(String.format(player.getPlayerName() + ":/n%s" + player.getFirstCard().getDescription(), lineSeparator));

    }

    /* TO DO: StringBuilder for appending the values, using a loop?
     * Write the category and corresponding values selected for the current round to the log
     * @param Player the player for whom the values are displayed
     * @param ? the category
     * @param ? the corresponding value
     */
    public void writeCategoryValues() {

        topTrumpsLogger.info(String.format("The categories and values, basically.%s", lineSeparator));

    }

    /* DONE.
     * Write the game's winner to the log
     * @param Player the winner of the game (defined in Game class)
     */
    public void writeWinner(Player player) {

        topTrumpsLogger.info("The winner of the game is: " + player.getPlayerName());

    }

}