package birds.waterBirds.waterfowl;

import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Type;

// Bird -> WaterBird -> Waterfowl -> Geese
public class Geese extends Waterfowl {
    public Geese(int birdId, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food, String nameOfWater) {
        super(birdId, Enums.Type.Geese, characteristic, isExtinct, numOfWings, food, nameOfWater);
    }
}
