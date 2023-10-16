package nz.ac.auckland.se206;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.application.Platform;
import javafx.concurrent.Task;
import nz.ac.auckland.se206.controllers.MainRoomController;
import nz.ac.auckland.se206.controllers.PantryController;
import nz.ac.auckland.se206.controllers.RocketController;

public class TTSManager {
  private static Voice voice;
  private static TTSManager ttsManager;

  public TTSManager() {
    // Initialize the FreeTTS voice
    System.setProperty(
        "freetts.voices", "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
    voice = VoiceManager.getInstance().getVoice("kevin16");
    if (voice != null) {
      voice.allocate();
    } else {
      throw new IllegalStateException("Cannot find the FreeTTS voice.");
    }
    voice.setPitch(500);
    voice.setRate(140);
  }

  public void setVolume(float volume) {
    if (voice != null) {
      voice.setVolume(volume);
    }
  }

  public void speak(String text) {
    if (voice != null && GameState.textToSpeech) {
      voice.speak(text);
    }
  }

  public static void close() {
    if (voice != null) {
      MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
      mainRoom.getTTS().deallocate();
      RocketController rocket = (RocketController) SceneManager.getController("rocket");
      rocket.getTTS().deallocate();
      PantryController pantry = (PantryController) SceneManager.getController("pantry");
      pantry.getTTS().deallocate();
    }
  }

  private void deallocate() {
    voice.deallocate();
  }

  public static void speakInitialise(String Message) {

    Platform.runLater(
        () -> {
          // Create a new TTS manager instance
          ttsManager = new TTSManager();

          // Text to speech task
          Task<Void> textToSpeechTask =
              new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                  ttsManager.speak(Message);
                  return null;
                }
              };

          Thread textToSpeechThread = new Thread(textToSpeechTask);
          textToSpeechThread.start();
        });
  }
}
