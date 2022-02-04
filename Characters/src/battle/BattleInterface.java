package battle;

import gears.*;
import characters.Character;

public interface BattleInterface {
  // invoke one turn of the battle.
  // Each turn a character pick an item based on the rules.
  // returns false if the battle ends
  boolean oneTurn();
  // Print out each character with what they are wearing
  // and their attack and defense strength
  String status();
  // return the winner's name or "tie"
  String winner();
}
