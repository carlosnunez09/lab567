package environment;

import lifeform.LifeForm;
import weapon.Weapon;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.abs;


public class Environment {

  public static Cell[][] cells;
  private static Environment insta;
  private int envRows;
  private int envCols;

  /**
   * Creates a Enviroment with provided rows and cols of the Cell class
   */

  public Environment(int row, int col) {
    cells = new Cell[row][col];
    envRows = row;
    envCols = col;
    //LifeForm life = new LifeForm("Bob", 20);
    //cells[row-1][col-1].addLifeForm(life);

  }


  public static Environment getEnvironment(int row, int col) {
    if (insta == null) {
      insta = new Environment(row, col);
    }
    return insta;
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
  public void clearBoard(){
    //put all cells in envir to null
    for (int i =0; i < envRows; i++){
      for (int j =0; j < envCols; j++){
        cells[i][j] = null;
      }
    }

  }

  public double getDistance(int row1, int col1, int row2, int col2){

    if (row1 != row2 && col1 != col2){
      return Math.hypot((row1 - row2) * 5,(col1 - col2) * 5);     // a^2 + b^2 = c^2
    }
    else{
      double temp = (abs(row1 - row2) * 5) + (abs(col1 - col2) * 5);
      return temp;
    }
  }

  public double getDistance(LifeForm lifeform1, LifeForm lifeform2){
    int Life1row = lifeform1.getRow();
    int Life1col = lifeform1.getCol();
    int Life2row = lifeform2.getRow();
    int Life2col = lifeform2.getCol();

    if (Life1row != Life2row && Life1col != Life2col){
      return Math.hypot((Life1row - Life2row) * 5,(Life1col - Life2col) * 5);
    }
    else{
      double temp = (abs(Life1row - Life2row) * 5) + (abs(Life1col - Life2col) * 5);
      return temp;
    }

  }

  public int getNumCols(){
    return envCols;
  }

  public int getNumRows(){
    return envRows;
  }

  public Weapon[] getWeapons(int row, int col){
    Weapon[] weapons = new Weapon[2];
    weapons[0] = cells[row][col].getWeapon1();
    weapons[1] = cells[row][col].getWeapon2();
    return weapons;
  }


  public Boolean addWeapon(Weapon weapon, int row, int col){
    if (envCols > col || envRows > row){
      return false;
    }
    else if (cells[row][col].addWeapon(weapon)){
      return true;
    }
    else {
      return false;
    }
  }


  public Weapon removeWeapon(Weapon weapon, int row, int col){
    if (envCols > col || envRows > row){
      return null;
    }
    else{
      return cells[row][col].removeWeapon(weapon);
    }
  }

}
