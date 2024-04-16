/*
 * LVNonAccessibleTile.java
 * Ankur
 * 4/15/2024
 * 
 * LVCell representing a tile that is non-accessible
 */

public class LVNonAccessibleTile extends LVCell{
	// the class represent Inaccesible cells which extend LOVCell
    public LVNonAccessibleTile(int row, int col) {
        super(row, col, '&');
        setPositions(new String[]{Colors.ANSI_PURPLE + "X " + Colors.ANSI_RESET
        , Colors.ANSI_PURPLE + "X  " + Colors.ANSI_RESET});
    }

    @Override
    public void doBoostBehavior(Hero hero) {
        //DO nothing for now...
    }
}