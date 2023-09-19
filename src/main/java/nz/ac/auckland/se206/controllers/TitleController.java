package nz.ac.auckland.se206.controllers;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class TitleController {

  @FXML private Pane pane;
  @FXML private Label movingTextLabel;
  @FXML private Label movingTextCopyLabel;

  public void initialize() {
    pane.layoutBoundsProperty()
        .addListener(
            (observable, oldValue, newValue) -> {
              double windowWidth =
                  newValue.getWidth(); // Update window width when layout bounds change

              // Create a Timeline animation to move the text continuously
              Timeline timelineMain =
                  new Timeline(
                      new KeyFrame(
                          Duration.ZERO, new KeyValue(movingTextLabel.translateXProperty(), 0)),
                      new KeyFrame(
                          Duration.seconds(4),
                          new KeyValue(movingTextLabel.translateXProperty(), windowWidth)));

              timelineMain.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely

              // Create a second KeyFrame to reset the text position when it goes beyond the
              // window's width
              Timeline timelineCopy =
                  new Timeline(
                      new KeyFrame(
                          Duration.ZERO, new KeyValue(movingTextCopyLabel.translateXProperty(), 0)),
                      new KeyFrame(
                          Duration.seconds(4),
                          new KeyValue(movingTextCopyLabel.translateXProperty(), windowWidth)));

              timelineCopy.setCycleCount(Timeline.INDEFINITE); // Repeat indefinitely

              // Start the animation
              timelineMain.playFromStart(); // Ensure the animation starts from the beginning
              timelineCopy.playFromStart(); // Ensure the animation starts from the beginning
            });
  }

  @FXML
  public void onClickMouse(MouseEvent event) {
    switchToMenu();
  }

  @FXML
  public void onPressKey(KeyEvent event) {
    switchToMenu();
  }

  private void switchToMenu() {
    App.setUi(AppUi.MENU);
  }
}
