package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import gameplay.SimpleTimer;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestGenericWeapon {


  /**
   * Ammo decreases every shot even out of range
   */
  @Test
  public void ammoCountDecrease() throws WeaponException {
    MockWeapon mock = new MockWeapon();

    assertEquals(5, mock.getCurrentAmmo());

    assertEquals(10, mock.fire(15));

    assertEquals(4, mock.getCurrentAmmo());
  }


  @Test
  public void testRateOfFire() throws WeaponException {
    MockWeapon mock = new MockWeapon();

    mock.fire(15);
    mock.fire(15);

    assertEquals(3, mock.getCurrentAmmo());

    mock.fire(15);
    assertEquals(3, mock.getCurrentAmmo());

  }


  @Test
  public void testReload() throws WeaponException {
    MockWeapon mock = new MockWeapon();

    mock.fire(15);
    mock.fire(15);

    assertEquals(3, mock.getCurrentAmmo());

    mock.reload();

    assertEquals(5, mock.getCurrentAmmo());
  }

  @Test
  public void testNoAmmoNoDamage() throws WeaponException {
    MockWeapon mock = new MockWeapon();

    mock.currentAmmo = 0;

    assertEquals(0, mock.fire(15));
  }

  @Test
  public void testAmmoNoDmgOutRange() throws WeaponException {
    MockWeapon mock = new MockWeapon();

    assertEquals(0, mock.fire(16));

    assertEquals(4, mock.getCurrentAmmo());
  }

  @Test
  public void testWeaponTimeObs() throws WeaponException {
    SimpleTimer time = new SimpleTimer();

    MockWeapon mock = new MockWeapon();

    time.addTimeObserver(mock);

    mock.fire(10);
    mock.fire(10);
    mock.fire(10);

    assertEquals(3, mock.getCurrentAmmo());

    time.timeChanged();

    assertEquals(2, mock.getShotsLeft());
  }

  @Test(expected = AttachmentException.class)
  public void testTooManyAttachments() throws AttachmentException {
    MockWeapon mock = new MockWeapon();
    Scope s = new Scope(mock);
    Scope ss = new Scope(s);
    Scope sss = new Scope(ss);


  }

}
