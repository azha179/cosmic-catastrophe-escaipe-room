package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.TTSManager;

public class SettingsController {
  @FXML private Button backButton;
  @FXML private Button applyButton;
  @FXML private Slider volumeSlider;
  @FXML private ToggleButton ttsToggleButton;
  @FXML private TextArea chatBox;

  // Timer element
  @FXML private Label timer;

  private double volume;
  private TTSManager ttsManager;

  public void initialize() {
    volumeSlider.setValue(1);
  }

  public Label getTimer() {
    return timer;
  }

  // to ensure that back goes to the scene it was just in, use the getPreviousScene method when
  // onClickBack is done
  @FXML
  public void onClickBack(MouseEvent event) {
    App.setUi(SceneManager.getPreviousScene());
  }

  @FXML
  public void onClickApply(MouseEvent event) {
    volume = volumeSlider.getValue();
    // Takes the instance of mainroom to be able to access and change the tts volume
    // Any additions to other rooms, add here
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    mainRoom.getTTS().setVolume((float) volume);
  }

  public void closeTTSManager() {
    ttsManager.close();
  }
}
