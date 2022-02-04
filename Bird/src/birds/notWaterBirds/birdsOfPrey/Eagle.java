package birds.notWaterBirds.birdsOfPrey;

import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Type;

// Bird -> BirdOfPrey -> Eagle
public class Eagle extends BirdOfPrey {
    public Eagle(int birdId, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food) {
        super(birdId, Enums.Type.Eagles, characteristic, isExtinct, numOfWings, food);
    }
}
