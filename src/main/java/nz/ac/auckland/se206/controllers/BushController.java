package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.Hover;
import nz.ac.auckland.se206.Hud;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class BushController {
  @FXML private ImageView backButton;
  @FXML private ImageView catToy;
  @FXML private ImageView note1;

  // Timer element
  @FXML private Label timer;

  public Label getTimer() {
    return timer;
  }

  @FXML
  public void clickToy(MouseEvent event) {
    GameState.toyFound = true;
    ImageView image = (ImageView) event.getTarget();
    image.setVisible(false);
    if (GameState.toyFound && GameState.note1Found) {
      backButton.setVisible(true);
    }
    // checking task 1 off
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    mainRoom.getTasks().get(0).setSelected(true);
    RocketController rocket = (RocketController) SceneManager.getController("rocket");
    rocket.getTasks().get(0).setSelected(true);
    PantryController pantry = (PantryController) SceneManager.getController("pantry");
    pantry.getTasks().get(0).setSelected(true);
  }

  // if the note is clicked on, it will hide from the scene builder
  @FXML
  public void clickNote1(MouseEvent event) {
    GameState.note1Found = true;
    // Reset current hint in rocket
    RocketController rocketController = (RocketController) SceneManager.getController("rocket");
    rocketController.resetCurrentHint();
    ImageView image = (ImageView) event.getTarget();
    image.setVisible(false);
    // The note is hidden and the hud is updated to contain the note
    Hud.updateNote1(true, "x1");
    // if statement ensures that the note and toy have been found before exit
    if (GameState.toyFound && GameState.note1Found) {
      backButton.setVisible(true);
    }
  }

  @FXML
  public void clickBack(MouseEvent event) {
    if ((GameState.toyFound) && (GameState.note1Found)) {
      switchToMainRoom();
    }
  }

  private void switchToMainRoom() {
    App.setUi(AppUi.MAIN_ROOM);
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
