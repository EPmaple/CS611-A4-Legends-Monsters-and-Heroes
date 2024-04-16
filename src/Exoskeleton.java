/*
 * Exoskeleton.java
 * Tony Cen Cen
 * 4/15/2024
 * 
 * Class for creating the specific concrete Exoskeleton object
 */

public class Exoskeleton extends Monster{
    public Exoskeleton(String name, int level, int HP, State state, int damage,
                  int defense, int dodgeChance, MonsterType monsterType) {
        super(name, level, HP, state, damage, defense, dodgeChance, monsterType);
    }
}
