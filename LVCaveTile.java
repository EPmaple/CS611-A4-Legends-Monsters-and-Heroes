public class LVCaveTile extends LVCell{
	// the class represent cave cells which extend LOVCell
    public LVCaveTile(int row, int col) {
        super(row, col, 'C');

    }

    @Override
    public void doBoostBehavior(Hero hero) {
        super.doBoostBehavior(hero);
        hero.setTileAgility((int) (hero.getAgility()+50));
    }
}