/*
 * Game.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * The hub for users to indicate which game to play.
 */

public class Game {
  private IO io;

  public Game(IO io) {
    this.io = io;
  }

  public void gameStart() {
    int gameSelectionIndex = io.queryForGameSelection();

    switch (gameSelectionIndex) {
      case 0:
        MH();
        break;
      default:
        break;
    }
  }

  private void MH() {
    MHGame mhGame = new MHGame(io);
    mhGame.gameStarts();
  }
}
