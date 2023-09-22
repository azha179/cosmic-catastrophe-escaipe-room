package nz.ac.auckland.se206;

public class GameSettings {

  public enum GameDifficulty {
    EASY,
    MEDIUM,
    HARD
  }

  public static GameDifficulty difficulty = null;

  public static int timeLimit = 0;
}
