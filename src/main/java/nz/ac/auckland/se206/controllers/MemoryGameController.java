package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.ButtonSequence;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class MemoryGameController {

  @FXML private ImageView back;
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

  private void switchToRocket() {
    App.setUi(AppUi.ROCKET_INTERIOR);
  }

  @FXML
  private void pressButton(MouseEvent event) {

    ImageView image = (ImageView) event.getTarget();

    // button turns green when pressed
    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setHue(-0.4);
    image.setEffect(colorAdjust);
  }

  @FXML
  private void releaseButton(MouseEvent event) {

    ImageView image = (ImageView) event.getTarget();

    // button turns to original when released
    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setHue(0);
    image.setEffect(colorAdjust);

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
}
