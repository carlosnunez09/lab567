/**
 * @author Miguel
 */

package command;

import environment.Environment;
import lifeform.LifeForm;

public class MoveCmd implements Command {
  private final Environment environment;

  public MoveCmd(Environment environment) {
    this.environment = environment;
  }

  @Override
  public void execute(int row, int col) {
    if (environment.getLifeForm(row, col) != null) {
      LifeForm life = environment.getLifeForm(row, col);
      environment.getLifeForm(row, col).move(environment);
      environment.notifyObservers(life);
      if (life.getWeapon() != null) {
        life.getWeapon().updateTime(1); // clue how it works,but it works, prob different implementation  for the future
      }
      //System.out.println("from command: Moved" + " at row: " + row + " col: " + col); // testing to be removed
    }
  }
}
