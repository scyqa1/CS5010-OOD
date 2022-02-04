package battle;

import gears.*;
import characters.Character;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Battle implements BattleInterface{

    private List<Gear> battleGear = new ArrayList<>();
    private List<Character> characters = new ArrayList<>();

    // Constructor
    public Battle(Character character1,
                  Character character2,
                  Gear gear1,
                  Gear gear2,
                  Gear gear3,
                  Gear gear4,
                  Gear gear5,
                  Gear gear6,
                  Gear gear7,
                  Gear gear8,
                  Gear gear9,
                  Gear gear10){
        if(character1 == null || character2 == null){
            throw new IllegalArgumentException("character cannot be null.");
        }
        characters.add(character1);
        characters.add(character2);

        battleGear.add(gear1);
        battleGear.add(gear2);
        battleGear.add(gear3);
        battleGear.add(gear4);
        battleGear.add(gear5);
        battleGear.add(gear6);
        battleGear.add(gear7);
        battleGear.add(gear8);
        battleGear.add(gear9);
        battleGear.add(gear10);

    }

    @Override
    public boolean oneTurn() {
        if (battleGear.size() != 0){
            chooseGear(characters.get(0), battleGear);
            chooseGear(characters.get(1), battleGear);
            return true;

        }

        return false;
    }

    // Choose which gear to be picked up
    public void chooseGear(Character character, List<Gear> gears){
        int highestAttack = 0;
        int choosedGear = 0;
        if (character.gethand().size() < character.HAND_GEAR_NUM){
            for (int i = 0; i < gears.size(); i++){
                if (gears.get(i).getType() == GearType.HAND){
                    if (gears.get(i).getAttack() > highestAttack){
                        choosedGear = i;
                        highestAttack = gears.get(i).getAttack();
                    }
                }
            }
        }
        if (character.gethead().size() < character.HEAD_GEAR_NUM){
            for (int i = 0; i < gears.size(); i++){
                if (gears.get(i).getType() == GearType.HEAD){
                    if (gears.get(i).getAttack() > highestAttack){
                        choosedGear = i;
                        highestAttack = gears.get(i).getAttack();
                    }
                }
            }
        }
        if (character.getfoot().size() < character.FOOT_WEAR_NUM){
            for (int i = 0; i < gears.size(); i++){
                if (gears.get(i).getType() == GearType.FOOT){
                    if (gears.get(i).getAttack() > highestAttack){
                        choosedGear = i;
                        highestAttack = gears.get(i).getAttack();
                    }
                }
            }
        }
        if (highestAttack != 0){
            character.pickup(gears.get(choosedGear));
            gears.remove(choosedGear);
            return;
        }

        for (int i = 0; i < gears.size(); i++){
            if (gears.get(i).getAttack() > highestAttack){
                choosedGear = i;
                highestAttack = gears.get(i).getAttack();
            }
        }
        character.pickup(gears.get(choosedGear));
        gears.remove(choosedGear);
        return;

    }

    // return status for two characters
    @Override
    public String status() {
        String wear1 = "";
        for (int i = 0; i < characters.get(0).gethand().size(); i++) {
            wear1 = wear1 + "Hand Gear: " + characters.get(0).gethand().get(i).getAdj() + " " + characters.get(0).gethand().get(i).getNoun() + "\n";
        }
        for (int i = 0; i < characters.get(0).getfoot().size(); i++) {
            wear1 = wear1 + "Foot Wear: " + characters.get(0).getfoot().get(i).getAdj() + " " + characters.get(0).getfoot().get(i).getNoun() + "\n";
        }
        for (int i = 0; i < characters.get(0).gethead().size(); i++) {
            wear1 = wear1 + "Head Gear: " + characters.get(0).gethead().get(i).getAdj() + " " + characters.get(0).gethead().get(i).getNoun() + "\n";
        }

        String char1 = "--Character " + characters.get(0).getName() + " wears \n" + wear1
                + "Total attack: " + characters.get(0).getAttack() + "  Total defence: " + characters.get(0).getDefense() + "\n";


        String wear2 = "";

        for (int i = 0; i < characters.get(1).gethand().size(); i++) {
            wear2 = wear2 + "Hand Gear: " + characters.get(1).gethand().get(i).getAdj() + " " + characters.get(1).gethand().get(i).getNoun() + "\n";
        }
        for (int i = 0; i < characters.get(1).getfoot().size(); i++) {
            wear2 = wear2 + "Foot Wear: " + characters.get(1).getfoot().get(i).getAdj() + " " + characters.get(1).getfoot().get(i).getNoun() + "\n";
        }
        for (int i = 0; i < characters.get(1).gethead().size(); i++) {
            wear2 = wear2 + "Head Gear: " + characters.get(1).gethead().get(i).getAdj() + " " + characters.get(1).gethead().get(i).getNoun() + "\n";
        }


        String char2 = "--Character " + characters.get(1).getName() + " wears \n" + wear2
                + "Total attack: " + characters.get(1).getAttack() + "  Total defence: " + characters.get(1).getDefense();

        return char1 + char2 + "\n ___________________________";

    }

    // return winner details
    @Override
    public String winner() {
        String winner;
        if (characters.get(1).getAttack() + characters.get(1).getDefense()
                > characters.get(0).getAttack() + characters.get(0).getDefense()){
            winner = "Winner is " + characters.get(1).getName();
        } else if(characters.get(0).getAttack() + characters.get(0).getDefense()
                > characters.get(1).getAttack() + characters.get(1).getDefense()) {
            winner = "Winner is " + characters.get(0).getName();
        } else
            winner = "There is a tie";

        return winner;
    }

}
