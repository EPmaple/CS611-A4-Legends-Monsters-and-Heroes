/*
 * IO.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * The superclass that holds the most general IO's and queries of the program
 */

import java.util.*;

public class IO {
  protected static final Scanner scanner = new Scanner(System.in); // So there no multiple instances of scanners

  // Displays

  public void displayPlayerDefeatedMsg() {
    String msg = "You were " + Colors.ANSI_RED + "defeated" + Colors.ANSI_RESET +
    " in battle and game is over.";
    displayMsg(msg);
  }

  public void displayConsumableRemainingUses(Item item, int remainingUses) {
    // Currently there is only numOfRemainingUses for Spell and Potion
    if ((item instanceof Spell) || (item instanceof Potion)) {
      String msg = "The number of remaining uses for " + item.getName() +
      " is " + remainingUses + ". ";

      if (remainingUses <= 0) { // Just in case
        msg += "It has perished.";
      }

      displayMsg(msg);
      
    } else {
      throw new IllegalArgumentException("Unsupported gear type: " +
      item.getClass().getSimpleName());
    }
  }

  public void displayEquipGearMsg(Hero hero, Item itemTookOff, Item itemPutOn) {
    String msg = "";

    if (itemTookOff != null) {
      // Do a sanity check that both are weapon or armor
      // 
      if (!((itemTookOff instanceof Weapon && itemPutOn instanceof Weapon) ||
      (itemTookOff instanceof Armor && itemPutOn instanceof Armor))) {
        throw new IllegalArgumentException("A hero currently can only " +
        "exchange armor for armor, or weapon for weapon. Current items: " +
        itemTookOff.getName() + " and " + itemPutOn.getName() + ".");
      }

      msg += hero.getName() + " has took off " + itemTookOff.getName() + ".";
    } 
    // Else, hero currently does not have any armor or weapon equipped

    msg += hero.getName() + " has put on " + itemPutOn.getName() + ".";

    displayMsg(msg);
  }

  public void displayPotionUseMsg(Hero hero, Potion potion) {
    String msg = hero.getName() + " used the potion " + potion.getName() + " and";

    int potionAttributeInc = potion.getAttributeIncrease();
    for (Attribute attribute : potion.getAttributesAffected()) {
      switch (attribute) {
        case HEALTH:
          msg += ", recovered " + potionAttributeInc + " HP";
          break;
        case MANA:
          msg += ", recovered " + potionAttributeInc + " MP";
          break;
        case STRENGTH:
          msg += ", increased " + potionAttributeInc + " strength";
          break;
        case AGILITY:
          msg += ", increased " + potionAttributeInc + " agility";
          break;
        case DEXTERITY:
          msg += ", increased " + potionAttributeInc + " dexterity";
          break;
        case DEFENSE:
          msg += ", increased " + potionAttributeInc + " defense";
          break;
        case DAMAGE:
        case DODGE_CHANCE:
        default:
          throw new IllegalArgumentException("The attribute of " + potion.getName() +
          " is " + attribute + ", which is not currently supported.");
      }
    }

    msg += ".";
    displayMsg(msg);
  }

  public void displayGoldGainMsg(Hero hero, int goldGain) {
    String msg = hero.getName() + " has gained " + Colors.ANSI_YELLOW +
    goldGain + Colors.ANSI_RESET + " gold.";
    displayMsg(msg);
  }

  public void displayExpIncMsg(Hero hero, int expIncAmt) {
    String msg = hero.getName() + " has gained " + Colors.ANSI_YELLOW 
    + expIncAmt + Colors.ANSI_RESET + " exp.";
    displayMsg(msg);
  }

  public void displayLvlUpMsg(Hero hero) {
    String msg = hero.getName() + " has leveled up and is now lvl." + 
    Colors.ANSI_YELLOW + hero.getLevel() + Colors.ANSI_RESET;
    displayMsg(msg);
  }

  // ***************************************************************
  // queries

  public Item queryForInventoryUsage(Hero hero) {
    List<Item> inventory = hero.getInventory();

    String prompt = "Below is the inventory for " + hero.getName() + ": \n";

    for (int i = 0; i < inventory.size(); i++) {
      prompt += "[" + i + "] " + inventory.get(i) + "\n";
    }
    prompt += "[>] Please enter the number for the item you want " +
    "to select, or enter Z/z to return to the prior level: ";

    Integer inventorySelectedIndex = queryIntWithPrior(prompt, 0, inventory.size()-1);
    if (inventorySelectedIndex == null) {
      return null;
    }

    return inventory.get(inventorySelectedIndex);
  }

  public Integer queryForGameSelection() {
    String msg = "Welcome! Please enter the number for the game you want ot play: \n";
    msg += "[0] Legends: Monsters and Heroes\n";
    msg += "[1] Legends of Valor";

    int selectionIndex = queryInt(msg, 0, 1);

    return selectionIndex;
  }

  public HeroType queryForHeroType() {
    String msg = "Enter 'paladin', 'sorcerer', or 'warrior' to select the type of the hero: "+
    "\n Paladins are favored on strength and dexterity, "+
    "\n Sorcerers are favored on dexterity and agility, "+
    "\n Warriors are favored on strength and agility.";

    List<String> types = new ArrayList<String>();
    types.addAll(Arrays.asList("paladin", "sorcerer", "warrior"));

    String heroTypeSelection = queryString(msg, types);

    HeroType selectedHeroType = HeroType.valueOf(heroTypeSelection.toUpperCase());
    if (selectedHeroType == null) { // Meaning String != HeroType
      throw new IllegalArgumentException("The heroType of user input " +
      "did not match with any of the currently supported heroType. " +
      "User input: " + heroTypeSelection);
    }

    return selectedHeroType;
  }

