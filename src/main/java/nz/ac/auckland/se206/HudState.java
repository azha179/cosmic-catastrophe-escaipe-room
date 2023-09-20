package nz.ac.auckland.se206;

import javafx.scene.effect.ColorAdjust;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class HudState {

  // if torchHudDone so the torch has been used, it will be greyed out
  public static void torchHudDone(ImageView image) {
    if ((GameState.torchFound) && (GameState.footprintsFound) && (GameState.puzzle1)) {
      greyImage(image);
    }
    if (!GameState.torchFound) {
      image.setOpacity(0);
    } else {
      image.setOpacity(1);
    }
  }

  // if note1Done so the note has been used, it will be greyed out
  public static void note1HudDone(ImageView image) {
    if ((GameState.note1Found) && (GameState.puzzle3)) {
      greyImage(image);
    }
    if (!GameState.note1Found) {
      image.setOpacity(0);
    } else {
      image.setOpacity(1);
    }
  }

  // if note1Done so the note has been used, it will be greyed out
  public static void note2HudDone(ImageView image) {
    if ((GameState.note1Found) && (GameState.puzzle3)) {
      greyImage(image);
    }
    if (!GameState.note2Found) {
      image.setOpacity(0);
    } else {
      image.setOpacity(1);
    }
  }

  public static void highlightRectangle(Rectangle rectangle) {
    DropShadow dropShadowEffect = new DropShadow();
    dropShadowEffect.setColor(Color.WHITE);
    dropShadowEffect.setOffsetX(5.0);
    dropShadowEffect.setOffsetY(5.0);
    dropShadowEffect.setRadius(10.0); // Adjust the radius as needed
    rectangle.setEffect(dropShadowEffect);
  }

  private static void greyImage(ImageView image) {
    // Create a ColorAdjust effect
    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setSaturation(-1.0); // Set saturation to -1
    // Apply the ColorAdjust effect to the ImageView
    image.setEffect(colorAdjust);
  }

  public static void removeHighlightRectangle(Rectangle rectangle) {
    rectangle.setEffect(null);
  }
}
