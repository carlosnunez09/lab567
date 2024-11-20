package command;

import environment.Cell;
import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

public class ReloadCmd implements Command {

  private final Environment environment;

  public ReloadCmd(Environment environment) {
    this.environment = environment;
  }

  public void execute(int row, int col) {

    Cell cell = environment.getCell(row, col);
    LifeForm lifeForm = cell.getLifeForm();

    if (lifeForm != null && lifeForm.hasWeapon()) {
      Weapon weapon = lifeForm.getWeapon();
      weapon.reload();
      //System.out.println("i reloaded");
    }


  }


}
