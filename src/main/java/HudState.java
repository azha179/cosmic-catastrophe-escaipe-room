import javafx.scene.effect.ColorAdjust;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import nz.ac.auckland.se206.GameState;

public class HudState {

  Color unselected = new Color(1.0, 0.7176, 0.0, 1.0);

  // if torchHudDone so the torch has been used, it will be greyed out
  private static void torchHudDone(ImageView image) {
    if ((GameState.torchFound) && (GameState.footprintsFound) && (GameState.puzzle1)) {
      greyImage(image);
    }
  }

  private static void greyImage(ImageView image) {
    // Create a ColorAdjust effect
    ColorAdjust colorAdjust = new ColorAdjust();
    colorAdjust.setSaturation(-1.0); // Set saturation to -1
    // Apply the ColorAdjust effect to the ImageView
    image.setEffect(colorAdjust);
  }
}
