package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import java.util.HashSet;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/** Controller class for the room view. */
public class MainRoomController {

  @FXML private Pane room;
  @FXML private ImageView roomImage;
  @FXML private ImageView catImage;
  @FXML private Circle catInitial;
  @FXML private Rectangle rocket;
  @FXML private Rectangle pantryOut;
  private HashSet<Node> hoverable;

  /** Initializes the room view, it is called when the room loads. */
  public void initialize() {
    // Initialization code goes here
    hoverable = new HashSet<Node>();
    hoverable.add(rocket);
    hoverable.add(pantryOut);
  }

  /**
   * Handles the key pressed event.
   *
   * @param event the key event
   */
  @FXML
  public void onKeyPressed(KeyEvent event) {
    System.out.println("key " + event.getCode() + " pressed");
  }

  /**
   * Handles the key released event.
   *
   * @param event the key event
   */
  @FXML
  public void onKeyReleased(KeyEvent event) {
    System.out.println("key " + event.getCode() + " released");
  }

  /**
   * Displays a dialog box with the given title, header text, and message.
   *
   * @param title the title of the dialog box
   * @param headerText the header text of the dialog box
   * @param message the message content of the dialog box
   */
  private void showDialog(String title, String headerText, String message) {
    Alert alert = new Alert(Alert.AlertType.INFORMATION);
    alert.setTitle(title);
    alert.setHeaderText(headerText);
    alert.setContentText(message);
    alert.showAndWait();
  }

  /**
   * Handles the click event on the door.
   *
   * @param event the mouse event
   * @throws IOException if there is an error loading the chat view
   */
  @FXML
  public void clickDoor(MouseEvent event) throws IOException {
    System.out.println("door clicked");

    if (!GameState.isRiddleResolved) {
      showDialog("Info", "Riddle", "You need to resolve the riddle!");
      App.setRoot("chat");
      return;
    }

    if (!GameState.isKeyFound) {
      showDialog(
          "Info", "Find the key!", "You resolved the riddle, now you know where the key is.");
    } else {
      showDialog("Info", "You Won!", "Good Job!");
    }
  }

  /**
   * Handles the click event on cat initialise click at the start of the game.
   *
   * @param event the mouse event
   */
  @FXML
  public void catInitialise(MouseEvent catInitialise) {
    System.out.println("cat first clicked");
    // hide catInitial
    catInitial.setVisible(false);
    // change image
    Image image = new Image("/images/AwokenCat.png");
    catImage.setImage(image);
  }

  /**
   * Handles the click event on the rocket.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickRocket(MouseEvent event) {
    switchToRocket();
    System.out.println("rocket clicked");
  }

  private void switchToRocket() {
    App.setUi(AppUi.ROCKET_INTERIOR);
  }

  /**
   * Handles the click event on the rocket.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickPantry(MouseEvent event) {
    switchToPantry();
    System.out.println("pantry clicked");
  }

  private void switchToPantry() {
    App.setUi(AppUi.PANTRY_INTERIOR);
  }

  @FXML
  public void onHoverInteractable(MouseEvent event) {
    Node node = (Node) event.getTarget();
  }
}
