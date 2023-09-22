package nz.ac.auckland.se206;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.ThreadLocalRandom;
import javafx.scene.image.ImageView;

public class FoodRecipe {

  public static ArrayList<ImageView> prefixIngredient = new ArrayList<ImageView>();
  public static ArrayList<ImageView> baseIngredient = new ArrayList<ImageView>();
  public static ArrayList<ImageView> desiredRecipe = new ArrayList<ImageView>();
  public static ArrayList<ImageView> playerRecipe = new ArrayList<ImageView>();
  public static String food;

  public static void initialiseDesiredRecipe() {

    // adds 1 random base ingredient
    int randomIndex = ThreadLocalRandom.current().nextInt(0, baseIngredient.size());
    desiredRecipe.add(baseIngredient.get(randomIndex));

    // adds 2 random prefix ingredients
    int firstRandomPrefixIndex;
    int secondRandomPrefixIndex;
    do {
      firstRandomPrefixIndex = ThreadLocalRandom.current().nextInt(0, prefixIngredient.size());
      secondRandomPrefixIndex = ThreadLocalRandom.current().nextInt(0, prefixIngredient.size());
    } while (firstRandomPrefixIndex == secondRandomPrefixIndex);
    desiredRecipe.add(prefixIngredient.get(firstRandomPrefixIndex));
    desiredRecipe.add(prefixIngredient.get(secondRandomPrefixIndex));

    FoodRecipe.reorderRecipe(FoodRecipe.desiredRecipe);
    food = FoodRecipe.recipeToString(FoodRecipe.desiredRecipe);
  }

  public static String recipeToString(ArrayList<ImageView> recipe) {

    String string = "";
    for (ImageView ingredient : recipe) {
      String add = ingredientToString(ingredient);
      string += add;
      if (ingredient.equals(recipe.get(2))) {
        break;
      }
      // on ingredient #1
      if (ingredient.equals(recipe.get(0))) {
        // both adjectives
        if ((int) ingredient.getUserData() == 1 && ((int) recipe.get(1).getUserData() == 1)) {
          string += "-";
          // both nouns
        } else if ((int) ingredient.getUserData() == 2
            && ((int) recipe.get(1).getUserData() == 2)) {
          string += " & ";
        } else {
          string += " ";
        }
      } else {
        string += " ";
      }
    }

    return string;
  }

  public static void reorderRecipe(ArrayList<ImageView> recipe) {

    Comparator<ImageView> comparator =
        new Comparator<ImageView>() {
          @Override
          public int compare(ImageView img1, ImageView img2) {
            int userData1 = (int) img1.getUserData();
            int userData2 = (int) img2.getUserData();
            return Integer.compare(userData1, userData2);
          }
        };

    Collections.sort(recipe, comparator);
  }

  // Takes the ingredient imageview and translates it to its string name
  public static String ingredientToString(ImageView ingredient) {

    String string = "";
    // dependent on the name of the imageview, set its string
    switch (ingredient.getId().substring(10).toLowerCase()) {
        // make it have the correct casing
      case "milk":
        string = "Milky";
        break;
      case "cheese":
        string = "Cheesy";
        break;
        // make it have the correct casing
      case "carrot":
        string = "Carrot";
        break;
      case "mushroom":
        string = "Mushroom";
        break;
      case "beer":
        string = "Tipsy";
        break;
      case "burger":
        string = "Burger";
        break;
      case "toast":
        string = "Sandwich";
        break;
        // make it have the correct casing
      case "pudding":
        string = "Jiggly";
        break;
      case "fish":
        string = "Fish";
        break;
      case "banana":
        string = "Banana";
        break;
      case "lollipop":
        string = "Sweet";
        break;
      case "meat":
        string = "Meat";
        break;
        // make it have the correct casing
      case "chicken":
        string = "Chicken";
        break;
      case "egg":
        string = "Omelette";
        break;
      case "pear":
        string = "Fruity";
        break;
        // make it have the correct casing
      case "hotdog":
        string = "Hotdog";
        break;
      case "icecream":
        string = "Ice Cream";
        break;
      case "onigiri":
        string = "Onigiri";
        break;
    }
    return string;
  }

  public static boolean checkEqual(ArrayList<ImageView> recipe1, ArrayList<ImageView> recipe2) {
    if (recipe1.size() != recipe2.size()) {
      return false;
    }

    for (ImageView ingredient : recipe1) {
      if (!recipe2.contains(ingredient)) {
        return false;
      }
    }
    return true;
  }
}
