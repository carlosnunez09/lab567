package weapon;

import exceptions.WeaponException;

public abstract class GenericWeapon implements Weapon {

  protected int baseDamage;
  protected int currentAmmo;
  protected int maxAmmo;
  protected int maxRange;
  protected int rateOfFire;
  protected int shotsLeft;


  public GenericWeapon() {

  }

  @Override
  public void updateTime(int time) {
    shotsLeft = rateOfFire;
  }

  @Override
  public int fire(int distance) throws WeaponException {
    if (distance < 0) {
      throw new WeaponException("Negative Distance");
    }

    //no shots left in round
    if (getShotsLeft() <= 0) {
      return 0;
    }
    --shotsLeft;

    //ammo update
    if (getCurrentAmmo() <= 0) {
      return 0;
    }
    --currentAmmo;

    //no damage out of range
    if (distance > getMaxRange()) {
      return 0;
    }

    return this.getBaseDamage();
  }

  @Override
  public int getBaseDamage() {
    return baseDamage;
  }

  @Override
  public int getCurrentAmmo() {
    return currentAmmo;
  }

  @Override
  public int getMaxRange() {
    return maxRange;
  }

  @Override
  public int getNumAttachments() {
    return 0;
  }

  @Override
  public int getRateOfFire() {
    return rateOfFire;
  }

  @Override
  public int getMaxAmmo() {
    return maxAmmo;
  }

  @Override
  public int getShotsLeft() {
    return shotsLeft;
  }

  @Override
  public void reload() {
    currentAmmo = maxAmmo;
  }

}
