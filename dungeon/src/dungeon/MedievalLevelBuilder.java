package dungeon;

public class MedievalLevelBuilder {
	  public final int levelNumber;
	  private final Level level;

	  private int roomCount;
	  private int monsterCount;
	  private int treasureCount;
	  private int totalRooms;

	  /**
	   * Constructor
	   */
	  public MedievalLevelBuilder(
	      int levelNumber, int roomCount, int monsterCount, int treasureCount)
	      throws IllegalArgumentException {
	    if (levelNumber < 0 | roomCount < 0 | monsterCount < 0 | treasureCount < 0) {
	      throw new IllegalArgumentException(" Negative values not allowed");
	    }
	    this.levelNumber = levelNumber;
	    this.roomCount = roomCount;
	    this.totalRooms = roomCount;
	    this.monsterCount = monsterCount;
	    this.treasureCount = treasureCount;
	    level = new Level(levelNumber);
	  }

	  /**
	   * Add room
	   */
	  public MedievalLevelBuilder addRoom(String description) {
	    if (roomCount == 0) {
	      throw new IllegalStateException("Room size cannot exceed number of rooms");
	    }
	    level.addRoom(description);
	    this.roomCount--;
	    return this;
	  }

	  /**
	   * Add goblin monster
	   */
	  public MedievalLevelBuilder addGoblins(int roomIndex, int value) {
	    exception_MonsterAndTreasure(
	        roomIndex,
	        monsterCount,
	        value);
	    
	    Monster goblin =
	        new Monster(
	            "goblin",
	            "mischievous and very unpleasant, vengeful, and greedy "
	                + "creature whose primary purpose is to cause trouble to humankind",
	            7);
	    for (int i = 0; i < value; i++) {
	      level.addMonster(roomIndex, goblin);
	      monsterCount--;
	    }
	    return this;
	  }

	  /**
	   * Add orc monster
	   */
	  public MedievalLevelBuilder addOrc(int roomIndex) {
	    exception_MonsterAndTreasure(
	        roomIndex,
	        monsterCount,
	        1);
	    
	    Monster orc = new Monster("orc", "brutish, aggressive, malevolent being serving evil", 20);
	    level.addMonster(roomIndex, orc);
	    monsterCount--;
	    return this;
	  }

	  /**
	   * Add ogre monster
	   */
	  public MedievalLevelBuilder addOgre(int roomIndex) {
	    exception_MonsterAndTreasure(
	        roomIndex,
	        monsterCount,
	        1);
	    
	    Monster ogre =
	        new Monster("ogre", "large, hideous man-like being that likes to eat humans for lunch", 50);

	    level.addMonster(roomIndex, ogre);
	    monsterCount--;
	    return this;
	  }

	  /**
	   * Add human as a type of monster
	   */
	  public MedievalLevelBuilder addHuman(int roomIndex, String name, String description, int value) {
	    exception_MonsterAndTreasure(
	        roomIndex,
	        monsterCount,
	        1);
	    
	    Monster human = new Monster(name, description, value);
	    level.addMonster(roomIndex, human);
	    monsterCount--;
	    return this;
	  }

	  /**
	   * Add potion treasure
	   */
	  public MedievalLevelBuilder addPotion(int roomIndex) {
	    exception_MonsterAndTreasure(
	        roomIndex,
	        treasureCount,
	        1);
	    
	    Treasure potion = new Treasure("a healing potion", 1);
	    level.addTreasure(roomIndex, potion);
	    treasureCount--;
	    return this;
	  }

	  /**
	   * Add gold treasure
	   */
	  public MedievalLevelBuilder addGold(int roomIndex, int value) {
	    exception_MonsterAndTreasure(
	        roomIndex,
	        treasureCount,
	        1);
	    
	    Treasure gold = new Treasure("pieces of gold", value);
	    level.addTreasure(roomIndex, gold);
	    treasureCount--;
	    return this;
	  }

	  /**
	   * Add weapon treasure
	   */
	  public MedievalLevelBuilder addWeapon(int roomIndex, String weaponName) {
	    exception_MonsterAndTreasure(
	        roomIndex,
	        treasureCount,
	        1);
	    
	    Treasure weapon = new Treasure(weaponName, 10);
	    level.addTreasure(roomIndex, weapon);
	    treasureCount--;
	    return this;
	  }

	  /**
	   * Add special treasure
	   */
	  public MedievalLevelBuilder addSpecial(int roomIndex, String description, int value) {
	    exception_MonsterAndTreasure(
	        roomIndex,
	        treasureCount,
	        1);
	    
	    Treasure special = new Treasure(description, value);
	    level.addTreasure(roomIndex, special);
	    treasureCount--;
	    return this;
	  }

	  private void exception_MonsterAndTreasure(int roomIndex, int number, int value) {
	    if (this.roomCount != 0) {
	      throw new IllegalStateException("Not enough rooms were created.");
	    }
		if (value > number) {
	      throw new IllegalStateException("maximum number of monster/treasure reached");
	    }
	    if (roomIndex < 0 || roomIndex >= totalRooms) {
	      throw new IllegalArgumentException("Room room index error");
	    }
	  }

	  /**
	   * Builds the object with the specific client input.
	   *
	   * @return a level object only after it has been completely constructed
	   * @throws IllegalStateException if the level is called before completion
	   */
	  public Level build() {
	    if (roomCount > 0 || monsterCount > 0 || treasureCount > 0) {
	      throw new IllegalStateException(
	          "Finish Creating room monsters and treasure before calling build");
	    }
	    return level;
	  }
	  
	  /*
	  public static void main(String[] args) {
		  Level level = new MedievalLevelBuilder(1, 1, 1, 1)
			            .addRoom("first room")
			            .addGoblins(0, 1)
			            .addGold(0, 1)
			            .build();
		  System.out.println(level.toString());
		  
	  }
	  */
	}