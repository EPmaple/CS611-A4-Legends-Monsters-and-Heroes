/*
 * TileBehavior.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * A Java interface that implements the Strategy Pattern
 */

import java.util.*;
/*
 * Strategy pattern: The Strategy Part
 */
public interface TileBehavior {
  public void interact(List<Hero> heroes);
}
