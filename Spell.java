/*
 * Spell.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Class containing behavior and states of Spell objects
 */

public class Spell extends Item{
  protected int damage;
  protected int manaCost;
  protected SpellType spellType;
  /*
   * Ex. of enemy attributes affected
   * damage, defense, dodge chance
   */
  protected int remainingUses;
  protected int maxNumOfUses;

  /*
   * Ice spell: reduces the damage of the target
• Fire spell: reduces the defense of the target
• Lightning spell: reduces the dodge chance of the target
   */

  public Spell(String name, int cost, int requiredLevel, int damage, int manaCost, SpellType spellType, int maxNumOfUses) {
    super(name, cost, requiredLevel);
    setDamage(damage);
    setManaCost(manaCost);
    this.spellType = spellType;
    setRemainingUses(maxNumOfUses);
    this.maxNumOfUses = maxNumOfUses;
  }

  public Spell(Spell spell) {
    this(spell.name, spell.buyPrice, spell.requiredLevel, spell.damage, spell.manaCost, spell.spellType, spell.maxNumOfUses);
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

  // manaCost
  public void setManaCost(int manaCost) {
    if (manaCost < 0) {
      throw new IllegalArgumentException("Input to setManaCost cannot be negative.");
    }
    this.manaCost = manaCost;
  }
  public int getManaCost() {
    return this.manaCost;
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

  // spellType
  public void setSpellType(SpellType spellType) {
    if (spellType == null) {
      throw new IllegalArgumentException("Input to setSpellType cannot be null.");
    }
    this.spellType = spellType;
  }
  public SpellType getSpellType() {
    return this.spellType;
  }

  @Override
  public String toString() {
    String returnStr = "Name: " + Colors.ANSI_YELLOW + this.name + Colors.ANSI_RESET + "\n" +
                      "Buy Price: " + this.buyPrice + "\n" +
                      "Sell Price: " + this.sellPrice + "\n" +
                      "Required Level: " + this.requiredLevel + "\n" +
                      "Damage: " + this.damage + "\n" +
                      "Mana Cost: " + this.manaCost + "\n" +
                      "Spell Type: " + this.spellType + "\n" +
                      "Number of Uses Remaining: " + this.remainingUses + "/" + this.maxNumOfUses + "\n";
    return returnStr;
  }
}
