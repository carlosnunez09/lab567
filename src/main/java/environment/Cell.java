package environment;

import lifeform.LifeForm;
import weapon.Weapon;

/**
 * A Cell that can hold a LifeForm.
 *
 */
public class Cell {
  private LifeForm entity;
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
    return false; // will update this afternoon
  }

  /**
   * Removes weapon from Cell
   * @param weapon
   * @return
   */
  public Weapon removeWeapon(Weapon weapon) {
    return null; // will update this afternoon
  }


  public void removeLifeForm() {
    lifeform = null;
  }

  public Weapon getWeapon1(){
    return null;
  }

  public Weapon getWeapon2(){
    return null;
  }

  public int getWeaponsCount(){
    return 0;
  }

}
