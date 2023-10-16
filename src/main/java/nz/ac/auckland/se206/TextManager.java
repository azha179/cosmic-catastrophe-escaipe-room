package nz.ac.auckland.se206;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.application.Platform;
import javafx.concurrent.Task;
import nz.ac.auckland.se206.controllers.MainRoomController;
import nz.ac.auckland.se206.controllers.PantryController;
import nz.ac.auckland.se206.controllers.RocketController;
import nz.ac.auckland.se206.controllers.SettingsController;

/** Manages the text to speech. */
public class TextManager {

  private static Voice voice;
  private static TextManager textManager;

  // Constructor
  public TextManager() {
    // Initialize the FreeTTS voice
    System.setProperty(
        "freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    voice = VoiceManager.getInstance().getVoice("kevin16");
    // Set the voice's attributes
    if (voice != null) {
      voice.allocate();
    } else {
      throw new IllegalStateException("Cannot find the FreeTTS voice.");
    }
    voice.setPitch(280);
    voice.setRate(120);
  }

  // Sets the volume of the voice if it is not already active
  public void setVolume(float volume) {
    if (voice != null) {
      voice.setVolume(volume);
    }
  }

  // Speaks the given text
  public void speak(String text) {
    if (voice != null && GameState.textToSpeech) {
      voice.speak(text);
    }
  }

  // Closes the voice
  public static void close() {
    if (voice != null) {
      // Deallocate the voice in each scene
      MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
      mainRoom.getTextManager().deallocate();
      RocketController rocket = (RocketController) SceneManager.getController("rocket");
      rocket.getTextManager().deallocate();
      PantryController pantry = (PantryController) SceneManager.getController("pantry");
      pantry.getTextManager().deallocate();
    }
  }

  // Deallocates the voice
  private void deallocate() {
    voice.deallocate();
  }

  // Speaks the given text in the chat
  public static void speakChatMessage(String Message) {

    // Run the text to speech task on the JavaFX thread
    Platform.runLater(
        () -> {
          // Create a new TTS manager instance
          textManager = new TextManager();
          textManager.setVolume((float) SettingsController.getVolume());
          // Text to speech task
          Task<Void> textToSpeechTask =
              new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                  textManager.speak(Message);
                  return null;
                }
              };
          // Start the text to speech thread
          Thread textToSpeechThread = new Thread(textToSpeechTask);
          textToSpeechThread.start();
        });
  }
}
