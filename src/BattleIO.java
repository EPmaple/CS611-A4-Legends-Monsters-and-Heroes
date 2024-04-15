/*
 * BattleIO.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * a subclass of IO filled with specific queries and displays related to Battle
 */

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class BattleIO extends IO{
  private static BattleIO instance;

  private BattleIO() {

  }

  public static BattleIO getInstance() {
    if (instance == null) {
      instance = new BattleIO();
    }
    return instance;
  }

  public void displayEndOfRoundMsg() {
    String msg = "A round has ended. Conscious heroes have regained some of their HP and MP.";
    displayMsg(msg);
  }

  public void displayCharacterRevivedMsg(Character character) {
    String msg = character.getName() + " has been revived.";
    displayMsg(msg);
  }

  public void displayGoldGainMsg(Hero hero, int goldGain) {
    String msg = hero.getName() + " has gained " + Colors.ANSI_YELLOW +
    goldGain + Colors.ANSI_RESET + " gold.";
    displayMsg(msg);
  }

  public void displayExpIncMsg(Hero hero, int expIncAmt) {
    String msg = hero.getName() + " has gained " + Colors.ANSI_YELLOW 
    + expIncAmt + Colors.ANSI_RESET + " exp.";
    displayMsg(msg);
  }

  public void displayLvlUpMsg(Hero hero) {
    String msg = hero.getName() + " has leveled up and is now lvl." + 
    Colors.ANSI_YELLOW + hero.getLevel() + Colors.ANSI_RESET;
    displayMsg(msg);
  }

  public void displayBattleVictoryMsg() {
    String msg = Colors.ANSI_YELLOW + "Victory!" + Colors.ANSI_RESET;
    displayMsg(msg);
  }

  public void displaySpellEffectMsg(Spell spell, Monster monster) {
    String msg = spell.getName() + " caused " + monster.getName() + "'s ";

    switch (spell.getSpellType()) {
      case ICE:
        msg += "damage to decrease to " + monster.getDamage();
        break;
      case FIRE:
        msg += "defense to decrease to " + monster.getDefense();
        break;
      case LIGHTNING:
        msg += "dodge chance to decrease to " + monster.getDodgeChance();
        break;
      default:
        throw new IllegalArgumentException("The spellType of " + spell.getName() +
        " is " + spell.getSpellType() + ", which is not supported.");
    }

    msg += ".";

    displayMsg(msg);
  }

  public void displaySpellUsageMsg(Character spellCaster, Spell spell, Character target) {
    String msg = spellCaster.getName() + " cast " + spell.getName() + " on "+
    target.getName() + ".";
    displayMsg(msg);
  }

  public void displayDodgeMsg(Character attacker, Character defender, boolean isAttack) {
    String msg = defender.getName() + " has dodged the ";

    // true means attack
    if (isAttack == true) {
      msg += "attack from ";
    } else { // false means spell
      msg += "spell from ";
    }

    msg += attacker.getName() + ".\n";

    displayMsg(msg);
  }

  public void displayDamageTakenMsg(Character character) {
    String msg = character.getName() + " has " + character.getHP() + " HP left. ";

    if (character.getState().equals(State.FAINTED)) {
      msg += character.getName() + " has fainted.";
    }
    // msg += "\n";
    displayMsg(msg);
  }

  public void displayCharacterAtkMsg(Character attacker, Character defender, int dmg) {
    String msg = attacker.getName() + " attacked " + defender.getName() + " for " + 
    dmg + ".";

    displayMsg(msg);
  }

  public void displayCharacterSpellMsg(Character attacker, Character defender, int dmg, Spell spell) {
    String msg = attacker.getName() + " cast " + spell.getName() + " on " +
    defender.getName() + " for " + dmg + " damage.";

    displayMsg(msg);
  }

  public void displayBattleTutorial() {
    String msg = "The following can be achieved by using a hero's inventory: "
    +"allows a hero to put on another piece of weapon or armor, to use a "
    +"spell on an enemy target, and to use a potion.";

    displayMsg(msg);
  }

  // ************************************************************

  public String queryForUserActionInBattle(Hero hero) {
    displayMsg("It is " + hero.getName() + "'s turn. ");

    String msg = "Enter A/a for Attack, U/u to use your inventory, " +
    "I/i to show information about heroes and monsters, T/t to show tutorial:";
    List<String> commands = new ArrayList<String>();
    commands.addAll(Arrays.asList("A", "U", "I", "T"));
    String action = queryString(msg, commands);

    return action;
  }

  public Monster queryForMonsterSelection(List<Monster> monsters) {
    /*
     * first, iterate through all monsters and get the corresponding indexes
     * for those monsters that are still conscious; we will also construct a 
     * prompt in the mean time
     */

    List<Integer> monsterIndexes = new ArrayList<Integer>();

    String msg = "Please enter the number for the monster you want to select" +
     ", or enter Z/z to go back to the previous step: \n    ";

    for (int i = 0; i < monsters.size(); i++) {
      Monster monster = monsters.get(i);
      if (monster.getState().equals(State.CONSCIOUS)) {
        monsterIndexes.add(i);
        msg += "[" + i + "] " + monster.getName() + " ";
      }
    }

    Integer monsterSelectedIndex = queryIntWithPrior(msg, monsterIndexes);
    if (monsterSelectedIndex == null) {
      return null;
    }

    return monsters.get(monsterSelectedIndex);
  }
}
