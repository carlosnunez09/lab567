package state;

import environment.Environment;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;

public abstract class ActionState {

  protected AIContext context;
  protected Environment e;
  protected LifeForm lifeform;



  public ActionState(AIContext context){
    this.context = context;
    this.e = context.getEnvironment();
    this.lifeform = context.getLifeForm();

  }

  public void executeAction() throws WeaponException, EnvironmentException, AttachmentException {

  }





}
