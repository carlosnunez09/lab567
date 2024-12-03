package state;

import command.DropCmd;
import exceptions.EnvironmentException;

import java.util.Random;

public class DeadState extends ActionState {
  AIContext ai;

  public DeadState(AIContext aiContext) {
    super(aiContext);
    ai = aiContext;
  }

  @Override
  public void executeAction() throws EnvironmentException {
    if (ai.getLifeForm().hasWeapon()) {
      DropCmd drop = new DropCmd(ai.getEnvironment());
      drop.execute(ai.getLifeForm().getRow(), ai.getLifeForm().getCol());
    }
    ai.getEnvironment().removeLifeForm(ai.getLifeForm().getRow(), ai.getLifeForm().getCol());
    ai.getLifeForm().revive();
    Random rand = new Random();
    boolean isAdded = false; // changed to false
    while (!isAdded) {
      isAdded = ai.getEnvironment().addLifeForm(
        ai.getLifeForm(),
        rand.nextInt(ai.getEnvironment().getNumRows() - 1),
        rand.nextInt(ai.getEnvironment().getNumCols() - 1)
      );
    }
    ai.setCurrentState(ai.getNoWeaponState());
    e.notifyObservers(ai.getLifeForm());
  }

}

