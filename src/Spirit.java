/*
 * Spirit.java
 * Tony Cen Cen
 * 4/15/2024
 * 
 * A concrete class of Hero specifying the Spirit monster type
 */

public class Spirit extends Monster{
    public Spirit(String name, int level, int HP, State state, int damage,
                  int defense, int dodgeChance, MonsterType monsterType) {
        super(name, level, HP, state, damage, defense, dodgeChance, monsterType);
    }
}
