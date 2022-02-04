package birds.notWaterBirds.pigeons;

import birds.Bird;
import birds.conservatory.Foods;
import birds.enums.Enums;

// Bird -> Pigeon
public class Pigeon extends Bird {
    public Pigeon(int birdId, Enums.Type type, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food) {
        super(birdId, Enums.Classification.Pigeons, type, characteristic, isExtinct, numOfWings, food);
    }
}
