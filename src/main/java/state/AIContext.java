package state;

import gameplay.TimerObserver;
import lifeform.LifeForm;
import environment.Environment;

public class AIContext implements TimerObserver {
  Environment env;
  LifeForm lifeform;
  ActionState current;

  DeadState dead = new DeadState(this);
  HasWeaponState weaponState = new HasWeaponState(this);
  NoWeaponState noWeapState = new NoWeaponState(this);
  OutOfAmmoState noAmmoState = new OutOfAmmoState(this);

  public AIContext(LifeForm lifeform, Environment e) {
  env = e;
  this.lifeform = lifeform;
  }

  public void execute(){

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
