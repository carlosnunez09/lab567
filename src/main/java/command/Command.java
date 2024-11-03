package command;

import exceptions.EnvironmentException;
import exceptions.WeaponException;

public interface Command {
  public void execute(int row, int col) throws WeaponException, EnvironmentException;
}
