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
  }

  public static String recipeToString(ArrayList<ImageView> recipe) {

    String name = "";

    return name;
  }

  public static ArrayList<ImageView> reorderRecipe(ArrayList<ImageView> recipe) {

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

    return recipe;
  }
}
