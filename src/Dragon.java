/*
 * Dragon.java
 * Tony Cen Cen
 * 4/15/2024
 * 
 * Class for creating the specific concrete Dragon object
 */

public class Dragon extends Monster{
    public Dragon(String name, int level, int HP, State state, int damage,
                   int defense, int dodgeChance, MonsterType monsterType) {
        super(name, level, HP, state, damage, defense, dodgeChance, monsterType);
    }
}
