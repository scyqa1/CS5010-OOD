package birds.conservatory;

import java.util.ArrayList;

import birds.Bird;
import birds.enums.Enums;

/** 
 * This class represent a conservatory
 * attribute aviaries (max 20) / foods / foodQuantities
 * method updateFoodAndQuantities() / assignToAviary() / lookUpBird()
 * printSign() / printMap() / printIndex()
 */
public class Conservatory {
    private int numOfAviaries;
    private ArrayList<Aviary> aviaries;
    
    private ArrayList<Enums.Food> foods;
    private ArrayList<Integer> foodQuantities;

    public Conservatory() {
        this.numOfAviaries = 0;
        this.aviaries = new ArrayList<Aviary>();
        this.foods = new ArrayList<Enums.Food>();
        this.foodQuantities = new ArrayList<Integer>();
    }


    public ArrayList<Enums.Food> getFoodsNeeded() {
        return this.foods;
    }

    public ArrayList<Integer> getFoodQuantities() {
        return this.foodQuantities;
    }
    
    public int getNumOfAviaries() {
        return this.numOfAviaries;
    }

    public ArrayList<Aviary> getAviaries() {
        return this.aviaries;
    }

    /**
     *  update kinds of foods and Quantities of foods when adding a new bird
     */
    private void updateFoodAndQuantities(Bird bird) {
        for (Foods food : bird.getFood()) {
            if (this.foods.contains(food.getFood())) {
                int idx = this.foods.indexOf(food.getFood());
                int quantity = this.foodQuantities.get(idx);
                this.foodQuantities.set(idx, quantity + food.getQuantity());
            } else {
                this.foods.add(food.getFood());
                this.foodQuantities.add(food.getQuantity());
            }
        }
    }

    /**
     *  assign a bird to a given aviary with the aviary ID
     * @param bird
     * @param aviaryId
     * @return 1 if success; else 0
     */
    public Boolean assignToAviary(Bird bird, int aviaryId) {
        if (aviaryId <= 0) {
            throw new IllegalArgumentException("Aviary ID must be positive.");
        }
        if (bird.getIsExtinct() == true) {
            throw new IllegalStateException("Cannot add an extinct bird.");
        }

        // find aviary with ID
        Boolean isSuccess = false;
        for (Aviary aviary : this.aviaries) {
            // if aviary ID is found, assign the bird
            if (aviary.getAviaryId() == aviaryId) {
                isSuccess = aviary.addNewBird(bird);
                if (isSuccess) {
                    bird.setAviaryId(aviaryId);
                    updateFoodAndQuantities(bird);
                    System.out.println("Successfully assigned " + bird.getType() + " " + bird.getBirdId() + " to Aviary " + aviaryId + ".");
                    System.out.println();
                } else {
                    System.out.println("Failed to assign " + bird.getType() + " " + bird.getBirdId() + " to Aviary " + aviaryId + ".");
                    System.out.println();
                }
                return isSuccess;
            }
        }
        
        
        int maxAviaries = 20;
        // if aviary ID not found, create a new aviary and assign the bird
        if (this.numOfAviaries < maxAviaries) {
            Aviary newAviary = new Aviary(aviaryId);
            newAviary.addNewBird(bird);
            bird.setAviaryId(aviaryId);
            aviaries.add(newAviary);
            this.numOfAviaries++;
            updateFoodAndQuantities(bird);
            System.out.println("Aviary " + aviaryId + " created.");
            System.out.println("Successfully assigned " + bird.getType() + " " + bird.getBirdId() + " to Aviary " + aviaryId + ".");
            System.out.println();
            return true;
        } else {
            throw new IllegalStateException("Conservatory is full.");
        }
    }

    /** look up bird with a bird ID
     * return -1 if not found
     */
    public int lookUpBird(int birdId) {
        if (birdId <= 0) {
            throw new IllegalArgumentException("Bird ID must be positive.");
        }
        for (Aviary aviary : this.aviaries) {
            for (Bird bird : aviary.getBirds()) {
                if (bird.getBirdId() == birdId) {
                    System.out.println("The Bird " + birdId + " is in Aviary " + aviary.getAviaryId() + ".");
                    return aviary.getAviaryId();
                }
            }
        }
        return -1;
    }
    
    /**
     * print the description and bird information of a given aviary
     * @param aviaryId
     */
    public void printSign(int aviaryId) {
        for (Aviary aviary : this.aviaries) {
            if (aviary.getAviaryId() == aviaryId) {
                System.out.println("==================================");
                // print aviary ID
                System.out.println("Aviary ID: " + aviary.getAviaryId());
                // print number of birds in it
                System.out.println("There are " + aviary.getNumOfBirds() + " bird(s) in this aviary.");
                // print details for each bird
                int idx = 0;
                for (Bird bird : aviary.getBirds()) {
                    idx++;
                    System.out.println("------------------------------");
                    System.out.println("Bird " + idx);
                    System.out.println("Bird ID: " + bird.getBirdId());
                    System.out.println("Type: " + bird.getType());
                    System.out.println("Classification: " + bird.getClassification());
                    System.out.println("Characteristic: " + bird.getCharacteristic());
                    System.out.println("Is extinct or not: " + bird.getIsExtinct());
                    System.out.println("Number of wings: " + bird.getNumOfWings());
                }
            }
        }
    }
    
    /**
     *  print all aviaries by location
     */
	public void printMap() {
	    int aviaryID = 0;
	    for (Aviary aviary : this.aviaries) {
	        aviaryID++;
	        System.out.println("==================================");
	        System.out.println("Aviary " + aviaryID);
	        System.out.println("Aviary ID: " + aviary.getAviaryId());
	        System.out.println("Aviary location (same as ID): " + aviary.getAviaryId());
	        System.out.println("It contains following bird(s):");
	        int birdIdx = 0;
	        for (Bird bird : aviary.getBirds()) {
	            birdIdx++;
	            System.out.println("-----------------------------");
	            System.out.println("Bird " + birdIdx);
	            System.out.println("Bird ID: " + bird.getBirdId());
	            System.out.println("Type: " + bird.getType());
	            System.out.println("Classification: " + bird.getClassification());
	            System.out.println("-----------------------------");
	        }
	        System.out.println("==================================");
	        System.out.println();
	    }
	}
    

                    		
                    		
}
