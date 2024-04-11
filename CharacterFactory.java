/*
 * CharacterFactory.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Factory responsible for loading all character .txt files
 * , createHero() and createMonster()
 */

import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;

public class CharacterFactory {
  // Singleton
  private static CharacterFactory instance;
  Random random;

  private CharacterFactory() {
    random = new Random();
    loadCharacters();
  }

  public static CharacterFactory getInstance() {
    if (instance == null) {
      instance = new CharacterFactory();
    }
    return instance;
  }

  // .txt files
  protected static final String PALADINS_FILE = "GameData/Paladins.txt";
  protected static final String SORCERERS_FILE = "GameData/Sorcerers.txt";
  protected static final String WARRIORS_FILE = "GameData/Warriors.txt";
  protected static final String DRAGONS_FILE = "GameData/Dragons.txt";
  protected static final String EXOSKELETONS_FILE = "GameData/Exoskeletons.txt";
  protected static final String SPIRITS_FILE = "GameData/Spirits.txt";

  // Characters
  protected List<Hero> paladins = new ArrayList<>();
  protected List<Hero> sorcerers = new ArrayList<>();
  protected List<Hero> warriors = new ArrayList<>();
  protected List<Hero> heroes = new ArrayList<>();
  protected List<Monster> dragons = new ArrayList<>();
  protected List<Monster> exoskeletons = new ArrayList<>();
  protected List<Monster> spirits = new ArrayList<>();
  protected List<Monster> monsters = new ArrayList<>();
  
  // create characters
  public Hero createHero(HeroType heroType, Integer index) {
    if (heroType == null || index == null) {
      throw new IllegalArgumentException("Either heroType is null: " +
      heroType + " , or the index is null: " + index);
    }

    List<Hero> specifiedHeroes = getSpecifiedHeroes(heroType);
    Hero specifiedHero = specifiedHeroes.get(index);
    /*
     * Given the uniqueness of Hero, we can afford to use Object.equals()
     * in the situation of Collection.remove()
     */
    specifiedHeroes.remove(specifiedHero);
    /*
     * There is no need to copy the Hero in this case, as we will like to
     * guarantee the uniqueness of each hero
     */
    return specifiedHero;
  }

  public Monster createMonster(int level) {
    /*
     * First, filter through the list of monsters for only monsters with
     * levels equal to currentHighestLevel
     * Then randomly select from the filtered list to create the monster 
     */
    List<Monster> filteredList = new ArrayList<>();
    for (Monster monster : monsters) {
      if (monster.level == level) {
        filteredList.add(monster);
      }
    }

    Collections.shuffle(filteredList);
    return new Monster(filteredList.get(0));
  }

  // read from .txt files
  private void loadCharacters() {
    loadHeroes();
    loadMonsters();
  }

  private void loadHeroes() {
    loadSpecifiedHeroes(PALADINS_FILE, HeroType.PALADIN, paladins);
    heroes.addAll(paladins);
    loadSpecifiedHeroes(SORCERERS_FILE, HeroType.SORCERER, sorcerers);
    heroes.addAll(sorcerers);
    loadSpecifiedHeroes(WARRIORS_FILE, HeroType.WARRIOR, warriors);
    heroes.addAll(warriors);
  }

