/*
 * Battle.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Class used to instantiate Battle instance, which is responsible
 * for the battle interface in our game
 */

/*
 * This file should be mainly responsible for the mechanics of the Battle,
 * whereas the querying, formatting, and printing should be done in a separate
 * BattleIO
 */

import java.util.*;

public class Battle {
  /*
   * Maybe make this a singleton, or make an instance of it everytime we
   * go into battle
   */
  // private static Battle instance;

  protected List<Hero> heroes;
  protected List<Monster> monsters;
  protected int round = 0;
  protected BattleIO io;
  protected BattleMechanics bm;
  protected CharacterFactory cf;
  protected Summary summaryInstance;
  protected int monsterLvl;


  public Battle(List<Hero> heroes) {
    this.io = BattleIO.getInstance();
    this.bm = BattleMechanics.getInstance();
    this.cf = CharacterFactory.getInstance();
    this.summaryInstance = Summary.getInstance();
    setHeroes(heroes);
    // this.io = io;
    // this.bm = bm;
    // setHeroes(heroes);
    // setMonsters(monsters);
  }

  // public static Battle getInstance() {
  //   // BattleIO, BattleMechanics, CharacterFactory do not need to be passed in
  //   if (instance == null) {
  //     instance = new Battle();
  //   }
  //   return instance;
  // }

  // This should be the outer while loop for roundStarts and roundEnds
  // until either all heroes or all monsters are defeated
  public void battleStarts() {

    if (heroes == null) {
      throw new IllegalArgumentException("Please use Battle.setHeroes() " +
      "to set heroes in Battle before running Battle.battleStarts(), " +
      "or your List<Hero> heroes may be empty and thus is null.");
    }

    String msg = "You were unlucky with the dice roll, and now you will be "+
    "entering battle.\n";
    io.displayMsg(msg);

    monsters = generateMonsters();

    boolean hasBattleEnded = false;
    while (!hasBattleEnded) {
      hasBattleEnded = executeRound();
    }

    // Having arrived here, battle has ended, and we will have to process
    // whether the heroes have won or the monsters have won

    // Heroes won
    if (allMonstersDefeated()) {
      io.displayBattleVictoryMsg();
      heroesExpGain();
      heroesGoldGain();
      reviveFaintedHeroes();

      // Clean-ups after battle ends
      resetBattleInstance();

    } else if (allHeroesDefeated()) {
      io.displayPlayerDefeatedMsg();
      summaryInstance.printSummary();
      System.exit(0);
      // Quit the game with a summary
      /*
       * Gained X gold in total
       * Highest hero lvl is X, a HeroType, named !#%#
       */
      // io.displayPlayerDefeatedMsg();
      // System.exit(0);
    } else {
      throw new IllegalArgumentException("You have reached the else "+
      "statement of the battleStarts() method, meaning battle has ended "+ "prematurely without all heroes defeated or all monsters defeated.");
    }
  }

  private void resetBattleInstance() {
    monsters = null;
    heroes = null;
    round = 0;
  }

  private List<Monster> generateMonsters() {
    List<Monster> monsters = new ArrayList<Monster>();

    monsterLvl = Constants.INITIAL_HERO_LEVEL; // monsterLvl = 1
    // To get highest lvl of heroes
    for (Hero hero : heroes) {
      if (hero.getLevel() > monsterLvl) {
        monsterLvl = hero.getLevel();
      }
    }

    for (int i = 0; i < heroes.size(); i++) {
      Monster monster = cf.createMonster(monsterLvl);
      monsters.add(monster);
    }

    return monsters;
  }

