/**
 * @author Miguel
 */

package command;

import environment.Environment;
import lifeform.LifeForm;

public class MoveCmd implements Command {
  private Environment environment;

  public MoveCmd(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void execute(int row, int col) {
    if (environment.getLifeForm(row, col) != null) {
      LifeForm life = environment.getLifeForm(row, col);
      environment.getLifeForm(row, col).move(environment);
      environment.notifyObservers(life);
    }
  }
}
