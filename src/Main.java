/*
 * Main.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * This class is used to initalize the Game.
 */


public class Main {
  public static void main(String[] args) {
    GameManager gameManager = new GameManager(new IO());
    gameManager.gameStart();

  }
}
