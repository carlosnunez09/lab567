package state;

import command.Acquire1Cmd;
import command.MoveCmd;
import exceptions.EnvironmentException;
import exceptions.WeaponException;

public class NoWeaponState extends ActionState{
  AIContext ai;

  public NoWeaponState(AIContext context) {

    super(context);
    ai = context;
  }

  public void executeAction() throws WeaponException, EnvironmentException {
    if (context.getCurrentState() != context.dead){
      if (ai.getEnvironment().getWeapons(ai.getLifeForm().getRow(), ai.getLifeForm().getCol())[0] != null){
        Acquire1Cmd weap1 = new Acquire1Cmd(context.getEnvironment());
        weap1.execute(ai.getLifeForm().getRow(), ai.getLifeForm().getCol());
        context.setCurrentState(context.getHasWeaponState());
      }
      else {
        MoveCmd move = new MoveCmd(ai.getEnvironment());
        move.execute(ai.getLifeForm().getRow(), ai.getLifeForm().getCol());
      }
    }
    ai.getEnvironment().notifyObservers(ai.getLifeForm());
  }
}
