package command;

import environment.Cell;
import environment.Environment;
import lifeform.LifeForm;
import weapon.Weapon;

public class DropCmd implements Command {

  private Environment environment;

  public DropCmd(Environment environment){ this.environment = environment; }


  public void execute(int row, int col){

    Cell cell = environment.getCell(row, col);
    LifeForm lifeForm = cell.getLifeForm();

    if(lifeForm != null && lifeForm.hasWeapon()){
      Weapon weapon = lifeForm.getWeapon();


      if (cell.getWeaponsCount() < 2){
        if (cell.addWeapon(weapon)) {
          lifeForm.dropWeapon();
        }
      }


    }

  }

}
