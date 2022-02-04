package birds.notWaterBirds.parrots;

import birds.conservatory.Foods;
import birds.enums.Enums;

// Bird -> Parrot -> GrayParrot
public class GrayParrot extends Parrot {
    public GrayParrot(int birdId, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food, int numOfWords, String singleWord) {
        super(birdId, Enums.Type.GrayParrot, characteristic, isExtinct, numOfWings, food, numOfWords, singleWord);
    }
}