  public Hero queryForHeroSelection(List<Hero> heroes, HeroType heroType) {
    String msg = "";
    for (int i = 0; i < heroes.size(); i++) {
      msg += "[" + i + "]" + heroes.get(i) + "\n";
    }
    msg += "Enter the number for the " + heroType.toString() + " you "+
    "want to select, or enter Z/z to go back to the prior level: ";

    Integer heroSelectionIndex = queryIntWithPrior(msg, 0, heroes.size() - 1);
    if (heroSelectionIndex == null) {
      return null;
    }

    return heroes.get(heroSelectionIndex);
  }
  

  /*
   * ****************************************************************
   */

  public Integer queryInt(String prompt, int min, int max) {
    Integer input = -1;
    boolean valid = false;

    do {
      displayMsg(prompt);
      try {
        String strInput = scanner.nextLine(); 
        // "Q" or "q" entered, meaning the user wants to QUIT
        if (strInput.equalsIgnoreCase(Constants.QUIT)) {
          System.exit(0);
        }

        input = Integer.parseInt(strInput);
        if (input < min || input > max) {
          throw new NumberFormatException();
        } else {
          valid = true;
        }

      } catch (NumberFormatException err) {
        displayMsg("Invalid input.");
      }
    } while (!valid);

    return input;
  }

  public Integer queryInt(String prompt, List<Integer> range) {
    Integer input = -1;
    boolean valid = false;

    do {
      displayMsg(prompt);
      try {
        String strInput = scanner.nextLine(); 
        // "Q" or "q" entered, meaning the user wants to QUIT
        if (strInput.equalsIgnoreCase(Constants.QUIT)) {
          System.exit(0);
        }

        // Else, the use input an
        input = Integer.parseInt(strInput);

        if (range.contains(input)) {
          valid = true;
        } else {
          throw new NumberFormatException();
        }

      } catch (NumberFormatException err) {
        displayMsg("Invalid input.");
      }
    } while (!valid);

    return input;
  }

  // Taking the option PRIOR into consideration
  public Integer queryIntWithPrior(String prompt, int min, int max) {
    Integer input = -1;
    boolean valid = false;

    do {
      displayMsg(prompt);
      try {
        String strInput = scanner.nextLine(); 
        // "Q" or "q" entered, meaning the user wants to QUIT
        if (strInput.equalsIgnoreCase(Constants.QUIT)) {
          System.exit(0);
        }

        // Z being prior
        if (strInput.equalsIgnoreCase(Constants.PRIOR)) {
          return null;
        }

        input = Integer.parseInt(strInput);
        if (input < min || input > max) {
          throw new NumberFormatException();
        } else {
          valid = true;
        }

      } catch (NumberFormatException err) {
        displayMsg("Invalid input.");
      }
    } while (!valid);

    return input;
  }

  // Only use this method when we are ready to handle null as a possible return
  public Integer queryIntWithPrior(String prompt, List<Integer> range) {
    Integer input = -1;
    boolean valid = false;

    do {
      displayMsg(prompt);
      try {
        String strInput = scanner.nextLine(); 
        // "Q" or "q" entered, meaning the user wants to QUIT
        if (strInput.equalsIgnoreCase(Constants.QUIT)) {
          System.exit(0);
        }

        // Z being prior
        if (strInput.equalsIgnoreCase(Constants.PRIOR)) {
          return null;
        }

        // Else, the use input an
        input = Integer.parseInt(strInput);

        if (range.contains(input)) {
          valid = true;
        } else {
          throw new NumberFormatException();
        }

      } catch (NumberFormatException err) {
        displayMsg("Invalid input.");
      }
    } while (!valid);

    return input;
  }

  // Perhaps we may want to change the input parameter so that we can
  // have something to match the input with

  /*
   * Like when querying for HeroType, the input String should match
   * 
   * Consider overload the following?
   */
  public String queryString(String prompt, List<String> strs) {
    String input = null;
    boolean valid = false;

    do {
      displayMsg(prompt);

      try {
        input = scanner.nextLine();

        // "Q" or "q" entered, meaning the user wants to QUIT
        if (input.equalsIgnoreCase(Constants.QUIT)) {
          System.exit(0);
        }
  
        // Z to go back to prior
        if (input.equalsIgnoreCase(Constants.PRIOR)) {
          for (String str : strs) {
            // Checking for "Z" in strs meaning that we are ready to handle
            // null as a return
            if (str.equalsIgnoreCase(Constants.PRIOR)) {
              return null;
            }
          }
        }
  
        for (String str : strs) {
          if (input.equalsIgnoreCase(str)) {
            valid = true;
          }
        }
        if (valid == false) {
          throw new IllegalArgumentException();
        }

      } catch (IllegalArgumentException err) {
        displayMsg("Invalid input.");
      }

    } while (!valid);

    return input;
  }

  public boolean queryBoolean(String prompt, String opt1, String opt2) {
    String input;
    boolean valid = false;

    do {
      displayMsg(prompt + "(" + opt1 + "/" + opt2 + ")");
      input = scanner.nextLine();

      if (input.equalsIgnoreCase(opt1) || input.equalsIgnoreCase(opt2)) {
        valid = true;
      } else {
        displayMsg("Invalid input. Please enter " + opt1 + " or " + opt2 + ": ");
      }

    } while (!valid);

    // By default returns true for the first option
    return input.equalsIgnoreCase(opt1); 
  }

  public void displayMsg(String msg) {
    System.out.println("[>] " + msg);
  }
}
