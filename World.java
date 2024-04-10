/*
 * World.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Class housing all the behaviors and states of the World interface
 * of our of game; it is where the majority of the game is built around
 */

import java.util.List;

public class World {
  private WorldIO io;
  private boolean onMarketTile = false;
  private MHTile[][] worldMap;
  private List<Hero> heroes;
  private MHTile currentTile;
  /*
   * Pass in: heroes, worldMap, WorldIO
   */
  public World(MHTile[][] worldMap, List<Hero> heroes) {
    this.io = WorldIO.getInstance();
    setWorldMap(worldMap);
    setHeroes(heroes);
    setStartingTile();
  }

  private void setStartingTile() {
    for (int i = 0; i < worldMap.length ; i++) {
      for (int j = 0 ; j < worldMap[0].length ; j++) {
        MHTile currTile = worldMap[i][j];

        if (!(currTile.getTileBehavior() instanceof InaccessibleTileBehavior)) {
          if (currentTile == null) {
            currTile.setHeroes(heroes);
            currentTile = currTile;
          }
        }
      }
    }
  }

  // Need to set the heroes of currentTile so the heroes has somewhere to start
  public void start() {
    while (true) {
      // print the world map
      io.displayWorldMap(worldMap);
      io.displayMsg("Current coordinate: " + currentTile.getCoordinate());
      String action = io.queryForUserActionInWorld(onMarketTile);

      if (action.equalsIgnoreCase("W")) {
        MHTile nextTile = getUpTile();
        boolean moveSuccessful = move(nextTile);
        io.displayMovementMsg(moveSuccessful);

      } else if (action.equalsIgnoreCase("A")) {
        MHTile nextTile = getLeftTile();
        boolean moveSuccessful = move(nextTile);
        io.displayMovementMsg(moveSuccessful);

      } else if (action.equalsIgnoreCase("S")) {
        MHTile nextTile = getDownTile();
        boolean moveSuccessful = move(nextTile);
        io.displayMovementMsg(moveSuccessful);

      } else if (action.equalsIgnoreCase("D")) {
        MHTile nextTile = getRightTile();
        boolean moveSuccessful = move(nextTile);
        io.displayMovementMsg(moveSuccessful);

      } else if (action.equalsIgnoreCase("U")) {
        /*
         * Request which hero's inventory to use
         * 
         * Then method to query for use of item from inventory:
         * armor, gear, potion
         */
        Hero selectedHero = io.queryHeroForInventoryUse(heroes);
        if (selectedHero == null) {
          // Go back to prior levle

        } else if (selectedHero.getInventory().size() == 0) {
          String msg = selectedHero.getName() + "'s inventory cannot be used " +
          "because it is currently empty.";
          io.displayMsg(msg);

        } else {
          boolean usingInventory = true;
          while (usingInventory) {
            if (useInventoryInWorld(selectedHero) == null) {
              // Exit out of using inventory for the selectedHero
              usingInventory = false; 
            }
          }
        }

      } else if (action.equalsIgnoreCase("I")) {
        // Display info of all heroes
        io.displayHeroInfoInWorld(this, heroes);

      } else if (action.equalsIgnoreCase("M")) {
        // The option M/m is only shown when onMarketTile==true, 
        // meaning currentTile is MarketTile
        currentTile.interact(heroes);

      } else if (action.equalsIgnoreCase("T")) {
        io.displayWorldTutorial();
        
      } else {
        throw new IllegalArgumentException("The action string: " + action +
        " has not been fully implemented for the correct functionality. " +
        "Please add another else if statement in World.start() to take "+
        "care of it.");
      }
    }
  }

  /*
   * If failed to move, print a message that the nextTile is inaccessible
   * if moved, nothing
   */
  /*
   * move should also check if nextTile is outofbounds, if yes, print
   */

