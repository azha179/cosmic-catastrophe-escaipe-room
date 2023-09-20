package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.Hover;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class RocketController {

  @FXML private Pane pane;
  @FXML private ImageView back;
  @FXML private ImageView cat;
  @FXML private ImageView temp;

  public void initialize() {}

  @FXML
  public void clickBack(MouseEvent event) {
    switchToRoom();
  }

  @FXML
  public void onClickCat(MouseEvent event) {
    System.out.println("meow");
  }

  @FXML
  public void clickTemp(MouseEvent event) {
    switchToMemoryGame();
  }

  private void switchToRoom() {
    App.setUi(AppUi.MAIN_ROOM);
  }

  private void switchToMemoryGame() {
    App.setUi(AppUi.MEMORY_GAME);
    // gives focus to memory game
    Parent memoryGameScene = SceneManager.getAppUi(AppUi.MEMORY_GAME);
    App.getScene().setRoot(memoryGameScene);
    memoryGameScene.requestFocus();
  }

  @FXML
  public void onPressKey(KeyEvent event) {

    if (event.getCode() == KeyCode.ESCAPE) {
      switchToRoom();
    }
  }

  @FXML
  public void onHoverInteractable(MouseEvent event) {

    ImageView image = (ImageView) (Node) event.getTarget();
    Hover.scaleUp(image);
  }

  @FXML
  public void onLeaveInteractable(MouseEvent event) {
    ImageView image = (ImageView) (Node) event.getTarget();
    Hover.scaleDown(image);
  }
}
