package birds.notWaterBirds.birdsOfPrey;

import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Type;

// Bird -> BirdOfPrey -> Hawk
public class Hawk extends BirdOfPrey {
    public Hawk(int birdId, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food) {
        super(birdId, Enums.Type.Hawks, characteristic, isExtinct, numOfWings, food);
    }
}
