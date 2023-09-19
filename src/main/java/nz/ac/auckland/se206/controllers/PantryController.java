package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class PantryController {

  @FXML private Rectangle backButton;

  public void initialize() {}

  @FXML
  public void onClickBack(MouseEvent event) {
    switchToRoom();
  }

  private void switchToRoom() {
    App.setUi(AppUi.MAIN_ROOM);
  }
}
