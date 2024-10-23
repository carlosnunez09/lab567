package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestStabilizer {


  @Test
  public void testPlasmaCannonStabilizer() throws WeaponException, AttachmentException {
    PlasmaCannon p = new PlasmaCannon();
    Stabilizer s = new Stabilizer(p);

    assertEquals(62, s.fire(30));
  }

  @Test
  public void testPlasmaCannonStabilizerStabilizer() throws WeaponException, AttachmentException {
    PlasmaCannon p = new PlasmaCannon();
    Stabilizer s = new Stabilizer(p);
    Stabilizer s2 = new Stabilizer(s);

    assertEquals(77, s2.fire(30));
  }

  @Test
  public void testPistolScopeStabilizer() throws WeaponException, AttachmentException {
    Pistol p = new Pistol();
    Scope s = new Scope(p);
    Stabilizer s2 = new Stabilizer(s);

    assertEquals(11, s2.fire(30));
  }

  @Test
  public void testChainGunPowerBoosterStabilizer() throws WeaponException, AttachmentException {
    ChainGun c = new ChainGun();
    PowerBooster p = new PowerBooster(c);
    Stabilizer s2 = new Stabilizer(p);

    assertEquals(17, s2.fire(30));
  }



  @Test
  public void testStabilizerDmg() throws WeaponException, AttachmentException {
    MockWeapon mock = new MockWeapon();

    Stabilizer stabilizer = new Stabilizer(mock);

    assertEquals(12, stabilizer.fire(10));
  }


  @Test
  public void testStabilizerAutoReload() throws WeaponException, AttachmentException {
    MockWeapon mock = new MockWeapon();
    mock.currentAmmo = 0;

    Stabilizer stabilizer = new Stabilizer(mock);

    stabilizer.fire(5);

    assertEquals(5, stabilizer.getCurrentAmmo());
  }






}
