package recovery;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRecoveryNone {
  @Test
  public void testNoneRecoveryWhenMaxHealth() {
    RecoveryNone rl = new RecoveryNone();
    int maxLifePts = 30;
    int result = rl.calculateRecovery(30, maxLifePts);
    assertEquals(30, result);
  }

  @Test
  public void testNoneRecoveryWhenSomeHealth() {
    RecoveryNone rl = new RecoveryNone();
    int maxLifePts = 30;
    int result = rl.calculateRecovery(25, maxLifePts);
    assertEquals(25, result);
  }

}
