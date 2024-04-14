public class LVNonAccessibleTile extends LVCell{
	// the class represent Inaccesible cells which extend LOVCell
    public LVNonAccessibleTile(int row, int col) {
        super(row, col, '&');
        setPositions(new char[]{'X','X'});
    }

    @Override
    public void doBoostBehavior(Hero hero) {
        //DO nothing for now...
    }
}