package environment;

import lifeform.LifeForm;

/**
 * A Cell that can hold a LifeForm.
 *
 */
public class Cell {
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


  public void removeLifeForm() {
    lifeform = null;
  }

  public boolean addWeapon(Weapon weapon){
    return false;
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

  public Weapon removeWeapon(Weapon weapon){
    return null;
  }


}
