package nz.ac.auckland.se206;

public class GameSettings {

  public enum GameDifficulty {
    EASY,
    MEDIUM,
    HARD
  }

  public enum TimeLimit {
    TWO,
    FOUR,
    SIX
  }

  public static GameDifficulty difficulty = null;

  public static TimeLimit timeLimit = null;
}
