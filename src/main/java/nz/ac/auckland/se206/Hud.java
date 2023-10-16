package nz.ac.auckland.se206;

import javafx.scene.control.Label;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import nz.ac.auckland.se206.controllers.MainRoomController;
import nz.ac.auckland.se206.controllers.PantryController;
import nz.ac.auckland.se206.controllers.RocketController;

public class Hud {

  public static void updateTorch(boolean status, String count) {

    PantryController pantry = (PantryController) SceneManager.getController("pantry");
    ImageView image = (ImageView) pantry.getHudElements().get(0);
    image.setVisible(status);
    Label label = (Label) pantry.getHudElements().get(3);
    label.setVisible(status);
    label.setText(count);
    RocketController rocket = (RocketController) SceneManager.getController("rocket");
    image = (ImageView) rocket.getHudElements().get(0);
    image.setVisible(status);
    label = (Label) rocket.getHudElements().get(3);
    label.setVisible(status);
    label.setText(count);
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    image = (ImageView) mainRoom.getHudElements().get(0);
    image.setVisible(status);
    label = (Label) mainRoom.getHudElements().get(3);
    label.setVisible(status);
    label.setText(count);
  }

  public static void updateNote1(boolean status, String count) {

    PantryController pantry = (PantryController) SceneManager.getController("pantry");
    ImageView image = (ImageView) pantry.getHudElements().get(1);
    image.setVisible(status);
    Label label = (Label) pantry.getHudElements().get(4);
    label.setVisible(status);
    label.setText(count);
    RocketController rocket = (RocketController) SceneManager.getController("rocket");
    image = (ImageView) rocket.getHudElements().get(1);
    image.setVisible(status);
    label = (Label) rocket.getHudElements().get(4);
    label.setVisible(status);
    label.setText(count);
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    image = (ImageView) mainRoom.getHudElements().get(1);
    image.setVisible(status);
    label = (Label) mainRoom.getHudElements().get(4);
    label.setVisible(status);
    label.setText(count);
  }

  public static void updateNote2(boolean status, String count) {

    PantryController pantry = (PantryController) SceneManager.getController("pantry");
    ImageView image = (ImageView) pantry.getHudElements().get(2);
    image.setVisible(status);
    Label label = (Label) pantry.getHudElements().get(5);
    label.setVisible(status);
    label.setText(count);
    RocketController rocket = (RocketController) SceneManager.getController("rocket");
    image = (ImageView) rocket.getHudElements().get(2);
    image.setVisible(status);
    label = (Label) rocket.getHudElements().get(5);
    label.setVisible(status);
    label.setText(count);
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    image = (ImageView) mainRoom.getHudElements().get(2);
    image.setVisible(status);
    label = (Label) mainRoom.getHudElements().get(5);
    label.setVisible(status);
    label.setText(count);
  }

  public static void disableTorch() {
    PantryController pantry = (PantryController) SceneManager.getController("pantry");
    ImageView image = (ImageView) pantry.getHudElements().get(0);
    image.setDisable(true);
    greyScale(image);
    RocketController rocket = (RocketController) SceneManager.getController("rocket");
    image = (ImageView) rocket.getHudElements().get(0);
    image.setDisable(true);
    greyScale(image);
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    image = (ImageView) mainRoom.getHudElements().get(0);
    image.setDisable(true);
    greyScale(image);
  }

  public static void disableNote1() {
    PantryController pantry = (PantryController) SceneManager.getController("pantry");
    ImageView image = (ImageView) pantry.getHudElements().get(1);
    image.setDisable(true);
    greyScale(image);
    RocketController rocket = (RocketController) SceneManager.getController("rocket");
    image = (ImageView) rocket.getHudElements().get(1);
    image.setDisable(true);
    greyScale(image);
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    image = (ImageView) mainRoom.getHudElements().get(1);
    image.setDisable(true);
    greyScale(image);
  }

  public static void disableNote2() {
    PantryController pantry = (PantryController) SceneManager.getController("pantry");
    ImageView image = (ImageView) pantry.getHudElements().get(2);
    image.setDisable(true);
    greyScale(image);
    RocketController rocket = (RocketController) SceneManager.getController("rocket");
    image = (ImageView) rocket.getHudElements().get(2);
    image.setDisable(true);
    greyScale(image);
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    image = (ImageView) mainRoom.getHudElements().get(2);
    image.setDisable(true);
    greyScale(image);
  }

  private static void greyScale(ImageView image) {
    ColorAdjust colourAdjust = new ColorAdjust(0, -1, -0.4, 0);
    image.setEffect(colourAdjust);
  }
}
