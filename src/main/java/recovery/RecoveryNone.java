package recovery;

public class RecoveryNone implements RecoveryBehavior {
  public RecoveryNone() {

  }

  public int calculateRecovery(int currentLife, int maxLife) {
    return currentLife;
  }

}
