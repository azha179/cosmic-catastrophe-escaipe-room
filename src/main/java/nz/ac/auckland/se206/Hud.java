package nz.ac.auckland.se206;

import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.controllers.MainRoomController;
import nz.ac.auckland.se206.controllers.PantryController;
import nz.ac.auckland.se206.controllers.RocketController;

public class Hud {

  public static void updateTorch(boolean status, String count) {

    PantryController pantry = (PantryController) SceneManager.getController("pantry");
    ImageView torchImage = (ImageView) pantry.getHudElements().get(0);
    torchImage.setVisible(status);
    Label torchLabel = (Label) pantry.getHudElements().get(3);
    torchLabel.setVisible(status);
    torchLabel.setText(count);
    RocketController rocket = (RocketController) SceneManager.getController("rocket");
    torchImage = (ImageView) rocket.getHudElements().get(0);
    torchImage.setVisible(status);
    torchLabel = (Label) rocket.getHudElements().get(3);
    torchLabel.setVisible(status);
    torchLabel.setText(count);
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    torchImage = (ImageView) mainRoom.getHudElements().get(0);
    torchImage.setVisible(status);
    torchLabel = (Label) mainRoom.getHudElements().get(3);
    torchLabel.setVisible(status);
    torchLabel.setText(count);
  }

  public static void updateNote1(boolean status, String count) {

    PantryController pantry = (PantryController) SceneManager.getController("pantry");
    ImageView torchImage = (ImageView) pantry.getHudElements().get(1);
    torchImage.setVisible(status);
    Label torchLabel = (Label) pantry.getHudElements().get(4);
    torchLabel.setVisible(status);
    torchLabel.setText(count);
    RocketController rocket = (RocketController) SceneManager.getController("rocket");
    torchImage = (ImageView) rocket.getHudElements().get(1);
    torchImage.setVisible(status);
    torchLabel = (Label) rocket.getHudElements().get(4);
    torchLabel.setVisible(status);
    torchLabel.setText(count);
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    torchImage = (ImageView) mainRoom.getHudElements().get(1);
    torchImage.setVisible(status);
    torchLabel = (Label) mainRoom.getHudElements().get(4);
    torchLabel.setVisible(status);
    torchLabel.setText(count);
  }

  public static void updateNote2(boolean status, String count) {

    PantryController pantry = (PantryController) SceneManager.getController("pantry");
    ImageView torchImage = (ImageView) pantry.getHudElements().get(2);
    torchImage.setVisible(status);
    Label torchLabel = (Label) pantry.getHudElements().get(5);
    torchLabel.setVisible(status);
    torchLabel.setText(count);
    RocketController rocket = (RocketController) SceneManager.getController("rocket");
    torchImage = (ImageView) rocket.getHudElements().get(2);
    torchImage.setVisible(status);
    torchLabel = (Label) rocket.getHudElements().get(5);
    torchLabel.setVisible(status);
    torchLabel.setText(count);
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    torchImage = (ImageView) mainRoom.getHudElements().get(2);
    torchImage.setVisible(status);
    torchLabel = (Label) mainRoom.getHudElements().get(5);
    torchLabel.setVisible(status);
    torchLabel.setText(count);
  }
}
