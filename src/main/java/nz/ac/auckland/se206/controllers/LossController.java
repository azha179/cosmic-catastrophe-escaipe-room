package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nz.ac.auckland.se206.CountDownTimer;
import nz.ac.auckland.se206.Hover;
import nz.ac.auckland.se206.SceneManager;

public class LossController {

  @FXML private ImageView exit;

  @FXML
  public void clickExit(MouseEvent event) {
    // Terminate text to speech
    MainRoomController mainRoomController =
        (MainRoomController) SceneManager.getController("mainroom");
    mainRoomController.terminateTextToSpeech();

    // Terminate the timer
    if (CountDownTimer.countdownTimeline != null) {
      CountDownTimer.countdownTimeline.stop();
    }

    Stage stage = (Stage) exit.getScene().getWindow();
    stage.close();
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
