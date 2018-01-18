import java.util.Arrays;

/**
 * This class represents the AI players in the game
 * They choose a category based on the highest numeric value on their current first card.
 */
public class Computer extends Player {

    /**
     * Constructor for computer/ AI players
     * @param playerName
     */
    public Computer(String playerName) {

        super(playerName);
    }

    /**
     * Always returns the highest value of all categories of one card
     * If the highest value is twice on one card the first category found is returned
     * @return categoryChosen
     */
    public String chooseCategory() {
        String categoryChosen = "noCategoryChosen";
        //get values of each category
        int att1 = this.getFirstCard().getAtt1();
        int att2 = this.getFirstCard().getAtt2();
        int att3 = this.getFirstCard().getAtt3();
        int att4 = this.getFirstCard().getAtt4();
        int att5 = this.getFirstCard().getAtt5();

        int [] attributeValues = {att1, att2, att3, att4, att5};

        //get category name of each category
        String att1Name = "";
        String att2Name = "";
        String att3Name = "";
        String att4Name = "";
        String att5Name = "";

        String [] categoryNames = {att1Name, att2Name, att3Name, att4Name, att5Name};

        //get the highest value in attributeValues array
        int maxValue = Arrays.stream(attributeValues).max().getAsInt();

        //check where in the array the highst value is -> categoryNames has corresponding indexes
        for (int i=0; i<attributeValues.length; i++) {
            if(attributeValues[i] == maxValue) {
                categoryChosen = categoryNames[i];
            }
        }

        return categoryChosen;

    }
}
