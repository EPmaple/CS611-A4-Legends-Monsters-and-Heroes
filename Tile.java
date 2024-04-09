public abstract class Tile {

    protected TileBehavior tileBehavior;
    private Coordinate coordinate;


    public void setTileBehavior(TileBehavior tileBehavior) {
        if (tileBehavior == null) {
            throw new IllegalArgumentException("TileBehavior of a tile cannot "+
                    "be null.");
        }
        this.tileBehavior = tileBehavior;
    }
    public TileBehavior getTileBehavior() {
        return this.tileBehavior;
    }

    public void setCoordinate(Coordinate coordinate) {
        if (coordinate == null) {
            throw new IllegalArgumentException("Coordinate of a tile cannot "+
                    "be null.");
        }
        this.coordinate = coordinate;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    @Override
    public String toString() {
        return this.getCoordinate().toString();
    }

}
