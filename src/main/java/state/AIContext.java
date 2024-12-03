package state;

import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import gameplay.TimerObserver;
import lifeform.LifeForm;
import environment.Environment;

import javax.swing.*;

public class AIContext implements TimerObserver {
  Environment env;
  LifeForm lifeform;
  ActionState current;

  public DeadState dead = new DeadState(this);
  HasWeaponState weaponState = new HasWeaponState(this);
  NoWeaponState noWeapState = new NoWeaponState(this);
  OutOfAmmoState noAmmoState = new OutOfAmmoState(this);

  public AIContext(LifeForm lifeform, Environment e) {
  env = e;
  this.lifeform = lifeform;
  }

  public void execute() throws AttachmentException, WeaponException, EnvironmentException {
    current.executeAction();
  }

  public ActionState getCurrentState(){
    return current;
  }

  public Environment getEnvironment(){
    return env;
  }

  public HasWeaponState getHasWeaponState(){
    return weaponState;
  }

  public LifeForm getLifeForm(){
    return lifeform;
  }

  public NoWeaponState getNoWeaponState(){
    return noWeapState;
  }

  public OutOfAmmoState getOutOfAmmoState(){
    return noAmmoState;
  }

  public DeadState getDeadState() { return dead;}

  public void setCurrentState(ActionState state){
    current = state;
  }


  /**
   * @param time
   */
  @Override
  public void updateTime(int time) {

  }
}
