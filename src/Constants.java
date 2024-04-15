/*
 * Constants.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * This class provides all global constants for any game.
 */

public class Constants {
  public static final int MAX_NUM_OF_USES = 3;
  public static final int EXP_SCALE = 10;
  public static final int INITIAL_HERO_LEVEL = 1;
  public static final int HP_SCALE = 100;
  public static final int GOLD_SCALE = 100;
  public static final int FIST_DMG = 100;
  public static final int BATTLE_THRESHOLD = 3;
  public static final int DEFAULT_HERO_DEFENSE = 50;
  public static final double SKILL_LOSS_FACTOR = 0.1;
  public static final String QUIT = "Q";
  public static final double HERO_ATK_SCALE = 0.5;
  public static final int SPELL_NORMALIZATION_FACTOR = 10000;
  public static final double HERO_DODGE_CHANCE_SCALE = 0.01;
  public static final double MONSTER_DODGE_CHANCE_SCALE = 0.01;
  public static final int NUM_OF_ITEM_TYPES = 4;
  public static final int EXP_GAIN_SCALE = 2;
  public static final String PRIOR = "Z";
  public static final int DEFAULT_WORLD_WIDTH = 8;
  public static final int DEFAULT_WORLD_HEIGHT = 8;
  public static final int MIN_WORLD_WIDTH = 3;
  public static final int MIN_WORLD_HEIGHT = 3;
  public static final int MIN_NUM_OF_HEROES = 1;
  public static final int MAX_NUM_OF_HEROES = 3;
  public static final String GOLD_GAIN = "goldGain";
  public static final String GOLD_SPENT = "goldSpent";
  public static final double DEFAULT_SKILL_SCALE_FACTOR = 1.05;
  public static final double SPECIAL_SKILL_SCALE_FACTOR = 1.10;
  public static final int LV_BOTTOMMOST_ROW = 7;
  public static final int LV_TOPMOST_ROW = 0;

  public static final String DEFAULT_LVCELL_HEROPOSITION = "  ";
  public static final String DEFAULT_LVCELL_MONSTERPOSITION = "   ";
  public static final String LVNonAccessibleCellType = "&";
  public static final int INFINITE_HP = 1000000;
  public static final int LVGAME_GOLD_SCALE = 500;
}
