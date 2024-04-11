/*
 * Monster.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Class containing behavior and states of Monster objects
 */

import java.util.*;

public class Monster extends Character{
  protected int damage;
  protected int dodgeChance;
  protected MonsterType monsterType;

  /*
   *  Dragons have increased base damage.
• Exoskeletons have increased defense.
• Spirits have increased dodge ability.
   */

  public Monster(String name, int level, int HP, State state, int damage, int defense, int dodgeChance, MonsterType monsterType) {
    super(name, level, HP, state, defense);
    setDamage(damage);
    setDodgeChance(dodgeChance);
    this.monsterType = monsterType;
  }

  public Monster(Monster monster) {
    this(monster.name, monster.level, monster.HP, monster.state, monster.damage, 
    monster.defense, monster.dodgeChance, monster.monsterType);
  }

  // ***************************************************************

  public void attack(Character target, BattleMechanics bm, BattleIO io) {
    Hero targetHero;
    if (target instanceof Monster) {
      throw new IllegalArgumentException("Having one monster attack another " +
      "monster is not supported at the moment.");
    } else {
      targetHero = (Hero) target;
    }

    // Let's be lazy in this case
    Random random = new Random();
    int accuracy = random.nextInt(100) + 1; // 0 to 100 inclusive

    // Currently HERO_DODGE_CHANCE_SCALE = 0.01, and by casting to int, it 
    // rounds down
    int heroDodgeChance = (int) (targetHero.getAgility() * Constants.HERO_DODGE_CHANCE_SCALE);

    if (accuracy >= heroDodgeChance) {
      // HIT
      int monsterDmg = bm.calculateMonsterDmg(this, targetHero);
      if (monsterDmg <= 0) {
        monsterDmg = 1;
      }
      // X has attacked Y for #$! damage
      io.displayCharacterAtkMsg(this, targetHero, monsterDmg);

      // Y has #$! HP left.
      targetHero.takeDamage(monsterDmg, io);

    } else {
      // MISS
      io.displayDodgeMsg(this, targetHero, true);
    }
  }


  public void castSpell(Spell spell, Character target, BattleMechanics bm, BattleIO io) {
    throw new IllegalArgumentException("The feature for monsters to cast a spell " +
    "is not supported at this moment.");
  }

  // ***************************************************************

  // damage
  public void setDamage(int damage) {
    // Take into consideration of the edge case: going below 0
    if (damage < 0) {
      damage = 0;
    }
    this.damage = damage;
  }
  public int getDamage() {
    return this.damage;
  } 

  // DodgeChance
  public void setDodgeChance(int dodgeChance) {
    // Take into consideration of the edge case: going below 0
    if (dodgeChance < 0) {
      dodgeChance = 0;
    }
    this.dodgeChance = dodgeChance;
  }
  public int getDodgeChance() {
    return this.dodgeChance;
  } 

  @Override
  public String toString() {
    String returnStr = "\nName: " + Colors.ANSI_RED + this.name + Colors.ANSI_RESET + "\n" +
                      "Level: " + this.level + "\n" +
                      "State: " + this.state + "\n" +
                      "HP: " + this.HP + "\n" +
                      "Damage: " + this.damage + "\n" +
                      "Defense: " + this.defense + "\n" +
                      "Dodge Chance: " + this.dodgeChance + "\n" +
                      "Monster Type: " + this.monsterType + "\n";
    return returnStr;
  }

  // Overload toString() method to print different info about Monster
  // at different contexts
  public String toString(Battle battle) {
    String returnStr = "\nName: " + Colors.ANSI_RED + this.name + Colors.ANSI_RESET + "\n" +
                      "Level: " + this.level + "\n" +
                      "HP: " + this.HP + "\n" +
                      "Damage: " + this.damage + "\n" +
                      "Defense: " + this.defense + "\n" +
                      "Dodge Chance: " + this.dodgeChance + "\n";
    return returnStr;
  }
}
