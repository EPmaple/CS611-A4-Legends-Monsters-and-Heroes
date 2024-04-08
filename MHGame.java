/*
 * MHGame.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Class that is the game starter for the game Legends: Monsters and
 * Heroes, by first querying from the user necessary game metrics
 */

import java.util.*;

public class MHGame {
  private IO io;
  private CharacterFactory cfInstance;
  private MapFactory mfInstance;

  public MHGame(IO io) {
    this.io = io;
    cfInstance = CharacterFactory.getInstance();
    mfInstance = MapFactory.getInstance();
  }

  public void gameStarts() {
    boolean continueToPlay = true;

    while (continueToPlay) {
    // first, query if the user wants to change dimension
    String msg = "Would you like to change the dimensions of the world? " +
    "(Default is 8x8)";
    boolean changeDimension = io.queryBoolean(msg, "y", "n");

    int width = Constants.DEFAULT_WORLD_WIDTH;
    int height = Constants.DEFAULT_WORLD_HEIGHT;
    if (changeDimension) {
      msg = "Enter the integer for the width of your world: (min is 3)";
      width = io.queryInt(msg, Constants.MIN_WORLD_WIDTH, 99);
      msg = "Enter the integer for the height of your world: (min is 3)";
      height = io.queryInt(msg, Constants.MIN_WORLD_HEIGHT, 99);
    }

    // second, query for the number of heroes the user wants
    msg = "How many heroes do you want in your party? (min is 1, max is 3)";
    int numOfHeroes = io.queryInt(msg, Constants.MIN_NUM_OF_HEROES, Constants.MAX_NUM_OF_HEROES);

    List<Hero> heroes = new ArrayList<Hero>();
    // third, make a for loop to select the number of heroes
    for (int i = 0; i < numOfHeroes; i++) {
      boolean heroSelected = false;
      while (!heroSelected) {
        // first ask which type of hero he wants to select 
        HeroType herotype = io.queryForHeroType();
        List<Hero> typedHeroes = cfInstance.getSpecifiedHeroes(herotype);

        // second ask which hero he wants to select (w/ prior)
        Hero selectedHero = io.queryForHeroSelection(typedHeroes, herotype);
        if (selectedHero != null) {
          heroSelected = true;
          heroes.add(selectedHero);
          typedHeroes.remove(selectedHero);
          msg = "The " + herotype + ", " + selectedHero.getName() + " has been"+
          " selected. \n";
          io.displayMsg(msg);
        } // null => go on to the next iteration of the while loop
      }
    }

    Tile[][] worldMap = mfInstance.createWorld(height, width);

    // fourth, party formed, can now pass heroes to the world and start the
    // game

    World world = new World(worldMap, heroes);
    world.start();

    msg = "Would you like to play another round of Legends: Monsters and Heroes?";
    continueToPlay = io.queryBoolean(msg, "y", "n");
    }
  }
}
