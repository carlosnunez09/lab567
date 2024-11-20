package weapon;

import exceptions.WeaponException;
import gameplay.SimpleTimer;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPistol {

  @Test
  public void testDamageDistA() throws WeaponException {
    Pistol p = new Pistol();

    assertEquals(4, p.fire(40));
  }


  @Test
  public void testDamageDistB() throws WeaponException {
    Pistol p = new Pistol();

    assertEquals(10, p.fire(10));
  }


  @Test
  public void testPistolInit() throws WeaponException {
    Pistol p = new Pistol();
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(p);
    timer.timeChanged();
    Assert.assertEquals("Pistol", p.toString());
    Assert.assertEquals(6L, p.fire(30));
    timer.timeChanged();
    Assert.assertEquals(9L, p.getCurrentAmmo());
  }

  @Test
  public void testPistolFire() throws WeaponException {
    Pistol p = new Pistol();
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(p);
    timer.timeChanged();
    Assert.assertEquals("Pistol", p.toString());
    Assert.assertEquals(6L, p.fire(30));
    timer.timeChanged();
    Assert.assertEquals(9L, p.getCurrentAmmo());
    Assert.assertEquals(0L, p.fire(60));
    timer.timeChanged();
    Assert.assertEquals(8L, p.getCurrentAmmo());
  }


  @Test
  public void testFireOnEmpty() throws WeaponException {
    Pistol p = new Pistol();
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(p);
    for (int i = 10; i > 0; i--) {
      p.fire(30);
      timer.timeChanged();
    }

    Assert.assertEquals(0L, p.getCurrentAmmo());
    Assert.assertEquals(0L, p.fire(10));
    timer.timeChanged();
    Assert.assertEquals(0L, p.getCurrentAmmo());

  }

  @Test(
    expected = WeaponException.class
  )
  public void testNegativeFire() throws WeaponException {
    Pistol p = new Pistol();
    p.fire(-10);
  }
}