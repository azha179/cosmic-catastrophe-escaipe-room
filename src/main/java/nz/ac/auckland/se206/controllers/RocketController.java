package nz.ac.auckland.se206.controllers;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
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
  @FXML private ImageView temp;

  // Cat and Chat Elements
  @FXML private ImageView catImageSleep;
  @FXML private ImageView catImageAwoken;
  @FXML private ImageView catImageActive;
  @FXML private Pane chatPane;
  @FXML private TextArea catTextArea;
  @FXML private TextField replyTextField;
  @FXML private ImageView replyImage;
  @FXML private Rectangle replyRectangle;

  // HUD Elements
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
  ArrayList<ImageView> hudElements;

  @FXML private ImageView settingButton;
  @FXML private Rectangle memoryGameRectangle;
  @FXML private Rectangle leftMeowPad;
  @FXML private Rectangle rightMeowPad;

  private boolean isLeftMeowPadPressed = false;
  private Timeline leftMeowPadPressTimer;

  private Timeline rightMeowPadDragTimer;
  private long rightMeowPadDragStartTime;
  private final long RIGHT_MEOW_PAD_DRAG_THRESHOLD = 3000;

  public void initialize() {
    hudElements = new ArrayList<ImageView>();
    hudElements.add(torchHud);
    hudElements.add(note1Hud);
    hudElements.add(note2Hud);
    HudState.initialiseHud(hudElements);

    initialiseLeftMeowPad();
    initialiseRightMeowPad();

    memoryGameRectangle.setDisable(true);
    memoryGameRectangle.setVisible(false);
  }

  public ArrayList<ImageView> getHudElements() {
    return hudElements;
  }

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

  @FXML
  public void onPressLeftMeowPad(MouseEvent event) {
    if (!isLeftMeowPadPressed && !GameState.isLeftMeowPadActivated) {
      leftMeowPadPressTimer.play();
      isLeftMeowPadPressed = true;
    }
  }

  @FXML
  public void onReleaseLeftMeowPad(MouseEvent event) {
    leftMeowPadPressTimer.stop();
    isLeftMeowPadPressed = false;
  }

  private void handleRightMeowPadActivation() {
    GameState.isRightMeowPadActivated = true;
    System.out.println("right Meow pad activated");
    if (GameState.isLeftMeowPadActivated && GameState.isRightMeowPadActivated) {
      GameState.isNotesResolved = true;
      System.out.println("2 notes resolved");
      memoryGameRectangle.setDisable(false);
      memoryGameRectangle.setVisible(true);
    }
  }

  private void handleLeftMeowPadActivation() {
    System.out.println("left Meow pad activated");
    GameState.isLeftMeowPadActivated = true;
    if (GameState.isLeftMeowPadActivated && GameState.isRightMeowPadActivated) {
      GameState.isNotesResolved = true;
      System.out.println("2 notes resolved");
      memoryGameRectangle.setDisable(false);
      memoryGameRectangle.setVisible(true);
    }
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

  // Ensure onClickSettings has the  SceneManager.getAppUi(AppUi."currentscene"); to work
  @FXML
  public void onClickSetting(MouseEvent event) {
    App.setUi(AppUi.SETTING);
    SceneManager.getAppUi(AppUi.ROCKET_INTERIOR);
  }

  @FXML
  public void onHoverHud(MouseEvent event) {
    Rectangle rectangle =
        HudState.findRectangle(event, torchRectangle, note1Rectangle, note2Rectangle);
    if (rectangle != null) {
      HudState.highlightRectangle(rectangle);
      onHoverInteractable(event);
    }
  }

  @FXML
  public void onLeaveHud(MouseEvent event) {
    Rectangle rectangle =
        HudState.findRectangle(event, torchRectangle, note1Rectangle, note2Rectangle);
    if (rectangle != null) {
      HudState.removeHighlightRectangle(rectangle);
      onLeaveInteractable(event);
    }
  }

  @FXML
  public void onMouseRectangle(MouseEvent event) {
    Rectangle rectangle = (Rectangle) (Node) event.getTarget();
    HudState.highlightRectangle(rectangle);
  }

  @FXML
  public void offMouseRectangle(MouseEvent event) {
    Rectangle rectangle = (Rectangle) (Node) event.getTarget();
    HudState.removeHighlightRectangle(rectangle);
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

  private void initialiseLeftMeowPad() {
    leftMeowPadPressTimer =
        new Timeline(
            new KeyFrame(
                Duration.seconds(2),
                new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent event) {
                    handleLeftMeowPadActivation();
                  }
                }));
    leftMeowPadPressTimer.setCycleCount(1);
    leftMeowPadPressTimer.setOnFinished(
        new EventHandler<ActionEvent>() {
          @Override
          public void handle(ActionEvent event) {
            isLeftMeowPadPressed = false;
          }
        });
  }

  private void initialiseRightMeowPad() {
    rightMeowPadDragTimer =
        new Timeline(
            new KeyFrame(
                Duration.millis(100),
                event -> {
                  long currentTime = System.currentTimeMillis();
                  long dragDuration = currentTime - rightMeowPadDragStartTime;
                  if (dragDuration >= RIGHT_MEOW_PAD_DRAG_THRESHOLD) {
                    handleRightMeowPadActivation();
                  }
                }));
    rightMeowPadDragTimer.setCycleCount(Timeline.INDEFINITE);

    rightMeowPad.setOnMousePressed(
        event -> {
          rightMeowPadDragStartTime = System.currentTimeMillis();
          rightMeowPadDragTimer.play();
        });

    rightMeowPad.setOnMouseReleased(
        event -> {
          rightMeowPadDragTimer.stop();
        });
    
  /** Getter method for chatTextArea. */
  public TextArea getCatTextArea() {
    return catTextArea;
  }
}
