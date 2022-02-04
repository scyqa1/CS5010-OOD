package birds.notWaterBirds.birdsOfPrey;

import birds.Bird;
import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Classification;
import birds.enums.Enums.Type;

// Bird -> BirdOfPrey
// BirdOfPrey -> Hawk / Eagle / Osprey
public class BirdOfPrey extends Bird {
    public BirdOfPrey(int birdId, Enums.Type type, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food) {
        super(birdId, Enums.Classification.BirdsOfPrey, type, characteristic, isExtinct, numOfWings, food);
    }
}
