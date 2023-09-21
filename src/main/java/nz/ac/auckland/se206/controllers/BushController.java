package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.Hover;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class BushController {
  @FXML private ImageView backButton;
  @FXML private ImageView catToy;
  @FXML private ImageView note1;

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

  @FXML
  public void clickBack(MouseEvent event) {
    App.setUi(AppUi.MAIN_ROOM);
  }
}
