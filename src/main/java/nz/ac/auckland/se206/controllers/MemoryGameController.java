package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class MemoryGameController {

  @FXML private ImageView back;
  @FXML private ImageView button0;
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

  public void initialize() {}

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

    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setHue(-0.4);
    image.setEffect(colorAdjust);
  }

  @FXML
  private void releaseButton(MouseEvent event) {

    ImageView image = (ImageView) event.getTarget();

    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setHue(0);
    image.setEffect(colorAdjust);
  }
}
