package nz.ac.auckland.se206;

import java.util.HashMap;
import javafx.scene.Parent;

// Class to manage scene changes
public class SceneManager {

  public enum AppUi {
    TITLE,
    MENU,
    MAIN_ROOM,
    ROCKET_INTERIOR,
    PANTRY_INTERIOR,
    MEMORY_GAME
  }

  private static HashMap<AppUi, Parent> sceneMap = new HashMap<AppUi, Parent>();

  public static void addAppUi(AppUi appUi, Parent parent) {
    sceneMap.put(appUi, parent);
  }

  public static Parent getAppUi(AppUi appUi) {
    return sceneMap.get(appUi);
  }
}
