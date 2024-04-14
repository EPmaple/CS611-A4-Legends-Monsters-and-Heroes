
public interface LVBoostStrategy {
	//interface for cells which can doBoostBehavior and resetBoost
    void doBoostBehavior(Hero hero);

    default void resetBoost(Hero hero){
        hero.setTileStrength(0);
        hero.setTileDexterity(0);
        hero.setTileAgility(0);
    }
}