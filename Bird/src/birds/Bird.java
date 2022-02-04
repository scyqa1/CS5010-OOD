package birds;

import birds.conservatory.Foods;
import birds.enums.Enums;

// Bird class is the parent class of all kinds of bird
public class Bird {
    private int birdId;
    private String characteristic;
    private Boolean isExtinct;
    private int numOfWings;
    
    private Enums.Classification classification;
    private Enums.Type type;
    
    private Foods[] food;
    private int aviaryId;

    public Bird(int birdId, Enums.Classification classification, Enums.Type type, String characteristic, Boolean isExtinct, int numOfWings, Foods[] food) {
        if (birdId <= 0) {
            throw new IllegalArgumentException("Bird ID must be positive.");
        }
        if (numOfWings < 0) {
            throw new IllegalArgumentException("Number of wings can't be negative.");
        }

        this.birdId = birdId;
        this.classification = classification;
        this.type = type;
        this.characteristic = characteristic;
        this.isExtinct = isExtinct;
        this.numOfWings = numOfWings;
        this.food = food;
        this.aviaryId = 0; 
    }

    public Enums.Classification getClassification() {
        return this.classification;
    }

    public Enums.Type getType() {
        return this.type;
    }
    
    public int getBirdId() {
        return this.birdId;
    }
    
    public Foods[] getFood() {
        return this.food;
    }

    public String getCharacteristic() {
        return this.characteristic;
    }
    
    public int getNumOfWings() {
        return this.numOfWings;
    }
    
    public Boolean getIsExtinct() {
        return this.isExtinct;
    }
    
    public void setAviaryId(int aviaryId) {
        this.aviaryId = aviaryId;
    }
    
    public int getAviaryId() {
        return this.aviaryId;
    }

}
