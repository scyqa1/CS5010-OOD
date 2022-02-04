package birds.waterBirds.waterfowl;

import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Classification;
import birds.enums.Enums.Type;
import birds.waterBirds.WaterBird;

// Bird -> WaterBird -> Waterfowl
// parent of Duck, Swan and Geese
public class Waterfowl extends WaterBird {
    public Waterfowl(int birdId, Enums.Type type, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food, String nameOfWater) {
        super(birdId, Enums.Classification.Waterfowl, type, characteristic, isExtinct, numOfWings, food, nameOfWater);
    }
}
