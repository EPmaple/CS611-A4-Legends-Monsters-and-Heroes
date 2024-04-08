/*
 * Armor.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Class used for instantiating Armor objects
 */

public class Armor extends Item{
  protected int damageReductionValue;

  public Armor(String name, int cost, int requiredLevel, int damageReductionValue) {
    super(name, cost, requiredLevel);
    setDamageReductionValue(damageReductionValue);
  }

  public Armor(Armor armor) {
    // super(armor);
    // this.damageReductionValue = 
    this(armor.name, armor.buyPrice, armor.requiredLevel, armor.damageReductionValue);
  }

  // damageReductionValue
  public void setDamageReductionValue(int damageReductionValue) {
    // At least for now
    if (damageReductionValue < 0) {
      throw new IllegalArgumentException("Input to setDamageReductionValue" +
       "cannot be negative.");
    }
    this.damageReductionValue = damageReductionValue;
  }
  public int getDamageReductionValue() {
    return this.damageReductionValue;
  }

  /*
   * Only return a String, and if formatting is needed, let other methods
   * parse this string and do the formatting
   */
  @Override
  public String toString() {
    String returnStr = "Name: " + Colors.ANSI_YELLOW + this.name + Colors.ANSI_RESET + "\n" +
                       "Buy Price: " + this.buyPrice + "\n" +
                       "Sell Price: " + this.sellPrice + "\n" +
                       "Required Level: " + this.requiredLevel + "\n" +
                       "Damage Reduction Value: " + this.damageReductionValue + "\n";
    return returnStr;
  }
}
