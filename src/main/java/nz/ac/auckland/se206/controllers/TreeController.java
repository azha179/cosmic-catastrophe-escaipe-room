package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.Hover;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.Hud;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class TreeController {

  @FXML private ImageView note2;
  @FXML private ImageView back;

  // Timer element
  @FXML private Label timer;

  @FXML
  public void onClickNote2(MouseEvent event) {
    GameState.note2Found = true;
    if (GameState.note1Found) {
      // reset current hint in rocket
      RocketController rocketController = (RocketController) SceneManager.getController("rocket");
      rocketController.resetCurrentHint();
    }
    ImageView image = (ImageView) event.getTarget();
    image.setVisible(false);
    back.setVisible(true);
    if (!GameState.isNotesResolved) {
      Hud.updateNote2(true, "x1");
    }
  }

  public Label getTimer() {
    return timer;
  }

  @FXML
  public void clickBack(MouseEvent event) {
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
