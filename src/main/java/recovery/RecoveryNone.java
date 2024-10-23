package recovery;

public class RecoveryNone extends Object implements RecoveryBehavior {
  public RecoveryNone() {

  }

  public int calculateRecovery(int currentLife, int maxLife) {
    return currentLife;
  }

}
