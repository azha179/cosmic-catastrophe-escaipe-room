package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class RocketController {

  @FXML private Pane pane;
  @FXML private ImageView exit;
  @FXML private ImageView cat;

  public void initialize() {}

  @FXML
  public void onClickExit(MouseEvent event) {
    switchToRoom();
  }

  @FXML
  public void onClickCat(MouseEvent event) {
    System.out.println("meow");
  }

  private void switchToRoom() {
    App.setUi(AppUi.MAIN_ROOM);
  }
}
