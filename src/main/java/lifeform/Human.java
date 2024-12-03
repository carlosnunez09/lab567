package lifeform;


public class Human extends LifeForm {
  public String humanName;
  private int lifePoints;
  private int humanArmor;


  /**
   * Create an instance
   *
   * @param name       the name of the life for
   * @param lifepoints the current starting life points of the life form
   * @param armor      armor
   */

  public Human(String name, int lifepoints, int armor) {
    super(name, lifepoints);
    humanName = name;
    lifePoints = getCurrentLifePoints();
    attackStrength = 5;
    setArmorPoints(armor);
    maxSpeed = 3;
  }

  public int getArmorPoints() {
    return this.humanArmor;
  }

  public int getMaxLifePoints() { return this.lifePoints;
  }

  /**
   * Create an instance
   *
   * @param points the current starting life points of the life form
   */

  public void setArmorPoints(int points) {
    currentLifePoints += humanArmor;
    if (points >= 0) {
      humanArmor = points;
    } else {
      humanArmor = 0;
    }
  }

  /**
   * Create an instance
   *
   * @param damage the current starting life points of the life form
   */

  public void takeHit(int damage) {
    if (getArmorPoints() >= damage) {
      lifePoints += 0;
    } else if (((lifePoints + getArmorPoints()) - damage) >= 0) {
      lifePoints += getArmorPoints();
      lifePoints -= damage;
    } else {
      lifePoints = 0;

    }
    currentLifePoints = lifePoints;


  }

}
