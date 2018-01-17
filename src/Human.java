import java.util.Scanner;

public class Human extends Player{
    Scanner scanner = new Scanner(System.in);

    public Human(String playerName) {
        super(playerName);
    }

    public String chooseCategory() {
        String category = "noCategoryChosen";


        System.out.println("Please choose a category: ");
        System.out.println(this.getFirstCard().getDescription());
        System.out.println("size");
        System.out.print(this.getFirstCard().getSize());
        System.out.println("speed");
        System.out.print(this.getFirstCard().getSpeed());
        System.out.println("range");
        System.out.print(this.getFirstCard().getRange());
        System.out.println("firepower");
        System.out.print(this.getFirstCard().getFirepower());
        System.out.println("cargo");
        System.out.print(this.getFirstCard().getCargo());

        if(scanner.next().equals("size") || scanner.next().equals("speed") || scanner.next().equals("range")
                || scanner.next().equals("firepower") || scanner.next().equals("cargo")) {
            category = scanner.next();

        } else {
            System.out.println("wrong input! Try again");
        }
        return category;
    }

}




