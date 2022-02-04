package characters;

import gears.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Character {
  public static final int FOOT_WEAR_NUM = 2;
  public static final int HAND_GEAR_NUM = 2;
  public static final int HEAD_GEAR_NUM = 1;

  private final String name;
  private final int attackBase;
  private final int defenseBase;
  public String getName(){
    return this.name;
  }

  private List<Gear> handGear = new ArrayList<>();
  private List<Gear> footWear = new ArrayList<>();
  private List<Gear> headGear = new ArrayList<>();

  public List<Gear> gethead(){
    return this.headGear;
  }
  public List<Gear> gethand(){
    return this.handGear;
  }
  public List<Gear> getfoot(){
    return this.footWear;
  }

  private int attack;
  private int defense;
  public int getAttack(){
    return this.attack;
  }
  public int getDefense(){
    return this.defense;
  }

  // constructor
  public Character(String name, int attack, int defense) {
    if(name == ""){
      throw new IllegalArgumentException("The name of characters cannot be empty.");
    }
    if(attack < 0){
      throw new IllegalArgumentException("attack must be positive.");
    }
    if(defense < 0){
      throw new IllegalArgumentException("defence must be positive.");
    }

    this.name = name;
    this.attackBase = attack;
    this.defenseBase = defense;

    this.update_OverallProperty();
  }

  // update attribute 'attack' and 'defence'
  public void update_OverallProperty(){
    this.attack = attackBase;
    for (int i = 0; i < handGear.size(); i++) {
      this.attack = this.attack + handGear.get(i).getAttack();
    }
    for (int i = 0; i < footWear.size(); i++) {
      this.attack = this.attack + footWear.get(i).getAttack();
    }
    for (int i = 0; i < headGear.size(); i++) {
      this.attack = this.attack + headGear.get(i).getAttack();
    }

    this.defense = defenseBase;
    for (int i = 0; i < handGear.size(); i++) {
      this.defense = this.defense + handGear.get(i).getDefense();
    }
    for (int i = 0; i < footWear.size(); i++) {
      this.defense = this.defense + footWear.get(i).getDefense();
    }
    for (int i = 0; i < headGear.size(); i++) {
      this.defense = this.defense + headGear.get(i).getDefense();
    }

  }


  public void pickup(Gear g) {
    if(g == null){
      throw new IllegalArgumentException("Gear cannot be null.");
    }
    // simply put `g` on if there is avail slot
    // otherwise, combine `g` with an existing one
    // with the same type, by calling g.Combine(...);
    if (g.getType() == GearType.HEAD){
      if (headGear.size() < HEAD_GEAR_NUM){
        headGear.add(g);
      } else{
        headGear.get(0).combine(g);
      }

    }else if (g.getType() == GearType.HAND){
      if (handGear.size() < HAND_GEAR_NUM){
        handGear.add(g);
      } else{
        handGear.get(0).combine(g);
      }

    }else if (g.getType() == GearType.FOOT){
      if (footWear.size() < FOOT_WEAR_NUM){
        footWear.add(g);
      } else{
        footWear.get(0).combine(g);
      }
    }
    this.update_OverallProperty();

  }

}
