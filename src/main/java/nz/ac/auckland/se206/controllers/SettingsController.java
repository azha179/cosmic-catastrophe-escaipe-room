package nz.ac.auckland.se206.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager;

public class SettingsController {
  @FXML private Button backButton;
  @FXML private Button applyButton;
  @FXML private Slider volumeSlider;
  @FXML private ToggleButton toggleButton;
  @FXML private TextArea chatBox;

  // Timer element
  @FXML private Label timer;

  private static double volume = 1;

  public void initialize() {
    volumeSlider.setValue(1);
    // set the toggle (TextToSpeech) as off
    toggleButton.setSelected(false);
  }

  /**
   * Getter method for the timer label.
   *
   * @return the timer label
   */
  public Label getTimer() {
    return timer;
  }

  // to ensure that back goes to the scene it was just in, use the getPreviousScene method when
  // onClickBack is done

  /**
   * Handles the click event for the back button and returns to the previous scene
   *
   * @param event the mouse event
   */
  @FXML
  public void onClickBack(MouseEvent event) {
    App.setUi(SceneManager.getPreviousScene());
  }

  @FXML
  public void onClickApply(MouseEvent event) {
    volume = volumeSlider.getValue();
    // Takes the instance of mainroom to be able to access and change the tts volume
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    mainRoom.getTextManager().setVolume((float) volume);
    RocketController rocket = (RocketController) SceneManager.getController("rocket");
    rocket.getTextManager().setVolume((float) volume);
    PantryController pantry = (PantryController) SceneManager.getController("pantry");
    pantry.getTextManager().setVolume((float) volume);

    if (toggleButton.isSelected()) {
      GameState.textToSpeech = true;
    } else {
      GameState.textToSpeech = false;
    }
  }

  @FXML
  public void onToggleClicked(ActionEvent event) {
    if (toggleButton.isSelected()) {
      toggleButton.setText("Turn Off");
    } else {
      toggleButton.setText("Turn On");
    }
  }

  public static double getVolume() {
    return volume;
  }
}
