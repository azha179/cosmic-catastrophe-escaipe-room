package nz.ac.auckland.se206;

import java.util.ArrayList;
import java.util.concurrent.ThreadLocalRandom;

public class ButtonSequence {
  public static ArrayList<Integer> correctSequence = new ArrayList<Integer>();
  public static ArrayList<Integer> playerSequence = new ArrayList<Integer>();

  public static void initialiseCorrectSequence() {

    for (int i = 0; i < 5; i++) {
      // random integer from 1 - 16
      int randomInt = ThreadLocalRandom.current().nextInt(1, 17);
      correctSequence.add(randomInt);
    }

    for (int val : correctSequence) {
      System.out.println("its " + val);
    }
  }

  public static void add(int num) {
    playerSequence.add(num);
  }

  public static void clear() {
    playerSequence.clear();
  }
}
