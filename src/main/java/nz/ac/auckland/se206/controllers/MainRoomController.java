package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.GptActions;
import nz.ac.auckland.se206.Hover;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.gpt.ChatMessage;
import nz.ac.auckland.se206.gpt.GptPromptEngineering;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionRequest;

/** Controller class for the room view. */
public class MainRoomController {

  @FXML private Pane room;
  @FXML private ImageView roomImage;
  @FXML private ImageView catImageSleep;
  @FXML private ImageView catImageAwoken;
  @FXML private ImageView catImageActive;
  @FXML private ImageView rocketImage;
  @FXML private ImageView pantryImage;
  @FXML private Pane chatPane;
  @FXML private TextArea catTextArea;

  private ChatCompletionRequest chatCompletionRequest;

  /** Initializes the room view, it is called when the room loads. */
  public void initialize() {
    // Initialization code goes here
  }

  /**
   * Handles the key pressed event.
   *
   * @param event the key event
   */
  @FXML
  public void onKeyPressed(KeyEvent event) {
    System.out.println("key " + event.getCode() + " pressed");
  }

  /**
   * Handles the key released event.
   *
   * @param event the key event
   */
  @FXML
  public void onKeyReleased(KeyEvent event) {
    System.out.println("key " + event.getCode() + " released");
  }

  /**
   * Displays a dialog box with the given title, header text, and message.
   *
   * @param title the title of the dialog box
   * @param headerText the header text of the dialog box
   * @param message the message content of the dialog box
   */
  private void showDialog(String title, String headerText, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * Handles the click event on the door.
   *
   * @param event the mouse event
   * @throws IOException if there is an error loading the chat view
   */
  @FXML
  public void clickDoor(MouseEvent event) throws IOException {
    System.out.println("door clicked");

    if (!GameState.isRiddleResolved) {
      showDialog("Info", "Riddle", "You need to resolve the riddle!");
      App.setRoot("chat");
      return;
    }

    if (!GameState.isKeyFound) {
      showDialog(
          "Info", "Find the key!", "You resolved the riddle, now you know where the key is.");
    } else {
      showDialog("Info", "You Won!", "Good Job!");
    }
  }

  /**
   * Handles the click event on cat initialise click at the start of the game.
   *
   * @param event the mouse event
   */
  @FXML
  public void catInitialise(MouseEvent catInitialise) {
    System.out.println("cat first clicked");
    // Hide sleeping cat
    catImageSleep.setVisible(false);
    // Show awake cat
    catImageAwoken.setVisible(true);

    // Initiate first message from GPT after cat is clicked using a thread
    Task<Void> initiateDeviceTask =
        new Task<Void>() {
          // Call GPT
          @Override
          protected Void call() throws Exception {
            chatCompletionRequest =
                new ChatCompletionRequest()
                    .setN(1)
                    .setTemperature(0.2)
                    .setTopP(0.5)
                    .setMaxTokens(100);
            ChatMessage chatMessage;
            chatMessage =
                GptActions.runGpt(
                    new ChatMessage("user", GptPromptEngineering.getIntroductionMessage()),
                    chatCompletionRequest);

            Platform.runLater(
                () -> {
                  // Append chat message to device text area
                  GptActions.appendChatMessage(chatMessage, catTextArea);
                  // Make chat pane visible
                  chatPane.setVisible(true);
                  // Hide catImageAwoken
                  catImageAwoken.setVisible(false);
                  // Show catImageActive
                  catImageActive.setVisible(true);
                });

            return null;
          }
        };

    Thread initiateDeviceThread = new Thread(initiateDeviceTask);
    initiateDeviceThread.start();
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
   * Handles the click event on the rocket.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickRocket(MouseEvent event) {
    switchToRocket();
    System.out.println("rocket clicked");
  }

  private void switchToRocket() {
    App.setUi(AppUi.ROCKET_INTERIOR);
  }

  /**
   * Handles the click event on the rocket.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickPantry(MouseEvent event) {
    switchToPantry();
    System.out.println("pantry clicked");
  }

  private void switchToPantry() {
    App.setUi(AppUi.PANTRY_INTERIOR);
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
