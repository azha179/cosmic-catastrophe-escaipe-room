package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Slider;
import javafx.scene.control.ToggleButton;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager;

public class SettingsController {
  @FXML private Button backButton;
  @FXML private Button applyButton;
  @FXML private Slider volumeSlider;
  @FXML private ToggleButton ttsToggleButton;
  @FXML private ChoiceBox resolutionButton;

  public void initialize() {}

  // to ensure that back goes to the scene it was just in, use the getPreviousScene method when
  // onClickBack is done

  @FXML
  public void onClickBack(MouseEvent event) {
    App.setUi(SceneManager.getPreviousScene());
  }
}
