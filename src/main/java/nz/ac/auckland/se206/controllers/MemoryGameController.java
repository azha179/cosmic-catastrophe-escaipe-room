package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
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
    System.out.println("press button");
    ImageView image = (ImageView) event.getTarget();
    System.out.println(image);
  }

  @FXML
  private void releaseButton(MouseEvent event) {
    System.out.println("release button");
  }
}
