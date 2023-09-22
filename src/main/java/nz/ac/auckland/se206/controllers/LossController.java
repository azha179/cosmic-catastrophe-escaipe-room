package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import nz.ac.auckland.se206.Hover;

public class LossController {

  @FXML private ImageView exit;

  @FXML
  public void clickExit(MouseEvent event) {

    Stage stage = (Stage) exit.getScene().getWindow();
    stage.close();
  }

  @FXML
  public void onHoverInteractable(MouseEvent event) {
    System.out.println("wad");
    ImageView image = (ImageView) (Node) event.getTarget();
    Hover.scaleUp(image);
  }

  @FXML
  public void onLeaveInteractable(MouseEvent event) {
    System.out.println("oo");
    ImageView image = (ImageView) (Node) event.getTarget();
    Hover.scaleDown(image);
  }
}
