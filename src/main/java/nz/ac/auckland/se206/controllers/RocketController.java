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
import javafx.scene.control.Label;
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
import nz.ac.auckland.se206.CountDownTimer;
import nz.ac.auckland.se206.GameSettings;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.GptActions;
import nz.ac.auckland.se206.Hover;
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
  @FXML private Label hintsLabel;

  // HUD Elements
  @FXML private ImageView settingButton;
  private ArrayList<ImageView> hudElements;

  // Meow Pad
  @FXML private Rectangle memoryGameRectangle;
  @FXML private Rectangle leftMeowPad;
  @FXML private Rectangle rightMeowPad;
  @FXML private Circle leftActivateCircle;
  @FXML private Circle rightActivateCircle;
  private boolean isLeftMeowPadPressed = false;
  private Timeline leftMeowPadPressTimer;
  private boolean isRightMeowPadpressed = false;
  private int rightMeowPadCount;

  // Task Log
  @FXML private ImageView log;
  @FXML private Rectangle logBackground;
  @FXML private Rectangle logHover;
  @FXML private Pane logPane;
  @FXML private CheckBox task1;
  @FXML private CheckBox task2;
  @FXML private CheckBox task3;
  private ArrayList<CheckBox> taskList;

  // Timer element
  @FXML private Label timer;

  private boolean isRoomFirstEntered = false;

  public void initialize() {
    hudElements = new ArrayList<ImageView>();

    taskList = new ArrayList<CheckBox>();
    taskList.add(task1);
    taskList.add(task2);
    taskList.add(task3);

    initialiseLeftMeowPad();

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

  public Label getTimer() {
    return timer;
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

  @FXML
  public void clickNote1Return(MouseEvent event) {}

  @FXML
  public void clickNote2Return(MouseEvent event) {}

  private void switchToRoom() {
    App.setUi(AppUi.MAIN_ROOM);
  }

  @FXML
  public void onPressRightMeowPad(MouseEvent event) {
    if (!GameState.isRightMeowPadActivated) {
      rightMeowPadCount = 0;
      isRightMeowPadpressed = true;
    }
  }

  @FXML
  public void onReleaseRightMeowPad(MouseEvent event) {
    isRightMeowPadpressed = false;
  }

  @FXML
  public void onMouseDrag(MouseEvent event) {
    if (isRightMeowPadpressed && !GameState.isRightMeowPadActivated) {
      rightMeowPadCount++;
      System.out.println(rightMeowPadCount);
      if (rightMeowPadCount == 150) {
        handleRightMeowPadActivation();
      }
    }
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

  @FXML
  public void onMouseRectangle(MouseEvent event) {}

  @FXML
  public void offMouseRectangle(MouseEvent event) {}

  // Dependent on the state of the rightpad, the memory game will be revealed
  private void handleRightMeowPadActivation() {
    GameState.isRightMeowPadActivated = true;

    // Generate message
    // only if both notes are found AND left meow pad is not activated
    if (GameState.note1Found && GameState.note2Found && !GameState.isLeftMeowPadActivated) {
      // Hide chat
      hideChat();
      // Initiate first message from GPT
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
              // depends on difficulty
              if (GameSettings.difficulty == GameSettings.GameDifficulty.HARD) {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage(
                            "user", GptPromptEngineering.getRightPadCompleteMessageHard()),
                        GptActions.chatCompletionRequest3);
              } else {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage("user", GptPromptEngineering.getRightPadCompleteMessage()),
                        GptActions.chatCompletionRequest3);
              }

              Platform.runLater(
                  () -> {
                    // Set chat message to text area
                    GptActions.setChatMessage(chatMessage, catTextArea);
                    // Show chat
                    showChat();
                  });

              return null;
            }
          };

      Thread initiateDeviceThread = new Thread(initiateDeviceTask);
      initiateDeviceThread.start();
    }

    System.out.println("right Meow pad activated");
    // If the task has been done, then it activates
    rightActivateCircle.setVisible(true);
    // if both notes are activated, then the memory game is activated
    if (GameState.isLeftMeowPadActivated && GameState.isRightMeowPadActivated) {
      GameState.isNotesResolved = true;
      System.out.println("2 notes resolved");
      memoryGameRectangle.setDisable(false);
      memoryGameRectangle.setVisible(true);

      // Generate message
      // Hide chat
      hideChat();
      // Initiate first message from GPT
      Task<Void> initiateDeviceTask =
          new Task<Void>() {
            // Call GPT
            @Override
            protected Void call() throws Exception {
              // clear messages
              GptActions.clearMessages(GptActions.chatCompletionRequest3);
              GptActions.chatCompletionRequest3 =
                  new ChatCompletionRequest()
                      .setN(1)
                      .setTemperature(0.2)
                      .setTopP(0.5)
                      .setMaxTokens(100);
              ChatMessage chatMessage;
              // depends on difficulty
              if (GameSettings.difficulty == GameSettings.GameDifficulty.HARD) {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage(
                            "user", GptPromptEngineering.getBothPadCompleteMessageHard()),
                        GptActions.chatCompletionRequest3);
              } else {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage("user", GptPromptEngineering.getBothPadCompleteMessage()),
                        GptActions.chatCompletionRequest3);
              }

              Platform.runLater(
                  () -> {
                    // Set chat message to text area
                    GptActions.setChatMessage(chatMessage, catTextArea);
                    // Show chat
                    showChat();
                  });

              return null;
            }
          };

      Thread initiateDeviceThread = new Thread(initiateDeviceTask);
      initiateDeviceThread.start();
    }
  }

  // Dependent on the state of the leftpad, the memory game will be revealed
  private void handleLeftMeowPadActivation() {
    System.out.println("left Meow pad activated");
    GameState.isLeftMeowPadActivated = true;
    // Generate message
    // only if both notes are found AND right meow pad is not activated
    if (GameState.note1Found && GameState.note2Found && !GameState.isRightMeowPadActivated) {
      // Hide chat
      hideChat();
      // Initiate first message from GPT
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
              // depends on difficulty
              if (GameSettings.difficulty == GameSettings.GameDifficulty.HARD) {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage(
                            "user", GptPromptEngineering.getLeftPadCompleteMessageHard()),
                        GptActions.chatCompletionRequest3);
              } else {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage("user", GptPromptEngineering.getLeftPadCompleteMessage()),
                        GptActions.chatCompletionRequest3);
              }

              Platform.runLater(
                  () -> {
                    // Set chat message to text area
                    GptActions.setChatMessage(chatMessage, catTextArea);
                    // Show chat
                    showChat();
                  });

              return null;
            }
          };

      Thread initiateDeviceThread = new Thread(initiateDeviceTask);
      initiateDeviceThread.start();
    }

    leftActivateCircle.setVisible(true);
    // if both notes are activated, then the memory game is activated
    if (GameState.isLeftMeowPadActivated && GameState.isRightMeowPadActivated) {
      GameState.isNotesResolved = true;
      System.out.println("2 notes resolved");
      memoryGameRectangle.setDisable(false);
      memoryGameRectangle.setVisible(true);

      // Generate message
      // Hide chat
      hideChat();
      // Initiate first message from GPT
      Task<Void> initiateDeviceTask =
          new Task<Void>() {
            // Call GPT
            @Override
            protected Void call() throws Exception {
              // clear messages
              GptActions.clearMessages(GptActions.chatCompletionRequest3);
              GptActions.chatCompletionRequest3 =
                  new ChatCompletionRequest()
                      .setN(1)
                      .setTemperature(0.2)
                      .setTopP(0.5)
                      .setMaxTokens(100);
              ChatMessage chatMessage;
              // depends on difficulty
              if (GameSettings.difficulty == GameSettings.GameDifficulty.HARD) {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage(
                            "user", GptPromptEngineering.getBothPadCompleteMessageHard()),
                        GptActions.chatCompletionRequest3);
              } else {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage("user", GptPromptEngineering.getBothPadCompleteMessage()),
                        GptActions.chatCompletionRequest3);
              }

              Platform.runLater(
                  () -> {
                    // Set chat message to text area
                    GptActions.setChatMessage(chatMessage, catTextArea);
                    // Show chat
                    showChat();
                  });

              return null;
            }
          };

      Thread initiateDeviceThread = new Thread(initiateDeviceTask);
      initiateDeviceThread.start();
    }
  }

  @FXML
  public void clickLaunch(MouseEvent event) {
    GameState.isGameActive = false;
    switchToWin();
    CountDownTimer.countdownTimeline.stop();
    WinController win = (WinController) SceneManager.getController("win");
    win.getResult()
        .setText("...with " + CountDownTimer.timeToString(CountDownTimer.timeLeft) + " to spare!");
  }

  private void switchToWin() {
    App.setUi(AppUi.WIN);
  }

  public ImageView getLaunch() {
    return this.launch;
  }

  /** Initialise cat response upon entering the rocket for the first time. */
  public void catInitialise() {
    if (isRoomFirstEntered) {
      return;
    }
    // Disable cat
    catImageActive.setDisable(true);
    // Hide return button
    back.setVisible(false);
    // Hide chat pane
    chatPane.setVisible(false);
    // Hide reply area
    replyTextField.setVisible(false);
    replyImage.setVisible(false);
    replyRectangle.setVisible(false);

    // Initiate first message from GPT

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
            // If no notes are found, if note 1 only is found, if note 2 only is found, if both
            // notes
            if (!GameState.note1Found && !GameState.note2Found) {
              // depends on difficulty
              if (GameSettings.difficulty == GameSettings.GameDifficulty.HARD) {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage(
                            "user", GptPromptEngineering.getFirstEnterRocketMessageHard()),
                        GptActions.chatCompletionRequest3);
              } else {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage("user", GptPromptEngineering.getFirstEnterRocketMessage()),
                        GptActions.chatCompletionRequest3);
              }
            } else if (GameState.note1Found && !GameState.note2Found) {
              // depends on difficulty
              if (GameSettings.difficulty == GameSettings.GameDifficulty.HARD) {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage(
                            "user",
                            GptPromptEngineering.getFirstEnterRocketMessageNoteOneFoundHard()),
                        GptActions.chatCompletionRequest3);
              } else {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage(
                            "user", GptPromptEngineering.getFirstEnterRocketMessageNoteOneFound()),
                        GptActions.chatCompletionRequest3);
              }
            } else if (!GameState.note1Found && GameState.note2Found) {
              // depends on difficulty
              if (GameSettings.difficulty == GameSettings.GameDifficulty.HARD) {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage(
                            "user",
                            GptPromptEngineering.getFirstEnterRocketMessageNoteTwoFoundHard()),
                        GptActions.chatCompletionRequest3);
              } else {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage(
                            "user", GptPromptEngineering.getFirstEnterRocketMessageNoteTwoFound()),
                        GptActions.chatCompletionRequest3);
              }
            } else {
              // depends on difficulty
              if (GameSettings.difficulty == GameSettings.GameDifficulty.HARD) {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage(
                            "user",
                            GptPromptEngineering.getFirstEnterRocketMessageBothNotesFoundHard()),
                        GptActions.chatCompletionRequest3);
              } else {
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage(
                            "user",
                            GptPromptEngineering.getFirstEnterRocketMessageBothNotesFound()),
                        GptActions.chatCompletionRequest3);
              }
            }

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
                  // Show return button
                  back.setVisible(true);

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
                  // Check if reply had the word 'Sure' and game difficulty is medium
                  if (lastMsg.getContent().contains("Sure")
                      && GameSettings.difficulty == GameSettings.GameDifficulty.MEDIUM) {
                    // update hints left
                    GameState.hintsLeft--;
                    // update hint label
                    GameState.updateAllHintsLabel();
                    // if hints left is 0
                    if (GameState.hintsLeft == 0) {
                      GameSettings.difficulty = GameSettings.GameDifficulty.HARD;
                      GameState.isHintUsed = true;
                      hintsUsed();
                    }
                  }
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

  // Ensure onClickSettings has the  SceneManager.getAppUi(AppUi."currentscene"); to work
  @FXML
  public void onClickSetting(MouseEvent event) {
    App.setUi(AppUi.SETTING);
    SceneManager.getAppUi(AppUi.ROCKET_INTERIOR);
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
      // check if return button is visible
      if (back.isVisible()) {
        switchToRoom();
      }
    }
  }

  // Scales the image up where the mouse is hovering over
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

  // Method to hide all the chat elements while generating
  public void hideChat() {
    // Hide catImageSleep
    catImageSleep.setVisible(false);
    // hide catImageAwoken
    catImageAwoken.setVisible(false);
    // Show catImageActive
    catImageActive.setVisible(true);
    // Change image to thinking cat
    Image image = new Image("images/ThinkingCat.png");
    catImageActive.setImage(image);
    // Disable cat
    catImageActive.setDisable(true);
    // hide return button
    back.setVisible(false);
    // hide current chat pane
    chatPane.setVisible(false);
    // hide reply area
    replyTextField.setVisible(false);
    replyImage.setVisible(false);
    replyRectangle.setVisible(false);
  }

  // Method to show all the chat elements after generating
  public void showChat() {
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
    // show return button
    back.setVisible(true);
  }

  /** Method that calls GPT when hints are used up in medium difficulty */
  public void hintsUsed() {
    // Disable cat
    catImageActive.setDisable(true);
    // change image to thinking cat
    Image image = new Image("images/ThinkingCat.png");
    catImageActive.setImage(image);
    // send message to GPT
    Task<Void> initiateDeviceTask =
        new Task<Void>() {
          // Call GPT
          @Override
          protected Void call() throws Exception {
            GptActions.chatCompletionRequest2 =
                new ChatCompletionRequest()
                    .setN(1)
                    .setTemperature(0.2)
                    .setTopP(0.5)
                    .setMaxTokens(100);
            GptActions.runGpt(
                new ChatMessage("user", GptPromptEngineering.sendHintsUsed()),
                GptActions.chatCompletionRequest2);

            Platform.runLater(
                () -> {
                  // Change image to active cat
                  Image image = new Image("images/NeutralCat.png");
                  catImageActive.setImage(image);
                  // Enable cat
                  catImageActive.setDisable(false);
                });

            return null;
          }
        };

    Thread initiateDeviceThread = new Thread(initiateDeviceTask);
    initiateDeviceThread.start();
  }

  /** Updates hint label */
  public void updateHintsLabel() {
    // If easy difficult, set label to inf.
    if (GameSettings.difficulty == GameSettings.GameDifficulty.EASY) {
      hintsLabel.setText("Hints left: inf.");
      return;
    }
    hintsLabel.setText("Hints left: " + GameState.hintsLeft);
  }
}
