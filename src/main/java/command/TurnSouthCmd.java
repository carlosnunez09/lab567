package command;

import environment.Environment;

public class TurnSouthCmd implements Command {
  private final Environment environment;

  public TurnSouthCmd(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void execute(int row, int col) {
    if (environment.getLifeForm(row, col) != null) {
      environment.getLifeForm(row, col).setDirection("South");
      environment.notifyObservers(environment.getLifeForm(row, col));
    }
  }
}
