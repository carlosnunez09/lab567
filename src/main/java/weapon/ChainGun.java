package weapon;

import exceptions.WeaponException;

public class ChainGun extends GenericWeapon {


  /**
   * ChainGun Weapon Initialization
   */
  public ChainGun() {
    baseDamage = 15;
    maxRange = 60;
    rateOfFire = 4;
    shotsLeft = rateOfFire;
    maxAmmo = 40;
    currentAmmo = maxAmmo;
  }


  @Override
  public int fire(int distance) throws WeaponException {
    return (int) (((double)distance / getMaxRange()) * super.fire(distance));
  }


  @Override
  public java.lang.String toString() {
    return "ChainGun";
  }
}
