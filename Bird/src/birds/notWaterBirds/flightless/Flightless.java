package birds.notWaterBirds.flightless;

import birds.Bird;
import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Classification;
import birds.enums.Enums.Type;

// Bird -> Flightless
// Flightless -> Emu / Kiwi / Moa
public class Flightless extends Bird {
    public Flightless(int birdId, Enums.Type type, String characteristic, Boolean isExtinct, Foods[] food) {
        super(birdId, Enums.Classification.Flightless, type, characteristic, isExtinct, 0, food);
    }
}
