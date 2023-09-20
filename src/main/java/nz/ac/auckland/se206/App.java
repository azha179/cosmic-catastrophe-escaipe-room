package nz.ac.auckland.se206;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nz.ac.auckland.se206.SceneManager.AppUi;

/**
 * This is the entry point of the JavaFX application, while you can change this class, it should
 * remain as the class that runs the JavaFX application.
 */
public class App extends Application {

  private static Scene scene;

  public static void main(final String[] args) {
    launch();
  }

  public static void setRoot(String fxml) throws IOException {
    scene.setRoot(loadFxml(fxml));
  }

  /**
   * Sets the root of the scene to the input node.
   *
   * @param newUi
   */
  public static void setUi(AppUi newUi) {
    scene.setRoot(SceneManager.getAppUi(newUi));
  }

  public static Scene getScene() {
    return scene;
  }

  /**
   * Returns the node associated to the input file. The method expects that the file is located in
   * "src/main/resources/fxml".
   *
   * @param fxml The name of the FXML file (without extension).
   * @return The node of the input file.
   * @throws IOException If the file is not found.
   */
  private static Parent loadFxml(final String fxml) throws IOException {
    FXMLLoader loader = new FXMLLoader(App.class.getResource("/fxml/" + fxml + ".fxml"));
    Parent node = loader.load();
    // Get the controller associated to the FXML file and add it SceneManager
    SceneManager.addController(fxml, loader.getController());
    return node;
  }

  /**
   * This method is invoked when the application starts. It loads and shows the "Canvas" scene.
   *
   * @param stage The primary stage of the application.
   * @throws IOException If "src/main/resources/fxml/canvas.fxml" is not found.
   */
  @Override
  public void start(final Stage stage) throws IOException {
    SceneManager.addAppUi(AppUi.TITLE, loadFxml("title"));
    SceneManager.addAppUi(AppUi.MENU, loadFxml("menu"));
    SceneManager.addAppUi(AppUi.MAIN_ROOM, loadFxml("mainroom"));
    SceneManager.addAppUi(AppUi.ROCKET_INTERIOR, loadFxml("rocket"));
    SceneManager.addAppUi(AppUi.MEMORY_GAME, loadFxml("memorygame"));
    SceneManager.addAppUi(AppUi.PANTRY_INTERIOR, loadFxml("pantry"));
    SceneManager.addAppUi(AppUi.NOTE1, loadFxml("note1"));
    SceneManager.addAppUi(AppUi.NOTE2, loadFxml("note2"));
    scene = new Scene(SceneManager.getAppUi(AppUi.TITLE), 740, 550);

    scene.getStylesheets().add(getClass().getResource("/css/style.css").toExternalForm());
    stage.setTitle("Game");
    stage.setScene(scene);
    stage.setResizable(false);
    stage.show();

    scene.getRoot().requestFocus();
  }
}
