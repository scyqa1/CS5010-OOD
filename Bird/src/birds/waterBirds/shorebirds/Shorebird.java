package birds.waterBirds.shorebirds;

import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Classification;
import birds.enums.Enums.Type;
import birds.waterBirds.WaterBird;

// Bird -> WaterBird -> Shorebird
// Shorebird -> HornedPuffin / AfricanJacana
public class Shorebird extends WaterBird {
    public Shorebird(int birdId, Enums.Type type, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food, String nameOfWater) {
        super(birdId, Enums.Classification.Shorebirds, type, characteristic, isExtinct, numOfWings, food, nameOfWater);
    }
}
