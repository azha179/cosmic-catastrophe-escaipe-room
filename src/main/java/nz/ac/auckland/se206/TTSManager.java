package nz.ac.auckland.se206;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class TTSManager {
  private Voice voice;

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
    voice.setPitch(800);
    voice.setRate(150);
  }

  public void setVolume(float volume) {
    if (voice != null) {
      voice.setVolume(volume);
    }
  }

  public void speak(String text) {
    if (voice != null) {
      voice.speak(text);
    }
  }

  public void close() {
    if (voice != null) {
      voice.deallocate();
    }
  }
}