  private void loadSpecifiedHeroes(String heroesFile, HeroType heroType, List<Hero> specifiedHeroes) {
    try (Scanner scanner = new Scanner(new File(heroesFile))) {
      // Skip the first line, which are the stat descriptions
      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }
      // Read and process the remaining lines
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        // Split each into parts based on whitespace
        String[] parts = line.split("\\s+");
        String name = parts[0];
        int level = Constants.INITIAL_HERO_LEVEL; // Currently 1
        int HP = level * Constants.HP_SCALE; // Currently lvl * 100
        State state = State.CONSCIOUS;
        int MP = Integer.parseInt(parts[1]);
        int strength = Integer.parseInt(parts[2]);
        int agility = Integer.parseInt(parts[3]);
        int dexterity = Integer.parseInt(parts[4]);
        int defense = Constants.DEFAULT_HERO_DEFENSE; // Currently 50
        int gold = Integer.parseInt(parts[5]);
        int experience = Integer.parseInt(parts[6]);
        Weapon weapon = null;
        Armor armor = null;
        List<Item> inventory = new ArrayList<Item>();

        if (heroType.equals(HeroType.PALADIN)) {
          specifiedHeroes.add(new Paladin(name, level, HP, state, MP, strength, agility,
                  dexterity, defense, gold, experience, weapon, armor, heroType, inventory));
        } else if (heroType.equals(HeroType.SORCERER)) {
          specifiedHeroes.add(new Sorcerer(name, level, HP, state, MP, strength, agility,
                  dexterity, defense, gold, experience, weapon, armor, heroType, inventory));
        } else if (heroType.equals(HeroType.WARRIOR)) {
          specifiedHeroes.add(new Warrior(name, level, HP, state, MP, strength, agility,
                  dexterity, defense, gold, experience, weapon, armor, heroType, inventory));
        } else {
          throw new IllegalArgumentException("heroType must be one of the constants " +
                  "of enum HeroType.");
        }
      }
      // Output a warning if the armory is empty
      if (specifiedHeroes.isEmpty()) {
        System.out.println("Warning: The " + heroesFile + " is currently empty.");
      }

    } catch (FileNotFoundException err) {
      err.printStackTrace();
    }
  }

  private void loadMonsters() {
    loadSpecifiedMonsters(DRAGONS_FILE, MonsterType.DRAGON, dragons);
    monsters.addAll(dragons);
    loadSpecifiedMonsters(EXOSKELETONS_FILE, MonsterType.EXOSKELETON, exoskeletons);
    monsters.addAll(exoskeletons);
    loadSpecifiedMonsters(SPIRITS_FILE, MonsterType.SPIRIT, spirits);
    monsters.addAll(spirits);
  }

  private void loadSpecifiedMonsters(String monstersFile, MonsterType monsterType, List<Monster> specifiedMonsters) {
    try (Scanner scanner = new Scanner(new File(monstersFile))) {
      // Skip the first line, which are the stat descriptions
      if (scanner.hasNextLine()) {
        scanner.nextLine();
      }
      // Read and process the remaining lines
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        // Split each into parts based on whitespace
        String[] parts = line.split("\\s+");
        String name = parts[0];
        int level = Integer.parseInt(parts[1]);
        int HP = level * Constants.HP_SCALE; // Currently lvl * 100
        State state = State.CONSCIOUS;
        int damage = Integer.parseInt(parts[2]);
        int defense = Integer.parseInt(parts[3]);
        int dodgeChance = Integer.parseInt(parts[4]);
        // System.out.println("printing defense for debug: " + defense);

        Monster monster;
        if (monsterType.equals(MonsterType.DRAGON)) {
          monster = new Dragon(name, level, HP, state, damage, defense, dodgeChance,
                  monsterType);
        } else if (monsterType.equals(MonsterType.SPIRIT)) {
          monster = new Spirit(name, level, HP, state, damage, defense, dodgeChance,
                  monsterType);
        } else if (monsterType.equals(MonsterType.EXOSKELETON)) {
          monster = new Exoskeleton(name, level, HP, state, damage, defense, dodgeChance,
                  monsterType);
        } else {
          throw new IllegalArgumentException("monsterType must be one of the constants " +
                  "of enum MonsterType.");
        }

        specifiedMonsters.add(monster);
        // System.out.println("monster defense after creation: " + monster.getDefense() + "\n");
      }
      // Output a warning if the armory is empty
      if (specifiedMonsters.isEmpty()) {
        System.out.println("Warning: The " + monstersFile + " is currently empty.");
      }

    } catch (FileNotFoundException err) {
      err.printStackTrace();
    }
  }
  
  // Helper methods for creating characters
  public List<Hero> getSpecifiedHeroes(HeroType heroType) {
    switch (heroType) {
      case PALADIN:
        return paladins;
      case SORCERER:
        return sorcerers;
      case WARRIOR:
        return warriors;
      default:
        // It is null, 
        throw new IllegalArgumentException("heroType input is invalid: " + heroType);
    }
  }

  public List<Hero> getPaladins() {
    return this.paladins;
  }
  public List<Hero> getSorcerers() {
    return this.sorcerers;
  }
  public List<Hero> getWarriors() {
    return this.warriors;
  }
}
