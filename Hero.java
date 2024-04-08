/*
 * Hero.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Class containing behavior and states of Hero objects
 */

import java.util.*;

public class Hero extends Character{
  protected int MP;
  protected int strength;
  protected int agility;
  protected int dexterity;
  protected int gold;
  protected int experience;
  protected int experienceCap;
  protected HeroType heroType;
  protected Weapon equippedWeapon;
  protected Armor equippedArmor;
  protected List<Item> inventory;

  public Hero(String name, int level, int HP, State state, int MP, int strength,
  int agility, int dexterity, int defense, int gold, int experience,
  Weapon weapon, Armor armor, HeroType heroType, List<Item> inventory) {
    super(name, level, HP, state, defense);
    setMP(MP);
    setStrength(strength);
    setAgility(agility);
    setDexterity(dexterity);
    setGold(gold);
    setExperience(experience);
    this.experienceCap = this.level * Constants.EXP_SCALE;
    this.heroType = heroType;
    setEquippedWeapon(weapon);
    setEquippedArmor(armor);
    this.inventory = inventory;
  }

  /*
   * An outside Market.buy() method may call this method of the hero
   * This method is written here to reduce the overhead of having outside
   * sources accessing these members of Hero
   */
  // public boolean purchase(Item gear) {
  //   if (this.level >= gear.requiredLevel && this.gold >= gear.buyPrice) {
  //     setGold(this.gold - gear.buyPrice);
  //     inventory.add(gear);
  //     return true;
  //   }
  //   return false;
  // }

  // ************************************************************

  public void equipArmor(Armor armor, IO io) {
    Armor prevArmor = null;

    if (this.equippedArmor != null) {
      // Put the currently equipped armor back into the inventory
      prevArmor = this.equippedArmor;
      this.inventory.add(prevArmor);
    }
    // Else, meaning the hero currently does not have any armor equipped

    this.setEquippedArmor(armor);
    this.inventory.remove(armor);
    io.displayEquipGearMsg(this, prevArmor, armor);
  }

  public void equipWeapon(Weapon weapon, IO io) {
    Weapon prevWeapon = null;

    if (this.equippedWeapon != null) {
      // Put the currently equipped weapon back into the inventory
      prevWeapon = this.equippedWeapon;
      this.inventory.add(prevWeapon);
    }
    // Else, meaning the hero currently does not have any weapon equipped

    this.setEquippedWeapon(weapon);
    this.inventory.remove(weapon);
    io.displayEquipGearMsg(this, prevWeapon, weapon);
  }

  /*
   * When a hero attacks:
   * 1.) Handle dodge
   * 2.) Typecast target
   * 3.) Calculate dmg on target and return dmg
   * 4.) Target.takeDamage(dmg)
   * atkMsg and takeDmgMsg should be taken care of in the corresponding
   * atk and takeDmg methods()
   */
  public void attack(Character target, BattleMechanics bm, BattleIO io) {
    Monster targetMonster;
    if (target instanceof Hero) {
      throw new IllegalArgumentException("Having one hero attack another " +
      "hero is not supported at the moment.");
    } else {
      targetMonster = (Monster) target;
    }

    Random random = new Random();
    // A number from 1 to 100, inclusive on both ends
    int accuracy = random.nextInt(100) + 1;

    if (accuracy >= targetMonster.getDodgeChance()) {
      // HIT
      // Attack monster with dmg = heroDmg
      int heroAtk = bm.calculateHeroAtk(this);
      int heroDmg = bm.calculateHeroDmg(heroAtk, targetMonster);
      if (heroDmg <= 0) {
        heroDmg = 1; // by default only deal 1 dmg
      }
      // X has attacked Y for #$! damage
      io.displayCharacterAtkMsg(this, targetMonster, heroDmg);

      // Y has #$! HP left.
      targetMonster.takeDamage(heroDmg, io);

    } else {
      // MISS, then display dodge message
      io.displayDodgeMsg(this, targetMonster, true);
    }
  }

  /*
   * The use of a potion increases the skills of a hero, we also 
   * decrement its number of remaining uses
   */
  public void usePotion(Potion potion, IO io) {
    // The use of potion is refactored to BattleMechanics
    applyPotionEffectsToHero(potion, this);

    // X used the potion @#$! and, recoverd 100 HP, increased 100 strength, etc...
    io.displayPotionUseMsg(this, potion);  
    
    potion.setRemainingUses(potion.getRemainingUses() - 1);
    int remainingUses = potion.getRemainingUses();
    if (remainingUses == 0) {
      this.inventory.remove(potion);
    }
    // NEED to output message regarding uses for spell
    io.displayConsumableRemainingUses(potion, remainingUses);
  }

