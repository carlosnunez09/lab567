package recovery;

import java.lang.Math;

public class RecoveryFractional extends Object implements RecoveryBehavior {
  public double fraction;

  /**
   * Create an instance
   *
   * @param recoveryFraction the input
   *
   */

  public RecoveryFractional(double recoveryFraction) {
    fraction = recoveryFraction;
  }

  /**
   * Create an instance
   *
   * @param currentLife the input
   * @param maxLife max life
   */

  public int calculateRecovery(int currentLife, int maxLife) {
    if (currentLife == 0) {
      return 0;
    }
    if (currentLife >= maxLife) {
      return maxLife;
    }
    int temp = (int) Math.ceil(currentLife * fraction);
    temp = temp + currentLife;


    //figure out why this section isnt working
    if (temp > maxLife) {
      return maxLife;
    } else {
      return temp;
    }



  }
}
