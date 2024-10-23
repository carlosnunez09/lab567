package weapon;

import exceptions.WeaponException;

public class PlasmaCannon extends GenericWeapon {

  /**
   * PlasmaCannon Weapon Initialization
   */
  public PlasmaCannon() {
    baseDamage = 50;
    maxRange = 40;
    rateOfFire = 1;
    shotsLeft = rateOfFire;
    maxAmmo = 4;
    currentAmmo = maxAmmo;
  }


  @Override
  public int fire(int distance) throws WeaponException {
    return (int) (((double) getCurrentAmmo() / getMaxAmmo()) * super.fire(distance));
  }


  @Override
  public java.lang.String toString() {
    return "PlasmaCannon";
  }
}
