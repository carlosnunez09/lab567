package state;

import exceptions.EnvironmentException;

public class DeadState extends ActionState {
  AIContext ai;

  public DeadState(AIContext aiContext) {
    super(aiContext);
    ai = aiContext;
  }

  @Override
  public void executeAction() throws EnvironmentException {
    if (ai.getLifeForm().hasWeapon()) {
      DropCommand drop = new DropCommand(ai.getEnvironment());
      drop.execute(ai.getLifeForm().getRow(), ai.getLifeForm().getCol());
    }
    ai.getEnvironment().removeLifeForm(ai.getLifeForm().getRow(), ai.getLifeForm().getCol());
    ai.getLifeForm().revive();
    boolean isAdded = true;
    while(!isAdded) {
      isAdded = ai.getEnvironment().addLifeForm(ai.getLifeForm(),new RandInt(0,e.getNumRows() - 1).choose(),new RandInt(0,e.getNumCols()).choose() - 1);
    }
    ai.setCurrentState(ai.getNoWeaponState());
    e.notifyObservers(ai.getLifeForm());
  }

}

