package environment;

import lifeform.LifeForm;
import weapon.Weapon;

/**
 * A Cell that can hold a LifeForm.
 *
 */
public class Cell {
  private int weaponCount;
  protected Weapon weaponOne;
  protected Weapon weaponTwo;

  /**
   * Setting initial Cell Weapon values.
   */
  public Cell() {
    weaponCount = 0;
    weaponOne = null;
    weaponTwo = null;
    lifeform = null;
  }

  /**
   * @return the LifeForm in this Cell.
   */
  private LifeForm lifeform = null;

  /**
   * @return the LifeForm in this Cell.
   */
  public LifeForm getLifeForm() {
    if (lifeform != null) {
      return lifeform;
    } else {
      return null;
    }
  }

  /**
   * Tries to add the LifeForm to the Cell. Will not add if a
   * LifeForm is already present.
   *
   * @param entity the lifeform held in the cell
   * @return true if the LifeForm was added to the Cell, false otherwise.
   */
  public boolean addLifeForm(LifeForm entity) {
    if (lifeform == null) {
      lifeform = entity;
      return true;
    } else {
      return false;
    }
  }

  /**
   * Adds weapon if there are not two already in Cell
   * @param weapon
   * @return
   */
  public boolean addWeapon(Weapon weapon) {
    if (weaponOne == null && weaponTwo != weapon) {       // If no Weapon in Cell -> add Weapon
      weaponOne = weapon;
      weaponCount++;
      return true;       // If no second Weapon AND already one Weapon -> add Weapon
    } else if (weaponTwo == null && weaponOne != weapon) {
      weaponTwo = weapon;
      weaponCount++;
      return true;
    }
    return false;
  }

  /**
   * Removes weapon from Cell
   * @param weapon
   * @return
   */
  public Weapon removeWeapon(Weapon weapon) {
    Weapon removedWeapon = null;
    // Check if first weapon matches
    if (weaponOne == weapon) {
      removedWeapon = weaponOne;
      weaponOne = null;
      weaponCount--;
      return removedWeapon;
    } else if (weaponTwo == weapon) { // Check if second weapon matches
      removedWeapon = weaponTwo;
      weaponTwo = null;
      weaponCount--;
      return removedWeapon;
    }
    return removedWeapon;
  }


  public void removeLifeForm() {
    lifeform = null;
  }

  public Weapon getWeapon1() {
    return weaponOne;
  }

  public Weapon getWeapon2() {
    return weaponTwo;
  }

  public int getWeaponsCount() {
    return weaponCount;
  }


}
