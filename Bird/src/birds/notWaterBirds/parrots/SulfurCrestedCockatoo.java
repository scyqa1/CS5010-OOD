package birds.notWaterBirds.parrots;

import birds.conservatory.Foods;
import birds.enums.Enums;

// Bird -> Parrot -> SulfurCrestedCockatoo
public class SulfurCrestedCockatoo extends Parrot {
    public SulfurCrestedCockatoo(int birdId, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food, int numOfWords, String singleWord) {
        super(birdId, Enums.Type.SulfurCrestedCockatoo, characteristic, isExtinct, numOfWings, food, numOfWords, singleWord);
    }
}
