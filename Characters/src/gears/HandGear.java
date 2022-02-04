package gears;

// define more gear types...
public class HandGear extends AbstractGear {

    // constructor
    public HandGear(String adj, String noun, int attack, int defense) {
        super(adj, noun, attack, defense, GearType.HAND);
    }

}