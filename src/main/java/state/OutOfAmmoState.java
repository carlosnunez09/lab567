package state;

import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.WeaponException;

public class OutOfAmmoState extends ActionState{
  public OutOfAmmoState(AIContext context) {
    super(context);
  }

  @Override
  public void executeAction() throws WeaponException{

  }
}
