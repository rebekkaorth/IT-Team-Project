public class Computer extends Player {

    public Computer(String playerName) {
        super(playerName);
    }

    public String chooseCategory(Card card) {
        this.getFirstCard().getDescription();
        int size = Integer.parseInt(this.getFirstCard().getSize());
        int speed = Integer.parseInt(this.getFirstCard().getSize());
        int range = Integer.parseInt(this.getFirstCard().getSize());
        int firepower = Integer.parseInt(this.getFirstCard().getSize());
        int cargo = Integer.parseInt(this.getFirstCard().getSize());

        return "";
    }
}
