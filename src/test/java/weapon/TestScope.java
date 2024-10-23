package weapon;

import exceptions.AttachmentException;
import exceptions.WeaponException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestScope {

  @Test
  public void testPistolScope() throws WeaponException, AttachmentException {
    Pistol p = new Pistol();
    Scope s = new Scope(p);

    assertEquals(9, s.fire(30));
  }

  @Test
  public void testPistolScopeScope() throws WeaponException, AttachmentException {
    Pistol p = new Pistol();
    Scope s = new Scope(p);
    Scope s2 = new Scope(s);

    assertEquals(14, s2.fire(30));
  }

  @Test
  public void testChainGunPowerBoosterScope() throws WeaponException, AttachmentException {
    ChainGun c = new ChainGun();
    PowerBooster p = new PowerBooster(c);
    Scope s = new Scope(p);


    assertEquals(22, s.fire(30));
  }

  @Test
  public void testPlasmaCannonStabilizerScope() throws WeaponException, AttachmentException {
    PlasmaCannon p = new PlasmaCannon();
    Stabilizer s = new Stabilizer(p);
    Scope s2 = new Scope(s);

    assertEquals(86, s2.fire(30));
  }




  @Test
  public void testScopeRange() throws WeaponException, AttachmentException {
    MockWeapon mock = new MockWeapon();

    Scope scope = new Scope(mock);

    assertEquals(mock.getMaxRange() + 10, scope.getMaxRange());

    assertEquals(15, scope.fire(16));

    assertEquals(14, scope.fire(15));
  }


}
