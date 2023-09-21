package nz.ac.auckland.se206;

import javafx.scene.Node;
import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HudState {

  public static void updateTorchHud(ImageView image) {
    // toy and note 1 are found
    if ((GameState.torchFound) && (GameState.footprintsFound)) {
      greyImage(image);
      image.setOpacity(0.33);
    }
    if (!GameState.torchFound) {
      image.setOpacity(0);
      image.setDisable(true);
    } else {
      image.setOpacity(1);
      image.setDisable(false);
    }
  }

  // if note1Done so the note has been used, it will be greyed out
  public static void updateNote1Hud(ImageView image) {
    if ((GameState.note1Found) && (GameState.puzzle2) && (GameState.puzzle3)) {
      greyImage(image);
    }
    if (!GameState.note1Found) {
      image.setOpacity(0);
      image.setDisable(true);
    } else {
      image.setOpacity(1);
      image.setDisable(false);
    }
  }

  // if note1Done so the note has been used, it will be greyed out
  public static void updateNote2Hud(ImageView image) {
    if ((GameState.note2Found) && (GameState.puzzle2) && (GameState.puzzle3)) {
      greyImage(image);
    }
    if (!GameState.note2Found) {
      image.setOpacity(0);
      image.setDisable(true);
    } else {
      image.setOpacity(1);
      image.setDisable(false);
    }
  }

  public static void highlightRectangle(Rectangle rectangle) {
    DropShadow dropShadowEffect = new DropShadow();
    dropShadowEffect.setColor(Color.WHITE);
    dropShadowEffect.setOffsetX(5.0);
    dropShadowEffect.setOffsetY(5.0);
    dropShadowEffect.setRadius(10.0);
    rectangle.setEffect(dropShadowEffect);
  }

  private static void greyImage(ImageView image) {
    // Create a ColorAdjust effect
    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setSaturation(-1.0);
    colorAdjust.setBrightness(-0.3);
    // Apply the ColorAdjust effect to the ImageView
    image.setEffect(colorAdjust);
  }

  public static void removeHighlightRectangle(Rectangle rectangle) {
    rectangle.setEffect(null);
  }

  public static Rectangle findRectangle(
      MouseEvent event, Rectangle torch, Rectangle note1, Rectangle note2) {
    Rectangle rectangle = new Rectangle();
    ImageView image = (ImageView) (Node) event.getTarget();
    if ("torchHud".equals(image.getId())) {
      rectangle = torch;
      if ((GameState.footprintsFound) || (!GameState.torchFound)) {
        rectangle = null;
      }
    } else if ("note1Hud".equals(image.getId())) {
      rectangle = note1;
      if ((GameState.puzzle2) && (GameState.puzzle3) || (!GameState.note1Found)) {
        rectangle = null;
      }
    } else if ("note2Hud".equals(image.getId())) {
      rectangle = note2;
      if ((GameState.puzzle2) && (GameState.puzzle3) || (!GameState.note2Found)) {
        rectangle = null;
      }
    }
    return rectangle;
  }
}
