package birds.notWaterBirds.flightless;

import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Type;

// Bird -> Flightless -> Kiwi
public class Kiwi extends Flightless {
    public Kiwi(int birdId, String characteristic, Boolean isExtinct, Foods[] food) {
        super(birdId, Enums.Type.Kiwis, characteristic, isExtinct, food);
    }
}
