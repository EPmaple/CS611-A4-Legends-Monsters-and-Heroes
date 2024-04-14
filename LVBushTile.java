public class LVBushTile extends LVCell{
	// the class represent bush cells which extend LOVCell
    public LVBushTile(int row, int col) {
        super(row, col, 'B');
    }

    @Override
    public void doBoostBehavior(Hero hero) {
        super.doBoostBehavior(hero);
        hero.setTileDexterity((int) (hero.getDexterity()+50));
    }

}
