package environment;

import lifeform.LifeForm;
import weapon.Weapon;

public interface EnvironmentObserver {
  void update(int row, int col, LifeForm lifeform, Weapon weapon1, Weapon weapon2);
}
