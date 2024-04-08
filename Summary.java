/*
 * Summary.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Class to give a summary of the user from playing the game
 */


public class Summary {
  private static Summary instance;
  private IO io;

  private int totalGoldGain = 0;
  private int totalGoldSpent = 0;
  private int highestHeroLvl = 1;

  private Summary(IO io) {
    this.io = io;
  }

  public static Summary getInstance() {
    if (instance == null) {
      instance = new Summary(new IO());
    }
    return instance;
  }

  public void printSummary() {
    System.out.println("");
    String msg = "Total gold gain: " + Colors.ANSI_YELLOW + totalGoldGain + Colors.ANSI_RESET;
    io.displayMsg(msg);

    msg = "Total gold spent: " + Colors.ANSI_YELLOW + totalGoldSpent + Colors.ANSI_RESET;
    io.displayMsg(msg);

    msg = "Highest hero level achieved: " + Colors.ANSI_GREEN + highestHeroLvl + Colors.ANSI_RESET;
    io.displayMsg(msg);
  }

  // accessor and mutator methods

  public void setGoldGain(int goldGain) {
    if (goldGain < 0) {
      throw new IllegalArgumentException("Gold gain cannot be negative.");
    }
    this.totalGoldGain = goldGain;
  }
  public int getGoldGain() {
    return this.totalGoldGain;
  }
  public void incrementTotalGoldGain(int goldGain) {
    if (goldGain < 0) {
      throw new IllegalArgumentException("Gold gain cannot be negative.");
    }
    this.setGoldGain(totalGoldGain + goldGain);
  }

  public void setGoldSpent(int GoldSpent) {
    if (GoldSpent < 0) {
      throw new IllegalArgumentException("Gold spent cannot be negative.");
    }
    this.totalGoldSpent = GoldSpent;
  }
  public int getGoldSpent() {
    return this.totalGoldSpent;
  }
  public void incrementTotalGoldSpent(int GoldSpent) {
    if (GoldSpent < 0) {
      throw new IllegalArgumentException("Gold spent cannot be negative.");
    }
    this.setGoldSpent(totalGoldSpent + GoldSpent);
  }

  public void setHighestHeroLvl(int heroLvl) {
    if (heroLvl < 0) {
      throw new IllegalArgumentException("heroLvl cannot be negative.");
    }
    this.highestHeroLvl = heroLvl;
  }
  public int getHighestHeroLvl() {
    return this.highestHeroLvl;
  }
}
