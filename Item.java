/*
 * Item.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Abstract class that Armor, Weapon, Potion, and Spell extends from,
 * holds the general behavior and data members necessary for an Item
 */

public abstract class Item {
  protected String name;
  protected int buyPrice;
  protected int sellPrice;
  // protected int cost;
  protected int requiredLevel;

  public Item(String name, int cost, int requiredLevel) {
    setName(name);
    setBuyPrice(cost);
    setSellPrice((int) Math.ceil(cost / 2.0)); // Use Math.ceil() to round up
    setRequiredLevel(requiredLevel);
  }

  // name 
  public void setName(String name) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Input to setName cannot be the empty string.");
    }
    this.name = name;
  }
  public String getName() {
    return this.name;
  }

  // buyPrice
  public void setBuyPrice(int buyPrice) {
    if (buyPrice < 0) {
      throw new IllegalArgumentException("Input to setBuyPrice cannot be negative.");
    }
    this.buyPrice = buyPrice;
  }
  public int getBuyPrice() {
    return this.buyPrice;
  }

  // sellPrice
  public void setSellPrice(int sellPrice) {
    if (sellPrice < 0) {
      throw new IllegalArgumentException("Input to setSellPrice cannot be negative.");
    }
    this.sellPrice = sellPrice;
  }
  public int getSellPrice() {
    return this.sellPrice;
  }

  // requiredLevel
  public void setRequiredLevel(int requiredLevel) {
    if (requiredLevel < 0) {
      throw new IllegalArgumentException("Input to setRequiredLevel cannot be negative.");
    }
    this.requiredLevel = requiredLevel;
  }
  public int getRequiredLevel() {
    return this.requiredLevel;
  }

  /*
   * Should I use toString() for Gear and thus make each Gear responsible
   * for its own toString(), or should I have some other classes to do this?
   * 
   * If I use some other classes to do this
   */
  public abstract String toString();
}
