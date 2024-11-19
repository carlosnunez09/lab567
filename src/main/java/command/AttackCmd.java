package command;

import environment.Cell;
import environment.Environment;
import exceptions.WeaponException;
import gui.GUI2;
import lifeform.LifeForm;
import weapon.Weapon;

public class AttackCmd implements Command {

  private Environment environment;

  public AttackCmd(Environment environment) {
    this.environment = environment;
  }

  // Helper method to get the direction the LifeForm is facing
  public static String getLifeFormDirection(Environment env, int row, int col) {
    LifeForm lifeForm = env.getLifeForm(row, col);
    if (lifeForm != null) {
      return lifeForm.getCurrentDirection();
    } else {
      return "LifeForm not present at the given location";
    }
  }

  @Override
  public void execute(int row, int col) {
    // Get the cell at the specified coordinates
    Cell cell = environment.getCell(row, col);
    if (cell == null) {
      System.out.println("Invalid cell at (" + row + ", " + col + ").");
      return;
    }
    LifeForm attacker = cell.getLifeForm();

    // Check if there is a LifeForm at the given cell
    if (attacker == null) {
      System.out.println("No LifeForm found at (" + row + ", " + col + ").");
      return;
    }

    // Check if the LifeForm has a weapon to attack with
    if (attacker.hasWeapon()) {
      Weapon weapon = attacker.getWeapon();
      System.out.println(attacker.getName() + " has a weapon and is attacking!");
      int weaponMaxRange = weapon.getMaxRange();
      String facingDirection = GUI2.getLifeFormDirection(environment, row, col);

      // Print weapon stats for debugging
      System.out.println("Weapon max range: " + weaponMaxRange);
      System.out.println("Weapon current ammo: " + weapon.getCurrentAmmo());

      // Attack in the direction the LifeForm is facing
      switch (facingDirection.toUpperCase()) {
        case "NORTH":
          attackUntilTarget(row, col, -1, 0, weapon, weaponMaxRange); // North
          break;
        case "SOUTH":
          attackUntilTarget(row, col, 1, 0, weapon, weaponMaxRange);  // South
          break;
        case "EAST":
          attackUntilTarget(row, col, 0, 1, weapon, weaponMaxRange);  // East
          break;
        case "WEST":
          attackUntilTarget(row, col, 0, -1, weapon, weaponMaxRange); // West
          break;
        default:
          System.out.println("Unknown direction: " + facingDirection);
          break;
      }
    } else {
      System.out.println(attacker.getName() + " has no weapon to attack with.");
    }
  }

  // Helper method to handle attacking until a target is found in the specified direction
  private void attackUntilTarget(int row, int col, int rowDelta, int colDelta, Weapon weapon, int maxRange) {
    for (int i = 1; i <= maxRange; i++) {
      int targetRow = row + i * rowDelta;   // Calculate the target row
      int targetCol = col + i * colDelta;   // Calculate the target column

      System.out.println("Checking cell at (" + targetRow + ", " + targetCol + ") for LifeForms.");

      if (isValidCell(targetRow, targetCol)) {
        Cell targetCell = environment.getCell(targetRow, targetCol);
        if (targetCell == null) {
          System.out.println("Invalid target cell at (" + targetRow + ", " + targetCol + ").");
          continue;
        }
        LifeForm target = targetCell.getLifeForm();
        if (target != null) {
          try {
            // i is distance from attacker to target
            int damage = weapon.fire(i); // Fire weapon at the target


            target.takeHit(damage);// Target takes damage
            System.out.println("Attacked " + target.getName() + " at (" + targetRow + ", " + targetCol + ") with " + damage + " damage.");


            return;  // Stop once a target is successfully attacked
          } catch (WeaponException e) {
            System.out.println("Weapon malfunction: " + e.getMessage());
            return;  // Stop if weapon malfunctions
          }
        }
      } else {
        System.out.println("Cell out of bounds at (" + targetRow + ", " + targetCol + ").");
      }
    }
    System.out.println("No target found in direction.");
  }

  // Helper method to check if the cell is valid
  private boolean isValidCell(int row, int col) {
    boolean valid = row >= 0 && col >= 0 && row < environment.getNumRows() && col < environment.getNumCols();
    System.out.println("Cell (" + row + ", " + col + ") valid: " + valid);
    return valid;
  }
}
