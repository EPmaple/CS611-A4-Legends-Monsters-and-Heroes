/*
 * Potion.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Class containing behavior and states of Potion objects
 */

import java.util.Set;

public class Potion extends Item{
  protected int attributeIncrease;
  /*
   * Ex. of hero attribute affected 
   * All Health/Mana/Strength/Dexterity/Defense/Agility
   */
  protected Set<Attribute> attributesAffected;
  protected int remainingUses;
  protected int maxNumOfUses;

  public Potion(String name, int cost, int requiredLevel, int attributeIncrease, Set<Attribute> attributesAffected, int maxNumOfUses) {
    super(name, cost, requiredLevel);
    setAttributeIncrease(attributeIncrease);
    setAttributesAffected(attributesAffected);
    setRemainingUses(maxNumOfUses);
    this.maxNumOfUses = maxNumOfUses;
  }

  public Potion(Potion potion) {
    this(potion.name, potion.buyPrice, potion.requiredLevel, potion.attributeIncrease, potion.attributesAffected, potion.maxNumOfUses);
  }

  // attributeIncrease
  public void setAttributeIncrease(int attributeIncrease) {
    if (attributeIncrease < 0) {
      throw new IllegalArgumentException("Input to setAttributeIncrease cannot be negative.");
    }
    this.attributeIncrease = attributeIncrease;
  }
  public int getAttributeIncrease() {
    return this.attributeIncrease;
  }

  // attributesAffected
  public void setAttributesAffected(Set<Attribute> attributesAffected) {
    if (attributesAffected.size() < 1) {
      throw new IllegalArgumentException("Input to setAttributesAffected " +
      "must contain at least one attribute");
    }
    this.attributesAffected = attributesAffected;
  }
  public Set<Attribute> getAttributesAffected() {
    return this.attributesAffected;
  }

  // remainingUses
  public void setRemainingUses(int remainingUses) {
    if (remainingUses < 0) {
      throw new IllegalArgumentException("Input to setRemainingUses cannot be negative.");
    }
    this.remainingUses = remainingUses;
  }
  public int getRemainingUses() {
    return this.remainingUses;
  }

  /*
   * Some other methods that may need to be implemented
   * 
   * Potion.use(), Potion.consume()??
   * to use the Potion for the hero, and to decrease the 
   * number of remaining uses
   */

  @Override
  public String toString() {
    String returnStr = "Name: " + Colors.ANSI_YELLOW + this.name + Colors.ANSI_RESET + "\n" +
                       "Buy Price: " + this.buyPrice + "\n" +
                       "Sell Price: " + this.sellPrice + "\n" +
                       "Required Level: " + this.requiredLevel + "\n" +
                       "Attribute Increase: " + this.attributeIncrease + "\n" + 
                       "Attributes Affected: " + this.attributesAffected + "\n" +
                       "Number of Uses Remaining: " + this.remainingUses + "/" + this.maxNumOfUses + "\n";
    return returnStr;
  }
}
