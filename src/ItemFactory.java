/*
 * ItemFactory.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Facotry responsible for loading all item .txt files, and is
 * reponsible for housing all the create methods for the various items
 */

import java.util.List;
import java.util.Random;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;

public class ItemFactory {
  // Singleton
  private static ItemFactory instance;
  Random random;

  private ItemFactory() {
    random = new Random();
    loadItems();
  }

  public static ItemFactory getInstance() {
    if (instance == null) {
      instance = new ItemFactory();
    }
    return instance;
  }

  // .txt files
  private static final String ARMORY_FILE = "GameData/Armory.txt";
  private static final String POTIONS_FILE = "GameData/Potions.txt";
  private static final String FIRE_SPELLS_FILE = "GameData/FireSpells.txt";
  private static final String ICE_SPELLS_FILE = "GameData/IceSpells.txt";
  private static final String LIGHTNING_SPELLS_FILE = "GameData/LightningSpells.txt";
  private static final String WEAPON_FILE = "GameData/Weaponry.txt";

  // items
  // private List<Item> items = new ArrayList<Item>();
  private List<Armor> armory = new ArrayList<Armor>();
  private List<Potion> potions = new ArrayList<Potion>();
  private List<Spell> fireSpells = new ArrayList<Spell>();
  private List<Spell> iceSpells = new ArrayList<Spell>();
  private List<Spell> lightningSpells = new ArrayList<Spell>();
  // The union of the above three lists
  private List<Spell> spells = new ArrayList<Spell>();
  private List<Weapon> weaponry = new ArrayList<Weapon>();

  // create items
  public Item createItem() {
    // NUM_OF_ITEM_TYPES = 4
    int itemType = random.nextInt(Constants.NUM_OF_ITEM_TYPES);
    switch (itemType) {
      case 0:
        return createArmor();
      case 1:
        return createWeapon();
      case 2:
        return createPotion();
      case 3:
        return createSpell();
      default:
        throw new IllegalArgumentException("Invalid item type: " + itemType);
    }
  }

  public Armor createArmor() {
    // 0-inclusive, armory.size()-exclusive
    int index = random.nextInt(armory.size());
    /*
     * The decision to make a copy of the armor is for the uniqueness of armor
     * between different heroes
     */
    Armor armor = armory.get(index);
    return new Armor(armor);
  }

  public Potion createPotion() {
    Random random = new Random();
    // 0-inclusive, armory.size()-exclusive
    int index = random.nextInt(potions.size());
    /*
     * The decision to make a copy of the armor is for the uniqueness of armor
     * between different heroes
     */
    Potion potion = potions.get(index);
    return new Potion(potion);
  }

  public Spell createSpell() {
    Random random = new Random();
    // 0-inclusive, armory.size()-exclusive
    int index = random.nextInt(spells.size());
    /*
     * The decision to make a copy of the armor is for the uniqueness of armor
     * between different heroes
     */
    Spell spell = spells.get(index);
    return new Spell(spell);
  }

  public Weapon createWeapon() {
    Random random = new Random();
    // 0-inclusive, armory.size()-exclusive
    int index = random.nextInt(weaponry.size());
    /*
     * The decision to make a copy of the armor is for the uniqueness of armor
     * between different heroes
     */
    Weapon weapon = weaponry.get(index);
    return new Weapon(weapon);
  }

  // read from .txt files
  private void loadItems() {
    loadArmory();
    loadPotions();
    loadSpells();
    loadWeaponry();
  }

