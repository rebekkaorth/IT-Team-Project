package commandline;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogWriter {

    //instance variables of commandline.LogWriter class
    //name of commandline.LogWriter: should normally be based on the package name or class name of the logged component!
    private static final Logger topTrumpsLogger = Logger.getLogger("TopTrumpsLog");
    private FileHandler logFileHandler;

    /*
    The constructor
    Remember to ONLY generate call when in command line mode!
    */
    public LogWriter() {

        try {

            //instantiate the FileHandler
            logFileHandler = new FileHandler("toptrumps.log");
            topTrumpsLogger.addHandler(logFileHandler);

            //needed to format the output
            SimpleFormatter formatter = new SimpleFormatter();
            logFileHandler.setFormatter(formatter);

            //this is how the messages get written to the log
            topTrumpsLogger.info("This is the first line.");

        }

        catch (SecurityException se) {
            se.printStackTrace();
        }

        catch (IOException ioe) {
            ioe.printStackTrace();
        }

    }

    private final void separateLines() {

        topTrumpsLogger.info(String.format("/n--------------------------------/n"));

    }

    /*
    need information about the parameter to be passed to the method
    is the original deck going to be shuffled?
    is the communal pile going to be different?
    */
    public void writeDeckInfo() {

        topTrumpsLogger.info(String.format("The deck, basically."));
        separateLines();

    }

    //this method needs both the players' name as well as the deck as parameters
    public void writePlayerDeckInfo() {

        topTrumpsLogger.info(String.format("The deck of player + player +, basically."));
        separateLines();

    }

    //does it look different to the other decks?
    public void writeCommunalPile() {

        topTrumpsLogger.info(String.format("The others, basically."));
        separateLines();

    }

    //cards as parameters, call method in for-loop
    public void writeCurrentCards() {

        topTrumpsLogger.info(String.format("The single cards, basically."));
        separateLines();

    }

    //pass each category and values
    public void writeCategoryValues() {

        topTrumpsLogger.info(String.format("The categories and values, basically."));
        separateLines();

    }

    public void writeWinner() {

        topTrumpsLogger.info(String.format("The winners, basically."));
        separateLines();

    }

}