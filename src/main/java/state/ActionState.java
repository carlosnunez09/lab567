package state;

import environment.Environment;
import lifeform.LifeForm;

public abstract class ActionState extends {

  protected AIContext context;
  protected Environment e;
  protected LifeForm lifeform;



  public ActionState(AIContext context){
    this.context = context;
    this.e = context.getEnvironment();
    this.lifeform = context.getLifeForm();

  }

  abstract void executeAction(){

  }





}
