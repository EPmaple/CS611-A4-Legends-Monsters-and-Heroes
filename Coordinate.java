/*
 * Coordinate.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * This class is used to implement Coordinate for each Tile
 */

public class Coordinate {
  private int x;
  private int y;

  public Coordinate(int x, int y) {
    setXCoordinate(x);
    setYCoordinate(y);
  }

  public void setXCoordinate(int x) {
    if (x < 0) {
      throw new IllegalArgumentException("Negative coordinate values are "+
      "currently not supported.");
    }
    this.x = x;
  }
  public int getXCoordinate() {
    return this.x;
  }

  public void setYCoordinate(int y) {
    if (y < 0) {
      throw new IllegalArgumentException("Negative coordinate values are "+
      "currently not supported.");
    }
    this.y = y;
  }
  public int getYCoordinate() {
    return this.y;
  }

  @Override
  public boolean equals(Object obj) {
      // Check if the object is compared with itself
      if (this == obj) {
          return true;
      }
      // Check if the object is null
      if (obj == null) {
          return false;
      }
      // Check if the object is an instance of Coordinate
      if (getClass() != obj.getClass()) {
          return false;
      }
      // Cast the object to Coordinate
      Coordinate other = (Coordinate) obj;
      // Check if the x and y coordinates of both objects are equal
      return this.x == other.x && this.y == other.y;
  }

  @Override
  public String toString() {
    return "(" + this.x + ", " + this.y + ")";
  }
}
