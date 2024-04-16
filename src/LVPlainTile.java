/*
 * LVPlainTile.java
 * Ankur
 * 4/15/2024
 * 
 * LVCell that does nothing but simply resets the boost of the hero
 */

public class LVPlainTile extends LVCell {
	// the class represent plain cells which extend LOVCell
    public LVPlainTile(int row, int col) {
        super(row, col, 'P');
    }

    @Override
    public void doBoostBehavior(Hero hero) {
        super.doBoostBehavior(hero);
    }
}