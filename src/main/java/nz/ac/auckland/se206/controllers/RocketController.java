package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.Hover;
import nz.ac.auckland.se206.HudState;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class RocketController {

  @FXML private Pane pane;
  @FXML private ImageView back;
  @FXML private ImageView cat;
  @FXML private ImageView torchHud;
  @FXML private ImageView note1Hud;
  @FXML private ImageView note2Hud;
  @FXML private Pane note1Pane;
  @FXML private Pane note2Pane;
  @FXML private ImageView note1Return;
  @FXML private ImageView note2Return;
  @FXML private Rectangle torchRectangle;
  @FXML private Rectangle note1Rectangle;
  @FXML private Rectangle note2Rectangle;
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

  /**
   * Handles the click event on the note1.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickNote1(MouseEvent event) {
    if (GameState.note1Found) {
      note1Pane.setVisible(true);
    }
  }

  /**
   * Handles the click event on the note1return.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickNote1Return(MouseEvent event) {
    note1Pane.setVisible(false);
  }

  /**
   * Handles the click event on the note1.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickNote2(MouseEvent event) {
    if (GameState.note2Found) {
      note2Pane.setVisible(true);
    }
  }

  /**
   * Handles the click event on the note2return.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickNote2Return(MouseEvent event) {
    note2Pane.setVisible(false);
  }

  @FXML
  public void onMouseHub(MouseEvent event) {
    Rectangle rectangle =
        HudState.findRectangle(event, torchRectangle, note1Rectangle, note2Rectangle);
    if (rectangle != null) {
      HudState.highlightRectangle(rectangle);
      onHoverInteractable(event);
    }
  }

  @FXML
  public void offMouseHub(MouseEvent event) {
    Rectangle rectangle =
        HudState.findRectangle(event, torchRectangle, note1Rectangle, note2Rectangle);
    if (rectangle != null) {
      HudState.removeHighlightRectangle(rectangle);
      onLeaveInteractable(event);
    }
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
