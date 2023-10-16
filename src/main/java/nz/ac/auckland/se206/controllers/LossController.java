package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nz.ac.auckland.se206.CountDownTimer;
import nz.ac.auckland.se206.Hover;
import nz.ac.auckland.se206.TTSManager;

public class LossController {

  @FXML private ImageView exit;

  /**
   * Handles the click event for the exit button and closes the application
   *
   * @param event the mouse event
   */
  @FXML
  public void clickExit(MouseEvent event) {
    // Terminate text to speech
    TTSManager.close();

    // Terminate the timer
    if (CountDownTimer.countdownTimeline != null) {
      CountDownTimer.countdownTimeline.stop();
    }

    // Close the application
    Stage stage = (Stage) exit.getScene().getWindow();
    stage.close();
  }

  /**
   * Handles the hover event for the exit button and scales the image up
   *
   * @param event the mouse event
   */
  @FXML
  public void onHoverInteractable(MouseEvent event) {
    ImageView image = (ImageView) (Node) event.getTarget();
    Hover.scaleUp(image);
  }

  /**
   * Handles the hover event for the exit button and scales the image down
   *
   * @param event the mouse event
   */
  @FXML
  public void onLeaveInteractable(MouseEvent event) {
    ImageView image = (ImageView) (Node) event.getTarget();
    Hover.scaleDown(image);
  }
}
