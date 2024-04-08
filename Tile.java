/*
 * Tile.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Class that houses all the behaviors and states of Tile objects, part
 * of the Strategy Pattern of TileBehavior
 */

import java.util.*;

public class Tile implements TileBehavior{
  protected TileBehavior tileBehavior;
  protected List<Hero> heroes;
  private Coordinate coordinate;

  public Tile(TileBehavior tileBehavior, Coordinate coordinate) {
    setTileBehavior(tileBehavior);
    setCoordinate(coordinate);
  }

  // Maybe we would want have input List<Hero> heroes
  public void interact(List<Hero> heroes) {
    setHeroes(heroes); // don't know if this is necessary
    tileBehavior.interact(heroes);
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

  public void setTileBehavior(TileBehavior tileBehavior) {
    if (tileBehavior == null) {
      throw new IllegalArgumentException("TileBehavior of a tile cannot "+
      "be null.");
    }
    this.tileBehavior = tileBehavior;
  }
  public TileBehavior getTileBehavior() {
    return this.tileBehavior;
  }

  public void setCoordinate(Coordinate coordinate) {
    if (coordinate == null) {
      throw new IllegalArgumentException("Coordinate of a tile cannot "+
      "be null.");
    }
    this.coordinate = coordinate;
  }
  public Coordinate getCoordinate() {
    return this.coordinate;
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
  @Override
  public String toString() {
    return this.getCoordinate().toString();
  }

}

