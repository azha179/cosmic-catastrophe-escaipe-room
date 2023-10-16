package nz.ac.auckland.se206;

import nz.ac.auckland.se206.controllers.MainRoomController;
import nz.ac.auckland.se206.controllers.PantryController;
import nz.ac.auckland.se206.controllers.RocketController;

/** Represents the state of the game. */
public class GameState {

  /** Indicates whether the riddle has been resolved. */
  public static boolean isRiddleResolved = false;

  /** Indicates whether the key has been found. */
  public static boolean isKeyFound = false;

  public static boolean isGameActive = false;

  public static boolean toyFound = false;

  public static boolean torchFound = false;

  public static boolean footprintsFound = false;

  public static boolean note1Found = false;

  public static boolean note2Found = false;

  public static boolean isAnimationRunning = false;

  public static boolean isMemoryGameResolved = false;

  public static boolean isRecipeResolved = false;

  public static boolean isTorchOn = false;

  public static boolean isLeftMeowPadActivated = false;

  public static boolean isRightMeowPadActivated = false;

  public static boolean isNotesResolved = false;

  public static boolean isPantryFirstEntered = false;

  public static boolean isRocketFirstEntered = false;

  public static int hintsLeft;

  public static boolean isHintUsed = false;

  public static boolean textToSpeech = false;

  public static void updateAllHintsLabel() {
    MainRoomController mainRoomController =
        (MainRoomController) SceneManager.getController("mainroom");
    PantryController pantryController = (PantryController) SceneManager.getController("pantry");
    RocketController rocketController = (RocketController) SceneManager.getController("rocket");
    mainRoomController.updateHintsLabel();
    pantryController.updateHintsLabel();
    rocketController.updateHintsLabel();
  }
}
