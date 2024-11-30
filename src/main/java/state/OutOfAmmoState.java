package state;

import exceptions.WeaponException;

public class OutOfAmmoState extends ActionState{
  public OutOfAmmoState(AIContext context) {
    super(context);
  }

  @Override
  public void executeAction() throws WeaponException{
    if (context.getCurrentState() != context.dead){
      if (lifeform.hasWeapon()){
        lifeform.getWeapon().reload();
      }
    }
  }
}
