public abstract class Game {
    protected IO io;
    protected CharacterFactory cfInstance;

    public Game(IO io) {
        this.io = io;
        cfInstance = CharacterFactory.getInstance();
    }

    public abstract void gameStarts();
}
