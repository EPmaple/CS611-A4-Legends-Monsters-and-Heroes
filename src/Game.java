/*
 * Game.java
 * Tony Cen Cen
 * 4/15/2024
 * 
 * An abstract class to serve as a template to create other Game starters
 */

public abstract class Game {
    protected IO io;
    protected CharacterFactory cfInstance;

    public Game(IO io) {
        this.io = io;
        cfInstance = CharacterFactory.getInstance();
    }

    public abstract void gameStarts();
}
