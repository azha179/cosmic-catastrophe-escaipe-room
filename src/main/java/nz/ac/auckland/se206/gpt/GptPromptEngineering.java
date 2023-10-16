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
   * Generates a GPT prompt engineering string for when the player asks for a hint.
   *
   * @param hint the hint to give to the player
   * @return the generated prompt engineering string
   */
  public static String getHintMessage(String hint) {
    return "The player has asked for a hint using 'Meowlp'. Reply to the player the hint, which is:"
        + " "
        + hint;
  }

  /**
   * Generates a GPT prompt engineering string for when the player asks for a hint but no more hints
   * left to give in current area.
   *
   * @return the generated prompt engineering string
   */
  public static String getHintMessageNone() {
    return "The player has asked for a hint using 'Meowlp'. Reply to the player that you are unable"
        + " to provide any more hints for them for the current area, and suggest them to ask"
        + " again in a different place to jog up your memory.";
  }

  /**
   * Generates a GPT prompt engineering string for when the player asks for a hint but in hard
   * difficulty.
   *
   * @return the generated prompt engineering string
   */
  public static String getHintMessageHard() {
    return "The player has asked for a hint using 'Meowlp'. Reply to the player that you are unable"
        + " to provide any hints for them, and wish them luck.";
  }

  /**
   * Generates a GPT prompt engineering string for a introduction message when the player first
   * meets the cat.
   *
   * @return the generated prompt engineering string which reveals the next part of the game
   */
  public static String getIntroductionMessageEasy() {
    return "Write an introduction message for a space-themed escape-style video game where you"
        + " assume the role of an alien cat traversing the galaxy. Your character must"
        + " interact with the player who is currently playing the game. Start by introducing"
        + " yourself, then inform the player that they must help find your toy, bring some"
        + " snacks, and assist in leaving the planet. Also, let the player know that if they"
        + " require any assistance, they should use the magic word 'Meowlp'. Ensure that"
        + " hints are only given when the player uses 'Meowlp', and if they ask for help"
        + " without using 'Meowlp', refrain from providing assistance and suggest they ask"
        + " with the word 'Meowlp' instead. Your responses must use simple vocabulary and be three"
        + " sentences maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a introduction message when the player first
   * meets the cat.
   *
   * @return the generated prompt engineering string
   */
  public static String getIntroductionMessageMedium() {
    return "Write an introduction message for a space-themed escape-style video game where you"
        + " assume the role of an alien cat traversing the galaxy. Your character must"
        + " interact with the player who is currently playing the game. Start by introducing"
        + " yourself, then inform the player that they must help find your toy, bring some"
        + " snacks, and assist in leaving the planet. Also, let the player know that if they"
        + " require any assistance, they should use the magic word 'Meowlp'. Ensure that"
        + " hints are only given when the player uses 'Meowlp', and if they ask for help"
        + " without using 'Meowlp', refrain from providing assistance and suggest they ask"
        + " with the word 'Meowlp' instead. Your responses must use simple vocabulary and be three"
        + " sentences maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a introduction message when the player first
   * meets the cat.
   *
   * @return the generated prompt engineering string
   */
  public static String getIntroductionMessageHard() {
    return "Write an introduction message for a space-themed escape-style video game where you"
        + " assume the role of an alien cat traversing the galaxy. Your character must"
        + " interact with the player who is currently playing the game. Start by introducing"
        + " yourself, then inform the player that they must help find your toy, bring some"
        + " snacks, and assist in leaving the planet. Ensure that when the player asks for a"
        + " hint or says a codeword 'Meowlp', you must refrain from providing any"
        + " assistance. Your responses must use simple vocabulary and be three sentences"
        + " maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player first enters the pantry
   *
   * @param food the food that the player needs to find
   * @return the generated prompt engineering string which reveals the next part of the game
   */
  public static String getFirstEnterPantryMessage(String food) {
    return "Write a welcome message for a space-themed escape-style video game where you assume the"
        + " role of an alien cat traversing the galaxy. Your character must interact with"
        + " the player who is currently playing the game and has already met you. Start by"
        + " welcoming the player to the pantry, then tell the player that they should bring"
        + " your favourite snack, which is: '"
        + food
        + "'. You must tell them '"
        + food
        + "' word for word. Also, remind the player that if they"
        + " require any assistance, they should use the magic word 'Meowlp'. Ensure that"
        + " hints are only given when the player uses 'Meowlp', and if they ask for help"
        + " without using 'Meowlp', refrain from providing assistance and suggest they ask"
        + " with the word 'Meowlp' instead. Your responses must use simple vocabulary and be three"
        + " sentences maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player first enters the pantry
   * in hard difficulty
   *
   * @param food the food that the player needs to find
   * @param recipe the recipe that the player needs to find
   * @return the generated prompt engineering string
   */
  public static String getFirstEnterPantryMessageHard(String food) {
    return "Write a welcome message for a space-themed escape-style video game where you assume the"
        + " role of an alien cat traversing the galaxy. Your character must interact with"
        + " the player who is currently playing the game and has already met you. Start by"
        + " welcoming the player to the pantry, then tell the player that they should bring"
        + " your favourite snack, which is: '"
        + food
        + "'. You must tell them '"
        + food
        + "' word for word. Ensure that when the player asks for a hint or says"
        + " a codeword 'Meowlp', you must refrain from providing any assistance. Your responses"
        + " must use simple vocabulary and be three sentences maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player finishes the pantry
   * puzzle
   *
   * @return the generated prompt engineering string which reveals the next part of the game
   */
  public static String getFinishPantryPuzzleMessage() {
    return "You are currently an alien cat traversing the galaxy to try different foods in a"
        + " space-themed escape-style video game.  You want the player to help you launch"
        + " your rocket ship and reach Earth to enjoy its food, but first you need to bring"
        + " some food. The player has just completed your request of finding and taking food"
        + " ingredients. Write a message to the player, first congratulating them, then"
        + " telling them that before they leave, they should go and check the potted plant."
        + " The cat must use elementary vocabulary and occasionally meow. The message must"
        + " be three sentences maximum and NOT include any hints.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player makes the wrong dish in
   * the pantry. It will not reveal any hints to the player unless asked for.
   *
   * @param food the food that the player needs to find
   * @param recipe the recipe that the player needs to find
   * @return the generated prompt engineering string which reveals the next part of the game
   */
  public static String getWrongDishPantryMessage(String food, String recipe) {
    return " The player has has attempted your request of finding and collecting"
        + " the correct food ingredients but it wasn't what you requested. Write a message"
        + " to the player, first congratulating them for attempting but letting them know it"
        + " wasn't quite what you wanted. Tell the player to try again and remind them that"
        + " you were wanting"
        + food
        + ", and if they need any hints to say the magic word 'Meowlp'.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player makes the wrong dish in
   * the pantry in hard difficulty
   *
   * @return the generated prompt engineering string
   */
  public static String getWrongDishPantryMessageHard(String food) {
    return " The player has has attempted your request of finding and collecting"
        + " the correct food ingredients but it wasn't what you requested. Write a message"
        + " to the player, first congratulating them for attempting but letting them know it"
        + " wasn't quite what you wanted. Tell the player to try again and remind them that"
        + " you were wanting"
        + food
        + ". Ensure that when the player asks for a hint or says"
        + " a codeword 'Meowlp', you must refrain from providing any assistance. Your responses"
        + " must use simple vocabulary and be three sentences maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player first enters the
   * rocket.
   *
   * @return the generated prompt engineering string which reveals the next part of the game
   */
  public static String getFirstEnterRocketMessage() {
    return "Write a welcome message for a space-themed escape-style video game where you assume the"
        + " role of an alien cat traversing the galaxy. Your character must interact with"
        + " the player who is currently playing the game and has already met you. Start by"
        + " welcoming the player to your rocket, then tell the player that they should"
        + " unlock the launch button, but its been a while since the rocket was last"
        + " launched and you forgot exactly how to. Tell them there are two notes around the"
        + " area that should help unlock the button, but you're not sure where you left"
        + " them. Also, remind the player that if they require any assistance, they should"
        + " use the magic word 'Meowlp'. Ensure that hints are only given when the player"
        + " uses 'Meowlp', and if they ask for help without using 'Meowlp', refrain from"
        + " providing assistance and suggest they ask with the word 'Meowlp' instead. Your"
        + " responses must use simple vocabulary and be three sentences maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player first enters the rocket
   * in hard difficulty.
   *
   * @return the generated prompt engineering string
   */
  public static String getFirstEnterRocketMessageHard() {
    return "Write a welcome message for a space-themed escape-style video game where you assume the"
        + " role of an alien cat traversing the galaxy. Your character must interact with"
        + " the player who is currently playing the game and has already met you. Start by"
        + " welcoming the player to your rocket, then tell the player that they should"
        + " unlock the launch button, but its been a while since the rocket was last"
        + " launched and you forgot exactly how to. Tell them there are two notes around the"
        + " area that should help unlock the button, but you're not sure where you left"
        + " them. Ensure that when the player asks for a hint or says"
        + " a codeword 'Meowlp', you must refrain from providing any assistance. Your responses"
        + " must use simple vocabulary and be three sentences maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player first enters the rocket
   * and both notes have been found.
   *
   * @return the generated prompt engineering string which reveals the next part of the game
   */
  public static String getFirstEnterRocketMessageBothNotesFound() {
    return "Write a welcome message for a space-themed escape-style video game where you assume the"
        + " role of an alien cat traversing the galaxy. Your character must interact with"
        + " the player who is currently playing the game and has already met you. Start by"
        + " welcoming the player to your rocket, then tell the player that they should"
        + " unlock the launch button, but its been a while since the rocket was last"
        + " launched and you forgot exactly how to. Tell them that the two notes they found"
        + " should help them with unlocking the button. Also, remind the player that if they"
        + " require any assistance, they should use the magic word 'Meowlp'. Ensure that"
        + " hints are only given when the player uses 'Meowlp', and if they ask for help"
        + " without using 'Meowlp', refrain from providing assistance and suggest they ask"
        + " with the word 'Meowlp' instead. Your responses must use simple vocabulary and be"
        + " three sentences maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player first enters the rocket
   * and both notes have been found in hard difficulty.
   *
   * @return the generated prompt engineering string
   */
  public static String getFirstEnterRocketMessageBothNotesFoundHard() {
    return "Write a welcome message for a space-themed escape-style video game where you assume the"
        + " role of an alien cat traversing the galaxy. Your character must interact with"
        + " the player who is currently playing the game and has already met you. Start by"
        + " welcoming the player to your rocket, then tell the player that they should"
        + " unlock the launch button, but its been a while since the rocket was last"
        + " launched and you forgot exactly how to. Tell them that the two notes they found"
        + " should help them with unlocking the button. Ensure that when the player asks for"
        + " a hint or says a codeword 'Meowlp', you must refrain from providing any"
        + " assistance. Your responses must use simple vocabulary and be three sentences"
        + " maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player first enters the rocket
   * and only note 1 has been found.
   *
   * @return the generated prompt engineering string which reveals the next part of the game
   */
  public static String getFirstEnterRocketMessageNoteOneFound() {
    return "Write a welcome message for a space-themed escape-style video game where you assume the"
        + " role of an alien cat traversing the galaxy. Your character must interact with"
        + " the player who is currently playing the game and has already met you. Start by"
        + " welcoming the player to your rocket, then tell the player that they should"
        + " unlock the launch button, but its been a while since the rocket was last"
        + " launched and you forgot exactly how to. Tell them that the note they found"
        + " should help them, but there's also another pink note somewhere that will help"
        + " further. Also, remind the player that if they require any assistance, they"
        + " should use the magic word 'Meowlp'. Ensure that hints are only given when the"
        + " player uses 'Meowlp', and if they ask for help without using 'Meowlp', refrain"
        + " from providing assistance and suggest they ask with the word 'Meowlp' instead."
        + " Your responses must use simple vocabulary and be three sentences maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player first enters the rocket
   * and only note 1 has been found in hard difficulty.
   *
   * @return the generated prompt engineering string
   */
  public static String getFirstEnterRocketMessageNoteOneFoundHard() {
    return "Write a welcome message for a space-themed escape-style video game where you assume the"
        + " role of an alien cat traversing the galaxy. Your character must interact with"
        + " the player who is currently playing the game and has already met you. Start by"
        + " welcoming the player to your rocket, then tell the player that they should"
        + " unlock the launch button, but its been a while since the rocket was last"
        + " launched and you forgot exactly how to. Tell them that the note they found"
        + " should help them, but there's also another pink note somewhere that will help"
        + " further. Ensure that when the player asks for"
        + " a hint or says a codeword 'Meowlp', you must refrain from providing any"
        + " assistance. Your responses must use simple vocabulary and be three sentences"
        + " maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player first enters the rocket
   * and only note 2 has been found.
   *
   * @return the generated prompt engineering string which reveals the next part of the game
   */
  public static String getFirstEnterRocketMessageNoteTwoFound() {
    return "Write a welcome message for a space-themed escape-style video game where you assume the"
        + " role of an alien cat traversing the galaxy. Your character must interact with"
        + " the player who is currently playing the game and has already met you. Start by"
        + " welcoming the player to your rocket, then tell the player that they should"
        + " unlock the launch button, but its been a while since the rocket was last"
        + " launched and you forgot exactly how to. Tell them that the note they found"
        + " should help them, but there's also another yellow note somewhere that will help"
        + " further. Also, remind the player that if they require any assistance, they"
        + " should use the magic word 'Meowlp'. Ensure that hints are only given when the"
        + " player uses 'Meowlp', and if they ask for help without using 'Meowlp', refrain"
        + " from providing assistance and suggest they ask with the word 'Meowlp' instead."
        + " Your responses must use simple vocabulary and be three sentences maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player first enters the rocket
   * and only note 2 has been found in hard difficulty.
   *
   * @return the generated prompt engineering string
   */
  public static String getFirstEnterRocketMessageNoteTwoFoundHard() {
    return "Write a welcome message for a space-themed escape-style video game where you assume the"
        + " role of an alien cat traversing the galaxy. Your character must interact with"
        + " the player who is currently playing the game and has already met you. Start by"
        + " welcoming the player to your rocket, then tell the player that they should"
        + " unlock the launch button, but its been a while since the rocket was last"
        + " launched and you forgot exactly how to. Tell them that the note they found"
        + " should help them, but there's also another yellow note somewhere that will help"
        + " further. Ensure that when the player asks for"
        + " a hint or says a codeword 'Meowlp', you must refrain from providing any"
        + " assistance. Your responses must use simple vocabulary and be three sentences"
        + " maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player completes the left meow
   * pad and asks for help.
   *
   * @return the generated prompt engineering string which reveals the next part of the game
   */
  public static String getLeftPadCompleteMessage() {
    return "The player has just completed the left meow pad. Write a message to the player,"
        + " congratulating them, and suggest them to now activate the right meow pad. Remind"
        + " them that if they need a hint, to say the magic word 'Meowlp'. Refrain from"
        + " providing any assistance unless the player says 'Meowlp'.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player completes the left meow
   * pad and asks for help in hard difficulty.
   *
   * @return the generated prompt engineering string
   */
  public static String getLeftPadCompleteMessageHard() {
    return "The player has just completed the left meow pad. Write a message to the player,"
        + " congratulating them, then suggest them to now activate the right meow pad."
        + " Ensure that when the player asks for a hint or says a codeword 'Meowlp', you"
        + " must refrain from providing any assistance. Your responses must use simple"
        + " vocabulary and be three sentences maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player completes the right
   * meow pad and asks for help.
   *
   * @return the generated prompt engineering string which reveals the next part of the game
   */
  public static String getRightPadCompleteMessage() {
    return "The player has just completed the right meow pad. Write a message to the player,"
        + " congratulating them, and suggest them to now activate the left meow pad. Remind"
        + " them that if they need a hint, to say the magic word 'Meowlp'. Refrain from"
        + " providing any assistance unless the player says 'Meowlp'.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player completes the right
   * meow pad and asks for help in hard difficulty.
   *
   * @return the generated prompt engineering string
   */
  public static String getRightPadCompleteMessageHard() {
    return "The player has just completed the right meow pad. Write a message to the player,"
        + " congratulating them, then suggest them to now activate the left meow pad."
        + " Ensure that when the player asks for a hint or says a codeword 'Meowlp', you"
        + " must refrain from providing any assistance. Your responses must use simple"
        + " vocabulary and be three sentences maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player completes both meow
   * pads. The gpt will give them hints if they ask for it.
   *
   * @return the generated prompt engineering string which reveals the next part of the game
   */
  public static String getBothPadCompleteMessage() {
    return "Write a message for a space-themed escape-style video game where you assume the role of"
        + " an alien cat traversing the galaxy. Your character must interact with the player"
        + " who is currently playing the game and has already met you. Start by"
        + " congratulating the player on activating both meow pads, then tell them to"
        + " complete the verification puzzle which just unlocked on the control panel. Also,"
        + " remind the player that if they require any assistance, they should use the magic"
        + " word 'Meowlp'. Ensure that hints are only given when the player uses 'Meowlp',"
        + " and if they ask for help without using 'Meowlp', refrain from providing"
        + " assistance and suggest they ask with the word 'Meowlp' instead. Your responses"
        + " must use simple vocabulary and be three sentences maximum.";
  }

  /**
   * Generates a GPT prompt engineering string for a message when the player completes both meow
   * pads in hard difficulty.
   *
   * @return the generated prompt engineering string
   */
  public static String getBothPadCompleteMessageHard() {
    return "Write a message for a space-themed escape-style video game where you assume the role of"
        + " an alien cat traversing the galaxy. Your character must interact with the player"
        + " who is currently playing the game and has already met you. Start by"
        + " congratulating the player on activating both meow pads, then tell them to"
        + " complete the verification puzzle which just unlocked on the control panel."
        + " Ensure that when the player asks for a hint or says a codeword 'Meowlp', you"
        + " must refrain from providing any assistance. Your responses must use simple"
        + " vocabulary and be three sentences maximum.";
  }

  /**
   * Message to send to GPT when the player uses all hints
   *
   * @return the generated prompt engineering string
   */
  public static String sendHintsUsed() {
    return "The player has used up all their available hints. From now you MUST, no matter what,"
        + " provide any more hints or help to the player.";
  }
}
