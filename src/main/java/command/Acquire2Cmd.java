package command;

import environment.Cell;
import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

public class Acquire2Cmd implements Command {


  private Environment environment;
  public Acquire2Cmd(Environment environment){
    this.environment = environment;
  }


  /*

  sup
   */


  public void execute(int row, int col) {


    Cell cell = environment.getCell(row, col);
    LifeForm lifeForm = cell.getLifeForm();

    if (lifeForm != null) {
      Weapon weapon2 = cell.getWeapon2();
      if (weapon2 != null) {
        if (lifeForm.hasWeapon()) {
          Weapon currentWeapon = lifeForm.getWeapon();
          lifeForm.pickUpWeapon(weapon2);
          cell.addWeapon(currentWeapon);
        } else {
          lifeForm.pickUpWeapon(weapon2);
        }
        cell.removeWeapon(weapon2);
      }
    }


  }


}
