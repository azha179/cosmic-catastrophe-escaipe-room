package nz.ac.auckland.se206.controllers;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.SceneManager.AppUi;

/** Controller class for the room view. */
public class MainRoomController {

  @FXML private Pane room;
  @FXML private ImageView roomImage;
  @FXML private ImageView catImage;
  @FXML private ImageView rocketImage;
  @FXML private ImageView pantryImage;
  @FXML private Circle catInitial;

  private double originalWidth;
  private double originalHeight;
  private double originalX;
  private double originalY;

  /** Initializes the room view, it is called when the room loads. */
  public void initialize() {
    // Initialization code goes here
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

    ImageView image = (ImageView) (Node) event.getTarget();

    originalWidth = image.getFitWidth();
    originalHeight = image.getFitHeight();
    originalX = image.getLayoutX();
    originalY = image.getLayoutY();
    double aspectRatio = originalWidth / originalHeight;
    double newWidth = originalWidth + 6;
    double newHeight = newWidth / aspectRatio;

    // Calculate the difference in width and height
    double widthDiff = newWidth - originalWidth;
    double heightDiff = newHeight - originalHeight;

    // Adjust the layout position to maintain the same center point
    image.setLayoutX(image.getLayoutX() - widthDiff / 2);
    image.setLayoutY(image.getLayoutY() - heightDiff / 2);

    image.setFitWidth(newWidth);
    image.setFitHeight(newHeight);
  }

  @FXML
  public void onLeaveInteractable(MouseEvent event) {
    ImageView image = (ImageView) (Node) event.getTarget();

    // Revert to the original width and height
    image.setFitWidth(originalWidth);
    image.setFitHeight(originalHeight);

    // Reset the layout position to its original value
    image.setLayoutX(originalX);
    image.setLayoutY(originalY);
  }
}
