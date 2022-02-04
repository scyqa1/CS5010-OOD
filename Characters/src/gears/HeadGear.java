package gears;

// define more gear types...
public class HeadGear extends AbstractGear {

    // constructor
    public HeadGear(String adj, String noun, int attack, int defense) {
        super(adj, noun, attack, defense, GearType.HEAD);
    }

}