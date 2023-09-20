package nz.ac.auckland.se206.controllers;

import java.lang.reflect.Field;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.ButtonSequence;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class MemoryGameController {

  @FXML private ImageView back;
  @FXML private ImageView play;
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

  private int sequenceIndex;

  public void initialize() {
    // creates a random button sequence
    ButtonSequence.initialiseCorrectSequence();

    // assigns each button to values 1-16 respectively
    initaliseUserData();
  }

  @FXML
  public void clickBack(MouseEvent event) {
    switchToRocket();
  }

  @FXML
  public void clickPlay(MouseEvent event) {

    // checking if the index is within the bounds of the sequence
    if (sequenceIndex >= ButtonSequence.correctSequence.size()) {
      sequenceIndex = 0;
      return;
    }

    int currentInteger = ButtonSequence.correctSequence.get(sequenceIndex);
    ImageView button = findButtonByUserData(currentInteger);
    setToGreen(button);

    PauseTransition firstPause = new PauseTransition(Duration.seconds(0.6));
    firstPause.setOnFinished(
        (ActionEvent e) -> {
          setToOriginal(button);
          sequenceIndex++;

          // checking if there are more integers to process
          if (sequenceIndex < ButtonSequence.correctSequence.size()) {
            PauseTransition secondPause = new PauseTransition(Duration.seconds(0.5));
            secondPause.setOnFinished((ActionEvent event1) -> clickPlay(null));
            secondPause.play();
          }
        });
    firstPause.play();
  }

  private void switchToRocket() {
    App.setUi(AppUi.ROCKET_INTERIOR);

    // resets player sequence when exiting the memory game
    ButtonSequence.clear();
  }

  @FXML
  private void pressButton(MouseEvent event) {

    // button turns green when pressed
    setToGreen((ImageView) event.getTarget());
  }

  @FXML
  private void releaseButton(MouseEvent event) {

    ImageView image = (ImageView) event.getTarget();

    // button turns to original when released
    setToOriginal(image);

    // retrives assigned value from button
    int button = Integer.parseInt((String) image.getUserData());
    ButtonSequence.add(button);
  }

  private void initaliseUserData() {
    button1.setUserData("1");
    button2.setUserData("2");
    button3.setUserData("3");
    button4.setUserData("4");
    button5.setUserData("5");
    button6.setUserData("6");
    button7.setUserData("7");
    button8.setUserData("8");
    button9.setUserData("9");
    button10.setUserData("10");
    button11.setUserData("11");
    button12.setUserData("12");
    button13.setUserData("13");
    button14.setUserData("14");
    button15.setUserData("15");
    button16.setUserData("16");
  }

  private ImageView findButtonByUserData(int value) {
    try {

      String num = Integer.toString(value);
      String buttonName = "button" + num;
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
    colorAdjust.setHue(-0.4);
    image.setEffect(colorAdjust);
  }

  private void setToOriginal(ImageView image) {
    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setHue(0);
    image.setEffect(colorAdjust);
  }
}
