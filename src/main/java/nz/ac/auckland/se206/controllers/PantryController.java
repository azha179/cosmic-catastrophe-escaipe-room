package nz.ac.auckland.se206.controllers;

import java.lang.reflect.Field;
import java.util.ArrayList;
import javafx.application.Platform;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import nz.ac.auckland.se206.App;
import nz.ac.auckland.se206.FoodRecipe;
import nz.ac.auckland.se206.GameState;
import nz.ac.auckland.se206.GptActions;
import nz.ac.auckland.se206.Hover;
import nz.ac.auckland.se206.HudState;
import nz.ac.auckland.se206.SceneManager;
import nz.ac.auckland.se206.SceneManager.AppUi;
import nz.ac.auckland.se206.gpt.ChatMessage;
import nz.ac.auckland.se206.gpt.GptPromptEngineering;
import nz.ac.auckland.se206.gpt.openai.ChatCompletionRequest;

public class PantryController {

  // Cat and Chat Elements
  @FXML private ImageView catImageSleep;
  @FXML private ImageView catImageAwoken;
  @FXML private ImageView catImageActive;
  @FXML private Pane chatPane;
  @FXML private TextArea catTextArea;
  @FXML private TextField replyTextField;
  @FXML private ImageView replyImage;
  @FXML private Rectangle replyRectangle;

  // HUD Elements
  @FXML private Rectangle backButton;
  @FXML private ImageView torchHud;
  @FXML private ImageView note1Hud;
  @FXML private ImageView note2Hud;
  @FXML private Pane note1Pane;
  @FXML private Pane note2Pane;
  @FXML private ImageView note1Return;
  @FXML private ImageView note2Return;
  @FXML private Rectangle torchRectangle;
  @FXML private Rectangle note1Rectangle;
  @FXML private Rectangle note2Rectangle;
  @FXML private ImageView settingButton;
  private ArrayList<ImageView> hudElements;

  // Room elements
  @FXML private Pane pane;
  @FXML private ImageView back;
  @FXML private ImageView pantryImage;
  @FXML private ImageView plantImage;

  // Food ingredients
  @FXML private ImageView ingredientMilk;
  @FXML private ImageView ingredientCheese;
  @FXML private ImageView ingredientCarrot;
  @FXML private ImageView ingredientMushroom;
  @FXML private ImageView ingredientBeer;
  @FXML private ImageView ingredientBurger;
  @FXML private ImageView ingredientToast;
  @FXML private ImageView ingredientPudding;
  @FXML private ImageView ingredientFish;
  @FXML private ImageView ingredientBanana;
  @FXML private ImageView ingredientLollipop;
  @FXML private ImageView ingredientMeat;
  @FXML private ImageView ingredientChicken;
  @FXML private ImageView ingredientEgg;
  @FXML private ImageView ingredientPear;
  @FXML private ImageView ingredientHotdog;
  @FXML private ImageView ingredientIceCream;
  @FXML private ImageView ingredientOnigiri;

  // Task Log
  @FXML private ImageView log;
  @FXML private Rectangle logBackground;
  @FXML private Rectangle logHover;
  @FXML private Pane logPane;
  @FXML private CheckBox task1;
  @FXML private CheckBox task2;
  @FXML private CheckBox task3;
  private ArrayList<CheckBox> taskList;

  public void initialize() {
    hudElements = new ArrayList<ImageView>();
    hudElements.add(torchHud);
    hudElements.add(note1Hud);
    hudElements.add(note2Hud);
    HudState.initialiseHud(hudElements);

    taskList = new ArrayList<CheckBox>();
    taskList.add(task1);
    taskList.add(task2);
    taskList.add(task3);

    // assigns a value 1-3 to each food item
    initialiseUserData();

    // adds ingredients to collections in FoodRecipe
    storeIngredients();

    // creates a random recipe the player will have to replicate
    FoodRecipe.initialiseDesiredRecipe();

    // Cat and Chat initialisation
    // Hide catImageSleep
    catImageSleep.setVisible(false);
    // Show catImageActive
    catImageActive.setVisible(true);
    // Change image to thinking cat
    Image image = new Image("images/ThinkingCat.png");
    catImageActive.setImage(image);
    // Disable cat
    catImageActive.setDisable(true);

    // Unfocus replyTextField when room is clicked
    pane.setOnMouseClicked(
        event -> {
          if (replyTextField.isFocused()) {
            // unfocus replyTextField
            replyTextField.getParent().requestFocus();
          }
        });
  }

