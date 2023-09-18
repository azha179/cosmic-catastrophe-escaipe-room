package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class TitleController {

  @FXML private Pane pane;

  public void initialize() {}

  @FXML
  public void onMouseClick(MouseEvent event) {
    switchToMenu();
  }

  @FXML
  public void onKeyPress(KeyEvent event) {
    switchToMenu();
  }

  private void switchToMenu() {
    App.setUi(AppUi.MENU);
  }
}
