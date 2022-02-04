package birds.waterBirds.shorebirds;

import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Type;

// Bird -> WaterBird -> Shorebird -> HornedPuffin
public class HornedPuffin extends Shorebird {
    public HornedPuffin(int birdId, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food, String nameOfWater) {
        super(birdId, Enums.Type.HornedPuffin, characteristic, isExtinct, numOfWings, food, nameOfWater);
    }
}
