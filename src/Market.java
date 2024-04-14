/*
 * Market.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Class housing all the behaviors and states of the Market interface
 * our of game
 */

import java.util.*;

public class Market {
  // private static Market instance;

  protected List<Hero> heroes;
  protected MarketIO io;
  protected Summary summaryInstance;
  /*
   * The idea is to when the player has moved 10 or 25, or some turns,
   * then some of the stocks would refresh, and this is a message that is 
   * shown on the world context
   */
  protected List<Item> stock;

  public Market (List<Hero> heroes, List<Item> stock) {
    this.io = MarketIO.getInstance();
    this.summaryInstance = Summary.getInstance();
    setHeroes(heroes);
    setStock(stock);
  }

  // public static Market getInstance() {
  //   if (instance == null) {
  //     instance = new Market();
  //   }
  //   return instance;
  // }

  // @Override
  // public void interact(List<Hero> heroes) {
  //     // Handle hero actions while in the market
  // }
  // *****************************************************************

  public void beginTrade() {
    if (stock == null) {
      throw new IllegalArgumentException("Please use Market.setHeroes() " +
      "and Market.setStock() to set heroes and stock in market before "+
      "running Market.beginTrade().");
    }

    io.displayMsg("You have entered the market.");

    // Heroes enter the market individually
    for (Hero hero : heroes) {
      boolean endTrade = false;

      while (!endTrade) {
        io.displayHeroGoldandLvl(hero);

        // "B", "S", "F"
        String action = io.queryForUserActionInMarket(hero);

        if (action.equalsIgnoreCase("B")) {
          // logics for buy
          toBuy(hero);
        } else if (action.equalsIgnoreCase("S")) {
          // logics for sale
          toSell(hero);
        } else if (action.equalsIgnoreCase("F")) {
          // meaning the user decided to end trade for the current hero
          endTrade = true; 

        } else if (action.equalsIgnoreCase("T")) {
          io.displayMarketTutorial();
          
        } else {
          throw new IllegalArgumentException("An action that was not "+
          "requested was returned by queryForUserActionInMarket(): " +
          action + ".");
        }
      }
    }
    // Clean-ups
    resetMarketInstance();

    // After all heroes have finished trading, exit out of market and return
    // to the World
  }

  private void resetMarketInstance() {
    heroes = null;
    stock = null;
  }

  // *****************************************************************

  public Boolean toBuy(Hero hero) {
    // request from the user if he wants to go back to the prior level, or
    //    ask him for a number so buy the item
    while (true) {
      String msg = "Below are the items available in this current market: ";
      io.displayMsg(msg);
  
      io.displayStock(stock);
  
      io.displayHeroGoldandLvl(hero);

      Item purchaseSelection = io.queryForPurchaseSelection(stock);
      if (purchaseSelection == null) {
        return null; // null => prior level
      }
  
      if (hero.getLevel() >= purchaseSelection.getRequiredLevel() && hero.getGold() >= purchaseSelection.getBuyPrice()) {
        stock.remove(purchaseSelection); // remove item from stock
        hero.getInventory().add(purchaseSelection); // add it to hero's inventory
        hero.setGold(hero.getGold() - purchaseSelection.getBuyPrice());
        // update gold spent in summary
        summaryInstance.incrementTotalGoldSpent(purchaseSelection.getBuyPrice());
        // Display msg indicating successful purchase
        io.displaySuccessfulPurchaseMsg(hero, purchaseSelection);
      } else {
        // Display msg indicating purchase failed
        io.displayPurchaseFailedMsg(hero, purchaseSelection);
      }
    }
  }

  public Boolean toSell(Hero hero) {
    while (true) {
      Item inventorySelection = io.queryForInventoryUsage(hero);
      if (inventorySelection == null) {
        return null; // null -> prior level
      }

      String msg = "Are you sure you want to sell " + inventorySelection.getName() + 
      " for " + inventorySelection.getSellPrice() + " gold? Enter Y/y for yes, " +
      "N/n for no: ";

      boolean sellDecision = io.queryBoolean(msg, "y", "n");

      if (sellDecision) {
        /*
         * To sell the item, remove it from hero's inventory
         * increase hero's gold by the sellprice
         * add it to the stock of the market
         */
        hero.getInventory().remove(inventorySelection);
        hero.setGold(hero.getGold() + inventorySelection.getSellPrice());
        stock.add(inventorySelection);
        io.displaySuccessfulSellMsg(hero, inventorySelection);
      }
      /*
       * if no, go on to the next iteration of the while loop
       */
    }
  }

  // *****************************************************************

  public void setStock(List<Item> stock) {
    if (stock == null || stock.size() == 0) {
      throw new IllegalArgumentException("Stock should not be null " +
      " and the number of items in the stock should not be zero. " +
      "Here is a print of stock for your refernce: " + stock);
    }
    this.stock = stock;
  }

  public void setHeroes(List<Hero> heroes) {
    if (heroes == null || heroes.size() == 0) {
      throw new IllegalArgumentException("Heroes should not be null " +
      " and the number of heroes in the party should not be zero. " +
      "Here is a print of heroes for your refernce: " + heroes);
    }
    this.heroes = heroes;
  }
}