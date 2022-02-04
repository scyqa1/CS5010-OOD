package test;

import dungeon.*;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MedievalLevelBuilderTest {

	  Level level;

	  @Before
	  public void setUp() {
	    level =
	        new MedievalLevelBuilder(1, 1, 1, 1)
	            .addRoom("room")
	            .addGoblins(0, 1)
	            .addWeapon(0, "weapon")
	            .build();
	  }

	  @Test
	  public void testConstructor() {
	    assertEquals(1, level.getLevelNumber());
	    assertEquals(
	        "Level 1 contains 1 rooms:\n"
	            + "\n"
	            + "Room 0 -- room\n"
	            + "Monsters:\n"
	            + "\tgoblin (hitpoints = 7) is a mischievous and very unpleasant, "
	            + "vengeful, and greedy creature whose primary purpose is to cause "
	            + "trouble to humankind\n"
	            + "Treasures:\n"
	            + "\tweapon (value = 10)\n",
	        level.toString());
	    
	    new MedievalLevelBuilder(1, 1, 4, 4)
        .addRoom("room")
        .addGoblins(0, 1)
        .addHuman(0, "hunman", "i'm human", 1)
        .addOgre(0)
        .addOrc(0)
        .addGold(0, 1)
        .addPotion(0)
        .addSpecial(0, "i'm special", 1)
        .addWeapon(0, "weapon")
        .build();
	  }

	  @Test(expected = IllegalArgumentException.class)
	  public void testInvalidConsturctor() {
	    new MedievalLevelBuilder(-1, 1, 1, 1)
	        .addRoom("room")
	        .addGoblins(0, 1)
	        .addWeapon(0, "weapon")
	        .build();
	  }

	  @Test(expected = IllegalStateException.class)
	  public void testInvalidAddRoom() {
	    new MedievalLevelBuilder(1, 1, 1, 1)
	        .addRoom("room")
	        .addRoom("extra room")
	        .addGoblins(0, 1)
	        .addWeapon(0, "weapon")
	        .build();
	  }

	  @Test(expected = IllegalStateException.class)
	  public void testInvalidAddMonster() {
	    new MedievalLevelBuilder(1, 1, 4, 1)
	        .addRoom("room")
	        .addGoblins(0, 6)
	        .addGold(0, 1)
	        .build();
	  }

	  @Test(expected = IllegalArgumentException.class)
	  public void testIlnvalidAddMonster2() {
	    new MedievalLevelBuilder(1, 1, 4, 1)
	        .addRoom("room")
	        .addGoblins(1, 4)
	        .addWeapon(0, "weapon")
	        .build();
	  }


	  @Test(expected = IllegalStateException.class)
	  public void testInvalidAddTreasure() {
	    new MedievalLevelBuilder(1, 1, 1, 1)
	        .addRoom("room")
	        .addOgre(0)
	        .addGold(0, 1)
	        .addWeapon(0, "weapon")
	        .build();
	  }

	  @Test(expected = IllegalArgumentException.class)
	  public void testInvalidAddTreasure2() {
	    new MedievalLevelBuilder(1, 1, 1, 1)
	    	.addRoom("room")
	    	.addOgre(0)
	    	.addPotion(1)
	    	.build();
	  }


	  @Test(expected = IllegalStateException.class)
	  public void testInvalidBuild() {
	    new MedievalLevelBuilder(1, 2, 1, 1)
	        .addRoom("room")
	        .addOgre(0)
	        .addSpecial(0, "big gun", 100)
	        .build();
	  }
	  
	  @Test(expected = IllegalStateException.class)
	  public void testInvalidBuild2() {
	    new MedievalLevelBuilder(1, 1, 2, 1)
	        .addRoom("room")
	        .addOgre(0)
	        .addSpecial(0, "big gun", 100)
	        .build();
	  }
	  
	  @Test(expected = IllegalStateException.class)
	  public void testInvalidBuild3() {
	    new MedievalLevelBuilder(1, 1, 1, 2)
	        .addRoom("room")
	        .addOgre(0)
	        .addSpecial(0, "big gun", 100)
	        .build();
	  }
	}
