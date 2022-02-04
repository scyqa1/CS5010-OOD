package birds.waterBirds.shorebirds;

import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Type;

// Bird -> WaterBird -> Shorebird -> GreatAuk
public class GreatAuk extends Shorebird {
    public GreatAuk(int birdId, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food, String bodyOfWater) {
        super(birdId, Enums.Type.GreatAuk, characteristic, isExtinct, numOfWings, food, bodyOfWater);
    }
}
