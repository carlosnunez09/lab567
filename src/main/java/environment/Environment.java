package environment;

import lifeform.LifeForm;
import weapon.Weapon;



public class Environment {

  public static Cell[][] cells;

  /**
   * Creates a Enviroment with provided rows and cols of the Cell class
   */

  public Environment(int row, int col) {
    cells = new Cell[row][col];
    //LifeForm life = new LifeForm("Bob", 20);
    //cells[row-1][col-1].addLifeForm(life);

  }

  /**
   * Adds a Lifeform to the cell
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
  public void clearBoard(){     //put all cells in envir to null

  }

  public double getDistance(int row1, int col1, int row2, int col2){
    return 0.0;
  }

  public double getDistance(LifeForm lifeform1, LifeForm lifeform2){
    return 0.0;
  }

  public int getNumCols(){
    return 0;
  }

  public int getNumRows(){
    return 0;
  }

  public Weapon[] getWeapons(int row, int col){
    return null;
  }

  public Weapon removeWeapon(Weapon weapon, int row, int col){
    return null;
  }

}
