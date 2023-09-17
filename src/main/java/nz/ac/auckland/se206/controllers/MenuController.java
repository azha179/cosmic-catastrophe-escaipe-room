package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class MenuController {

  @FXML private Pane pane;
  @FXML private Button play;

  public void initialize() {}

  @FXML
  public void onClickPlay(MouseEvent event) {
    switchToRoom();
  }

  private void switchToRoom() {
    App.setUi(AppUi.ROOM);
  }
}
