/*
 * LVKoulouTile.java
 * Ankur
 * 4/15/2024
 * 
 * LVCell responsible for boosting a hero's strength
 */

public class LVKoulouTile extends LVCell {
	// the class represent Koulou cells which extend LOVCell
    public LVKoulouTile(int row, int col) {
        super(row, col, 'K');
    }

    @Override
    public void doBoostBehavior(Hero hero) {
        super.doBoostBehavior(hero);
        hero.setTileStrength((int) (hero.getStrength()+50));
    }
}
