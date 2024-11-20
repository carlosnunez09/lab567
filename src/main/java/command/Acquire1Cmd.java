package command;

import environment.Cell;
import environment.Environment;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;
import weapon.Weapon;

public class Acquire1Cmd implements Command {

  private final Environment environment;

  public Acquire1Cmd(Environment environment) {
    this.environment = environment;
  }


  @Override
  public void execute(int row, int col) throws WeaponException, EnvironmentException {


    Cell cell = environment.getCell(row, col);
    LifeForm lifeForm = cell.getLifeForm();

    if (lifeForm != null) {
      Weapon weapon1 = cell.getWeapon1();
      if (weapon1 != null) {
        if (lifeForm.hasWeapon()) {
          Weapon currentWeapon = lifeForm.getWeapon();
          lifeForm.pickUpWeapon(weapon1);
          cell.addWeapon(currentWeapon);
        } else {
          lifeForm.pickUpWeapon(weapon1);
        }
        cell.removeWeapon(weapon1);
      }
    }

  }


}



