import java.util.List;

public class LVTile extends Tile implements TileBehavior{
    protected Hero hero;
    protected Monster monster;

    public LVTile(TileBehavior tileBehavior, Coordinate coordinate) {
        setTileBehavior(tileBehavior);
        setCoordinate(coordinate);
    }

    // Maybe we would want have input List<Hero> heroes
    public void interact(List<Hero> heroes) {
        super.tileBehavior.interact(heroes);
        /*
         * Only move the heroes onto this tile if interact returns true
         */
    }
    /*
    this class has not been finished implementing
     */
}