  // A method for moving the heroes from one tile to another tile
  public boolean move(MHTile nextTile) {
    if (nextTile == null) { // Meaning it is out of bounds
      // print "cannot move to this tile"
      System.out.println("nextTile is null");
      return false;
    }
    // Prints the coordinate of the nextTile
    // io.displayMsg(nextTile.getCoordinate().toString());

    TileBehavior nextTileBehavior = nextTile.getTileBehavior();
    if (nextTileBehavior instanceof CommonTileBehavior) {
      // logic for moving onto commontile, and also need to call the
      // interact method of the tile

      // The action of moving the heroes from one tile to another
      currentTile.setHeroes(null);
      nextTile.setHeroes(heroes);

      currentTile = nextTile; // update currentTile
      onMarketTile = false;

      currentTile.interact(heroes); // roll a dice and may get into battle

      return true; // successful movement from one tile to another

    } else if (nextTileBehavior instanceof MarketTileBehavior) {
      // logic for moving onto markettile
      currentTile.setHeroes(null);
      nextTile.setHeroes(heroes);
      currentTile = nextTile;
      /*
       * Given the option to enter the market is on the user to enter
       * M/m, thus we do not explicitly call MarketTile.interact here
       */
      onMarketTile = true;
      return true;

    } else if (nextTileBehavior instanceof InaccessibleTileBehavior) {
      // cannot move onto an inaccessible tile
      return false; // To indicate failed to move

    } else {
      throw new IllegalArgumentException("The tileBehavior for the nextTile "+
      "is: " + nextTileBehavior + ". Either the related functionality for " +
      "this tileBehavior has not been implemented yet, or it is null.");
    }
  }

  // **********************************************************************

  public void setHeroes(List<Hero> heroes) {
    if (heroes == null || heroes.size() == 0) {
      throw new IllegalArgumentException("Heroes should not be null " +
      " and the number of heroes in the party should not be zero. " +
      "Here is a print of heroes for your refernce: " + heroes);
    }
    this.heroes = heroes;
  }

  // So the querying has to get the values that are within bound
  public void setWorldMap(MHTile[][] worldMap) {
    if (worldMap == null || !verifyMapValidity(worldMap)) {
      throw new IllegalArgumentException("The input worldMap is not a valid "+
      "map, as it is null or its tiles may be null.");
    }
    this.worldMap = worldMap;
  }

  private boolean verifyMapValidity(MHTile[][] worldMap) {
    for (int i = 0; i < worldMap.length ; i++) {
      for (int j = 0; j < worldMap[0].length ; j++) {
        if (worldMap[i][j] == null) {
          return false;
        }
      }
    }

    return true;
  }

  // Returning null for the user's want to exit to prior level
  public Boolean useInventoryInWorld(Hero hero) {
    Item selectedItem = io.queryForInventoryUsage(hero);

    if (selectedItem == null) {
      return null;
    }

    if (selectedItem instanceof Armor) {
      // Take off hero's current equipped armor and put it into the inventory
      hero.equipArmor((Armor) selectedItem, io);

    } else if (selectedItem instanceof Weapon) {
      // Equip weapon
      hero.equipWeapon((Weapon) selectedItem, io);

    } else if (selectedItem instanceof Spell) {
      String msg = "Spells cannot be used outside of battle.";
      io.displayMsg(msg);
      return false;

    } else if (selectedItem instanceof Potion) {
      hero.usePotion((Potion) selectedItem, io);

    }
    return true;
  }

  // *****************************************************************

  private MHTile getUpTile() {
    int currX = currentTile.getCoordinate().getXCoordinate();
    int currY = currentTile.getCoordinate().getYCoordinate();
    if (isInBounds(currX, currY-1)) {
      // return worldMap[currX][currY-1];
      return worldMap[currY-1][currX];
    }
    return null;
  }
  private MHTile getLeftTile() {
    int currX = currentTile.getCoordinate().getXCoordinate();
    int currY = currentTile.getCoordinate().getYCoordinate();
    if (isInBounds(currX-1, currY)) {
      // return worldMap[currX-1][currY];
      return worldMap[currY][currX-1];
    }
    return null;
  }
  private MHTile getDownTile() {
    int currX = currentTile.getCoordinate().getXCoordinate();
    int currY = currentTile.getCoordinate().getYCoordinate();
    if (isInBounds(currX, currY+1)) {
      // return worldMap[currX][currY+1];
      return worldMap[currY+1][currX];
    }
    return null;
  }
  private MHTile getRightTile() {
    int currX = currentTile.getCoordinate().getXCoordinate();
    int currY = currentTile.getCoordinate().getYCoordinate();
    if (isInBounds(currX+1, currY)) {
      // return worldMap[currX+1][currY];
      return worldMap[currY][currX+1];
    }
    return null;
  }

  protected boolean isInBounds(int xIndex, int yIndex) {
    if ((xIndex >= 0 && xIndex < worldMap[0].length ) && 
    (yIndex >= 0 && yIndex < worldMap.length )) {
      return true;
    }
    return false;
  }

  protected boolean isInBounds(Coordinate coodinate) {
    int currX = coodinate.getXCoordinate();
    int currY = coodinate.getYCoordinate();

    return isInBounds(currX, currY);
  }
}
