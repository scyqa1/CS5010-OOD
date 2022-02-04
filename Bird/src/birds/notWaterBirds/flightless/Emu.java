package birds.notWaterBirds.flightless;

import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Type;

// Bird -> Flightless -> Emu
public class Emu extends Flightless {
    public Emu(int birdId, String characteristic, Boolean isExtinct, Foods[] food) {
        super(birdId, Enums.Type.Emus, characteristic, isExtinct, food);
    }
}
