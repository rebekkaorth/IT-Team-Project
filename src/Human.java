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
        System.out.println(this.getFirstCard().getDescription());
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

        if(scanner.next().equals("size") || scanner.next().equals("speed") || scanner.next().equals("range")
                || scanner.next().equals("firepower") || scanner.next().equals("cargo")) {
            categoryChosen = scanner.next();

        } else {
            System.out.println("Wrong input! Try again");
        }
        return categoryChosen;
    }


}




