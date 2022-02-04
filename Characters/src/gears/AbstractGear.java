package gears;

import characters.Character;

public class AbstractGear implements Gear {

  private final GearType type;
  private String adj;
  private final String noun;
  private int attack;
  private int defense;

  // constructor
  public AbstractGear(String adj, String noun, int attack, int defense, GearType type) {
    if(noun == ""){
      throw new IllegalArgumentException("The noun of gears cannot be empty.");
    }
    if(adj == ""){
      throw new IllegalArgumentException("The adj. of gears cannot be empty.");
    }
    if(attack < 0){
      throw new IllegalArgumentException("attack must be positive.");
    }
    if(defense < 0){
      throw new IllegalArgumentException("defence must be positive.");
    }


    this.adj = adj;
    this.noun = noun;
    this.attack = attack;
    this.defense = defense;
    this.type = type;
  }

  @Override
  public void combine(Gear other) {
    this.adj = this.adj + ", " + other.getAdj();
    this.attack = this.attack + other.getAttack();
    this.defense = this.defense + other.getDefense();
  }

  @Override
  public GearType getType() {

    return this.type;
  }

  @Override
  public String getAdj() {
    return this.adj;
  }

  @Override
  public String getNoun() {

    return this.noun;
  }

  @Override
  public int getAttack() {
    return this.attack;
  }

  @Override
  public int getDefense() {
    return this.defense;
  }

  // more methods if necessary...


}
