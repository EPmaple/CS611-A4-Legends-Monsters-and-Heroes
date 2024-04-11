/*
 * MarketIO.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * a subclass of IO filled with specific queries and displays related
 * to Market
 */

import java.util.*;

public class MarketIO extends IO{
  private static MarketIO instance;

  private MarketIO() {

  }

  public static MarketIO getInstance() {
    if (instance == null) {
      instance = new MarketIO();
    }
    return instance;
  }

  public void displayMarketTutorial() {
    String indent = "    ";
    String msg = "You can only buy items if: \n"+ indent + "Hero's level is" +
    " greater than the item's required level, and that the hero has enough"  +
    " gold to buy it.\n" + indent + "You can sell anything from the hero's " +
    "inventory for half of its cost, and the sold item would be transferred " +
    "to the market.";

    displayMsg(msg);
  }

  public void displaySuccessfulSellMsg(Hero hero, Item item) {
    String msg = "You have successfully sold " + item.getName() +
    " for " + item.getSellPrice() + " gold. It has been successfully " +
    "transferred to the stock of this market, and " + hero.getName() +
    " now has " + hero.getGold() + " gold.";
    
    displayMsg(msg);
  }

  public void displayHeroGoldandLvl(Hero hero) {
    String msg = hero.getName() + " is currently lvl." + hero.getLevel() + ", and"
    + " currently has " + hero.getGold() + " gold.";

    displayMsg(msg);
  }

  public void displayPurchaseFailedMsg(Hero hero, Item item) {
    String msg = hero.getName() + " failed to purchase " + item.getName() +
    " due to either insufficient level or gold.";

    displayMsg(msg);
  }

  public void displaySuccessfulPurchaseMsg(Hero hero, Item item) {
    String msg = hero.getName() + " has successfullly purchased " +
    item.getName() + " for " + item.getBuyPrice() + ". " + hero.getName() +
    " has " + hero.getGold() + " gold left.";

    displayMsg(msg);
  }
  
  // displays
  public void displayStock(List<Item> stock) {
    String msg = ""; // Reset msg
    for (int i = 0; i < stock.size(); i++) {
      Item item = stock.get(i);
      msg += "[" + i + "] " + item + "\n";
    }
    // Display all the info of the items in the stock of this market
    displayMsg(msg);
  }

  // *************************************************************
  // queries

  public String queryForUserActionInMarket(Hero hero) {
    String msg = "Enter B/b to buy any item from the stock available, or"+ 
    " S/s to sell any item from " + hero.getName() + "'s inventory, or" +
    " F/f to finish trading for " + hero.getName() + ", or T/t for tutorial:";
    List<String> commands = new ArrayList<String>();
    commands.addAll(Arrays.asList("B", "S", "F", "T"));

    String action = queryString(msg, commands);

    return action;
  }

  public Item queryForPurchaseSelection(List<Item> stock) {
    String msg = "Please enter the number for the item you want to buy, "+
    "or enter Z/z to go back to the prior level: ";

    Integer purchaseSelectionIndex = queryIntWithPrior(msg, 0, stock.size() - 1);
    if (purchaseSelectionIndex == null) {
      return null;
    }

    return stock.get(purchaseSelectionIndex);
  }
}
