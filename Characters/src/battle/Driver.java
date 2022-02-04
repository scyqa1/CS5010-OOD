package battle;

import gears.*;
import characters.Character;

public class Driver {
  public static void main(String[] args) {
    //  Create characters
    Character char1 = new Character ("Alice", 3, 2);
    Character char2 = new Character ("Jason", 4, 1);

    // Create gears
    Gear gear1 = new AbstractGear("beautiful", "Sandals", 0, 3, GearType.FOOT);
    Gear gear2 = new AbstractGear("happy", "HoverBoard", 2, 2, GearType.FOOT);
    Gear gear3 = new AbstractGear("sleeveless", "Crown", 8, 9, GearType.HEAD);
    Gear gear4 = new AbstractGear("edible", "Longsord", 4, 1, GearType.HAND);
    Gear gear5 = new AbstractGear("meaningless", "Katana", 6, 0, GearType.HAND);
    Gear gear6 = new AbstractGear("adorable", "Sneaker", 1, 4, GearType.FOOT);
    Gear gear7 = new AbstractGear("gorgeous", "Birdbrain", 12, 3, GearType.HEAD);
    Gear gear8 = new AbstractGear("breakable", "Koi", 7, 11, GearType.HEAD);
    Gear gear9 = new AbstractGear("mathematical", "Boots", 0, 8, GearType.FOOT);
    Gear gear10 = new AbstractGear("wooden", "Dagger", 5, 2, GearType.HAND);


    // test
    BattleInterface battle = new Battle(char1, char2, gear1, gear2, gear3, gear4, gear5, gear6, gear7, gear8, gear9, gear10);
    while(battle.oneTurn()) {
      System.out.println(battle.status());
    }
    System.out.println(battle.winner());
  }
}