  // Return true if the battle ended in this round
  // return false if the battle has not yet ended
  // There is no way for heroes to attack heroes, monsters to attack
  // monsters at the moment
  public boolean executeRound() {
    // print monster info
    displayMonsterInfoInBattle();
    String msg = "";

    for (Hero hero : heroes) {
      if (hero.getState().equals(State.CONSCIOUS)) {
        boolean turnConsumed = false;
        // while hero has not consumed his turn yet
        while (!turnConsumed) {
          // The option to A, U, or I, which calls the corresponding three methods
          String action = io.queryForUserActionInBattle(hero);
  
          if (action.equalsIgnoreCase("A")) {
            if (attackInBattle(hero) != null) {
              turnConsumed = true;
            }
  
          } else if (action.equalsIgnoreCase("U")) {
            if (hero.getInventory().size() == 0) {
              msg = hero.getName() + "'s inventory cannot be used " +
              "because it is currently empty.";
              io.displayMsg(msg);
            } else {
              if (useInventoryInBattle(hero) != null) {
                turnConsumed = true;
              }
            }
  
          } else if (action.equalsIgnoreCase("I")) {
            displayInfoInBattle();

          } else if (action.equalsIgnoreCase("T")) {
            io.displayBattleTutorial();

          } else {
            throw new IllegalArgumentException("An action that was not "+
            "requested was returned by queryForUserActionInBattle(): " +
            action + ".");
          }
        }

        // After one hero's turn has been consumed
        if (allMonstersDefeated()) {
          return true;
        }
      }
    }

    io.displayMsg("Now is the monsters' turn.");
    for (Monster monster : monsters) {
      if (monster.getState().equals(State.CONSCIOUS)) {
        monsterAttack(monster);
        // Check if all heroes have fainted after each monster attack
        if (allHeroesDefeated()) {
          // Battle ends when all monsters are defeated
          return true;
        }
      }
    }

    round += 1;
    // heroes that are still conscious regain 10% of their HP and MP
    endOfRoundRecovery();
    return false;
  }

  /*
   * attackInBattle
   */
  public Boolean attackInBattle(Hero hero) {
    Monster selectedMonster = io.queryForMonsterSelection(monsters);
    if (selectedMonster == null) {
      return null;
    }
    // System.out.println("Printing selected monster's defense for "+
    // "debugging purposes: " + selectedMonster.getDefense());
    hero.attack(selectedMonster, bm, io);
    return true;
  }

  /*
   * displayInfoInBattle
   */
  public void displayInfoInBattle() {
    String msg = "Below are information about your heroes: \n";
    for (Hero hero : heroes) {
      msg += hero.toString(this);
    }

    io.displayMsg(msg);

    displayMonsterInfoInBattle();
  }

  private void displayMonsterInfoInBattle() {
    String msg = "Below are information about the monsters you are facing: \n";
    for (Monster monster : monsters) {
      msg += monster.toString(this);
    }

    io.displayMsg(msg);
  }

  // Returning null for the user's want to exit to prior level
  public Boolean useInventoryInBattle(Hero hero) {
    Item selectedItem = io.queryForInventoryUsage(hero);

    if (selectedItem == null) {
      return null;
    }

    if (selectedItem instanceof Armor) {
      // Take off hero's current equipped armor and put it into the inventory
      hero.equipArmor((Armor) selectedItem, io);

    } else if (selectedItem instanceof Weapon) {
      // Equip weapon
      hero.equipWeapon((Weapon) selectedItem, io);

    } else if (selectedItem instanceof Spell) {
      Monster selectedMonster = io.queryForMonsterSelection(monsters);
      hero.castSpell((Spell) selectedItem, selectedMonster, bm, io);

    } else if (selectedItem instanceof Potion) {
      hero.usePotion((Potion) selectedItem, io);

    }
    return true;
  }

  // At the end of each round of a battle, the heroes that are still conscious
  // regain 10% of their HP and 10% of their MP
  public void endOfRoundRecovery() {
    for (Hero hero : heroes) {
      if (hero.getState().equals(State.CONSCIOUS)) {
        hero.setHP( (int)(hero.getHP() * 1.1) );
        hero.setMP( (int)(hero.getMP() * 1.1) );
      }
    }
    // Display the message that their HP and MP has been incremented
    io.displayEndOfRoundMsg();
  }

