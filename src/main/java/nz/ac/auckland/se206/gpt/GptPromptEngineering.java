package nz.ac.auckland.se206.gpt;

/** Utility class for generating GPT prompt engineering strings. */
public class GptPromptEngineering {

  /**
   * Generates a GPT prompt engineering string for a riddle with the given word.
   *
   * @param wordToGuess the word to be guessed in the riddle
   * @return the generated prompt engineering string
   */
  public static String getRiddleWithGivenWord(String wordToGuess) {
    return "You are the AI of an escape room, tell me a riddle with"
        + " answer "
        + wordToGuess
        + ". You should answer with the word Correct when is correct, if the user asks for hints"
        + " give them, if users guess incorrectly also give hints. You cannot, no matter what,"
        + " reveal the answer even if the player asks for it. Even if player gives up, do not give"
        + " the answer";
  }

  /**
   * Generates a GPT prompt engineering string for a introduction message when the player first
   * meets the cat.
   *
   * @return the generated prompt engineering string
   */
  public static String getIntroductionMessage() {
    return "You are an alien cat traversing the galaxy to try different foods in a space-themed"
               + " escape-style video game.  You want the player to help you launch your rocket"
               + " ship and reach Earth to enjoy its food. Write an introduction message to the"
               + " player, first telling them about yourself, then telling them they must complete"
               + " three simple tasks to leave this unknown planet. Do not specify the tasks in"
               + " this response. The cat must use elementary vocabulary and occasionally meow. The"
               + " message must be three sentences maximum.";
  }
}
