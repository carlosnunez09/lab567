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


//  public void execute(int row, int col) {
//    Cell cell = environment.getCell(row, col);
//    LifeForm attacker = cell.getLifeForm();
//    if (attacker == null) {
//      System.out.println("No LifeForm found at (" + row + ", " + col + ").");
//      return;
//    }
//
//    if (attacker.hasWeapon()) {
//      System.out.println(attacker.getName() + " has a weapon and is attacking!");
//
//      int weaponMaxRange = attacker.getWeapon().getMaxRange();
//      Direction facingDirection = attacker.getFacingDirection();  // Get the direction the LifeForm is facing
//
//      // Only attack in the direction the LifeForm is facing
//      switch (facingDirection) {
//        case NORTH:
//          attackInDirection(row, col, -1, 0, weaponMaxRange); // North
//          break;
//        case SOUTH:
//          attackInDirection(row, col, 1, 0, weaponMaxRange);  // South
//          break;
//        case EAST:
//          attackInDirection(row, col, 0, 1, weaponMaxRange);  // East
//          break;
//        case WEST:
//          attackInDirection(row, col, 0, -1, weaponMaxRange); // West
//          break;
//        default:
//          System.out.println("Unknown direction: " + facingDirection);
//          break;
//      }
//    } else {
//      System.out.println(attacker.getName() + " has no weapon to attack with.");
//    }
//  }
//
//  // Helper method to handle the attack logic in the specified direction
//  private void attackInDirection(int row, int col, int rowDelta, int colDelta, int maxRange) {
//    for (int i = 1; i <= maxRange; i++) {
//      int targetRow = row + i * rowDelta;   // Move in row direction (up/down)
//      int targetCol = col + i * colDelta;   // Move in column direction (left/right)
//
//      if (isValidCell(targetRow, targetCol)) {
//        Cell targetCell = environment.getCell(targetRow, targetCol);
//        LifeForm target = targetCell.getLifeForm();
//        if (target != null) {
//          try {
//            int damage = target.getWeapon().fire(i); // Fire weapon at distance
//            target.takeHit(damage);
//            System.out.println("Attacked " + target.getName() + " at (" + targetRow + ", " + targetCol + ")");
//            return;  // Stop once a target is successfully attacked
//          } catch (WeaponException e) {
//            System.out.println("Weapon malfunction: " + e.getMessage());
//            return;
//          }
//        }
//      }
//    }
//  }



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
        int targetRow = row - i; // move north
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
        int targetRow = row + i; // move south
        if (isValidCell(targetRow, col)) {
          Cell targetCell = environment.getCell(targetRow, col);
          LifeForm target = targetCell.getLifeForm();
          if (target != null) {
            try {
              int damage = attacker.getWeapon().fire(i); // fires weapon
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

      // east
      for (int i = 1; i <= weaponMaxRange; i++) {
        int targetCol = col + i; // move east
        if (isValidCell(row, targetCol)) {
          Cell targetCell = environment.getCell(row, targetCol);
          LifeForm target = targetCell.getLifeForm();
          if (target != null) {
            try {
              int damage = attacker.getWeapon().fire(i); // fires weapon
              target.takeHit(damage);
              System.out.println("attacked " + target.getName() + " at (" + row + ", " + targetCol + ")");
              return;
            } catch (WeaponException e) {
              System.out.println("weapon malfunction: " + e.getMessage());
              return;
            }
          }
        }
      }

      // west
      for (int i = 1; i <= weaponMaxRange; i++) {
        int targetCol = col - i; // move west
        if (isValidCell(row, targetCol)) {
          Cell targetCell = environment.getCell(row, targetCol);
          LifeForm target = targetCell.getLifeForm();
          if (target != null) {
            try {
              int damage = attacker.getWeapon().fire(i); // fires weapon
              target.takeHit(damage);
              System.out.println("Attacked " + target.getName() + " at (" + row + ", " + targetCol + ")");
              return;
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










