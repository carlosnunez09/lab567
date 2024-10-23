package weapon;

public class MockWeapon extends GenericWeapon {

  public MockWeapon() {
    baseDamage = 10;
    maxRange = 15;
    maxAmmo = 5;
    currentAmmo = maxAmmo;
    rateOfFire = 2;
    shotsLeft = rateOfFire;
  }

}
