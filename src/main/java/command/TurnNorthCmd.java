package command;


import environment.Environment;

public class TurnNorthCmd implements Command {
  private final Environment environment;

  public TurnNorthCmd(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void execute(int row, int col) {
    if (environment.getLifeForm(row, col) != null) {
      environment.getLifeForm(row, col).setDirection("North");
      environment.notifyObservers(environment.getLifeForm(row, col));
    }
  }
}
