package gears;

public interface Gear {
  // combine `other` with `this` to get a new Gear
  // throw exception if types are different
  void combine(Gear other);

  // getters
  GearType getType();
  String getAdj();
  String getNoun();
  int getAttack();
  int getDefense();

  // more methods if necessary...
}
