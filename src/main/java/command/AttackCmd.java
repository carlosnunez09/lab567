package command;

import environment.Cell;
import environment.Environment;
import exceptions.WeaponException;
import lifeform.LifeForm;

public class AttackCmd implements Command {

  private Environment environment;

  public AttackCmd(Environment environment) {
    this.environment = environment;
  }


  public void execute(int row, int col) {
    Cell cell = environment.getCell(row, col);
    LifeForm attacker = cell.getLifeForm();
    if (attacker == null) {
      System.out.println("No LifeForm found at (" + row + ", " + col + ").");
      return;
    }

    if (attacker.hasWeapon()) {
      System.out.println(attacker.getName() + " has a weapon and is attacking!");
      int weaponMaxRange = attacker.getWeapon().getMaxRange();

      // north
      for (int i = 1; i <= weaponMaxRange; i++) {
        int targetRow = row - i; // Move north
        if (isValidCell(targetRow, col)) {
          Cell targetCell = environment.getCell(targetRow, col);
          LifeForm target = targetCell.getLifeForm();
          if (target != null) {
            try {
              int damage = attacker.getWeapon().fire(i); // fire weapon
              target.takeHit(damage);
              System.out.println("attacked " + target.getName() + " at (" + targetRow + ", " + col + ")");
              return;
            } catch (WeaponException e) {
              System.out.println("weapon malfunction: " + e.getMessage());
              return;
            }
          }
        }
      }

      // south
      for (int i = 1; i <= weaponMaxRange; i++) {
        int targetRow = row + i; // Move south
        if (isValidCell(targetRow, col)) {
          Cell targetCell = environment.getCell(targetRow, col);
          LifeForm target = targetCell.getLifeForm();
          if (target != null) {
            try {
              int damage = attacker.getWeapon().fire(i); // Fire weapon at distance
              target.takeHit(damage);
              System.out.println("attacked " + target.getName() + " at (" + targetRow + ", " + col + ")");
              return;  // Stop once a target is successfully attacked
            } catch (WeaponException e) {
              System.out.println("weapon malfunction: " + e.getMessage());
              return;
            }
          }
        }
      }

      // east
      for (int i = 1; i <= weaponMaxRange; i++) {
        int targetCol = col + i; // Move east
        if (isValidCell(row, targetCol)) {
          Cell targetCell = environment.getCell(row, targetCol);
          LifeForm target = targetCell.getLifeForm();
          if (target != null) {
            try {
              int damage = attacker.getWeapon().fire(i); // Fire weapon at distance
              target.takeHit(damage);
              System.out.println("attacked " + target.getName() + " at (" + row + ", " + targetCol + ")");
              return;  // Stop once a target is successfully attacked
            } catch (WeaponException e) {
              System.out.println("weapon malfunction: " + e.getMessage());
              return;
            }
          }
        }
      }

      // west
      for (int i = 1; i <= weaponMaxRange; i++) {
        int targetCol = col - i; // Move west
        if (isValidCell(row, targetCol)) {
          Cell targetCell = environment.getCell(row, targetCol);
          LifeForm target = targetCell.getLifeForm();
          if (target != null) {
            try {
              int damage = attacker.getWeapon().fire(i); // Fire weapon at distance
              target.takeHit(damage);
              System.out.println("Attacked " + target.getName() + " at (" + row + ", " + targetCol + ")");
              return;  // Stop once a target is successfully attacked
            } catch (WeaponException e) {
              System.out.println("Weapon malfunction: " + e.getMessage());
              return;
            }
          }
        }
      }

    } else {
      System.out.println(attacker.getName() + " has no weapon to attack with.");
    }
  }

  private boolean isValidCell(int row, int col) {
    return row >= 0 && col >= 0 && row < environment.getNumRows() && col < environment.getNumCols();
  }

}










