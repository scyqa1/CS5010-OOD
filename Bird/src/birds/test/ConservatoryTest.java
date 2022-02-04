package birds.test;


import org.junit.Before;
import org.junit.Test;

import birds.Bird;
import birds.conservatory.Aviary;
import birds.conservatory.Conservatory;
import birds.conservatory.Foods;
import birds.enums.Enums;
import birds.notWaterBirds.flightless.Moa;
import birds.notWaterBirds.birdsOfPrey.Hawk;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class ConservatoryTest {
    private Conservatory conservatory;

    
    // Constructor
    @Before
    public void setUp() {
        conservatory = new Conservatory();
    }

    //test attribute
    @Test
    public void testGetNumOfAviaries() {
        assertEquals(0, conservatory.getNumOfAviaries());
    }

    //test attribute
    @Test
    public void testGetAviaries() {
        ArrayList<Aviary> aviaries = new ArrayList<Aviary>();
        assertEquals(aviaries, conservatory.getAviaries());
    }

    //test attribute
    @Test
    public void testGetFoodsNeeded() {
        ArrayList<Enums.Food> foodsNeeded = new ArrayList<Enums.Food>();
        assertEquals(foodsNeeded, conservatory.getFoodsNeeded());
    }

    //test attribute
    @Test
    public void testGetFoodQuantities() {
        ArrayList<Integer> foodQuantities = new ArrayList<Integer>();
        assertEquals(foodQuantities, conservatory.getFoodQuantities());
    }

    //test exceptions
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAviaryId1() {
        // hawk
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        Bird hawk = new Hawk(1, hawkCharacteristic, false, 6, hawkFoods);

        conservatory.assignToAviary(hawk, 0);
    }

    //test exceptions
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidAviaryId2() {
        // hawk
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        Bird hawk = new Hawk(1, hawkCharacteristic, false, 6, hawkFoods);

        conservatory.assignToAviary(hawk, -1);
    }

    //test exceptions
    @Test(expected = IllegalStateException.class)
    public void testInvalidIsExtinct() {
        // hawk
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        Bird hawk = new Hawk(1, hawkCharacteristic, true, 6, hawkFoods);

        conservatory.assignToAviary(hawk, 1);
    }

    /*
     * test Assign Bird To Aviary (hawk)
     * test would be similar for other types
     * */
    @Test
    public void testAssignToAviary1() {
        // hawk
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        Bird hawk = new Hawk(1, hawkCharacteristic, false, 6, hawkFoods);
        Boolean res = conservatory.assignToAviary(hawk, 1);

        assertEquals(true, res);

        assertEquals(1, conservatory.getNumOfAviaries());

        assertEquals(1, conservatory.getAviaries().get(0).getAviaryId());
        assertEquals("Birds of Prey", conservatory.getAviaries().get(0).getAviaryType());
        assertEquals(1, conservatory.getAviaries().get(0).getNumOfBirds());
        assertEquals(hawk, conservatory.getAviaries().get(0).getBirds().get(0));
        assertEquals(1, conservatory.getAviaries().get(0).getBirds().get(0).getAviaryId());

        assertEquals(Enums.Food.Eggs, conservatory.getFoodsNeeded().get(0));
        assertEquals(Enums.Food.Fish, conservatory.getFoodsNeeded().get(1));

        assertEquals((Integer) 3, conservatory.getFoodQuantities().get(0));
        assertEquals((Integer) 8, conservatory.getFoodQuantities().get(1));
    }

    @Test
    public void testAssignToAviary2() {
        // hawk
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        Bird hawk = new Hawk(1, hawkCharacteristic, false, 6, hawkFoods);
        Boolean res1 = conservatory.assignToAviary(hawk, 1);

        // moa
        Foods moaSeeds = new Foods(Enums.Food.Seeds, 6);
        Foods moaBuds = new Foods(Enums.Food.Buds, 2);
        Foods moaFish = new Foods(Enums.Food.Fish, 5);
        Foods[] moaFoods = new Foods[3];
        moaFoods[0] = moaSeeds;
        moaFoods[1] = moaBuds;
        moaFoods[2] = moaFish;
        String moaCharacteristic = "Live on the ground and have no (or undeveloped) wings.";
        Bird moa = new Moa(6, moaCharacteristic, false, moaFoods);
        Boolean res2 = conservatory.assignToAviary(moa, 6);

        assertEquals(true, res1);
        assertEquals(true, res2);

        assertEquals(2, conservatory.getNumOfAviaries());

        assertEquals(1, conservatory.getAviaries().get(0).getAviaryId());
        assertEquals("Birds of Prey", conservatory.getAviaries().get(0).getAviaryType());
        assertEquals(1, conservatory.getAviaries().get(0).getNumOfBirds());
        assertEquals(hawk, conservatory.getAviaries().get(0).getBirds().get(0));
        assertEquals(1, conservatory.getAviaries().get(0).getBirds().get(0).getAviaryId());

        assertEquals(6, conservatory.getAviaries().get(1).getAviaryId());
        assertEquals("Flightless Birds", conservatory.getAviaries().get(1).getAviaryType());
        assertEquals(1, conservatory.getAviaries().get(1).getNumOfBirds());
        assertEquals(moa, conservatory.getAviaries().get(1).getBirds().get(0));
        assertEquals(6, conservatory.getAviaries().get(1).getBirds().get(0).getAviaryId());

        assertEquals(Enums.Food.Eggs, conservatory.getFoodsNeeded().get(0));
        assertEquals(Enums.Food.Fish, conservatory.getFoodsNeeded().get(1));
        assertEquals(Enums.Food.Seeds, conservatory.getFoodsNeeded().get(2));
        assertEquals(Enums.Food.Buds, conservatory.getFoodsNeeded().get(3));

        assertEquals((Integer) 3, conservatory.getFoodQuantities().get(0));
        assertEquals((Integer) 13, conservatory.getFoodQuantities().get(1));
        assertEquals((Integer) 6, conservatory.getFoodQuantities().get(2));
        assertEquals((Integer) 2, conservatory.getFoodQuantities().get(3));
    }

    @Test
    public void testAddTwentyAviaries() {
        // twenty hawks and twenty aviaries
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        for (int i = 0; i < 20; i++) {
            Bird hawk = new Hawk(i + 1, hawkCharacteristic, false, 6, hawkFoods);
            conservatory.assignToAviary(hawk, i + 1);
            assertEquals(i + 1, conservatory.getNumOfAviaries());
        }
        assertEquals(20, conservatory.getNumOfAviaries());
    }
    
    //test exceptions for Invalid Number Of Aviaries
    @Test(expected = IllegalStateException.class)
    public void testInvalidNumOfAviaries() {
        // twenty-one hawks and twenty-one aviaries
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        for (int i = 0; i < 21; i++) {
            Bird hawk = new Hawk(i + 1, hawkCharacteristic, false, 6, hawkFoods);
            conservatory.assignToAviary(hawk, i + 1);
            assertEquals(i + 1, conservatory.getNumOfAviaries());
        }
    }

    //test exceptions for Invalid Bird ID
    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBirdId1() {
        // hawk
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        Bird hawk = new Hawk(1, hawkCharacteristic, false, 6, hawkFoods);
        Boolean res1 = conservatory.assignToAviary(hawk, 1);

        conservatory.lookUpBird(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testInvalidBirdId2() {
        // hawk
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        Bird hawk = new Hawk(1, hawkCharacteristic, false, 6, hawkFoods);
        Boolean res1 = conservatory.assignToAviary(hawk, 1);

        conservatory.lookUpBird(-1);
    }

    @Test
    public void testLookUpBird1() {
        assertEquals(-1, conservatory.lookUpBird(1));
    }

    @Test
    public void testLookUpBird2() {
        // hawk
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        Bird hawk = new Hawk(1, hawkCharacteristic, false, 6, hawkFoods);
        Boolean res1 = conservatory.assignToAviary(hawk, 1);

        assertEquals(1, conservatory.lookUpBird(1));
    }

    @Test
    public void testLookUpBird3() {
        // hawk
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        Bird hawk = new Hawk(1, hawkCharacteristic, false, 6, hawkFoods);
        Boolean res1 = conservatory.assignToAviary(hawk, 1);

        assertEquals(-1, conservatory.lookUpBird(6));
    }


    @Test
    public void testPrintIndex2() {
        // hawk
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        Bird hawk = new Hawk(1, hawkCharacteristic, false, 6, hawkFoods);
        Boolean res1 = conservatory.assignToAviary(hawk, 1);

        ArrayList<Bird> birds = new ArrayList<Bird>();
        birds.add(hawk);

    }

    @Test
    public void testPrintIndex3() {
        // hawk
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        Bird hawk = new Hawk(1, hawkCharacteristic, false, 6, hawkFoods);
        Boolean res1 = conservatory.assignToAviary(hawk, 1);

        // moa
        Foods moaSeeds = new Foods(Enums.Food.Seeds, 6);
        Foods moaBuds = new Foods(Enums.Food.Buds, 2);
        Foods moaFish = new Foods(Enums.Food.Fish, 5);
        Foods[] moaFoods = new Foods[3];
        moaFoods[0] = moaSeeds;
        moaFoods[1] = moaBuds;
        moaFoods[2] = moaFish;
        String moaCharacteristic = "Live on the ground and have no (or undeveloped) wings.";
        Bird moa = new Moa(6, moaCharacteristic, false, moaFoods);
        Boolean res2 = conservatory.assignToAviary(moa, 6);

        ArrayList<Bird> birds = new ArrayList<Bird>();
        birds.add(hawk);
        birds.add(moa);

    }

    @Test
    public void testPrintIndex4() {
        // hawk
        Foods hawkEggs = new Foods(Enums.Food.Eggs, 3);
        Foods hawkFish = new Foods(Enums.Food.Fish, 8);
        Foods[] hawkFoods = new Foods[2];
        hawkFoods[0] = hawkEggs;
        hawkFoods[1] = hawkFish;
        String hawkCharacteristic = "Sharp, hooked beaks with visible nostrils.";
        Bird hawk = new Hawk(6, hawkCharacteristic, false, 6, hawkFoods);
        Boolean res1 = conservatory.assignToAviary(hawk, 1);

        // moa
        Foods moaSeeds = new Foods(Enums.Food.Seeds, 6);
        Foods moaBuds = new Foods(Enums.Food.Buds, 2);
        Foods moaFish = new Foods(Enums.Food.Fish, 5);
        Foods[] moaFoods = new Foods[3];
        moaFoods[0] = moaSeeds;
        moaFoods[1] = moaBuds;
        moaFoods[2] = moaFish;
        String moaCharacteristic = "Live on the ground and have no (or undeveloped) wings.";
        Bird moa = new Moa(1, moaCharacteristic, false, moaFoods);
        Boolean res2 = conservatory.assignToAviary(moa, 6);

        ArrayList<Bird> birds = new ArrayList<Bird>();
        birds.add(moa);
        birds.add(hawk);

    }
}