package birds.notWaterBirds.owls;

import birds.Bird;
import birds.conservatory.Foods;
import birds.enums.Enums;

// Bird -> Owl
public class Owl extends Bird {
    public Owl(int birdId, Enums.Type type, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food) {
        super(birdId, Enums.Classification.Owls, type, characteristic, isExtinct, numOfWings, food);
    }
}