  public void castSpell(Spell spell, Character target, BattleMechanics bm, BattleIO io) {
    Monster targetMonster;
    if (target instanceof Hero) {
      throw new IllegalArgumentException("Having one hero cast a spell on another " +
      "hero is not supported at the moment.");
    } else {
      targetMonster = (Monster) target;
    }

    // Despite of HIT or MISS, the spell is being cast
    /*
    * Decrement the num of uses for the spell, and remove it from
    * the hero's inventory if numOfRemainingUses == 0
    */
    spell.setRemainingUses(spell.getRemainingUses() - 1);
    int remainingUses = spell.getRemainingUses();
    if (remainingUses == 0) {
      this.inventory.remove(spell); // checks memory address at the moment
    }
    // NEED to output message regarding uses for spell
    io.displayConsumableRemainingUses(spell, remainingUses);


    Random random = new Random();
    // A number from 1 to 100, inclusive on both ends
    int accuracy = random.nextInt(100) + 1;

    if (accuracy >= targetMonster.getDodgeChance()) {
      // HIT
      // Cast spell on monster with dmg = heroDmg
      int heroSpell = bm.calculateHeroSpell(spell, this);
      int heroDmg = bm.calculateHeroDmg(heroSpell, targetMonster);
      // X cast a spell on Y for #$! damage
      io.displayCharacterSpellMsg(this, targetMonster, heroDmg, spell);

      // Y has #$! HP left.
      targetMonster.takeDamage(heroDmg, io);

      bm.applySpellEffectsToMonster(spell, targetMonster);
      io.displaySpellEffectMsg(spell, targetMonster);

    } else {
      // MISS, then display dodge message
      // isAttack is false to indicate the target dodged a spell
      io.displaySpellUsageMsg(this, spell, targetMonster);
      io.displayDodgeMsg(this, targetMonster, false);
    }
  }

  /*
   * ************************************************************
   * When we increase the experience of a hero, we would check if it has
   * reached its expCap
   * 
   * If the hero has reached its expCap, then we would adjust the exp and
   * level up
   */
  public void incExp(int expIncAmt, BattleIO io) {
    this.setExperience(this.experience + expIncAmt);
    // X has gained Y exp.
    io.displayExpIncMsg(this, expIncAmt);

    if (this.experience >= experienceCap) {
      this.setExperience(this.experience - experienceCap); // Adjust exp
      lvlUp(io);
    }
  }
  // Let the new expCap be set in lvlUp, as there may be situations where
  // we simply want to level up but keep the exp the same
  public void lvlUp(BattleIO io) {
    this.setLevel(this.level + 1); // Inc lvl
    // X has leveled up and is now lvl.Y
    io.displayLvlUpMsg(this);

    this.setExperienceCap(this.level * Constants.EXP_SCALE); // Inc expCap
    // When we lvlUp, we would also need to inc the skills of the hero
    updateSkillsOnLvlUp();
  }
  // When we lvlUp, we would also need to inc the skills of the hero
  private void updateSkillsOnLvlUp() {
    this.setHP(this.level * Constants.HP_SCALE);
    this.setMP((int) (this.MP * 1.1)); // Truncating the fractional part

    double aglScaleFactor = 1.05;
    double strScaleFactor = 1.05;
    double dexScaleFactor = 1.05;

    switch (this.heroType) {
      case PALADIN:
        strScaleFactor += 0.05;
        dexScaleFactor += 0.05;
        break;
      case SORCERER:
        dexScaleFactor += 0.05;
        aglScaleFactor += 0.05;
        break;
      case WARRIOR:
        strScaleFactor += 0.05;
        aglScaleFactor += 0.05;
        break;
      default:
        break;
    }
    this.setStrength((int)(this.strength * strScaleFactor));
    this.setAgility((int)(this.agility * aglScaleFactor));
    this.setDexterity((int)(this.dexterity * dexScaleFactor));
  }

    /*
   * This method only increases the attributes of the hero given the use of 
   * a potion
   */
  public void applyPotionEffectsToHero(Potion potion, Hero hero) {
    int potionAttributeInc = potion.getAttributeIncrease();

    for (Attribute attribute : potion.getAttributesAffected()) {

      switch (attribute) {
        case HEALTH:
          hero.setHP(hero.getHP() + potionAttributeInc);
          break;
        case MANA:
          hero.setMP(hero.getMP() + potionAttributeInc);
          break;
        case STRENGTH:
          hero.setStrength(hero.getStrength() + potionAttributeInc);
          break;
        case AGILITY:
          hero.setAgility(hero.getAgility() + potionAttributeInc);
          break;
        case DEXTERITY:
          hero.setDexterity(hero.getDexterity() + potionAttributeInc);
          break;
        case DEFENSE:
          hero.setDefense(hero.getDefense() + potionAttributeInc);
          break;
        case DAMAGE:
        case DODGE_CHANCE:
        default:
          throw new IllegalArgumentException("The attribute of " + potion.getName() +
          " is " + attribute + ", which is not currently supported.");
      }
    }
  }

