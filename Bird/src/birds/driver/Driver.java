package birds.driver;

import birds.conservatory.Conservatory;
import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.enums.Enums.Food;
import birds.notWaterBirds.flightless.Moa;

/** 
 * This class is a driver to present on lab
 * align a moa with food to a new conservatory
 * look up the bird and print information
 */

public class Driver {
    public static void main(String[] args) {
        Conservatory C = new Conservatory();
        // Moa
        Foods buds = new Foods(Enums.Food.Buds, 3);
        Foods nuts = new Foods(Enums.Food.Nuts, 5);
        Foods[] moaFood = new Foods[2];
        moaFood[0] = buds;
        moaFood[1] = nuts;
        Moa moa = new Moa(3, "moa", false, moaFood);
        C.assignToAviary(moa, 1);

        C.lookUpBird(3);
        C.printMap();
        C.printSign(1);
    }
}