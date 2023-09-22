package nz.ac.auckland.se206.controllers;

import java.util.ArrayList;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.GptActions;
import nz.ac.auckland.se206.Hover;
import nz.ac.auckland.se206.HudState;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.gpt.ChatMessage;
import nz.ac.auckland.se206.gpt.GptPromptEngineering;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionRequest;

public class RocketController {

  @FXML private Pane pane;
  @FXML private ImageView back;
  @FXML private ImageView temp;
  @FXML private ImageView launch;

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

  // Meow Pad
  @FXML private ImageView settingButton;
  @FXML private Rectangle memoryGameRectangle;
  @FXML private Rectangle leftMeowPad;
  @FXML private Rectangle rightMeowPad;
  @FXML private Circle leftActivateCircle;
  @FXML private Circle rightActivateCircle;
  private boolean isLeftMeowPadPressed = false;
  private Timeline leftMeowPadPressTimer;
  private Timeline rightMeowPadDragTimer;
  private long rightMeowPadDragStartTime;
  private final long RIGHT_MEOW_PAD_DRAG_THRESHOLD = 3000;

  // Task Log
  @FXML private ImageView log;
  @FXML private Rectangle logBackground;
  @FXML private Rectangle logHover;
  @FXML private Pane logPane;
  @FXML private CheckBox task1;
  @FXML private CheckBox task2;
  @FXML private CheckBox task3;
  private ArrayList<CheckBox> taskList;

  private boolean isRoomFirstEntered = false;

  public void initialize() {
    hudElements = new ArrayList<ImageView>();
    hudElements.add(torchHud);
    hudElements.add(note1Hud);
    hudElements.add(note2Hud);
    HudState.initialiseHud(hudElements);

    taskList = new ArrayList<CheckBox>();
    taskList.add(task1);
    taskList.add(task2);
    taskList.add(task3);

    initialiseLeftMeowPad();
    initialiseRightMeowPad();

    memoryGameRectangle.setDisable(true);
    memoryGameRectangle.setVisible(false);

    // Cat and Chat initialisation
    // Hide catImageSleep
    catImageSleep.setVisible(false);
    // Show catImageActive
    catImageActive.setVisible(true);
    // Change image to thinking cat
    Image image = new Image("images/ThinkingCat.png");
    catImageActive.setImage(image);
    // Disable cat
    catImageActive.setDisable(true);

    // Unfocus replyTextField when room is clicked
    pane.setOnMouseClicked(
        event -> {
          if (replyTextField.isFocused()) {
            // unfocus replyTextField
            replyTextField.getParent().requestFocus();
          }
        });
  }

  public ArrayList<ImageView> getHudElements() {
    return hudElements;
  }

  public ArrayList<CheckBox> getTasks() {
    return taskList;
  }

  public void enableLog() {
    log.setVisible(true);
  }

  @FXML
  public void clickBack(MouseEvent event) {
    switchToRoom();
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
    rightActivateCircle.setVisible(true);
    if (GameState.isLeftMeowPadActivated && GameState.isRightMeowPadActivated) {
      GameState.isNotesResolved = true;
      System.out.println("2 notes resolved");
      memoryGameRectangle.setDisable(false);
      memoryGameRectangle.setVisible(true);
      HudState.updateHudAll();
      HudState.disableHud(1);
      HudState.disableHud(2);
    }
  }

  private void handleLeftMeowPadActivation() {
    System.out.println("left Meow pad activated");
    GameState.isLeftMeowPadActivated = true;

    leftActivateCircle.setVisible(true);
    if (GameState.isLeftMeowPadActivated && GameState.isRightMeowPadActivated) {
      GameState.isNotesResolved = true;
      System.out.println("2 notes resolved");
      memoryGameRectangle.setDisable(false);
      memoryGameRectangle.setVisible(true);
      HudState.updateHudAll();
      HudState.disableHud(1);
      HudState.disableHud(2);
    }
  }

  @FXML
  public void clickLaunch(MouseEvent event) {
    GameState.isGameActive = false;
    switchToWin();
  }

  private void switchToWin() {
    App.setUi(AppUi.LOSS);
  }

  public ImageView getLaunch() {
    return this.launch;
  }

  /** Initialise cat response upon entering the pantry for the first time. */
  public void catInitialise() {
    if (isRoomFirstEntered) {
      return;
    }
    // Disable cat
    catImageActive.setDisable(true);
    // Initiate first message from GPT after cat is clicked using a thread
    Task<Void> initiateDeviceTask =
        new Task<Void>() {
          // Call GPT
          @Override
          protected Void call() throws Exception {
            GptActions.chatCompletionRequest3 =
                new ChatCompletionRequest()
                    .setN(1)
                    .setTemperature(0.2)
                    .setTopP(0.5)
                    .setMaxTokens(100);
            ChatMessage chatMessage;
            chatMessage =
                GptActions.runGpt(
                    new ChatMessage("user", GptPromptEngineering.getFirstEnterRocketMessage()),
                    GptActions.chatCompletionRequest3);

            Platform.runLater(
                () -> {
                  // Set chat message to text area
                  GptActions.setChatMessage(chatMessage, catTextArea);
                  // Make chat pane visible
                  chatPane.setVisible(true);
                  // Change image to active cat
                  Image image = new Image("images/NeutralCat.png");
                  catImageActive.setImage(image);
                  // Show reply area
                  replyTextField.setVisible(true);
                  replyImage.setVisible(true);
                  replyRectangle.setVisible(true);

                  // Enable cat
                  catImageActive.setDisable(false);
                });

            return null;
          }
        };

    Thread initiateDeviceThread = new Thread(initiateDeviceTask);
    initiateDeviceThread.start();

    isRoomFirstEntered = true;
  }

