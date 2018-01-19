import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/*
 * get parameters from game class
 * game class instantiates DBConnection object
 *
 * Remember: think about how to close the DB connection when player quits the game/web browser
 * Remember: driver is required for using DB connection; is it included in the .jr file in the end?
 * Remember: adjust query method eadFromDB(); it is void at the moment; needs to give back values to the game class
 *
 */

public class DBConnector {
    private String dBName;
    private String userName;
    private String password;

    private Connection connection = null;


    public DBConnector (String dBName, String userName, String password) { //constructor method for DB object

        this.dBName=dBName;
        this.userName=userName;
        this.password=password;

    }


    public void connect() { //method to establish connection to the database

        try {
            connection =
                    DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/"+dBName,userName,password);
        }

        catch (SQLException e) {
            System.err.println("Connection Failed!");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("Connection successful!");
        }

        else {
            System.err.println("Failed to make connection!");
        }
    }


    public void closeConnection() { //method to close connection

        try {
            connection.close();
            System.out.println("Connection closed");
        }

        catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Connection could not be closed - SQL exception");
        }

    }


    public void writeToDB(String winner, int draws, int rounds, int humanRounds, int ai1Rounds) { //what happens when we have variable count of players; idea: overload methods for every num of opponent players

        Statement stmt = null;

        String query = "BEGIN;" +
                "INSERT INTO toptrumps.game(winner, numdraws, numrounds) values ('"+winner+"',"+draws+","+rounds+");" +
                "INSERT INTO toptrumps.rounds(gameid, human, ai1) values ((SELECT max(game.gameid) FROM toptrumps.game),"+humanRounds+", "+ai1Rounds+");" +
                "COMMIT;";

        try {
            stmt = connection.createStatement();
            int updates = stmt.executeUpdate(query);

            System.out.println("Rows updated: "+updates);
        }

        catch (SQLException e ) { //handle error
            e.printStackTrace();
            System.out.println("Error executing query.");

        }

    }


    public void writeToDB(String winner, int draws, int rounds, int humanRounds, int ai1Rounds, int ai2Rounds) {

        Statement stmt = null;

        String query = "BEGIN;" +
                "INSERT INTO toptrumps.game(winner, numdraws, numrounds) values ('"+winner+"',"+draws+","+rounds+");" +
                "INSERT INTO toptrumps.rounds(gameid, human, ai1, ai2) values ((SELECT max(game.gameid) FROM toptrumps.game),"+humanRounds+", "+ai1Rounds+","+ai2Rounds+");" +
                "COMMIT;";

        try {
            stmt = connection.createStatement();
            int updates = stmt.executeUpdate(query);

            System.out.println("Rows updated: "+updates);
        }

        catch (SQLException e ) { //handle error
            e.printStackTrace();
            System.out.println("Error executing query.");

        }

    }

    public void writeToDB(String winner, int draws, int rounds, int humanRounds, int ai1Rounds, int ai2Rounds, int ai3Rounds) {

        Statement stmt = null;

        String query = "BEGIN;" +
                "INSERT INTO toptrumps.game(winner, numdraws, numrounds) values ('"+winner+"',"+draws+","+rounds+");" +
                "INSERT INTO toptrumps.rounds(gameid, human, ai1, ai2, ai3) values ((SELECT max(game.gameid) FROM toptrumps.game),"+humanRounds+", "+ai1Rounds+","+ai2Rounds+", "+ai3Rounds+");" +
                "COMMIT;";

        try {
            stmt = connection.createStatement();
            int updates = stmt.executeUpdate(query);

            System.out.println("Rows updated: "+updates);
        }

        catch (SQLException e ) { //handle error
            e.printStackTrace();
            System.out.println("Error executing query.");

        }

    }


    public void writeToDB(String winner, int draws, int rounds, int humanRounds, int ai1Rounds, int ai2Rounds, int ai3Rounds, int ai4Rounds) {

        Statement stmt = null;

        String query = "BEGIN;" +
                "INSERT INTO toptrumps.game(winner, numdraws, numrounds) values ('"+winner+"',"+draws+","+rounds+");" +
                "INSERT INTO toptrumps.rounds(gameid, human, ai1, ai2, ai3, ai4) values ((SELECT max(game.gameid) FROM toptrumps.game),"+humanRounds+", "+ai1Rounds+","+ai2Rounds+", "+ai3Rounds+", "+ai4Rounds+");" +
                "COMMIT;";

        try {
            stmt = connection.createStatement();
            int updates = stmt.executeUpdate(query);

            System.out.println("Rows updated: "+updates);
        }

        catch (SQLException e ) { //handle error
            e.printStackTrace();
            System.out.println("Error executing query.");

        }

    }

    public void readFromDB() { //needs to be adjusted to return the information back to game class

        Statement stmt = null;

        String query = "SELECT count(game.gameid), max(game.numrounds), avg(game.numdraws) from toptrumps.game;";

        try {
            stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            while (rs.next()) {
                int game_count = rs.getInt("count");
                System.out.println(game_count);
                int max_rounds = rs.getInt("max");
                System.out.println(max_rounds);
                double avg_draws = rs.getDouble("avg");
                System.out.println(avg_draws);
            }

        }

        catch (SQLException e ) { //handle error
            e.printStackTrace();
            System.out.println("Error executing query.");
        }


        Statement stmt2 = null;

        String query2 = "SELECT sum(case when winner = 'Human' then 1 else 0 end) HumanCount, sum(case when winner <> 'Human' then 1 else 0 end) AICount from toptrumps.game;";

        try {
            stmt2 = connection.createStatement();
            ResultSet rs = stmt2.executeQuery(query2);

            while (rs.next()) {
                int human_wins = rs.getInt("humancount");
                System.out.println(human_wins);
                int ai_wins = rs.getInt("aicount");
                System.out.println(ai_wins);
            }

        }

        catch (SQLException e ) { //handle error
            e.printStackTrace();
            System.out.println("Error executing query.");
        }

    }




    //main method for testing
    public static void main(String[] args) {

        DBConnector DB1 = new DBConnector("m_17_2341731l", "m_17_2341731l", "2341731l");
        DB1.connect();

        //method overloading
        DB1.writeToDB("Human", 1, 8, 3, 4);
        DB1.writeToDB("AI1", 2, 9, 4, 1, 0);
        DB1.writeToDB("Human", 2, 9, 4, 1, 0, 1);
        DB1.writeToDB("AI4", 2, 9, 4, 1, 0, 1, 2);

        DB1.readFromDB();

        DB1.closeConnection();

    }

}

