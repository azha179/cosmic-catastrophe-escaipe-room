package nz.ac.auckland.se206.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.GameSettings;
import nz.ac.auckland.se206.GameSettings.GameDifficulty;
import nz.ac.auckland.se206.GameSettings.TimeLimit;
import nz.ac.auckland.se206.SceneManager.AppUi;

public class MenuController {

  @FXML private Pane pane;
  @FXML private Rectangle easy;
  @FXML private Rectangle medium;
  @FXML private Rectangle hard;
  @FXML private Rectangle two;
  @FXML private Rectangle four;
  @FXML private Rectangle six;
  @FXML private ImageView play;
  @FXML private Label difficulty;
  @FXML private Label timeLimit;

  public void initialize() {}

  @FXML
  public void onClickEasy(MouseEvent event) {
    GameSettings.difficulty = GameDifficulty.EASY;
    difficulty.setText("EASY");
  }

  @FXML
  public void onClickMedium(MouseEvent event) {
    GameSettings.difficulty = GameDifficulty.MEDIUM;
    difficulty.setText("MEDIUM");
  }

  @FXML
  public void onClickHard(MouseEvent event) {
    GameSettings.difficulty = GameDifficulty.HARD;
    difficulty.setText("HARD");
  }

  @FXML
  public void onClickTwo(MouseEvent event) {
    GameSettings.timeLimit = TimeLimit.TWO;
    timeLimit.setText("2 MIN");
  }

  @FXML
  public void onClickFour(MouseEvent event) {
    GameSettings.timeLimit = TimeLimit.FOUR;
    timeLimit.setText("4 MIN");
  }

  @FXML
  public void onClickSix(MouseEvent event) {
    GameSettings.timeLimit = TimeLimit.SIX;
    timeLimit.setText("6 MIN");
  }

  @FXML
  public void onClickPlay(MouseEvent event) {
    if (GameSettings.difficulty != null && GameSettings.timeLimit != null) {
      switchToRoom();
    }
  }

  private void switchToRoom() {
    App.setUi(AppUi.MAIN_ROOM);
  }
}
