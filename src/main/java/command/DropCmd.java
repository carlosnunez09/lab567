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

  @Override
  public void execute(int row, int col) {
    System.out.println("Executing drop command at row: " + row + ", col: " + col);
    // Find out if there is a LifeForm on the specified cell
    if (environment.getLifeForm(row, col) != null) {
      LifeForm lifeForm = environment.getLifeForm(row, col);
      System.out.println("LifeForm found at row: " + row + ", col: " + col);

      // Check if the LifeForm has a weapon
      if (lifeForm.hasWeapon()) {
        System.out.println("LifeForm has a weapon.");
        Weapon weapon = lifeForm.getWeapon();

        // Get the cell at the specified coordinates
        Cell cell = environment.getCell(row, col);
        System.out.println("Retrieved cell at row: " + row + ", col: " + col);

        // Check if there is room in the cell for another weapon
        if (cell.getWeaponsCount() < 2) {
          System.out.println("There is room in the cell for another weapon.");
          // Add the weapon to the cell and remove it from the LifeForm
          if (cell.addWeapon(weapon)) {
            System.out.println("Weapon successfully added to the cell.");
            lifeForm.dropWeapon();
            System.out.println("LifeForm dropped the weapon.");
          } else {
            System.out.println("Failed to add the weapon to the cell.");
          }
        } else {
          System.out.println("No room in the cell for another weapon.");
        }
      } else {
        System.out.println("LifeForm does not have a weapon to drop.");
      }
    } else {
      System.out.println("No LifeForm found at row: " + row + ", col: " + col);
    }
  }
}
