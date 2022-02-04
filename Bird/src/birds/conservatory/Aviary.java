package birds.conservatory;
import java.util.ArrayList;

import birds.Bird;
import birds.enums.Enums;
import birds.enums.Enums.Classification;

/**
 * This class represents an aviary
 * attribute: birds (max 5)
 * method: addNewBird()
 */

public class Aviary {
    private int aviaryID;
    private String aviaryType; // Empty, Mixed, Flightless Birds, Birds of Prey, Waterfowl
    private int numOfBirds;
    private ArrayList<Bird> birds;

    public Aviary(int aviaryID) {
        if (aviaryID <= 0) {
            throw new IllegalArgumentException("Aviary ID must be positive.");
        }

        this.aviaryID = aviaryID;
        this.aviaryType = "Empty";
        this.numOfBirds = 0;
        this.birds = new ArrayList<Bird>();
    }
    
    public int getNumOfBirds() {
        return this.numOfBirds;
    }
    
    public int getAviaryId() {
        return this.aviaryID;
    }

    public String getAviaryType() {
        return this.aviaryType;
    }

    public ArrayList<Bird> getBirds() {
        return this.birds;
    }
    
    /**
     *  check if the bird type matches
     *  used when add new birds
     * @param bird
     * @return
     */
    private Boolean isTypeMatch(Bird bird) {
        if (this.aviaryType.equals("Flightless Birds")) {
            if (bird.getClassification().equals(Enums.Classification.Flightless)) {
                return true;
            }
        } else if (this.aviaryType.equals("Birds of Prey")) {
            if (bird.getClassification().equals(Enums.Classification.BirdsOfPrey)) {
                return true;
            }
        } else if (this.aviaryType.equals("Waterfowl")) {
            if (bird.getClassification().equals(Enums.Classification.Waterfowl)) {
                return true;
            }
        } else if (this.aviaryType.equals("Mixed")) {
            if (!bird.getClassification().equals(Enums.Classification.Flightless) &&
                    !bird.getClassification().equals(Enums.Classification.BirdsOfPrey) &&
                    !bird.getClassification().equals(Enums.Classification.Waterfowl)) {
                return true;
            }
        } else {
            return false;
        }
        return false;
    }

    /**
     * The goal of this method is to add a new bird
     * @param bird
     * @return 1 if success
     */
    public Boolean addNewBird(Bird bird) {
        if (this.numOfBirds > 0 && this.numOfBirds < 5) {
            if (isTypeMatch(bird)) {
                this.birds.add(bird);
                this.numOfBirds++;
                return true;
            } else {
                // if type does not match, fail to add
                throw new IllegalStateException("Bird type and aviary type do not match.");
            }
        } else if (this.numOfBirds == 0) {
            this.birds.add(bird);
            this.numOfBirds++;
            // set aviary type when first bird is added
            setAviaryType(bird);
            return true;
        } else {
            // if aviary is full, fail to add
            throw new IllegalStateException("Aviary " + this.aviaryID + " is full.");
        }
    }

    /**
     *  this method set aviary type 
     *  used when first bird is assigned
     * @param bird
     */
    private void setAviaryType(Bird bird) {
        if (bird.getClassification().equals(Enums.Classification.Flightless)) {
            this.aviaryType = "Flightless Birds";
        } else if (bird.getClassification().equals(Enums.Classification.BirdsOfPrey)) {
            this.aviaryType = "Birds of Prey";
        } else if (bird.getClassification().equals(Enums.Classification.Waterfowl)) {
            this.aviaryType = "Waterfowl";
        } else {
            this.aviaryType = "Mixed";
        }
    }

}
