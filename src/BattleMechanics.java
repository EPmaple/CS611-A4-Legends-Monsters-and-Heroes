/*
 * BattleMechanics.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * a class responsible for all the mechanics in Battle
 */

public class BattleMechanics {
  private static BattleMechanics instance;

  private BattleMechanics() {

  }

  public static BattleMechanics getInstance() {
    if (instance == null) {
      instance = new BattleMechanics();
    }
    return instance;
  }

  /*
   * This method is only responsible for decreasing the attributes of the 
   * monster given the use of this spell
   */
  public void applySpellEffectsToMonster(Spell spell, Monster monster) {
    // Currently: 0.9 = 1 - 0.1
    double skillRetentionFactor = 1 - Constants.SKILL_LOSS_FACTOR;

    switch (spell.getSpellType()) {
      case ICE:
        monster.setDamage((int)(monster.getDamage() * skillRetentionFactor));
        break;
      case FIRE:
        monster.setDefense((int)(monster.getDefense() * skillRetentionFactor));
        break;
      case LIGHTNING:
        monster.setDodgeChance((int)(monster.getDodgeChance() * skillRetentionFactor));
        break;
      default:
        throw new IllegalArgumentException("The spellType of " + spell.getName() +
        " is " + spell.getSpellType() + ", which is not currently supported.");
    }
  }

  /*
   * 
   */

  public int calculateHeroSpell(Spell spell, Hero hero) {
    double spellDmg = spell.getDamage() + ( (hero.getDexterity()/Constants.SPELL_NORMALIZATION_FACTOR) *
     spell.getDamage() );
    return (int)(spellDmg);
  }

  public int calculateHeroAtk(Hero hero) {
    int weaponDmg;
    if (hero.getEquippedWeapon() == null) {
      weaponDmg = Constants.FIST_DMG;
    } else {
      weaponDmg = hero.getEquippedWeapon().getDamage();
    }
    double attackDmg = (hero.getStrength() + weaponDmg) * Constants.HERO_ATK_SCALE; // This scale is currently 0.5
    // System.out.println("weaponDmg: " + weaponDmg + ", (hero.getStrength() + weaponDmg): " + (hero.getStrength() + weaponDmg) + ", (int)(attackDmg): " + (int)(attackDmg) + ", hero.getStreng(): " + hero.getStrength());
    return (int)(attackDmg);
  }

  /*
   * In a series of hero attacks, the same Monster has to be called
   */
  public int calculateHeroDmg(int dmgValue, Monster monster) {
    // System.out.println("dmgValue: " + dmgValue + ", defense: " + monster.getDefense());
    return dmgValue - monster.getDefense();
  }

  /*
   * In a series of monster attacks, the same Hero has to be called
   */
  public int calculateMonsterDmg(Monster monster, Hero hero) {
    int heroDmgReduction = 0;
    if (hero.getEquipppedArmor() != null) {
      heroDmgReduction = hero.getEquipppedArmor().getDamageReductionValue();
    }
    return monster.getDamage() - heroDmgReduction - hero.getDefense();
  }
}
