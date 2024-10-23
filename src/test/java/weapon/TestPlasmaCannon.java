package weapon;

import exceptions.WeaponException;
import gameplay.SimpleTimer;
import org.junit.Assert;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPlasmaCannon {


  @Test
  public void testDamageDistA() throws WeaponException {
    PlasmaCannon p = new PlasmaCannon();

    assertEquals(50, p.fire(40));
  }


  @Test
  public void testDamageDistB() throws WeaponException {
    PlasmaCannon p = new PlasmaCannon();

    assertEquals(50, p.fire(10));
  }


  @Test
  public void testDamageAmmo() throws WeaponException {
    PlasmaCannon plasma = new PlasmaCannon();

    assertEquals(50, plasma.fire(30));

    plasma.shotsLeft = 1;

    assertEquals(37, plasma.fire(10));
  }


  @Test
  public void testPlasmaInit() throws WeaponException {
    PlasmaCannon p = new PlasmaCannon();
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(p);
    timer.timeChanged();
    Assert.assertEquals("PlasmaCannon", p.toString());
    int temp = p.fire(30);
    timer.timeChanged();
    Assert.assertEquals(3L, (long) p.getCurrentAmmo());
  }
  @Test
  public void testPlasmaFire() throws WeaponException {
    PlasmaCannon p = new PlasmaCannon();
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(p);
    timer.timeChanged();
    Assert.assertEquals("PlasmaCannon", p.toString());
    Assert.assertEquals(50L, (long) p.fire(30));
    timer.timeChanged();
    Assert.assertEquals(3L, (long)p.getCurrentAmmo());
    Assert.assertEquals(0L, (long)p.fire(100));
    timer.timeChanged();
    Assert.assertEquals(2L, (long)p.getCurrentAmmo());
  }


  @Test
  public void testFireOnEmpty() throws WeaponException {
    PlasmaCannon p = new PlasmaCannon();
    SimpleTimer timer = new SimpleTimer();
    timer.addTimeObserver(p);
    for (int i = 10; i > 0; i--) {
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
    Pistol p = new Pistol();
    p.fire(-10);
  }
}