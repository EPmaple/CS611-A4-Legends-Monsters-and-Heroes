/*
 * TileFactory.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Factory responsible for creating Tiles of different TileBehavior
 */

import java.util.*;

public class TileFactory {
  private static TileFactory instance;
  private ItemFactory ifInstance;

  private TileFactory() {
    ifInstance = ItemFactory.getInstance();
  }

  public static TileFactory getInstance() {
    if (instance == null) {
      instance = new TileFactory();
    }
    return instance;
  }


  public MHTile createInaccessibleTile(Coordinate coordinate) {
    return new MHTile(new InaccessibleTileBehavior(), coordinate);
  }

  public MHTile createCommonTile(Coordinate coordinate) {
    return new MHTile(new CommonTileBehavior(), coordinate);
  }

  public MHTile createMarketTile(Coordinate coordinate) {
    // To create stock for market tile
    List<Item> stock = new ArrayList<Item>();

    for (int i = 0; i < 10; i++) {
      stock.add(ifInstance.createItem());
    }

    return new MHTile(new MarketTileBehavior(stock), coordinate);
  }
}

