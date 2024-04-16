/*
 * Warrior.java
 * Tony Cen Cen
 * 4/15/2024
 * 
 * A concrete class of Hero specifying the Warrior hero type
 */

import java.util.*;
public class Warrior extends Hero{
    public Warrior(String name, int level, int HP, State state, int MP, int strength,
                   int agility, int dexterity, int defense, int gold, int experience,
                   Weapon weapon, Armor armor, HeroType heroType, List<Item> inventory) {
        super(name, level, HP, state, MP, strength, agility, dexterity, defense,
                gold, experience, weapon, armor, heroType, inventory);
    }

    public void updateSkillsOnLvlUp() {
        this.setHP(this.level * Constants.HP_SCALE);
        this.setMP((int) (this.MP * 1.1));

        double aglScaleFactor = Constants.SPECIAL_SKILL_SCALE_FACTOR; // 1.10
        double strScaleFactor = Constants.SPECIAL_SKILL_SCALE_FACTOR;
        double dexScaleFactor = Constants.DEFAULT_SKILL_SCALE_FACTOR; // 1.05

        this.setStrength((int)(this.strength * strScaleFactor));
        this.setAgility((int)(this.agility * aglScaleFactor));
        this.setDexterity((int)(this.dexterity * dexScaleFactor));
    }
}
