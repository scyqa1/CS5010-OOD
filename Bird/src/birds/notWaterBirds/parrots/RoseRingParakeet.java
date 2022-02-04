package birds.notWaterBirds.parrots;

import birds.conservatory.Foods;
import birds.enums.Enums;

// Bird -> Parrot -> RoseRingParakeet
public class RoseRingParakeet extends Parrot {
    public RoseRingParakeet(int birdId, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food, int numOfWords, String singleWord) {
        super(birdId, Enums.Type.RoseRingParakeet, characteristic, isExtinct, numOfWings, food, numOfWords, singleWord);
    }
}
