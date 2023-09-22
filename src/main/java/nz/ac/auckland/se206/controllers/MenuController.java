package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameSettings;
import nz.ac.auckland.se206.GameSettings.GameDifficulty;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.Hover;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class MenuController {

  @FXML private Pane pane;
  @FXML private Rectangle easy;
  @FXML private Rectangle medium;
  @FXML private Rectangle hard;
  @FXML private ImageView easyImage;
  @FXML private ImageView mediumImage;
  @FXML private ImageView hardImage;
  @FXML private Rectangle two;
  @FXML private Rectangle four;
  @FXML private Rectangle six;
  @FXML private Label twoText;
  @FXML private Label fourText;
  @FXML private Label sixText;
  @FXML private ImageView play;
  @FXML private Label playText;
  @FXML private ImageView settingButton;

  GameDifficulty setRectangle;
  GameDifficulty currentDifficulty;
  Color clear = Color.rgb(0, 0, 0, 0.0);
  Color unselected = new Color(1.0, 0.7176, 0.0, 1.0);

  public void initialize() {}

  @FXML
  public void onClickEasy(MouseEvent event) {
    GameSettings.difficulty = GameDifficulty.EASY;

    changeColourDifficulty(easy);
  }

  @FXML
  public void onClickMedium(MouseEvent event) {
    GameSettings.difficulty = GameDifficulty.MEDIUM;

    changeColourDifficulty(medium);
  }

  @FXML
  public void onClickHard(MouseEvent event) {
    GameSettings.difficulty = GameDifficulty.HARD;

    changeColourDifficulty(hard);
  }

  @FXML
  public void onClickTwo(MouseEvent event) {
    GameSettings.timeLimit = 2;
    changeColourTime(two);
  }

  @FXML
  public void onClickFour(MouseEvent event) {
    GameSettings.timeLimit = 4;
    changeColourTime(four);
  }

  @FXML
  public void onClickSix(MouseEvent event) {
    GameSettings.timeLimit = 6;
    changeColourTime(six);
  }

  @FXML
  public void onClickPlay(MouseEvent event) {
    if (GameSettings.difficulty != null && GameSettings.timeLimit != 0) {
      switchToRoom();
    }
  }

  // Ensure onClickSettings has the  SceneManager.getAppUi(AppUi."currentscene"); to work
  @FXML
  public void onClickSetting(MouseEvent event) {
    App.setUi(AppUi.SETTING);
    SceneManager.getAppUi(AppUi.MENU);
  }

  @FXML
  public void changeColourDifficulty(Rectangle rectangle) {
    easy.setFill(unselected);
    medium.setFill(unselected);
    hard.setFill(unselected);
    easy.setEffect(null);
    medium.setEffect(null);
    hard.setEffect(null);
    selectedObject(rectangle);
  }

  @FXML
  public void changeColourTime(Rectangle rectangle) {
    two.setFill(unselected);
    four.setFill(unselected);
    six.setFill(unselected);
    two.setEffect(null);
    four.setEffect(null);
    six.setEffect(null);
    selectedObject(rectangle);
  }

  @FXML
  public void selectedObject(Rectangle rectangle) {
    DropShadow dropShadowEffect = new DropShadow();
    dropShadowEffect.setColor(Color.WHITE);
    dropShadowEffect.setOffsetX(5.0);
    dropShadowEffect.setOffsetY(5.0);
    dropShadowEffect.setRadius(10.0); // Adjust the radius as needed
    rectangle.setEffect(dropShadowEffect);
    rectangle.setFill(Color.WHITE);
    rectangle.setOpacity(0.5);
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

  @FXML
  public void onMouseEnter(MouseEvent event) {
    ImageView image = (ImageView) (Node) event.getTarget();
    image.setOpacity(0.2);
  }

  @FXML
  public void onMouseExit(MouseEvent event) {
    ImageView image = (ImageView) (Node) event.getTarget();
    image.setOpacity(1);
  }

  private void switchToRoom() {
    App.setUi(AppUi.MAIN_ROOM);
    GameState.isGameActive = true;
  }
}
