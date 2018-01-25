package commandline;

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

        System.err.println("Your card name is: "+this.getFirstCard().getDescription());
        System.out.println("Please choose a category: ");
        System.out.print(categoryNames[0]);
        System.out.println(this.getFirstCard().getAtt(0));
        System.out.print(categoryNames[1]);
        System.out.println(this.getFirstCard().getAtt(1));
        System.out.print(categoryNames[2]);
        System.out.println(this.getFirstCard().getAtt(2));
        System.out.print(categoryNames[3]);
        System.out.println(this.getFirstCard().getAtt(3));
        System.out.print(categoryNames[4]);
        System.out.println(this.getFirstCard().getAtt(4));


        boolean inputTrue = false;

        while (inputTrue != true) {
            String userInput = scanner.next();
            userInput = userInput.toLowerCase();
            if (categoryNames[0].toLowerCase().equals(userInput) || categoryNames[1].toLowerCase().equals(userInput)
                    || categoryNames[2].toLowerCase().equals(userInput) || categoryNames[3].toLowerCase().equals(userInput)
                    || categoryNames[4].toLowerCase().equals(userInput)) {
                categoryChosen = userInput;

                inputTrue = true;

            } else {
                System.out.println("Wrong input! Try again");
            }
        }
        return categoryChosen;
    }


}