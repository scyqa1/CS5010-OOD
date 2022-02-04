package birds.notWaterBirds.parrots;

import birds.Bird;
import birds.conservatory.Foods;
import birds.enums.Enums;

// Bird -> Parrot
// Parrot -> RoseRingParakeet / GrayParrot / SulfurCrestedCockatoo
public class Parrot extends Bird {
    private int numOfWords;
    private String singleWord;

    public Parrot(int birdId, Enums.Type type, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food, int numOfWords, String singleWord) {
        super(birdId, Enums.Classification.Parrots, type, characteristic, isExtinct, numOfWings, food);
        if (numOfWords < 0) {
            throw new IllegalArgumentException("Number of words cannot be negative.");
        }

        this.numOfWords = numOfWords;
        this.singleWord = singleWord;
    }

    public int getNumOfWords() {
        return this.numOfWords;
    }

    public String getSingleFavSaying() {
        return this.singleWord;
    }
}
