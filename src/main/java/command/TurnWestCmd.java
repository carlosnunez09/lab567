package command;


import environment.Environment;

public class TurnWestCmd implements Command {
  private Environment environment;

  public TurnWestCmd(Environment environment) { this.environment = environment; }
  @Override
  public void execute(int row, int col) {
    if (environment.getLifeForm(row, col) != null){
      environment.getLifeForm(row, col).setDirection("East");
      environment.notifyObservers(environment.getLifeForm(row, col));
    }
  }
}