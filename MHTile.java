/*
 * Tile.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Class that houses all the behaviors and states of Tile objects, part
 * of the Strategy Pattern of TileBehavior
 */

import java.util.*;

public class MHTile extends Tile implements TileBehavior{
  protected List<Hero> heroes;

  public MHTile(TileBehavior tileBehavior, Coordinate coordinate) {
    setTileBehavior(tileBehavior);
    setCoordinate(coordinate);
  }

  // Maybe we would want have input List<Hero> heroes
  public void interact(List<Hero> heroes) {
    setHeroes(heroes); // don't know if this is necessary
    super.tileBehavior.interact(heroes);
    /*
     * Only move the heroes onto this tile if interact returns true
     */
  }

  public void setHeroes(List<Hero> heroes) {
    // Not doing a null check or size check here because those
    // checks would be done beforehand by World.java

    // Furthermore, "setHeroes(null)" should be allowed
    this.heroes = heroes;
  }

  /*
   * For printing a worldMap
   */
  public String toString(boolean printingMap) {
    if (heroes != null) {
      return Colors.ANSI_BLUE + "H" + Colors.ANSI_RESET; // blue H?
    }

    if (tileBehavior instanceof CommonTileBehavior) {
      return " "; // empty string
    } else if (tileBehavior instanceof MarketTileBehavior) {
      return Colors.ANSI_YELLOW + "M" + Colors.ANSI_RESET; // yellow M
    } else if (tileBehavior instanceof InaccessibleTileBehavior) {
      return Colors.ANSI_GREEN + "X" + Colors.ANSI_RESET; // green X
    } else {
      throw new IllegalArgumentException("Reached else statement in the " +
      "toString() method of Tile class.");
    }
  }

}

