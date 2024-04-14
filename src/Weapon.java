/*
 * Weapon.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Class containing behavior and states of Weapon objects
 */

public class Weapon extends Item{
  protected int damage;
  protected int requiredHands;

  public Weapon(String name, int cost, int requiredLevel, int damage, int requiredHands) {
    super(name, cost, requiredLevel);
    this.damage = damage;
    this.requiredHands = requiredHands;
  }

  public Weapon(Weapon weapon) {
    this(weapon.name, weapon.buyPrice, weapon.requiredLevel, weapon.damage, weapon.requiredHands);
  }

  // damage
  public void setDamage(int damage) {
    if (damage < 0) {
      throw new IllegalArgumentException("Input to setDamage cannot be negative.");
    }
    this.damage = damage;
  }
  public int getDamage() {
    return this.damage;
  }

  // requiredHands
  public void setRequiredHands(int requiredHands) {
    if (requiredHands < 0) {
      throw new IllegalArgumentException("Input to setRequiredHands cannot be negative.");
    }
    this.requiredHands = requiredHands;
  }
  public int getRequiredHands() {
    return this.requiredHands;
  }

  @Override
  public String toString() {
    String returnStr = "Name: " + Colors.ANSI_YELLOW + this.name + Colors.ANSI_RESET + "\n" +
                       "Buy Price: " + this.buyPrice + "\n" +
                       "Sell Price: " + this.sellPrice + "\n" +
                       "Required Level: " + this.requiredLevel + "\n" +
                       "Damage: " + this.damage + "\n" +
                       "Required Hands: " + this.requiredHands + "\n";
    return returnStr;
  }
}