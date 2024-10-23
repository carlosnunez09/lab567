package recovery;

public class RecoveryLinear extends Object implements RecoveryBehavior {
  public int recovery;

  /**
   * Create an instance
   *
   * @param recoveryAmount the input
   *
   */

  public RecoveryLinear(int recoveryAmount) {
    recovery = recoveryAmount;
  }


  /**
   * Create an instance
   *
   * @param currentLife the input
   * @param maxLife max Life
   */

  public int calculateRecovery(int currentLife, int maxLife) {
    if (currentLife == 0) {
      return 0;
    }

    if ((currentLife + recovery) <= maxLife) {
      return currentLife + recovery;
    } else if (currentLife > maxLife) {
      return maxLife - 1;
    } else {
      return maxLife;
    }


  }
}
