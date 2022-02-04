package test;

import battle.Battle;
import battle.BattleInterface;
import characters.Character;
import gears.AbstractGear;
import gears.Gear;
import gears.GearType;
import gears.HandGear;
import org.junit.Before;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class RoleGameTest {
    //  Create characters
    Character char1;
    Character char2;

    // Create gears
    Gear gear1;
    Gear gear2;
    Gear gear3;
    Gear gear4;
    Gear gear5;
    Gear gear6;
    Gear gear7;
    Gear gear8;
    Gear gear9;
    Gear gear10;

    BattleInterface battle;


    // Constructor
    @Before
    public void setUp() {

        char1 = new Character ("Alice", 3, 2);
        char2 = new Character ("Jason", 4, 1);

        gear1 = new AbstractGear("beautiful", "Sandals", 0, 3, GearType.FOOT);
        gear2 = new AbstractGear("happy", "HoverBoard", 2, 2, GearType.FOOT);
        gear3 = new AbstractGear("sleeveless", "Crown", 8, 9, GearType.HEAD);
        gear4 = new AbstractGear("edible", "Longsord", 4, 1, GearType.HAND);
        gear5 = new AbstractGear("meaningless", "Katana", 6, 0, GearType.HAND);
        gear6 = new AbstractGear("adorable", "Sneaker", 1, 4, GearType.FOOT);
        gear7 = new AbstractGear("gorgeous", "Birdbrain", 12, 3, GearType.HEAD);
        gear8 = new AbstractGear("breakable", "Koi", 7, 11, GearType.HEAD);
        gear9 = new AbstractGear("mathematical", "Boots", 0, 8, GearType.FOOT);
        gear10 = new AbstractGear("wooden", "Dagger", 5, 2, GearType.HAND);

        battle = new Battle(char1, char2, gear1, gear2, gear3, gear4, gear5, gear6, gear7, gear8, gear9, gear10);
    }

    //test function
    @Test
    public void battleTest() {
        battle.oneTurn();
        assertEquals("--Charcter Alice wears \n" +
                "Head Gear: gorgeous Birdbrain\n" +
                "Total attcak: 15  Total defence: 5\n" +
                "--Charcter Jason wears \n" +
                "Head Gear: sleeveless Crown\n" +
                "Total attcak: 12  Total defence: 10\n" +
                " ___________________________", battle.status());

        battle.oneTurn();
        assertEquals("--Charcter Alice wears \n" +
                "Hand Gear: meaningless Katana\n" +
                "Head Gear: gorgeous Birdbrain\n" +
                "Total attcak: 21  Total defence: 5\n" +
                "--Charcter Jason wears \n" +
                "Hand Gear: wooden Dagger\n" +
                "Head Gear: sleeveless Crown\n" +
                "Total attcak: 17  Total defence: 12\n" +
                " ___________________________", battle.status());


        assertEquals("Winner is Jason", battle.winner());
    }

    //test exception
    @Test (expected = IllegalArgumentException.class)
    public void nullGear() {
        Gear gear11 = null;
        char1.pickup(gear11);
    }

    //test exception
    @Test (expected = IllegalArgumentException.class)
    public void nullChar1() {
        Character char3 = null;
        battle = new Battle(char3, char1, gear1, gear2, gear3, gear4, gear5, gear6, gear7, gear8, gear9, gear10);
    }

    //test exception
    @Test (expected = IllegalArgumentException.class)
    public void nullChar2() {
        Character char3 = null;
        battle = new Battle(char1, char3, gear1, gear2, gear3, gear4, gear5, gear6, gear7, gear8, gear9, gear10);
    }

    //test exception
    @Test (expected = IllegalArgumentException.class)
    public void voidCharName() {
        char1 = new Character ("", 3, 2);
    }

    //test exception
    @Test (expected = IllegalArgumentException.class)
    public void voidCharAttack() {
        char1 = new Character ("Alice", -1, 2);
    }

    //test exception
    @Test (expected = IllegalArgumentException.class)
    public void voidCharDefence() {
        char1 = new Character ("Alice", 3, -2);
    }

    //test exception
    @Test (expected = IllegalArgumentException.class)
    public void voidGearNoun() {
        gear1 = new AbstractGear("beautiful", "", 1, 3, GearType.FOOT);
    }

    //test exception
    @Test (expected = IllegalArgumentException.class)
    public void voidGearAdj() {
        gear1 = new AbstractGear("", "Sandals", 1, 3, GearType.FOOT);
    }

    //test exception
    @Test (expected = IllegalArgumentException.class)
    public void voidGearAttack() {
        gear1 = new AbstractGear("beautiful", "Sandals", -1, 3, GearType.FOOT);
    }

    //test exception
    @Test (expected = IllegalArgumentException.class)
    public void voidGearDefence() {
        gear1 = new AbstractGear("beautiful", "Sandals", 0, -3, GearType.FOOT);
    }


}