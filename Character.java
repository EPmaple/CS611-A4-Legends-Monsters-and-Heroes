/*
 * Character.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Abstract class that Hero and Monster extends from, holds the
 * general behavior and data members necessary for a Character
 */

public abstract class Character {
  protected String name;
  protected int level;
  protected int HP;
  protected State state; // Either FAINTED, or CONSCIOUS;
  protected int defense;

  public Character(String name, int level, int HP, State state, int defense) {
    setName(name);
    setLevel(level);
    setHP(HP);
    setState(state);
    setDefense(defense);
  }

  // NAME
  public void setName(String name) {
    if (name.isEmpty()) {
      throw new IllegalArgumentException("Input to setName cannot be the empty string.");
    }
    this.name = name;
  }
  public String getName() {
    return this.name;
  } 

  // LEVEL
  public void setLevel(int level) {
    if (level < 1) {
      throw new IllegalArgumentException("Input to setLevel is: " +
      level + ", and level cannot be <= 0");
    }
    this.level = level;
  }
  public int getLevel() {
    return this.level;
  } 

  // HP
  public void setHP(int HP) {
    // Take into consideration of the edge case of HP possibly going below 0
    if (HP < 0) {
      HP = 0;
    }
    this.HP = HP;
  }
  public int getHP() {
    return this.HP;
  } 

  // STATE
  public void setState(State state) {
    this.state = state;
  }
  public State getState() {
    return this.state;
  } 

  // defense
  public void setDefense(int defense) {
    // Take into consideration of the edge case: going below 0
    if (defense < 0) {
      defense = 0;
    }
    this.defense = defense;
  }
  public int getDefense() {
    return this.defense;
  } 

  public void takeDamage(int dmg, BattleIO io) {
    this.setHP(this.HP - dmg);
    if (this.HP == 0) {
      this.setState(State.FAINTED);
    }
    
    io.displayDamageTakenMsg(this);
  }

  public abstract void attack(Character target, BattleMechanics bm, BattleIO io);
  public abstract void castSpell(Spell spell, Character target, BattleMechanics bm, BattleIO io);

  public abstract String toString();
}
