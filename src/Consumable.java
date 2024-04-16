/*
 * Consumable.java
 * Tony Cen Cen
 * 4/15/2024
 * 
 * A contract to clearly indicate that an item is consumable
 */

public interface Consumable {
    void setRemainingUses(int remainingUses);
    int getRemainingUses();
}
