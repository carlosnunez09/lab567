package recovery;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestRecoveryFrac {
  //@Test
  /*public void testRecoveryWhenPartialHealth()
  {
    RecoveryFractional rl= new RecoveryFractional(2.75);
    int maxLifePts = 30;
    int result = rl.calculateRecovery(25, maxLifePts);
    assertEquals(28,result);
  }*/

  @Test
  public void testRecoveryWithOver() {
    RecoveryFractional rl = new RecoveryFractional(2.5);
    int maxLifePts = 20;
    int result = rl.calculateRecovery(18, maxLifePts);
    Assert.assertEquals((long)maxLifePts, (long)result);
  }

  @Test
  public void testRecoveryFull() {
    RecoveryFractional rl = new RecoveryFractional(1.5);
    int maxLifePts = 20;
    int result = rl.calculateRecovery(20, maxLifePts);
    Assert.assertEquals((long)maxLifePts, (long)result);
  }


  @Test
  public void testRecoveryZero() {
    RecoveryFractional rl = new RecoveryFractional(0.5);
    int maxLifePts = 20;
    int result = rl.calculateRecovery(0, maxLifePts);
    Assert.assertEquals(0, (long)result);
  }

}
