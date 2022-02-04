package birds.waterBirds.waterfowl;

import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Type;

// Bird -> WaterBird -> Waterfowl -> Swan
public class Swan extends Waterfowl {
    public Swan(int birdId, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food, String nameOfWater) {
        super(birdId, Enums.Type.Swans, characteristic, isExtinct, numOfWings, food, nameOfWater);
    }
}