  // ************************************************************

  /*
   * Accessor and Mutator methods
   */

  // experience cap
  public void setExperienceCap(int expCap) {
    if (expCap < 0) {
      throw new IllegalArgumentException("Input to setExperienceCap is: " +
      expCap + ", and expCap cannot be < 0");
    }
    this.experienceCap = expCap;
  }

  // MP
  public void setMP(int MP) {
    // Take into consideration of the edge case of HP possibly going below 0
    if (MP < 0) {
      MP = 0;
    }
    this.MP = MP;
  }
  public int getMP() {
    return this.MP;
  } 

  // STRENGTH
  public void setStrength(int strength) {
    if (strength < 0) {
      throw new IllegalArgumentException("Input to setStrength is: " +
      strength + ", and strength cannot be < 0");
    }
    this.strength = strength;
  }
  public int getStrength() {
    return this.strength;
  } 

  // AGILITY
  public void setAgility(int agility) {
    if (agility < 0) {
      throw new IllegalArgumentException("Input to setAgility is: " +
      agility + ", and agility cannot be < 0");
    }
    this.agility = agility;
  }
  public int getAgility() {
    return this.agility;
  } 

  // STRENGTH
  public void setDexterity(int dexterity) {
    if (dexterity < 0) {
      throw new IllegalArgumentException("Input to setDexterity is: " +
      dexterity + ", and dexterity cannot be < 0");
    }
    this.dexterity = dexterity;
  }
  public int getDexterity() {
    return this.dexterity;
  } 

  // GOLD
  public void setGold(int gold) {
    if (gold < 0) {
      throw new IllegalArgumentException("Input to setGold is: " +
      gold + ", and gold cannot be < 0");
    }
    this.gold = gold;
  }
  public int getGold() {
    return this.gold;
  }

  // EXP
  public void setExperience(int experience) {
    if (experience < 0) {
      throw new IllegalArgumentException("Input to setExperience is: " +
      experience + ", and experience cannot be < 0");
    }
    this.experience = experience;
  }
  public int getExperience() {
    return this.experience;
  }

  // WEAPON
  public void setEquippedWeapon(Weapon weapon) {
    this.equippedWeapon = weapon;
  }
  public Weapon getEquippedWeapon() {
    return this.equippedWeapon;
  }

  // ARMOR
  public void setEquippedArmor(Armor armor) {
    this.equippedArmor = armor;
  }
  public Armor getEquipppedArmor() {
    return this.equippedArmor;
  }

  public List<Item> getInventory() {
    return this.inventory;
  }

  @Override
  public String toString() {
    String returnStr = "Name: " + Colors.ANSI_GREEN + this.name + Colors.ANSI_RESET + "\n" +
                      "Level: " + this.level + "\n" +
                      "State: " + this.state + "\n" +
                      "HP: " + this.HP + "\n" +
                      "MP: " + this.MP + "\n" +
                      "Strength: " + this.strength + "\n" +
                      "Agility: " + this.agility + "\n" +
                      "Dexterity: " + this.dexterity + "\n" +
                      "Gold: " + this.gold + "\n" +
                      "Experience: " + this.experience + "/" + this.experienceCap + "\n" +
                      "Hero Type: " + this.heroType + "\n" +
                      "Currently Equipped Weapon: " + this.equippedWeapon + "\n" +
                      "Currently Equipped Armor: " + this.equippedArmor + "\n" +
                      "Inventory: " + this.inventory + "\n";
    return returnStr;
  }
  
  // Overload
  public String toString(Battle battle) {
    String returnStr = "\nName: " + Colors.ANSI_GREEN + this.name + Colors.ANSI_RESET + "\n" +
      "Level: " + this.level + "\n" +
      "HP: " + this.HP + "\n" +
      "MP: " + this.MP + "\n" +
      "Currently Equipped Weapon: " + this.equippedWeapon + "\n" +
      "Currently Equipped Armor: " + this.equippedArmor + "\n";

    return returnStr;
  }

  // Overload
  public String toString(World world) {
    String returnStr = "\nName: " + Colors.ANSI_GREEN + this.name + Colors.ANSI_RESET + "\n" +
      "Level: " + this.level + "\n" +
      "HP: " + this.HP + "\n" +
      "MP: " + this.MP + "\n" +
      "Current Exp: " + this.experience + "/" + this.experienceCap + "\n" +
      "Gold: " + this.gold + "\n" +
      "Strength: " + this.strength + "\n" +
      "Agility: " + this.agility + "\n" +
      "Dexterity: " + this.dexterity + "\n";

    return returnStr;
  }
}