  /**
   * Handles the click event on awoken cat.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickCatAwoken(MouseEvent event) {
    System.out.println("cat clicked");
  }

  /**
   * Handles the click event on sleeping cat.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickCatSleep(MouseEvent event) {
    System.out.println("cat clicked");
    // disable cat
    catImageSleep.setDisable(true);
    // Small animation to make cat look like it is waking up using a thread
    Task<Void> catAwokenTask =
        new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            // Wait 100ms
            Thread.sleep(150);
            // Hide catImageSleep
            catImageSleep.setVisible(false);
            // Show catImageAwoken
            catImageAwoken.setVisible(true);
            // Wait 100ms
            Thread.sleep(300);
            // Hide catImageAwoken
            catImageAwoken.setVisible(false);
            // Show catImageActive
            catImageActive.setVisible(true);
            // Show/Hide chat pane
            chatPane.setVisible(!chatPane.isVisible());
            // Show/Hide reply area
            toggleReplyArea();
            // Enable cat
            catImageSleep.setDisable(false);

            return null;
          }
        };

    Thread catAwokenThread = new Thread(catAwokenTask);
    catAwokenThread.start();
  }

  /**
   * Handles the click event on active cat.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickCatActive(MouseEvent event) {
    System.out.println("cat clicked");
    // Hide active cat
    catImageActive.setVisible(false);
    // Show sleeping cat
    catImageSleep.setVisible(true);
    // Show/Hide reply area
    toggleReplyArea();
    // Show/Hide chat pane
    chatPane.setVisible(!chatPane.isVisible());
  }

  /** Method to toggle visibility of the reply area. */
  public void toggleReplyArea() {
    // Show/Hide reply area
    replyTextField.setVisible(!replyTextField.isVisible());
    replyImage.setVisible(!replyImage.isVisible());
    replyRectangle.setVisible(!replyRectangle.isVisible());
  }

  /**
   * Handles the click event on the reply button.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickReply(MouseEvent event) {
    System.out.println("reply clicked");
    // call reply method
    reply();
  }

  /**
   * Handles the key press event on the reply text field.
   *
   * @param event the key event
   */
  @FXML
  public void onPressKeyReply(KeyEvent event) {
    //
    // Check if enter key is pressed
    if (event.getCode().toString().equals("ENTER")) {
      System.out.println("enter pressed");
      // call reply method
      reply();
    }
  }

  // Method for replying
  public void reply() {
    String message = replyTextField.getText();
    if (message.trim().isEmpty()) {
      return;
    }
    // clear reply text field
    replyTextField.clear();
    // Disable reply button
    replyImage.setDisable(true);
    replyImage.setOpacity(0.5);

    // Update cat image to thinking
    Image image = new Image("images/ThinkingCat.png");
    catImageActive.setImage(image);
    // Disable cat image
    catImageActive.setDisable(true);
    // hide current chat pane
    chatPane.setVisible(false);
    // hide reply area
    toggleReplyArea();

    // Task for calling GPT
    Task<Void> replyTask =
        new Task<Void>() {
          // Call GPT
          @Override
          protected Void call() throws Exception {
            ChatMessage msg = new ChatMessage("user", message);
            ChatMessage lastMsg = GptActions.runGpt(msg, GptActions.chatCompletionRequest3);

            Platform.runLater(
                () -> {
                  // Update text area
                  GptActions.updateTextAreaAll(lastMsg);
                  // Enable reply button
                  replyImage.setDisable(false);
                  replyImage.setOpacity(1);
                  // Show chat pane
                  chatPane.setVisible(true);
                  // Update cat image to active
                  Image image = new Image("images/NeutralCat.png");
                  catImageActive.setImage(image);
                  // Enable cat image
                  catImageActive.setDisable(false);
                  // Show reply area
                  toggleReplyArea();
                });

            return null;
          }
        };

    Thread replyThread = new Thread(replyTask);
    replyThread.start();
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
  }

  /** Getter method for chatTextArea. */
  public TextArea getCatTextArea() {
    return catTextArea;
  }

  @FXML
  public void onHoverLog(MouseEvent event) {
    logPane.setVisible(true);
    logHover.setDisable(false);
  }

  @FXML
  public void onLeaveLog(MouseEvent event) {
    logPane.setVisible(false);
    logHover.setDisable(true);
  }
}
