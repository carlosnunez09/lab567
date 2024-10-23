package weapon;

import com.sun.net.httpserver.Filter;
import exceptions.WeaponException;
import gameplay.SimpleTimer;
import org.junit.Assert;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestChainGun {

  @Test
  public void testDamageDistA() throws WeaponException {
    ChainGun c = new ChainGun();

    assertEquals(13, c.fire(55));
  }


  @Test
  public void testDamageDistB() throws WeaponException {
    ChainGun c = new ChainGun();

    assertEquals(1, c.fire(5));
  }


  @Test
  public void testChainInit() throws WeaponException {
    ChainGun p = new ChainGun();
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(p);
    timer.timeChanged();
    Assert.assertEquals("ChainGun", p.toString());
    int temp = p.fire(30);
    timer.timeChanged();
    Assert.assertEquals(39L, (long) p.getCurrentAmmo());
  }
  @Test
  public void testChainFire() throws WeaponException {
    ChainGun p = new ChainGun();
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(p);
    timer.timeChanged();
    Assert.assertEquals("ChainGun", p.toString());
    Assert.assertEquals(7L, (long) p.fire(30));
    timer.timeChanged();
    Assert.assertEquals(39L, (long)p.getCurrentAmmo());
    Assert.assertEquals(0L, (long)p.fire(100));
    timer.timeChanged();
    Assert.assertEquals(38L, (long)p.getCurrentAmmo());
  }


  @Test
  public void testFireOnEmpty() throws WeaponException {
    ChainGun p = new ChainGun();
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(p);
    for (int i = 40; i > 0; i--) {
      p.fire(30);
      timer.timeChanged();
    }

    Assert.assertEquals(0L, (long)p.getCurrentAmmo());
    Assert.assertEquals(0L, (long)p.fire(10));
    timer.timeChanged();
    Assert.assertEquals(0L, (long)p.getCurrentAmmo());

  }

  @Test(
          expected = WeaponException.class
  )
  public void testNegativeFire() throws WeaponException {
    ChainGun p = new ChainGun();
    p.fire(-10);
  }
}