package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class MemoryGameController {

  @FXML private ImageView back;

  public void initialize() {}

  @FXML
  public void clickBack(MouseEvent event) {
    switchToRocket();
  }

  private void switchToRocket() {
    App.setUi(AppUi.ROCKET_INTERIOR);
  }
}
