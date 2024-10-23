package lifeform;
import exceptions.RecoveryRateException;
import exceptions.WeaponException;
import gameplay.SimpleTimer;
import org.junit.Assert;
import org.junit.Test;
import recovery.RecoveryBehavior;
import recovery.RecoveryFractional;
import recovery.RecoveryLinear;

import static org.junit.Assert.assertEquals;

public class TestAlien {



  @Test
  public void testAlienInit()
  {
    Alien rl= new Alien("Bill", 25);
    assertEquals("Bill", rl.getName());
  }
  @Test
  public void testAlienLifePoints()
  {
    Alien rl= new Alien("Bill", 25);
    int results = rl.getCurrentLifePoints();
    assertEquals(25,results);
  }


  @Test
  public void testAlienRecovery() {
    RecoveryFractional rf = new RecoveryFractional(0.5);
    Alien rl = new Alien("Bill", 25, rf);
    rl.takeHit(5);
    int results = rl.getCurrentLifePoints();
    rl.recover();
    results = rl.getCurrentLifePoints();
    assertEquals(25,results);

  }

  @Test
  public void testAlienAttack() throws WeaponException {
    Alien rl = new Alien("Bill", 25);
    Alien rf = new Alien("Bob", 25);

    rl.attack(rf, 0);
    int results = rf.getCurrentLifePoints();
    assertEquals(15,results);

  }

  @Test
  public void testSetRecoveryRate() throws RecoveryRateException {
    RecoveryBehavior linear = new RecoveryLinear(3);
    Alien a = new Alien("AlienBob", 15, linear, 5);
    Assert.assertEquals(5L, (long)a.getRecoveryRate());
  }

  @Test
  public void testAlienCombatRecovery() throws RecoveryRateException {
    RecoveryBehavior linear = new RecoveryLinear(3);
    Alien a = new Alien("AlienBob", 16, linear, 2);
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(a);
    timer.timeChanged();
    a.takeHit(6);
    Assert.assertEquals(10L, (long)a.getCurrentLifePoints());
    timer.timeChanged();
    Assert.assertEquals(13L, (long)a.getCurrentLifePoints());
    timer.timeChanged();
    Assert.assertEquals(13L, (long)a.getCurrentLifePoints());
    timer.timeChanged();
    Assert.assertEquals(16L, (long)a.getCurrentLifePoints());
    a.takeHit(20);
    Assert.assertEquals(0L, (long)a.getCurrentLifePoints());
    timer.timeChanged();
    Assert.assertEquals(0L, (long)a.getCurrentLifePoints());
  }


}
