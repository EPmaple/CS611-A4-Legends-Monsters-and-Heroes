/*
 * World.java
 * Tony Cen Cen
 * 4/15/2024
 * 
 * A simple interface that serves as a contract on what methods are 
 * essential in a new game world
 */

public interface World {
    public abstract void start();
    public abstract Boolean useInventory(Hero hero);
}
