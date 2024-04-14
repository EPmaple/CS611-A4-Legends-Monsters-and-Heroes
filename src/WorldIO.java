/*
 * WorldIO.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * a subclass of IO filled with specific queries and displays related
 * to World
 */

import java.util.*;

public class WorldIO extends IO{
  private static WorldIO instance;

  private WorldIO() {

  }

  public static WorldIO getInstance() {
    if (instance == null) {
      instance = new WorldIO();
    }
    return instance;
  }

  // ***************************************************

  public void displayWorldTutorial() {
    String indent = "    ";
    String msg = Colors.ANSI_BLUE + "H" + Colors.ANSI_RESET + " is where "+
    "you are on the map. \n" + indent + Colors.ANSI_GREEN + "X" + Colors.ANSI_RESET +
    " are inaccessible spaces. \n" + indent + Colors.ANSI_YELLOW + "X" + Colors.ANSI_RESET +
    " are markets where you can buy and sell things.\n" + indent +
    "When you moved onto a blank space, there is a 50/50 chance of entering battle.\n" +
    indent + Colors.ANSI_BLUE + "TIP:" + Colors.ANSI_RESET + " use "+
    "potions outside of battle to boost skills of the heroes for higher "+
    "chance of winning.";

    displayMsg(msg);
  }

  public void displayHeroInfoInWorld(HeroesAndMonstersWorld world, List<Hero> heroes) {
    String msg = "Below are information about your heroes: \n";

    for (Hero hero : heroes) {
      msg += hero.toString(world);
    }

    displayMsg(msg);
  }

  public void displayMovementMsg(boolean moveSuccessful) {
    String msg = "";

    if (moveSuccessful) {
      msg += "Move successful.";
    } else {
      msg += "Failed to move in the requested direction.";
    }

    displayMsg(msg);
  }

  public void displayWorldMap(MHTile[][] map) {
    System.out.println("");

    // the top most border
    String horitzontalBorder = "";
    for (int i = 0; i < map.length; i++) {
      horitzontalBorder += "----";
    }
    horitzontalBorder += "-";
    System.out.println(horitzontalBorder);

    for (MHTile[] row : map) {
      String rowContent = "|";

      for (MHTile tile : row) {
        rowContent += " " + tile.toString(true) + " |";
      }
      System.out.println(rowContent);
      System.out.println(horitzontalBorder);
    }
    System.out.println("");
  }

  // ***************************************************

  public Hero queryHeroForInventoryUse(List<Hero> heroes) {
    String msg = "Which of the following hero's inventory would you like to use, " +
    "or enter Z/z to go back to the prior level: \n";

    for (int i = 0; i < heroes.size() ; i++) {
      msg += "[" + i + "] " + heroes.get(i).getName() + "  ";
    }

    Integer heroSelectionIndex = queryIntWithPrior(msg, 0, heroes.size() - 1);
    if (heroSelectionIndex == null) {
      return null;
    }
    return heroes.get(heroSelectionIndex);
  }

  public String queryForUserActionInWorld(boolean onMarketTile) {
    /*
     * w, a, s, d, g, u, i, and if onMarketTile == true, m
     */
    String msg = "Enter W/w to move up, A/a to move left, S/s to move down, " +
    "D/d to move right, U/u to use the inventory of a hero," + 
    " I/i to show information about the heroes, T/t for tutorial";
    List<String> commands = new ArrayList<String>();
    commands.addAll(Arrays.asList("W", "A", "S", "D", "U", "I", "T"));

    if (onMarketTile == true) {
      msg += ", M/m to enter market";
      commands.add("M");
    }
    msg += ": ";

    String action = queryString(msg, commands);
    return action;
  }
}
