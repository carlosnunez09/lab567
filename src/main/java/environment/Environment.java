package environment;

import gui.GUI2;
import lifeform.LifeForm;
import weapon.Weapon;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;


public class Environment {

  public static Cell[][] cells;
  private static Environment insta;
  public List<EnvironmentObserver> gameboards = new ArrayList<>();
  private int envRows;
  private int envCols;

  /**
   * Creates an Enviroment with provided rows and cols of the Cell class
   */

  public Environment(int row, int col) {
    cells = new Cell[row][col];
    envRows = row;
    envCols = col;
    cells = new Cell[row][col];
    for (int i = 0; i < row; i++) {
      for (int j = 0; j < col; j++) {
        cells[i][j] = new Cell();
      }
    }
    //LifeForm life = new LifeForm("Bob", 20);
    //cells[row-1][col-1].addLifeForm(life);
  }

  /**
   * Singleton for this class
   */

  public static Environment getEnvironment(int row, int col) {
    if (insta == null) {
      insta = new Environment(row, col);
    }
    return insta;
  }

  /**
   * Adds a Lifeform to the cell
   *
   * @return if the Lifeform has been successfully added to the cell
   */

  public boolean addLifeForm(LifeForm entity, int row, int col) {
    if (cells[row][col] == null) {
      cells[row][col] = new Cell();
      cells[row][col].addLifeForm(entity);
      return true;
    } else {
      return false;
    }
  }

  /**
   * @return the LifeForm in this Cell.
   */

  public LifeForm getLifeForm(int row, int col) {
    if (cells[row][col] == null) {
      cells[row][col] = null;
      return null;
    } else {
      return cells[row][col].getLifeForm();
    }
  }

  /**
   * Removes the LifeForm in this Cell.
   */

  public void removeLifeForm(int row, int col) {
    if (cells[row][col] != null) {
      cells[row][col].removeLifeForm();
    }
  }

  /**
   * Clears the board with NULL
   **/

  public void clearBoard() {
    //put all cells in envir to null
    for (int i = 0; i < envRows; i++) {
      for (int j = 0; j < envCols; j++) {
        cells[i][j] = null;
      }
    }

  }


  /**
   * Get the distance between 2 cells
   *
   * @return A Double
   */

  public double getDistance(int row1, int col1, int row2, int col2) {

    if (row1 != row2 && col1 != col2) {
      return Math.hypot((row1 - row2) * 5, (col1 - col2) * 5);     // a^2 + b^2 = c^2
    } else {
      double temp = (abs(row1 - row2) * 5) + (abs(col1 - col2) * 5);
      return temp;
    }
  }

  /**
   * Gets the distance between 2 lifeforms
   *
   * @return the distance
   */

  public double getDistance(LifeForm lifeform1, LifeForm lifeform2) {
    int life1Row = lifeform1.getRow();
    int life1Col = lifeform1.getCol();
    int life2Row = lifeform2.getRow();
    int life2Col = lifeform2.getCol();

    if (life1Row != life2Row && life1Col != life2Col) {
      return Math.hypot((life1Row - life2Row) * 5, (life1Col - life2Col) * 5);
    } else {
      double temp = (abs(life1Row - life2Row) * 5) + (abs(life1Col - life2Col) * 5);
      return temp;
    }

  }

  /**
   * @return # of cols
   */

  public int getNumCols() {
    return envCols;
  }

  /**
   * @return # of rows
   */

  public int getNumRows() {
    return envRows;
  }

  /**
   * Get the weapons in the cell
   *
   * @return the list of weapons
   */

  public Weapon[] getWeapons(int row, int col) {
    if (cells[row][col] == null) {
      return null;
    } else {
      Weapon[] weapons = new Weapon[2];
      weapons[0] = cells[row][col].getWeapon1();
      weapons[1] = cells[row][col].getWeapon2();
      return weapons;
    }
  }

  /**
   * Removes a weapon from cell
   *
   * @return weapon that was removed
   */

  public Weapon removeWeapon(Weapon weapon, int row, int col) {
    if (envCols > col || envRows > row) {
      return null;
    } else {
      return cells[row][col].removeWeapon(weapon);
    }
  }


  /**
   * Add a weapon to a cell
   *
   * @return Boolean if weapon is added
   */
  public boolean addWeapon(Weapon weapon, int row, int col) {
    if (cells[row][col] == null) {
      cells[row][col] = new Cell();
    }
    if (cells[row][col].addWeapon(weapon)) {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Notifies observer
   *
   * @param lifeForm
   */
  public void notifyObservers(LifeForm lifeForm) {
    gameboards.forEach(gameboard -> gameboard.update(lifeForm.getRow(), lifeForm.getCol()));
    gameboards.forEach(gameboard -> gameboard.update(GUI2.getscRow(), GUI2.getscCol()));
  }

  public Cell getCell(int row, int col) {
    return cells[row][col];
  }

  /**
   * If the cell is in the environment and is also open, return true.
   *
   * @param row
   * @param col
   * @return
   */
  public boolean checkLegalPlay(int row, int col) {
    return this.getNumCols() > col && col >= 0 && this.getNumRows() > row && row >= 0;
  }
}
