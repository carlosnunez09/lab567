package state;

import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.LifeForm;

public class HasWeaponState extends ActionState {

  private final AIContext ai;

  public HasWeaponState(AIContext aiContext) {
    super(aiContext);
    this.ai = aiContext;
  }

  @Override
  public void executeAction() throws EnvironmentException, WeaponException, AttachmentException {
    LifeForm lifeForm = ai.getLifeForm();

    // Ensure LifeForm exists and is alive
    if (lifeForm == null || lifeForm.getCurrentLifePoints() == 0) {
      ai.setCurrentState(ai.getDeadState());
      return;
    }

    // Check if the LifeForm has a weapon
    if (lifeForm.getWeapon() == null) {
      ai.setCurrentState(ai.getNoWeaponState());
      return;
    }

    // Check weapon's ammo
    if (lifeForm.getWeapon().getCurrentAmmo() <= 0) {
      ai.setCurrentState(ai.getOutOfAmmoState());
      return;
    }

    LifeForm target = ai.getEnvironment().getTarget(lifeForm.getRow(), lifeForm.getCol());

    if (target != null
      && ai.getEnvironment().getDistance(lifeForm, target) < lifeForm.getWeapon().getMaxRange()
      && target.getClass() != lifeForm.getClass()) {
      // Fire at the target if within range and not the same class
      FireCommand fireCommand = new FireCommand(ai.getEnvironment());
      fireCommand.execute(lifeForm.getRow(), lifeForm.getCol());
    } else {
      // Randomly decide to move and set a new direction
      lifeForm.setDirection(new RandDirection().choose());
      if (new RandBoolean().choose()) {
        ai.getEnvironment().move(lifeForm);
      }
    }

    // Notify observers about the LifeForm's action
    ai.getEnvironment().notifyObservers(lifeForm);
  }
}
