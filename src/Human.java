import java.util.Scanner;

public class Human extends Player{

    //create scanner object for user input over the command line
    Scanner scanner = new Scanner(System.in);

    /**
     * Constructor method for human players
     * @param playerName
     */
    public Human(String playerName) {
        super(playerName);
    }

    /**
     * Lets the human player choose a category by displaying the first card in his deck in the command line
     * @return categoryChosen
     */
    public String chooseCategory(String [] categoryNames) {
        String categoryChosen = "noCategoryChosen";

        System.out.println("Please choose a category: ");
        System.out.println(categoryNames[0]);
        System.out.print(this.getFirstCard().getAtt(0));
        System.out.println(categoryNames[1]);
        System.out.print(this.getFirstCard().getAtt(1));
        System.out.println(categoryNames[2]);
        System.out.print(this.getFirstCard().getAtt(2));
        System.out.println(categoryNames[3]);
        System.out.print(this.getFirstCard().getAtt(3));
        System.out.println(categoryNames[4]);
        System.out.print(this.getFirstCard().getAtt(4));
        System.out.println(categoryNames[5]);
        System.out.print(this.getFirstCard().getAtt(5));

        String userInput = scanner.next();
        if(categoryNames[1].equals(userInput) || categoryNames[2].equals(userInput)
                || categoryNames[3].equals(userInput) || categoryNames[4].equals(userInput)
                || categoryNames[5].equals(userInput)) {
            categoryChosen = userInput;

        } else {
            System.out.println("Wrong input! Try again");
        }
        return categoryChosen;
    }


}




