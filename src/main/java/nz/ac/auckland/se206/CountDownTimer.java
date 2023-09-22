package nz.ac.auckland.se206;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.util.Duration;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.controllers.BushController;
import nz.ac.auckland.se206.controllers.MainRoomController;
import nz.ac.auckland.se206.controllers.MemoryGameController;
import nz.ac.auckland.se206.controllers.PantryController;
import nz.ac.auckland.se206.controllers.RocketController;
import nz.ac.auckland.se206.controllers.SettingsController;
import nz.ac.auckland.se206.controllers.TreeController;

public class CountDownTimer {

  public static int timeLeft;
  public static Timeline countdownTimeline;

  public static void initialiseCountdownTimer() {
    timeLeft = GameSettings.timeLimit * 60;
    String formattedTime = timeToString(timeLeft);
    updateTimerAll(formattedTime);

    // creates a timeline to update the countdown timer every second
    countdownTimeline =
        new Timeline(
            new KeyFrame(
                Duration.seconds(1),
                new EventHandler<ActionEvent>() {
                  @Override
                  public void handle(ActionEvent event) {
                    if (timeLeft > 0) {
                      // updating HUD timer
                      String formattedTime = timeToString(timeLeft);
                      updateTimerAll(formattedTime);

                      timeLeft--;

                    } else {
                      // time up - player loses
                      countdownTimeline.stop();
                      GameState.isGameActive = false;
                      App.setUi(AppUi.LOSS);
                    }
                  }
                }));
    countdownTimeline.setCycleCount(Timeline.INDEFINITE);
    countdownTimeline.play();
  }

  public static String timeToString(int timeInSeconds) {
    int minutes = timeInSeconds / 60;
    int seconds = timeInSeconds % 60;

    String formattedMinutes = String.format("%02d", minutes);
    String formattedSeconds = String.format("%02d", seconds);

    return formattedMinutes + ":" + formattedSeconds;
  }

  public static void updateTimerAll(String time) {

    BushController bush = (BushController) SceneManager.getController("bush");
    bush.getTimer().setText(time);
    MemoryGameController memoryGame =
        (MemoryGameController) SceneManager.getController("memorygame");
    memoryGame.getTimer().setText(time);
    PantryController pantry = (PantryController) SceneManager.getController("pantry");
    pantry.getTimer().setText(time);
    RocketController rocket = (RocketController) SceneManager.getController("rocket");
    rocket.getTimer().setText(time);
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    mainRoom.getTimer().setText(time);
    TreeController tree = (TreeController) SceneManager.getController("tree");
    tree.getTimer().setText(time);
    SettingsController settings = (SettingsController) SceneManager.getController("settings");
    settings.getTimer().setText(time);
  }
}