  // hero exp gain = # of monsters * 2
  public void heroesExpGain() {
    int numOfMonsters = monsters.size();

    for (Hero hero : heroes) {
      if (hero.getState().equals(State.CONSCIOUS)) {
        // Currently X * 2
        hero.incExp(numOfMonsters * Constants.EXP_GAIN_SCALE, io);

        // update lvl in summary
        if (hero.getLevel() > summaryInstance.getHighestHeroLvl()) {
          summaryInstance.setHighestHeroLvl(hero.getLevel());
        }
      }
    }
  }

  // Hero gold gain = monster_lvl * 100 for the heroes who did not faint
  public void heroesGoldGain() {
    for (Hero hero : heroes) {
      if (hero.getState().equals(State.CONSCIOUS)) {
        int goldGain = monsterLvl * Constants.GOLD_SCALE;
        hero.setGold(hero.getGold() + goldGain);
        // X has gained Y gold
        io.displayGoldGainMsg(hero, goldGain);

        // update gold gain in summary
        summaryInstance.incrementTotalGoldGain(goldGain);
        
      }
    }
  }

  // To revive fainted heroes with:
  // for simplicity sake, heroes are revied with 1/2 * lvl * 100 HP and MP * 1.1
  public void reviveFaintedHeroes() {
    for (Hero hero : heroes) {
      if (hero.getState().equals(State.FAINTED)) {
        hero.setHP( (int)(1/2 * hero.getLevel() * 100) );
        hero.setMP( (int)(hero.getMP() * 1.1) );
        hero.setState(State.CONSCIOUS);
        // X has been revived.
        io.displayCharacterRevivedMsg(hero);
      }
    }
  }

  /*
   * Action of monster attacking in battle
   */
  private void monsterAttack(Monster currMonster) {
    /*
     * Selects randomly from Heroes to attack
     */
    // Create an instance of Random
    Random random = new Random();

    List<Hero> consciousHeroes = new ArrayList<Hero>();
    // Filtered through the heroes for ones that are still conscious
    for (Hero hero : heroes) {
      if (hero.getState().equals(State.CONSCIOUS)) {
        consciousHeroes.add(hero);
      }
    }

    while (true) {
      // Get a random index within the size of the heroes list
      int randomIndex = random.nextInt(heroes.size());

      // Retrieve the corresponding hero from the list
      Hero targetHero = heroes.get(randomIndex);

      // Only allow the monster to go on to attack the hero if the targetHero
      // is conscious
      if (consciousHeroes.contains(targetHero)) {
        currMonster.attack(targetHero, bm, io);
        break;
      }
    }
  }

  public boolean allHeroesDefeated() {
    for (Hero hero : heroes) {
      if (hero.getState().equals(State.CONSCIOUS)) {
        return false;
      }
    }
    return true;
  }

  public boolean allMonstersDefeated() {
    for (Monster monster : monsters) {
      if (monster.getState().equals(State.CONSCIOUS)) {
        return false;
      }
    }
    return true;
  }

  /*
   * ******************************************************************
   */

  // heroes
  public void setHeroes(List<Hero> heroes) {
    if (heroes == null || heroes.size() == 0) {
      throw new IllegalArgumentException("Heroes should not be null " +
      " and the number of heroes in the party should not be zero. " +
      "Here is a print of heroes for your refernce: " + heroes);
    }
    this.heroes = heroes;
  }

  // monsters
  public void setMonsters(List<Monster> monsters) {
    if (monsters == null || monsters.size() == 0) {
      throw new IllegalArgumentException("Monsters should not be null " +
      " and the number of monsters fighting against should not be zero. " +
      "Here is a print of monsters for your refernce: " + monsters);
    }
    this.monsters = monsters;

    this.monsterLvl = monsters.get(0).getLevel();
  }
}
