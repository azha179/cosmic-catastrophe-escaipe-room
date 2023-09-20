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

  // items for the hub once true will go into the hub
  // logic for the grey out will be the game progress state and item true
  public static boolean torchFound = false;

  public static boolean footprintsFound = false;

  public static boolean note1Found = false;

  public static boolean note2Found = false;

  public static boolean puzzle1 = false;

  public static boolean puzzle2 = false;

  public static boolean puzzle3 = false;
}
