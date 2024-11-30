package state;

import command.Acquire1Cmd;
import command.MoveCmd;
import exceptions.EnvironmentException;
import exceptions.WeaponException;

public class NoWeaponState extends ActionState{
  ActionState nextState;
  public NoWeaponState(AIContext context) {
    super(context);
  }

  public void executeAction() throws WeaponException, EnvironmentException {
    if (context.getCurrentState() != context.dead){
      if (e.getWeapons(lifeform.getRow(), lifeform.getCol()) != null){
        Acquire1Cmd weap1 = new Acquire1Cmd(context.getEnvironment());
        weap1.execute(lifeform.getRow(), lifeform.getCol());
        context.setCurrentState(context.getHasWeaponState());
      }
      else {
        MoveCmd move = new MoveCmd(context.getEnvironment());
        move.execute(lifeform.getRow(), lifeform.getCol());
      }
    }
  }
}
