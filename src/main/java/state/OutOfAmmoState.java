package state;

import command.ReloadCmd;
import exceptions.EnvironmentException;
import exceptions.WeaponException;

import java.util.List;

public class OutOfAmmoState extends ActionState {

  AIContext ai;

  public OutOfAmmoState(AIContext cont) {
    super(cont);
    ai = cont;
  }

  public void executeAction() throws WeaponException, EnvironmentException {
    if (ai.getLifeForm().getCurrentLifePoints() == 0) {
      ai.setCurrentState(ai.getDeadState());
    } else {
      ReloadCmd reloadCom = new ReloadCmd(ai.getEnvironment());
      reloadCom.execute(ai.getLifeForm().getRow() + 1, ai.getLifeForm().getCol() + 1);
      ai.setCurrentState(ai.getHasWeaponState());
    }
    ai.getEnvironment().notifyObservers(ai.getLifeForm());
  }
}
