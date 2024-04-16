/*
 * GameManager.java
 * Tony Cen Cen
 * 3/30/2024
 * 
 * The hub for users to indicate which game to play.
 */

public class GameManager {
  private IO io;

  public GameManager(IO io) {
    this.io = io;
  }

  public void gameStart() {
    while (true) {
      int gameSelectionIndex = io.queryForGameSelection();

      switch (gameSelectionIndex) {
        case 0:
          MH();
          break;
        case 1:
          LV();
          break;
        default:
          break;
      }
    }
  }

  private void MH() {
    MHGame mhGame = new MHGame(io);
    mhGame.gameStarts();
  }

  private void LV() {
    LVGame lvGame = new LVGame(io);
    lvGame.gameStarts();
  }
}
