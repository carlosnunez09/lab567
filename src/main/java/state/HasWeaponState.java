package state;

import command.AttackCmd;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import gameplay.Random;
import lifeform.LifeForm;

import java.util.ArrayList;
import java.util.List;

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
      ai.setCurrentState(ai.dead);
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
      AttackCmd fireCommand = new AttackCmd(ai.getEnvironment());
      fireCommand.execute(lifeForm.getRow(), lifeForm.getCol());
    } else {
      // Randomly decide to move and set a new direction
      lifeForm.setDirection(new RandDirection().choose());
      if (new RandBool().choose()) {
        ai.getEnvironment().move(lifeForm);
      }
    }

    // Notify observers about the LifeForm's action
    ai.getEnvironment().notifyObservers(lifeForm);
  }
}

class RandInt implements Random<Integer> {
  //[lo, hi)
  private int lo;
  private int hi;

  public RandInt(int l, int h) {
    lo = l;
    hi = h;
  }

  public Integer choose() {
    return new java.util.Random().nextInt(hi - lo) + lo;
  }
}

//Two directions: 1) random A to random B, 2) random A to List<A>

class RandBool implements Random<Boolean> {
  public Boolean choose() {
    return new RandInt(0, 2).choose() == 0 ? true : false;
  }
}

//Note: not efficient to take a list, List<Supplier<A>> is better
class RandDirection implements Random<String> {
  List<String> choices = List.of("North", "South", "East", "West");

  public String choose() {
    return choices.get(new RandInt(0, choices.size()).choose());
  }
}
