package birds.notWaterBirds.flightless;

import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Type;

// Bird -> Flightless -> Moa
public class Moa extends Flightless {
    public Moa(int birdId, String characteristic, Boolean isExtinct, Foods[] food) {
        super(birdId, Enums.Type.Moas, characteristic, isExtinct, food);
    }
}
