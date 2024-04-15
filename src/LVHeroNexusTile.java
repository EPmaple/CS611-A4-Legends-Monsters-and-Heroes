import java.util.ArrayList;
import java.util.List;

public class LVHeroNexusTile extends LVCell implements TileBehavior{
	// the class represent Hero Nexus cells which extend LOVCell
    private List<Item> stock;
    public LVHeroNexusTile(int row, int col, List<Item> stock) {
        super(row, col, 'H');
        this.stock = stock;
    }

    @Override
    public void doBoostBehavior(Hero hero) {
        super.doBoostBehavior(hero);
    }

    @Override
    public void interact(List<Hero> heroes) {
        /*
         * Additional logic to have the heroes enter the market
         */

        Market market = new Market(heroes, stock, LVSummary.getInstance());
        market.beginTrade();
    }
}