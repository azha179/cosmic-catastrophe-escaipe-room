package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.Hover;
import nz.ac.auckland.se206.HudState;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class TreeController {

  @FXML private ImageView note2;
  @FXML private ImageView back;

  @FXML
  public void onClickNote2(MouseEvent event) {
    GameState.note2Found = true;
    ImageView image = (ImageView) event.getTarget();
    image.setVisible(false);
    HudState.updateHudAll();
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
