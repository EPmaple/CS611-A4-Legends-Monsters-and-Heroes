/*
 * MarketTileBehavior.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Part of the TileBehavior Strategy Pattern that
 * represents the behavior of a MarketTile
 */

import java.util.List;

public class MarketTileBehavior implements TileBehavior{
  // private Market marketInstance;
  private List<Item> stock;

  public MarketTileBehavior(List<Item> stock) {
    // marketInstance = Market.getInstance();
    this.stock = stock;
  }

  @Override
  public void interact(List<Hero> heroes) {
    /*
     * Additional logic to have the heroes enter the market
     */
    // Return true
    // marketInstance.setHeroes(heroes);
    // marketInstance.setStock(stock);
    Market market = new Market(heroes, stock, null);
    market.beginTrade();
  }
}
