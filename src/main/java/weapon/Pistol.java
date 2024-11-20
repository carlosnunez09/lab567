package weapon;

import exceptions.WeaponException;

public class Pistol extends GenericWeapon {

  /**
   * Pistol Weapon Initialization
   */
  public Pistol() {
    baseDamage = 10;
    maxRange = 50;
    rateOfFire = 2;
    shotsLeft = rateOfFire;
    maxAmmo = 10;
    currentAmmo = maxAmmo;
  }


  @Override
  public int fire(int distance) throws WeaponException {
    return (int) (super.fire(distance) * ((double) maxRange - distance + 10) / maxRange);
  }


  @Override
  public java.lang.String toString() {
    return "Pistol";
  }

}
