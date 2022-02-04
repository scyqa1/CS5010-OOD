package birds.waterBirds.shorebirds;

import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Type;

// Bird -> WaterBird -> Shorebird -> AfricanJacana
public class AfricanJacana extends Shorebird {
    public AfricanJacana(int birdId, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food, String nameOfWater) {
        super(birdId, Enums.Type.AfricanJacana, characteristic, isExtinct, numOfWings, food, nameOfWater);
    }
}
