/*
 * MapFactory.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Factory used to create a map (Tile[][])
 */

import java.util.*;

public class MapFactory {
  private static MapFactory instance;
  private TileFactory tfInstance;
  private Tile[][] worldMap;

  private MapFactory() {
    tfInstance = TileFactory.getInstance();
  }

  public static MapFactory getInstance() {
    if (instance == null) {
      instance = new MapFactory();
    }
    return instance;
  }

  // IMPORTANT NOTE!!!
  // HEIGHT == ROW == Y, WIDTH == COLUMN == X
  public Tile[][] createWorld(int height, int width) {
    boolean allTraversable = false;

    while (!allTraversable) {
      // create a new blank map
      worldMap = new Tile[height][width];

      int totalTiles = width * height;
      // Integer division, fractional parts truncated
      int inaccessibleTiles = totalTiles * 20 / 100; // 20%
      int marketTiles = totalTiles * 30 / 100;
      // int commonTiles = totalTiles - inaccessibleTiles - marketTiles;

      Set<Coordinate> visitedTiles = new HashSet<Coordinate>();

      Random random = new Random();
      // Fill inaccessible tiles
      for (int i = 0; i < inaccessibleTiles; i++) {
        boolean hasVisited = true;
        // If the coordinate has been visited, then we will continue to try
        //  to place down a Tile at a coordiante that has not been visited
        while (hasVisited) {
          int row = random.nextInt(height); // non-inclusive at the end
          int col = random.nextInt(width); // [0, width/height - 1]
          Coordinate coord = new Coordinate(col, row);

          if (!visitedTiles.contains(coord)) {
            visitedTiles.add(coord);
            hasVisited = false;
            worldMap[row][col] = tfInstance.createInaccessibleTile(coord);
          }
        }
      }

      // Fill market tiles
      for (int i = 0; i < marketTiles; i++) {
        boolean hasVisited = true;

        while (hasVisited) {
          int row = random.nextInt(height); // non-inclusive at the end
          int col = random.nextInt(width); // [0, width/height - 1]
          Coordinate coord = new Coordinate(col, row);

          if (!visitedTiles.contains(coord)) {
            visitedTiles.add(coord);
            hasVisited = false;
            worldMap[row][col] = tfInstance.createMarketTile(coord);
          }
        }
      }

      // Fill common tiles
      for (int i = 0; i < height; i++) {
        for (int j = 0; j < width; j++) {
          if (worldMap[i][j] == null) {
            worldMap[i][j] = tfInstance.createCommonTile(new Coordinate(j, i));
          }
        }
      }

      allTraversable = verifyTileConnectivity();
    }

    return worldMap;
  }

    // 1.) we would need the Set<CommonTile> and Set<InaccessibleTile>
  // 2.) run BFS from the startingTile, and add each visited tile to another set
  // 3.) after BFS, compare the set of visitedTiles to the union of Set<CommonTile> and Set<InaccessibleTile>
  //    if they are equal, then yes, all tiles are connected, else no
  public boolean verifyTileConnectivity() {
    Tile startingTile = getStartingTile();
    if (startingTile == null) {
      throw new IllegalArgumentException("This means the worldMap was " +
      "instantiated with all inaccessibleTiles, which should not be allowed.");
    }

    Set<Tile> visitedTiles = new HashSet<>();
    Set<Tile> commonAndInaccessibleTiles = new HashSet<>();

    // Find all tiles that are not inaccessible
    for (int i = 0; i < worldMap.length; i++) {
      for (int j = 0; j < worldMap[0].length; j++) {
        Tile currentTile = worldMap[i][j];
        if (!(currentTile.getTileBehavior() instanceof InaccessibleTileBehavior)) {
          commonAndInaccessibleTiles.add(currentTile);
        } 
      }
    }

    // Run BFS to find all tiles that are reachable from startingTile
    BFS(startingTile, visitedTiles);

    // System.out.println("Here is visitedTiles: " + visitedTiles.toString());
    // System.out.println("Here is commonAndInaccessibleTiles: " + commonAndInaccessibleTiles.toString());
    // System.out.println("Are they equal? " + visitedTiles.equals(commonAndInaccessibleTiles));

    // Return true if the set of tiles that could be visited is the same as
    // the set of all common and market tiles; else false
    return visitedTiles.equals(commonAndInaccessibleTiles);
  }

  private Tile getStartingTile() {
    Tile startingTile = null;
    // To get a starting tile
    for (int i = 0; i < worldMap.length; i++) { // i == ROW 
      for (int j = 0; j < worldMap[0].length; j++) { // j == COL
        if (!(worldMap[i][j].getTileBehavior() instanceof InaccessibleTileBehavior)) {
          startingTile = worldMap[i][j];
          return startingTile;
        }
      }
    }
    return startingTile;
  }

  private void BFS(Tile startingTile, Set<Tile> visitedTiles) {
    Queue<Tile> queue = new LinkedList<Tile>();

    // init
    visitedTiles.add(startingTile);
    queue.offer(startingTile);

    while (!queue.isEmpty()) {
      Tile currentTile = queue.poll();

      Set<Tile> neighbors = getNeighbors(currentTile);

      for (Tile tile : neighbors) {
        // For tiles we have not visited yet, we will queue them and visit them
        // next
        if (!visitedTiles.contains(tile) && !(tile.getTileBehavior() instanceof InaccessibleTileBehavior)) {
          visitedTiles.add(tile);
          queue.offer(tile);
        }
      }
    }
  }

  // HEIGHT == ROW == Y, WIDTH == COLUMN == X
  private Set<Tile> getNeighbors(Tile tile) {
    Set<Tile> validNeighbors = new HashSet<Tile>();
    // get the cardinal adjacents
    // remember not to index out of bound
    
    int col = tile.getCoordinate().getXCoordinate(); 
    int row = tile.getCoordinate().getYCoordinate(); 

    // All the conditional checks below are to verify that the neighbor isInBounds
    // row + 1, going down, [0, height - 1] are valid
    if (row + 1 <= worldMap.length - 1) {
      validNeighbors.add(worldMap[row+1][col]);
    }
    // row - 1, going up
    if (row - 1 >= 0) {
      validNeighbors.add(worldMap[row-1][col]);
    }
    // col + 1, going right, [0, width - 1]
    if (col + 1 <= worldMap[0].length - 1) {
      validNeighbors.add(worldMap[row][col+1]);
    }
    // col - 1, going left
    if (col - 1 >= 0) {
      validNeighbors.add(worldMap[row][col-1]);
    }

    // System.out.println("The neighbors for " + tile.getCoordinate() + " are: " +
    // validNeighbors.toString());

    return validNeighbors;
  }
}
