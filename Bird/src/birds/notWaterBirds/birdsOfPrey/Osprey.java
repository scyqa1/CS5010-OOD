package birds.notWaterBirds.birdsOfPrey;

import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Type;

// Bird -> BirdOfPrey -> Osprey
public class Osprey extends BirdOfPrey {
    public Osprey(int birdId, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food) {
        super(birdId, Enums.Type.Osprey, characteristic, isExtinct, numOfWings, food);
    }
}
