package lifeform;

import exceptions.WeaponException;
import weapon.Weapon;

/**
 * Keeps track of the information associated with a simple life form.
 * Also provides the functionality related to the life form.
 */
public abstract class LifeForm {
  private String myName;
  protected int currentLifePoints;

  protected int attackStrength;
  protected int col;
  protected int row;

  protected Weapon weapon;


  public LifeForm(String name, int life) {
    this(name, life, 0);
  }

  /**
   * Create an instance
   *
   * @param name the name of the life form
   * @param life the current starting life points of the life form
   */
  public LifeForm(String name, int life, int attackDmg) {
    myName = name;
    currentLifePoints = life;
    attackStrength = attackDmg;
  }

  /**
   * Deal damage to another lifeForm.
   * @param target Which LifeForm to attack.
   */
  public void attack(LifeForm target, int distance) throws WeaponException {
    if (currentLifePoints == 0) { //Alien is dead
      return;
    }

    //has usable weapon
    if (hasWeapon() && weapon.getCurrentAmmo() > 0) {
      target.takeHit(weapon.fire(distance));
      return;
    }

    //melee
    if (distance <= 5) {
      target.takeHit(attackStrength);
      return;
    }
  }

  /**
   * If has weapon will remove it.
   * @return Weapon Lifeform had.
   */
  public Weapon dropWeapon() {
    Weapon current = weapon;
    weapon = null;

    return current;
  }




  /**
   * Get lifeForms current AttackStrength
   * @return Int of attack Strength
   */
  public int getAttackStrength() {
    return attackStrength;
  }

  /**
   * @return the amount of current life points the life form has.
   */
  public int getCurrentLifePoints() {
    return currentLifePoints;
  }

  /**
   * @return the name of the life form.
   */
  public String getName() {
    return myName;
  }

  /**
   * If Lifeform currently has weapon.
   * @return True if has weapon.
   */
  public boolean hasWeapon() {
    return weapon != null;
  }

  /**
   * Pickup new weapon.
   * Will not pick up if already have weapon.
   * @param weapon Weapon to pick up.
   * @return True if does not already have weapon.
   */
  public boolean pickUpWeapon(Weapon weapon) {
    if (hasWeapon()) {
      return false;
    }
    this.weapon = weapon;

    return true;
  }

  /**
   * Decrease LifeForms with incoming damage
   * @param damage
   */
  public void takeHit(int damage) {
    currentLifePoints = Math.max(0, currentLifePoints - damage);
  }

  public int getRow() {
    return row;
  }

  public int getCol() {
    return col;
  }

  /**
   * Set the location of a lifeform
   */

  public void setLocation(int row, int col) {
    if (row < 0 || col < 0){
      this.row = -1;
      this.col = -1;

    } else {
      this.row = row;
      this.col = col;
    }
  }





}