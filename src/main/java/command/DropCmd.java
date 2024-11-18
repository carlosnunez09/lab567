package command;

import environment.Cell;
import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

public class DropCmd implements Command {

  private Environment environment;

  public DropCmd(Environment environment) {
    this.environment = environment;
  }


  public void execute(int row, int col) {
    LifeForm lifeForm = environment.getLifeForm(row, col);
    Cell cell = environment.getCell(row, col);

    if (lifeForm != null && lifeForm.hasWeapon()) {
      Weapon weapon = lifeForm.getWeapon();
      if (cell.addWeapon(weapon)) {
        lifeForm.dropWeapon();
      }
    }


  }

}