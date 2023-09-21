package nz.ac.auckland.se206;

import nz.ac.auckland.se206.gpt.openai.ChatCompletionRequest;

/** Represents the state of the game. */
public class GameState {

  /** The chat completion request. */
  public static ChatCompletionRequest chatCompletionRequest;

  /** Indicates whether the riddle has been resolved. */
  public static boolean isRiddleResolved = false;

  /** Indicates whether the key has been found. */
  public static boolean isKeyFound = false;

  public static boolean toyFound = false;

  public static boolean torchFound = false;

  public static boolean footprintsFound = false;

  public static boolean note1Found = false;

  public static boolean note2Found = false;

  public static boolean puzzle2 = false;

  public static boolean puzzle3 = false;

  public static boolean isAnimationRunning = false;

  public static boolean isMemoryGameResolved = false;

  public static boolean isRecipeResolved = false;

  public static boolean isTorchOn = false;
}
