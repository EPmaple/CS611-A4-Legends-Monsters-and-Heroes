import java.util.*;

public class LegendsOfValorMechanics {
  private static LegendsOfValorMechanics instance;

  private LegendsOfValorMechanics() {

  }

  public static LegendsOfValorMechanics getInstance() {
    if (instance == null) {
      instance = new LegendsOfValorMechanics();
    }
    return instance;
  }

  // ********************************************************

  public void heroesExpGain(Monster monster, List<Hero> heroes,
  LegendsOfValorIO io, LVSummary summaryInstance) {
    int monsterLvl = monster.getLevel();

    for (Hero hero : heroes) {
        hero.incExp(monsterLvl * Constants.EXP_GAIN_SCALE, io);

        // update lvl in summary
        if (hero.getLevel() > summaryInstance.getHighestHeroLvl()) {
            summaryInstance.setHighestHeroLvl(hero.getLevel());
        }
    }
  }

  public void heroesGoldGain(Monster monster, List<Hero> heroes,
  LegendsOfValorIO io, LVSummary summaryInstance) {
      int monsterLvl = monster.getLevel();

      for (Hero hero : heroes) {
          int goldGain = monsterLvl * Constants.GOLD_SCALE;
          hero.setGold(hero.getGold() + goldGain);
          // X has gained Y gold
          io.displayGoldGainMsg(hero, goldGain);
          // update gold gain in summary
          summaryInstance.incrementTotalGoldGain(goldGain);
      }
  }
}
