package commandline;

        import java.io.IOException;
        import java.util.logging.FileHandler;
        import java.util.logging.Logger;
        import java.util.logging.SimpleFormatter;

/*
 * This class is responsible for writing a detailed log of the program's operations
 * when it is run in command line mode, in order to facilitate debugging.
 * It produces an output file called toptrumps.log in the same directory as the program is run in.
 */

public class LogWriter {

    /*
     * Instance variables of LogWriter class consisting of
     * a line separator String as well as Logger [[and FileHandler]] objects.
     */
    private static final Logger topTrumpsLogger = Logger.getLogger("commandline.game");
    private static FileHandler logFileHandler;
    private final String lineSeparator = "\r\n-----------------------------------------------------\r\n";

    /*
     * Constructor, should only be called when program is run in command line mode.
     * @try to instantiate FileHandler and SimpleFormatter objects
     * @catch security exception se
     * @catch IOException ioe (file cannot be found etc.)
     */
    public LogWriter() {

        try {

            // instantiate the FileHandler and define file storage location
            logFileHandler = new FileHandler("toptrumps.log");
            topTrumpsLogger.addHandler(logFileHandler);

            // needed to format the output as log
            SimpleFormatter formatter = new SimpleFormatter();
            logFileHandler.setFormatter(formatter);

            /*
             * This line will ultimately disable the log output to the console.
             * I am leaving it in for debugging purposes atm.
             *
             */
            topTrumpsLogger.setUseParentHandlers(false);

        }

        catch (SecurityException se) {
            se.printStackTrace();
        }

        catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    /*
     * Write the contents of the current deck (initial OR shuffled) to the log
     * @param Deck the (initial) deck of cards
     */
    public void writeDeckInfo(Deck deck) {

        String deckString = "\r\nThe current deck:\r\n";
        StringBuilder deckBuilder = new StringBuilder(deckString);

        for (int i = 0; i < Deck.deckSize; i++) {
            deckBuilder.append(writeCard(deck, deck.deckArray[i]));
        }

        topTrumpsLogger.info(deckBuilder.toString() + lineSeparator);

    }

    /*
     * Write the contents of a player's deck to the log
     * @param Deck the (initial) deck of cards
     * @param Game the game currently played
     */
    public void writePlayerDeckInfo(Deck deck, Game game) {

        String playerDeckString = "\r\nThe current deck contents are:\r\n";
        StringBuilder playerDeckBuilder = new StringBuilder(playerDeckString);

        for (int i = 0; i < game.players.size(); i++) {

            playerDeckBuilder.append("Player " + game.players.get(i).getPlayerName() + ": ");

            for (int j = 0; j < game.players.get(i).getNumOfCardsInDeck(); j++) {
                playerDeckBuilder.append(writeCard(deck, game.players.get(i).getCardAtIndex(j)));
            }

        }

        topTrumpsLogger.info(playerDeckBuilder.toString() + lineSeparator);

    }

    /*
     * Write the contents of the communal pile to the log
     * @param Deck the (initial) deck of cards
     * @param CommunalPile the communal pile
     */
    public void writeCommunalPile(Deck deck, CommunalPile communalPile) {

        String pileString = "\r\nThe content of the communal pile is:\r\n";
        StringBuilder pileDeckBuilder = new StringBuilder(pileString);

        //for testing only:
        System.out.println("The current number of cards in the pile: " + communalPile.getNumOfCardsInPile());

        for (int i = 0; i < communalPile.getNumOfCardsInPile(); i++) {
            pileDeckBuilder.append(writeCard(deck, communalPile.getSpecificCard(i)));
        }

        topTrumpsLogger.info(pileDeckBuilder.toString() + lineSeparator);

    }

    /*
     * Write the contents of the current cards in play to the log
     * @param Deck the (initial) deck of cards
     * @param Game the game currently played
     */
    public void writeCurrentCards(Deck deck, Game game) {

        String currentCards = "\r\nThe contents of the current cards in play:\r\n";
        StringBuilder currentCardsBuilder = new StringBuilder(currentCards);

        for (int i = 0; i < game.players.size(); i++) {
            currentCardsBuilder.append(writeCard(deck, game.players.get(i).getFirstCard()));
        }

        topTrumpsLogger.info(currentCardsBuilder.toString() + lineSeparator);

    }

    /*
     * Write the category and corresponding values selected for the current round to the log
     * @param Deck the (initial) deck of cards
     * @param Game the game currently played
     */
    public void writeCategoryValues(Deck deck, Game game) {

        String currentCategoryValues = "\r\nThe corresponding values for the category " + game.chosenCategory + " are:\r\n";
        StringBuilder currentCategoryBuilder = new StringBuilder(currentCategoryValues);

        int categoryNum = deck.getCategoryIndex(game.chosenCategory);

        for (int i = 0; i < game.players.size(); i++) {
            currentCategoryBuilder.append("Player " + game.players.get(i).getPlayerName() + ": ");
            currentCategoryBuilder.append(game.players.get(i).getFirstCard().getAtt(categoryNum) + "\r\n");
        }

        topTrumpsLogger.info(currentCategoryBuilder.toString() + lineSeparator);

    }

    /*
     * Write the game's winner to the log
     * @param winner the winner of the game (defined in Game class)
     */
    public void writeWinner(String winner) {

        topTrumpsLogger.info("\r\nThe winner of the game is: " + winner);

    }

    /*
     * Create a String containing the (formatted) contents of a single card
     * @param Deck the generic deck of cards
     * @param Card the card to be printed
     * @return the single card as a String
     */
    private String writeCard(Deck deck, Card card) {

        String singleCard = "\r\n------------\r\n" + card.getDescription() + "\r\n------------\r\n";
        StringBuilder cardBuilder = new StringBuilder(singleCard);

        for (int i = 0; i < 5; i++) {
            cardBuilder.append(deck.getAttName(i) + ": " + card.getAtt(i) + "\r\n");
        }

        return cardBuilder.toString();
    }

    /*
     * Close the logFileHandler object after finishing writing to the toptrumps.log file
     * Otherwise the old file might not be overwritten (apparently a bug in java 8)
     */
    public void closeFileHandler() {

        logFileHandler.close();

    }

}