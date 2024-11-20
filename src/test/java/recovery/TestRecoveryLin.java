package recovery;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRecoveryLin {
  @Test
  public void testRecoveryHalf() {
    RecoveryLinear rl = new RecoveryLinear(3);
    int maxLifePts = 20;
    int result = rl.calculateRecovery(15, maxLifePts);
    assertEquals(18, result);
  }

  @Test
  public void testRecoveryPartial() {
    RecoveryLinear rl = new RecoveryLinear(3);
    int maxLifePts = 20;
    int result = rl.calculateRecovery(18, maxLifePts);
    Assert.assertEquals(maxLifePts, result);
  }

  @Test
  public void testRecoveryFull() {
    RecoveryLinear rl = new RecoveryLinear(3);
    int maxLifePts = 20;
    int result = rl.calculateRecovery(20, maxLifePts);
    Assert.assertEquals(maxLifePts, result);
  }


  @Test
  public void testRecoveryZero() {
    RecoveryLinear rl = new RecoveryLinear(2);
    int maxLifePts = 20;
    int result = rl.calculateRecovery(0, maxLifePts);
    Assert.assertEquals(0, result);
  }

}
