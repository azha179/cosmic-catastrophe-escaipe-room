package nz.ac.auckland.se206;

/** Represents the state of the game. */
public class GameState {

  /** Indicates whether the riddle has been resolved. */
  public static boolean isRiddleResolved = false;

  /** Indicates whether the key has been found. */
  public static boolean isKeyFound = false;

  // items for the hub once true will go into the hub
  // logic for the grey out will be the game progress state and item true
  public static boolean torchFound = false;

  // logic for item hubs feel free to change
  public static boolean torchHudDone() {
    if ((GameState.torchFound) && (GameState.footprintsFound) && (GameState.puzzle1)) {
      return true;
    }
    return false;
  }

  public static boolean footprintsFound = false;

  public static boolean note1Found = false;

  // LOGIC PROBS WRONG SINCE MADE BEFORE IMPLEMENTING PUZZLE
  public static boolean note1HudDone() {
    if ((GameState.note1Found) && (GameState.puzzle2) && (GameState.puzzle3)) {
      return true;
    }
    return false;
  }

  public static boolean note2Found = false;

  // LOGIC PROBS WRONG SINCE MADE BEFORE IMPLEMENTING PUZZLE
  public static boolean note2HudDone() {
    if ((GameState.note2Found) && (GameState.puzzle2) && (GameState.puzzle3)) {
      return true;
    }
    return false;
  }

  public static boolean puzzle1 = false;

  public static boolean puzzle2 = false;

  public static boolean puzzle3 = false;
}
