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
    MEMORY_GAME,
    SETTING,
    BUSH,
    WIN
  }

  private static HashMap<AppUi, Parent> sceneMap = new HashMap<AppUi, Parent>();
  // Keep track of the current scene (used for setting back button)
  private static AppUi previousScene = null;

  public static void addAppUi(AppUi appUi, Parent parent) {
    sceneMap.put(appUi, parent);
  }

  public static Parent getAppUi(AppUi appUi) {
    previousScene = appUi;
    return sceneMap.get(appUi);
  }

  public static AppUi getPreviousScene() {
    return previousScene; // Get the current scene
  }

  // Hashmap to store FXML controllers
  private static HashMap<String, Object> controllerMap = new HashMap<String, Object>();

  public static void addController(String name, Object controller) {
    controllerMap.put(name, controller);
  }

  public static Object getController(String name) {
    return controllerMap.get(name);
  }
}