  public ArrayList<ImageView> getHudElements() {
    return hudElements;
  }

  public ArrayList<CheckBox> getTasks() {
    return taskList;
  }

  public void enableLog() {
    log.setVisible(true);
  }

  ArrayList<ImageView> shadowArray = new ArrayList<>();

  public void dropShadow(ImageView image, String colour) {
    DropShadow dropShadow = new DropShadow();
    if (colour.equals("WHITE")) {
      dropShadow.setColor(javafx.scene.paint.Color.WHITE);
    } else {
          dropShadow.setColor(javafx.scene.paint.Color.GREEN);
        }
    dropShadow.setRadius(10.0);
    dropShadow.setOffsetX(5.0);
    dropShadow.setOffsetY(5.0);
    image.setEffect(dropShadow);
    shadowArray.add(image);
  }

  public void removeShadow() {
    for (ImageView imageView : shadowArray) {
      imageView.setEffect(null);
    }
  }

  @FXML
  public void clickIngredient(MouseEvent event) {
    ImageView ingredient = (ImageView) event.getTarget();
    if (FoodRecipe.playerRecipe.contains(ingredient) || GameState.isRecipeResolved) {
      return;
    }
    FoodRecipe.playerRecipe.add(ingredient);
    dropShadow(ingredient, "WHITE");

    if (FoodRecipe.playerRecipe.size() == 3) {
      if (FoodRecipe.checkEqual(FoodRecipe.desiredRecipe, FoodRecipe.playerRecipe)) {
        GameState.isRecipeResolved = true;

        // checking task 2 off
        MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
        mainRoom.getTasks().get(1).setSelected(true);
        RocketController rocket = (RocketController) SceneManager.getController("rocket");
        rocket.getTasks().get(1).setSelected(true);
        PantryController pantry = (PantryController) SceneManager.getController("pantry");
        pantry.getTasks().get(1).setSelected(true);

        for (ImageView desired:FoodRecipe.desiredRecipe){
          dropShadow(desired, "GREEN");
        }
        
        // enable plant
        plantImage.setDisable(false);
        // Cat response
        // Hide catImageSleep
        catImageSleep.setVisible(false);
        // hide catImageAwoken
        catImageAwoken.setVisible(false);
        // Show catImageActive
        catImageActive.setVisible(true);
        // Change image to thinking cat
        Image image = new Image("images/ThinkingCat.png");
        catImageActive.setImage(image);
        // Disable cat
        catImageActive.setDisable(true);
        // hide return button
        back.setVisible(false);
        // hide current chat pane
        chatPane.setVisible(false);
        // hide reply area
        replyTextField.setVisible(false);
        replyImage.setVisible(false);
        replyRectangle.setVisible(false);
        // Initiate first message from GPT after cat is clicked using a thread
        Task<Void> initiateDeviceTask =
            new Task<Void>() {
              // Call GPT
              @Override
              protected Void call() throws Exception {
                // clear messages
                GptActions.clearMessages(GptActions.chatCompletionRequest2);
                GptActions.chatCompletionRequest2 =
                    new ChatCompletionRequest()
                        .setN(1)
                        .setTemperature(0.2)
                        .setTopP(0.5)
                        .setMaxTokens(100);
                ChatMessage chatMessage;
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage(
                            "user", GptPromptEngineering.getFinishPantryPuzzleMessage()),
                        GptActions.chatCompletionRequest2);

                Platform.runLater(
                    () -> {
                      // Update all text areas
                      GptActions.updateTextAreaAll(chatMessage);
                      // Make chat pane visible
                      chatPane.setVisible(true);
                      // Change image to active cat
                      Image image = new Image("images/NeutralCat.png");
                      catImageActive.setImage(image);
                      // Show reply area
                      replyTextField.setVisible(true);
                      replyImage.setVisible(true);
                      replyRectangle.setVisible(true);

                      // Enable cat
                      catImageActive.setDisable(false);
                      // show return button
                      back.setVisible(true);
                    });

                return null;
              }
            };

        Thread initiateDeviceThread = new Thread(initiateDeviceTask);
        initiateDeviceThread.start();
      } else {
        FoodRecipe.playerRecipe.clear();
        removeShadow();
        // Cat response if incorrect dish
        // Hide catImageSleep
        catImageSleep.setVisible(false);
        // hide catImageAwoken
        catImageAwoken.setVisible(false);
        // Show catImageActive
        catImageActive.setVisible(true);
        // Change image to thinking cat
        Image image = new Image("images/ThinkingCat.png");
        catImageActive.setImage(image);
        // Disable cat
        catImageActive.setDisable(true);
        // hide return button
        back.setVisible(false);
        // hide current chat pane
        chatPane.setVisible(false);
        // hide reply area
        replyTextField.setVisible(false);
        replyImage.setVisible(false);
        replyRectangle.setVisible(false);
        // Initiate first message from GPT after cat is clicked using a thread
        Task<Void> initiateDeviceTask =
            new Task<Void>() {
              // Call GPT
              @Override
              protected Void call() throws Exception {
                // clear messages
                GptActions.clearMessages(GptActions.chatCompletionRequest2);
                GptActions.chatCompletionRequest2 =
                    new ChatCompletionRequest()
                        .setN(1)
                        .setTemperature(0.2)
                        .setTopP(0.5)
                        .setMaxTokens(100);
                ChatMessage chatMessage;
                String recipe = FoodRecipe.desiredRecipe.get(0).getId().substring(10).toLowerCase();
                chatMessage =
                    GptActions.runGpt(
                        new ChatMessage(
                            "user",
                            GptPromptEngineering.getWrongDishPantryMessage(
                                FoodRecipe.food, recipe)),
                        GptActions.chatCompletionRequest2);

                Platform.runLater(
                    () -> {
                      // Set chat message to text area
                      GptActions.setChatMessage(chatMessage, catTextArea);
                      // Make chat pane visible
                      chatPane.setVisible(true);
                      // Change image to active cat
                      Image image = new Image("images/NeutralCat.png");
                      catImageActive.setImage(image);
                      // Show reply area
                      replyTextField.setVisible(true);
                      replyImage.setVisible(true);
                      replyRectangle.setVisible(true);

                      // Enable cat
                      catImageActive.setDisable(false);
                      // show return button
                      back.setVisible(true);
                    });

                return null;
              }
            };

        Thread initiateDeviceThread = new Thread(initiateDeviceTask);
        initiateDeviceThread.start();
      }
    }
  }

  @FXML
  public void clickBack(MouseEvent event) {
    switchToRoom();
  }

  @FXML
  public void onPressKey(KeyEvent event) {

    if (event.getCode() == KeyCode.ESCAPE) {
      // check if return button is visible
      if (back.isVisible()) {
        switchToRoom();
      }
    }
  }

  private void switchToRoom() {
    App.setUi(AppUi.MAIN_ROOM);
  }

  /** Initialise cat response upon entering the pantry for the first time. */
  public void catInitialise() {
    if (GameState.isPantryFirstEntered) {
      return;
    }
    // Disable cat
    catImageActive.setDisable(true);
    // Hide return button
    back.setVisible(false);
    // Initiate first message from GPT after cat is clicked using a thread
    Task<Void> initiateDeviceTask =
        new Task<Void>() {
          // Call GPT
          @Override
          protected Void call() throws Exception {
            GptActions.chatCompletionRequest2 =
                new ChatCompletionRequest()
                    .setN(1)
                    .setTemperature(0.2)
                    .setTopP(0.5)
                    .setMaxTokens(100);
            ChatMessage chatMessage;
            String recipe = FoodRecipe.desiredRecipe.get(0).getId().substring(10).toLowerCase();
            chatMessage =
                GptActions.runGpt(
                    new ChatMessage(
                        "user",
                        GptPromptEngineering.getFirstEnterPantryMessage(FoodRecipe.food, recipe)),
                    GptActions.chatCompletionRequest2);

            Platform.runLater(
                () -> {
                  // Update all text areas
                  GptActions.updateTextAreaAll(chatMessage);
                  // Make chat pane visible
                  chatPane.setVisible(true);
                  // Change image to active cat
                  Image image = new Image("images/NeutralCat.png");
                  catImageActive.setImage(image);
                  // Show reply area
                  replyTextField.setVisible(true);
                  replyImage.setVisible(true);
                  replyRectangle.setVisible(true);

                  // Enable cat
                  catImageActive.setDisable(false);
                  // show return button
                  back.setVisible(true);
                });

            return null;
          }
        };

    Thread initiateDeviceThread = new Thread(initiateDeviceTask);
    initiateDeviceThread.start();

    GameState.isPantryFirstEntered = true;

    // assigning task 2
    MainRoomController mainRoom = (MainRoomController) SceneManager.getController("mainroom");
    mainRoom.enableLog();
    mainRoom.getTasks().get(1).setText("Make food");
    PantryController pantry = (PantryController) SceneManager.getController("pantry");
    pantry.enableLog();
    pantry.getTasks().get(1).setText("Make food");
    RocketController rocket = (RocketController) SceneManager.getController("rocket");
    rocket.enableLog();
    rocket.getTasks().get(1).setText("Make food");
  }

  /**
   * Handles the click event on awoken cat.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickCatAwoken(MouseEvent event) {
    System.out.println("cat clicked");
  }

  /**
   * Handles the click event on sleeping cat.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickCatSleep(MouseEvent event) {
    System.out.println("cat clicked");
    // disable cat
    catImageSleep.setDisable(true);
    // Small animation to make cat look like it is waking up using a thread
    Task<Void> catAwokenTask =
        new Task<Void>() {
          @Override
          protected Void call() throws Exception {
            // Wait 100ms
            Thread.sleep(150);
            // Hide catImageSleep
            catImageSleep.setVisible(false);
            // Show catImageAwoken
            catImageAwoken.setVisible(true);
            // Wait 100ms
            Thread.sleep(300);
            // Hide catImageAwoken
            catImageAwoken.setVisible(false);
            // Show catImageActive
            catImageActive.setVisible(true);
            // Show/Hide chat pane
            chatPane.setVisible(!chatPane.isVisible());
            // Show/Hide reply area
            toggleReplyArea();
            // Enable cat
            catImageSleep.setDisable(false);

            return null;
          }
        };

    Thread catAwokenThread = new Thread(catAwokenTask);
    catAwokenThread.start();
  }

  /**
   * Handles the click event on active cat.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickCatActive(MouseEvent event) {
    System.out.println("cat clicked");
    // Hide active cat
    catImageActive.setVisible(false);
    // Show sleeping cat
    catImageSleep.setVisible(true);
    // Show/Hide reply area
    toggleReplyArea();
    // Show/Hide chat pane
    chatPane.setVisible(!chatPane.isVisible());
  }

  /** Method to toggle visibility of the reply area. */
  public void toggleReplyArea() {
    // Show/Hide reply area
    replyTextField.setVisible(!replyTextField.isVisible());
    replyImage.setVisible(!replyImage.isVisible());
    replyRectangle.setVisible(!replyRectangle.isVisible());
  }

  /**
   * Handles the click event on the reply button.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickReply(MouseEvent event) {
    System.out.println("reply clicked");
    // call reply method
    reply();
  }

  /**
   * Handles the key press event on the reply text field.
   *
   * @param event the key event
   */
  @FXML
  public void onPressKeyReply(KeyEvent event) {
    //
    // Check if enter key is pressed
    if (event.getCode().toString().equals("ENTER")) {
      System.out.println("enter pressed");
      // call reply method
      reply();
    }
  }

  // Method for replying
  public void reply() {
    String message = replyTextField.getText();
    if (message.trim().isEmpty()) {
      return;
    }
    // clear reply text field
    replyTextField.clear();
    // Disable reply button
    replyImage.setDisable(true);
    replyImage.setOpacity(0.5);

    // Update cat image to thinking
    Image image = new Image("images/ThinkingCat.png");
    catImageActive.setImage(image);
    // Disable cat image
    catImageActive.setDisable(true);
    // hide current chat pane
    chatPane.setVisible(false);
    // hide reply area
    toggleReplyArea();

    // Task for calling GPT
    Task<Void> replyTask =
        new Task<Void>() {
          // Call GPT
          @Override
          protected Void call() throws Exception {
            ChatMessage msg = new ChatMessage("user", message);
            ChatMessage lastMsg = GptActions.runGpt(msg, GptActions.chatCompletionRequest2);

            Platform.runLater(
                () -> {
                  // Update text area
                  GptActions.updateTextAreaAll(lastMsg);
                  // Enable reply button
                  replyImage.setDisable(false);
                  replyImage.setOpacity(1);
                  // Show chat pane
                  chatPane.setVisible(true);
                  // Update cat image to active
                  Image image = new Image("images/NeutralCat.png");
                  catImageActive.setImage(image);
                  // Enable cat image
                  catImageActive.setDisable(false);
                  // Show reply area
                  toggleReplyArea();
                });

            return null;
          }
        };

    Thread replyThread = new Thread(replyTask);
    replyThread.start();
  }

  /**
   * Handles the click event on the plant.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickPlant(MouseEvent event) {

    // disable plant
    plantImage.setDisable(true);

    App.setUi(AppUi.TREE);

    // update game state
    GameState.note2Found = true;

    

    HudState.updateHudAll();
  }

  /**
   * Handles the click event on the note1.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickNote1(MouseEvent event) {
    if (GameState.note1Found) {
      note1Pane.setVisible(true);
    }
  }

  /**
   * Handles the click event on the note1return.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickNote1Return(MouseEvent event) {
    note1Pane.setVisible(false);
  }

  /**
   * Handles the click event on the note1.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickNote2(MouseEvent event) {
    if (GameState.note2Found) {
      note2Pane.setVisible(true);
    }
  }

  /**
   * Handles the click event on the note2return.
   *
   * @param event the mouse event
   */
  @FXML
  public void clickNote2Return(MouseEvent event) {
    note2Pane.setVisible(false);
  }

  // Ensure onClickSettings has the  SceneManager.getAppUi(AppUi."currentscene"); to work
  @FXML
  public void onClickSetting(MouseEvent event) {
    App.setUi(AppUi.SETTING);
    SceneManager.getAppUi(AppUi.PANTRY_INTERIOR);
  }

  @FXML
  public void onMouseHub(MouseEvent event) {
    Rectangle rectangle =
        HudState.findRectangle(event, torchRectangle, note1Rectangle, note2Rectangle);
    if (rectangle != null) {
      HudState.highlightRectangle(rectangle);
      onHoverInteractable(event);
    }
  }

  @FXML
  public void offMouseHub(MouseEvent event) {
    Rectangle rectangle =
        HudState.findRectangle(event, torchRectangle, note1Rectangle, note2Rectangle);
    if (rectangle != null) {
      HudState.removeHighlightRectangle(rectangle);
      onLeaveInteractable(event);
    }
  }

  private void initialiseUserData() {
    // 1 indicates adjective ingredient
    // 2 indicates noun ingredient
    // 3 indicates base ingredient
    ingredientMilk.setUserData(1);
    ingredientCheese.setUserData(1);
    ingredientCarrot.setUserData(2);
    ingredientMushroom.setUserData(2);
    ingredientBeer.setUserData(1);
    ingredientBurger.setUserData(3);
    ingredientToast.setUserData(3);
    ingredientPudding.setUserData(1);
    ingredientFish.setUserData(2);
    ingredientBanana.setUserData(2);
    ingredientLollipop.setUserData(1);
    ingredientMeat.setUserData(2);
    ingredientChicken.setUserData(2);
    ingredientEgg.setUserData(3);
    ingredientPear.setUserData(1);
    ingredientHotdog.setUserData(3);
    ingredientIceCream.setUserData(3);
    ingredientOnigiri.setUserData(3);
  }

  private void storeIngredients() {

    Field[] fields = PantryController.class.getDeclaredFields();
    for (Field field : fields) {
      // checks if the field is an ImageView and its name starts with "ingredient"
      if (field.getType() == ImageView.class && field.getName().startsWith("ingredient")) {
        ImageView ingredient = null;
        try {
          field.setAccessible(true);
          ingredient = (ImageView) field.get(this);
        } catch (IllegalAccessException e) {
          e.printStackTrace();
        }
        if (ingredient != null) {
          int userData = (int) ingredient.getUserData();
          if (userData == 1 || userData == 2) {
            FoodRecipe.prefixIngredient.add(ingredient);
          } else if (userData == 3) {
            FoodRecipe.baseIngredient.add(ingredient);
          }
        }
      }
    }
  }

  @FXML
  public void onHoverInteractable(MouseEvent event) {
    ImageView image = (ImageView) (Node) event.getTarget();
    Hover.scaleUp(image);
  }

  @FXML
  public void onLeaveInteractable(MouseEvent event) {
    ImageView image = (ImageView) (Node) event.getTarget();
    Hover.scaleDown(image);
  }

  /** Getter method for chatTextArea. */
  public TextArea getCatTextArea() {
    return catTextArea;
  }

  @FXML
  public void onHoverLog(MouseEvent event) {
    logPane.setVisible(true);
    logHover.setDisable(false);
  }

  @FXML
  public void onLeaveLog(MouseEvent event) {
    logPane.setVisible(false);
    logHover.setDisable(true);
  }
}
