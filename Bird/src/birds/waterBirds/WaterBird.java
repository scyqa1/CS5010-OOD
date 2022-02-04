package birds.waterBirds;

import birds.Bird;
import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Classification;
import birds.enums.Enums.Type;

// Bird -> WaterBird
// WaterBird -> Shorebird / Waterfowl
public class WaterBird extends Bird {
    private String nameOfWater;

    public WaterBird(int birdId, Enums.Classification classification, Enums.Type type, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food, String bodyOfWater) {
        super(birdId, classification, type, characteristic, isExtinct, numOfWings, food);
        this.nameOfWater = bodyOfWater;
    }

    public String getBodyOfWater() {
        return this.nameOfWater;
    }
}
