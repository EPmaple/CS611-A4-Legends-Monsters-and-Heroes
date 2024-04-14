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