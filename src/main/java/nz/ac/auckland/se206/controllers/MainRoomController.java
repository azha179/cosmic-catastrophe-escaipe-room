package nz.ac.auckland.se206.controllers;

import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
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

/** Controller class for the room view. */
public class MainRoomController {

  @FXML private Pane room;
  @FXML private ImageView roomImage;
  @FXML private ImageView rocketImage;
  @FXML private ImageView pantryImage;

  // Cat and Chat Elements
  @FXML private ImageView catImageSleep;
  @FXML private ImageView catImageAwoken;
  @FXML private ImageView catImageActive;
  @FXML private Pane chatPane;
  @FXML private TextArea catTextArea;

  // Puzzle 1 Elements
  @FXML private Pane footprintPane;
  @FXML private Pane footprint1Pane;
  @FXML private Pane footprint2Pane;
  @FXML private Pane footprint3Pane;
  @FXML private Pane footprint4Pane;
  @FXML private Pane footprint5Pane;
  @FXML private Pane footprint6Pane;
  @FXML private Pane footprint7Pane;
  @FXML private Pane footprint8Pane;
  @FXML private Pane footprint9Pane;
  @FXML private Pane footprint10Pane;
  @FXML private ImageView footprint1Image;
  @FXML private ImageView footprint2Image;
  @FXML private ImageView footprint3Image;
  @FXML private ImageView footprint4Image;
  @FXML private ImageView footprint5Image;
  @FXML private ImageView footprint6Image;
  @FXML private ImageView footprint7Image;
  @FXML private ImageView footprint8Image;
  @FXML private ImageView footprint9Image;
  @FXML private ImageView footprint10Image;

  @FXML private ImageView bushImage;
  @FXML private ImageView torchImage;

  // HUD Elements
  @FXML private ImageView torchHud;
  @FXML private Pane note1Pane;
  @FXML private Pane note2Pane;
  @FXML private ImageView note1Hud;
  @FXML private ImageView note2Hud;
  @FXML private ImageView note1Return;
  @FXML private ImageView note2Return;
  @FXML private Rectangle torchRectangle;
  @FXML private Rectangle note1Rectangle;
  @FXML private Rectangle note2Rectangle;

  private ChatCompletionRequest chatCompletionRequest;

  /** Initializes the room view, it is called when the room loads. */
  public void initialize() {
    // GameState.torchFound = true;
    // GameState.footprintsFound = true;
    // GameState.puzzle1 = true;
    // GameState.note1Found = true;
    GameState.note2Found = true;
    HudState.torchHudDone(torchHud);
    HudState.note1HudDone(note1Hud);
    HudState.note2HudDone(note2Hud);
    // Initialization code goes here
  }

  /**
   * Handles the click event on cat initialise click at the start of the game.
   *
   * @param event the mouse event
   */
  @FXML
  public void catInitialise(MouseEvent catInitialise) {
    System.out.println("cat first clicked");
    // Disable cat
    catImageSleep.setDisable(true);

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

    // Hide sleeping cat
    catImageSleep.setVisible(false);
    // Show awake cat
    catImageAwoken.setVisible(true);
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
    // gives focus to rocket
    Parent rocketScene = SceneManager.getAppUi(AppUi.ROCKET_INTERIOR);
    App.getScene().setRoot(rocketScene);
    rocketScene.requestFocus();
  }

  /**
   * Handles the click event on the pantry.
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
    // gives focus to pantry
    Parent pantryScene = SceneManager.getAppUi(AppUi.PANTRY_INTERIOR);
    App.getScene().setRoot(pantryScene);
    pantryScene.requestFocus();
  }

  /**
   * Handles the click event on the torch.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickTorch(MouseEvent event) {
    System.out.println("torch clicked");
    GameState.torchFound = true;
    HudState.torchHudDone(torchHud);
    // Hide torch
    torchImage.setVisible(false);
  }

  /**
   * Handles the click event on the bush.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickBush(MouseEvent event) {
    System.out.println("bush clicked");
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
