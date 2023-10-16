package nz.ac.auckland.se206.controllers;

import java.lang.reflect.Field;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.ButtonSequence;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.Hover;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class MemoryGameController {

  @FXML private Pane pane;
  @FXML private ImageView back;
  @FXML private ImageView play;
  @FXML private Label text;
  @FXML private ImageView button1;
  @FXML private ImageView button2;
  @FXML private ImageView button3;
  @FXML private ImageView button4;
  @FXML private ImageView button5;
  @FXML private ImageView button6;
  @FXML private ImageView button7;
  @FXML private ImageView button8;
  @FXML private ImageView button9;
  @FXML private ImageView button10;
  @FXML private ImageView button11;
  @FXML private ImageView button12;
  @FXML private ImageView button13;
  @FXML private ImageView button14;
  @FXML private ImageView button15;
  @FXML private ImageView button16;

  // Timer element
  @FXML private Label timer;

  private int sequenceIndex;

  public void initialize() {
    // creates a random button sequence
    ButtonSequence.initialiseCorrectSequence();

    // assigns each button to values 1-16 respectively
    initialiseUserData();
  }

  public Label getTimer() {
    return timer;
  }

  @FXML
  public void clickBack(MouseEvent event) {
    switchToRocket();
  }

  @FXML
  public void clickPlay(MouseEvent event) {
    if (GameState.isAnimationRunning) {
      return;
    }
    sequenceIndex = 0;
    // plays the correct memory sequence recursively
    playSequence();
  }

  private void switchToRocket() {
    App.setUi(AppUi.ROCKET_INTERIOR);
    // gives focus to memory game
    Parent rocketScene = SceneManager.getAppUi(AppUi.ROCKET_INTERIOR);
    App.getScene().setRoot(rocketScene);
    rocketScene.requestFocus();

    // resets player sequence when exiting the memory game
    ButtonSequence.clear();
  }

  @FXML
  public void onPressKey(KeyEvent event) {

    if (event.getCode() == KeyCode.ESCAPE) {
      switchToRocket();
    }
  }

  @FXML
  private void pressButton(MouseEvent event) {

    if (GameState.isAnimationRunning || GameState.isMemoryGameResolved) {
      return;
    }

    // button turns green when pressed
    setToGreen((ImageView) event.getTarget());
  }

  @FXML
  private void releaseButton(MouseEvent event) {

    if (GameState.isAnimationRunning || GameState.isMemoryGameResolved) {
      return;
    }
    ImageView image = (ImageView) event.getTarget();

    // button turns to original when released
    setToOriginal(image);

    // retrives assigned value from button
    int button = Integer.parseInt((String) image.getUserData());
    ButtonSequence.add(button);

    // this occurs when the most recently added value does not match the value at the same position
    // in the correct sequence
    if (!ButtonSequence.correctSequence
        .get(ButtonSequence.playerSequence.size() - 1)
        .equals(ButtonSequence.playerSequence.get(ButtonSequence.playerSequence.size() - 1))) {
      ButtonSequence.clear();

      text.setText("Incorrect!");

      // Create a Timeline animation
      Timeline timeline =
          new Timeline(
              new KeyFrame(
                  Duration.seconds(2), new KeyValue(text.textProperty(), "Try again ^=_=^")),
              new KeyFrame(Duration.seconds(1), new KeyValue(text.textProperty(), "Incorrect!")));

      timeline.setCycleCount(2); // Alternate between two values
      timeline.play();
    }
    // this occurs when all the added values match the values in the correct sequence
    if (ButtonSequence.correctSequence.equals(ButtonSequence.playerSequence)) {
      GameState.isMemoryGameResolved = true;

      RocketController rocket = (RocketController) SceneManager.getController("rocket");
      rocket.getLaunch().setDisable(false);

      setAllGreen();

      text.setText(("  /|\n(˚ˎ 。7\n|、˜〵\nじしˍ,)/"));

      Timeline timeline =
          new Timeline(
              new KeyFrame(
                  Duration.seconds(2),
                  new KeyValue(text.textProperty(), ("  /|\n(˚ˎ 。7\n|、˜〵\nじしˍ,)/"))),
              new KeyFrame(Duration.seconds(1), new KeyValue(text.textProperty(), "Correct!")));

      timeline.setCycleCount(2); // Alternate between two values
      timeline.play();
    }
  }

  private void setAllGreen() {
    Field[] buttonFields = getClass().getDeclaredFields();

    for (Field field : buttonFields) {
      if (field.getName().startsWith("button")) {
        try {
          ImageView button = (ImageView) field.get(this);
          // sets each button green
          setToGreen(button);
          // disables button
          button.setDisable(true);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
      }
    }
  }

  // save the buttons that the user inupts
  private void initialiseUserData() {
    Field[] buttonFields = getClass().getDeclaredFields();
    // set user data into the array
    for (Field field : buttonFields) {
      if (field.getName().startsWith("button")) {
        try {
          int buttonNumber = Integer.parseInt(field.getName().substring(6));
          ImageView button = (ImageView) field.get(this);
          button.setUserData(Integer.toString(buttonNumber));
        } catch (IllegalAccessException | NumberFormatException e) {
          e.printStackTrace();
        }
      }
    }
  }

  // Each button has a custom variable, this returns correct button with given user date
  private ImageView findButtonByUserData(int value) {
    try {
      // change the button variable to string and set name
      String num = Integer.toString(value);
      String buttonName = "button" + num;
      // returns the imageview variable that has the name button name
      Field field = getClass().getDeclaredField(buttonName);
      field.setAccessible(true);
      return (ImageView) field.get(this);
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
  }

  private void setToGreen(ImageView image) {
    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setHue(0.56);
    colorAdjust.setBrightness(0.26);
    colorAdjust.setContrast(0.26);
    colorAdjust.setSaturation(1);
    image.setEffect(colorAdjust);
  }

  private void setToOriginal(ImageView image) {
    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setHue(0);
    image.setEffect(colorAdjust);
  }

  // The sequence of the buttons which is random
  private void playSequence() {
    // if game is already running dont play again
    GameState.isAnimationRunning = true;
    // save buttons pressed and highlight if it is pressed
    int currentInteger = ButtonSequence.correctSequence.get(sequenceIndex);
    ImageView button = findButtonByUserData(currentInteger);

    setToGreen(button);
    PauseTransition firstPause = new PauseTransition(Duration.seconds(0.6));
    firstPause.setOnFinished(
        (ActionEvent e) -> {
          setToOriginal(button);
          sequenceIndex++;
          // play again if incorrect
          if (sequenceIndex < ButtonSequence.correctSequence.size()) {
            PauseTransition secondPause = new PauseTransition(Duration.seconds(0.5));
            secondPause.setOnFinished((ActionEvent event1) -> playSequence());
            secondPause.play();
          } else {
            GameState.isAnimationRunning = false;
          }
        });
    firstPause.play();
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
