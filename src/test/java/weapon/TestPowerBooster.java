package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestPowerBooster {


  @Test
  public void testChainGunPowerBooster() throws WeaponException, AttachmentException {
    ChainGun c = new ChainGun();
    PowerBooster p = new PowerBooster(c);

    assertEquals(14, p.fire(30));
  }

  @Test
  public void testPistolScopePowerBooster() throws WeaponException, AttachmentException {
    Pistol p = new Pistol();
    Scope s = new Scope(p);
    PowerBooster power = new PowerBooster(s);

    assertEquals(18, power.fire(30));
  }

  @Test
  public void testChainGunPowerBoosterPowerBooster() throws WeaponException, AttachmentException {
    ChainGun c = new ChainGun();
    PowerBooster p = new PowerBooster(c);
    PowerBooster p2 = new PowerBooster(p);

    assertEquals(28, p2.fire(30));
  }

  @Test
  public void testPlasmaCannonStabilizerPowerBooster() throws WeaponException, AttachmentException {
    PlasmaCannon c = new PlasmaCannon();
    Stabilizer p = new Stabilizer(c);
    PowerBooster p2 = new PowerBooster(p);

    assertEquals(124, p2.fire(30));
  }


  @Test
  public void testStabilizerDmg() throws WeaponException, AttachmentException {
    MockWeapon mock = new MockWeapon();

    PowerBooster powerBooster = new PowerBooster(mock);

    assertEquals(20, powerBooster.fire(10));
  }
}
