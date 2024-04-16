/*
 * Tradeable.java
 * Tony Cen Cen
 * 4/15/2024
 * 
 * An interface to clearly indicate that any classes that implements it
 * should be able to be purchased and sold
 */

public interface Tradeable {
    int getBuyPrice();
    int getSellPrice();
    void setBuyPrice(int price);
    void setSellPrice(int price);
}
