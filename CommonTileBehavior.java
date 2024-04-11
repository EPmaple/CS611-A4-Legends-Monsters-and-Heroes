/*
 * CommonTileBehavior.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Part of the TileBehavior Strategy Pattern that
 * represents the behavior of a CommoneTile
 */

import java.util.*;

public class CommonTileBehavior implements TileBehavior{
  // private Battle battleInstance;

  public CommonTileBehavior() {
    // battleInstance = Battle.getInstance();
  }

  public void interact(List<Hero> heroes) {
    Random random = new Random();
    int roll = random.nextInt(6) + 1; // Number between 1 to 6, inclusive

    // 1,2,3 => no battle, 4,5,6 => battle
    if (roll > Constants.BATTLE_THRESHOLD) {
      
      Battle battle = new Battle(heroes);
      battle.battleStarts();
      // battleInstance.setHeroes(heroes);
      // battleInstance.battleStarts();
    }
  } 
}