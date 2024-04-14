/*
 * Attribute.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * Enum used to represent attributes of Character
 */

/*
 * The idea of using enum to represent attributes instead of using something
 * like a global constant, comes from my CS440 Prof Andrew's use of the enum
 * ShipType in his programming assignment of Battleship
 */

public enum Attribute {
  HEALTH,
  MANA,
  /*
   * The three attributes below are currently for heroes
   */
  STRENGTH,
  AGILITY,
  DEXTERITY,
  /*
   * The three attribute below are currently for monsters
   */
  DAMAGE,
  DEFENSE,
  DODGE_CHANCE;
}
// VALUES() method
 /*
  * The enum type has a values() method, which returns an array of all enum constants. This method is useful when you want to loop through the constants of an enum
  */
