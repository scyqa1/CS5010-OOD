package gears;

// define more gear types...
public class FootWear extends AbstractGear {

  // constructor
  public FootWear(String adj, String noun, int attack, int defense) {
    super(adj, noun, attack, defense, GearType.FOOT);
  }

}