  private void loadArmory() {
    try (Scanner scanner = new Scanner(new File(ARMORY_FILE))) {
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
        int cost = Integer.parseInt(parts[1]);
        int requiredLevel = Integer.parseInt(parts[2]);
        int damageReduction = Integer.parseInt(parts[3]);
        armory.add(new Armor(name, cost, requiredLevel, damageReduction));
      }
      // Output a warning if the armory is empty
      if (armory.isEmpty()) {
        System.out.println("Warning: The armory is currently empty.");
      }

    } catch (FileNotFoundException err) {
      err.printStackTrace();
    }
  }

  private void loadPotions() {
    try (Scanner scanner = new Scanner(new File(POTIONS_FILE))) {
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
        int cost = Integer.parseInt(parts[1]);
        int requiredLevel = Integer.parseInt(parts[2]);
        int attributeIncrease = Integer.parseInt(parts[3]);
        Set<Attribute> attributesAffected = parseAttributes(parts[4]);
        int maxNumOfUses = Constants.MAX_NUM_OF_USES;
        potions.add(new Potion(name, cost, requiredLevel, attributeIncrease, attributesAffected, maxNumOfUses));
      }
      // Output a warning if the armory is empty
      if (potions.isEmpty()) {
        System.out.println("Warning: The potions is currently empty.");
      }

    } catch (FileNotFoundException err) {
      err.printStackTrace();
    }
  }

  private void loadSpells() {
    loadSpecifiedSpells(FIRE_SPELLS_FILE, SpellType.FIRE, fireSpells);
    spells.addAll(fireSpells);
    loadSpecifiedSpells(ICE_SPELLS_FILE, SpellType.ICE, iceSpells);
    spells.addAll(iceSpells);
    loadSpecifiedSpells(LIGHTNING_SPELLS_FILE, SpellType.LIGHTNING, lightningSpells);
    spells.addAll(lightningSpells);

    // Output a warning if the spells is empty
    if (spells.isEmpty()) {
      System.out.println("Warning: The List<Spell> spells is currently empty." +
      " Meaning none of the spells.txt have any spells in them.");
    }
  }

  private void loadSpecifiedSpells(String spellsFile, SpellType spellType, List<Spell> specifiedSpells) {
    try (Scanner scanner = new Scanner(new File(spellsFile))) {
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
        int cost = Integer.parseInt(parts[1]);
        int requiredLevel = Integer.parseInt(parts[2]);
        int damage = Integer.parseInt(parts[3]);
        int manaCost = Integer.parseInt(parts[4]);
        int maxNumOfUses = Constants.MAX_NUM_OF_USES;
        specifiedSpells.add(new Spell(name, cost, requiredLevel, damage, manaCost, spellType, maxNumOfUses));
      }
      // Output a warning if the armory is empty
      if (specifiedSpells.isEmpty()) {
        System.out.println("Warning: The " + spellsFile + " is currently empty.");
      }

    } catch (FileNotFoundException err) {
      err.printStackTrace();
    }
  }

  private void loadWeaponry() {
    try (Scanner scanner = new Scanner(new File(WEAPON_FILE))) {
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
        int cost = Integer.parseInt(parts[1]);
        int requiredLevel = Integer.parseInt(parts[2]);
        int damage = Integer.parseInt(parts[3]);
        int requiredHands = Integer.parseInt(parts[4]);
        weaponry.add(new Weapon(name, cost, requiredLevel, damage, requiredHands));
      }
      // Output a warning if the armory is empty
      if (weaponry.isEmpty()) {
        System.out.println("Warning: The weaponry is currently empty.");
      }

    } catch (FileNotFoundException err) {
      err.printStackTrace();
    }
  }

  // Helper methods for parsing .txt files
  private Set<Attribute> parseAttributes(String attributeString) {
    Set<Attribute> attributes = new HashSet<Attribute>();

    if (attributeString.equalsIgnoreCase("all")) {
      attributes.add(Attribute.HEALTH);
      attributes.add(Attribute.MANA);
      attributes.add(Attribute.STRENGTH);
      attributes.add(Attribute.DEXTERITY);
      attributes.add(Attribute.DEFENSE);
      attributes.add(Attribute.AGILITY);
      return attributes;
    }

    if (attributeString.contains("/")) {
      // The case when multiple attribute would be affected
      String[] attributeArray = attributeString.split("/");
      for (String attr : attributeArray) {
        attributes.add(Attribute.valueOf(attr.toUpperCase()));
      }
    } else {
      // If only one attribute is going to be affected
      attributes.add(Attribute.valueOf(attributeString.toUpperCase()));
    }

    if (attributes.isEmpty()) {
      throw new IllegalArgumentException("The attribute affected option " +
      "is empty for one of the potions in Potions.txt");
    }

    return attributes;
  }
}
