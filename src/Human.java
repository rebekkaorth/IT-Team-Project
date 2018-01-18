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
    public String chooseCategory() {
        String categoryChosen = "noCategoryChosen";

        System.out.println("Please choose a category: ");
        System.out.println(this.getFirstCard().getDescription());
        System.out.println();
        System.out.print(this.getFirstCard().getAtt1());
        System.out.println();
        System.out.print(this.getFirstCard().getAtt2());
        System.out.println();
        System.out.print(this.getFirstCard().getAtt3());
        System.out.println();
        System.out.print(this.getFirstCard().getAtt4());
        System.out.println();
        System.out.print(this.getFirstCard().getAtt5());

        if(scanner.next().equals("size") || scanner.next().equals("speed") || scanner.next().equals("range")
                || scanner.next().equals("firepower") || scanner.next().equals("cargo")) {
            categoryChosen = scanner.next();

        } else {
            System.out.println("wrong input! Try again");
        }
        return categoryChosen;
    }

}




