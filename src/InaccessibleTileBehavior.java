/*
 * InaccessibleTileBehavior.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Part of the TileBehavior Strategy Pattern that
 * represents the behavior of a InaccessibleTile
 */

import java.util.List;

public class InaccessibleTileBehavior implements TileBehavior{
  public InaccessibleTileBehavior() {

  }

  @Override
  public void interact(List<Hero> heroes) {
    /*
     * Simply return false to represent the player is not able to
     * interact with this tile in any way, at least for now
     */
    throw new IllegalArgumentException("In the current implementation, "+
    "one can only call a TileBehavior's interact() method when the heroes "+
    "are on that tile. But heroes cannot be on an InaccessibleTile and thus "+
    "cannot call the interact() method of an InaccessibleTile. Do an " +
    "instanceof check on the TileBehavior of the nextTile to determine " +
    "it is an InaccessibleTile and thus prevent the heroes from moving onto it.");
  }
}
