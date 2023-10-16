package nz.ac.auckland.se206;

// Stores the game settings
public class GameSettings {

  /*
   * Stores the game difficulty.
   *
   * @param difficulty the game difficulty.
   */
  public enum GameDifficulty {
    EASY,
    MEDIUM,
    HARD
  }

  public static GameDifficulty difficulty = null;

  public static int timeLimit = 0;
}
