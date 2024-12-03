package state;

import command.AttackCmd;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import gameplay.Random;
import lifeform.LifeForm;

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

    LifeForm target = null;
    String direction = lifeForm.getCurrentDirection();

    if (direction.equals("North")) {
      int count = lifeForm.getRow() + 1;
      while (count <= lifeForm.getRow() + lifeForm.getWeapon().getMaxRange()) {
        if (count < 0 || count >= ai.getEnvironment().getNumRows()) break;
        LifeForm potentialTarget = ai.getEnvironment().getLifeForm(count, lifeForm.getCol());
        if (potentialTarget != null && !potentialTarget.getClass().equals(lifeForm.getClass())) {
          target = potentialTarget;
          break;
        }
        count++;
      }
    } else if (direction.equals("South")) {
      int count = lifeForm.getRow() - 1;
      while (count >= lifeForm.getRow() - lifeForm.getWeapon().getMaxRange()) {
        if (count < 0 || count >= ai.getEnvironment().getNumRows()) break;
        LifeForm potentialTarget = ai.getEnvironment().getLifeForm(count, lifeForm.getCol());
        if (potentialTarget != null && !potentialTarget.getClass().equals(lifeForm.getClass())) {
          target = potentialTarget;
          break;
        }
        count--;
      }
    } else if (direction.equals("East")) {
      int count = lifeForm.getCol() + 1;
      while (count <= lifeForm.getCol() + lifeForm.getWeapon().getMaxRange()) {
        if (count < 0 || count >= ai.getEnvironment().getNumCols()) break;
        LifeForm potentialTarget = ai.getEnvironment().getLifeForm(lifeForm.getRow(), count);
        if (potentialTarget != null && !potentialTarget.getClass().equals(lifeForm.getClass())) {
          target = potentialTarget;
          break;
        }
        count++;
      }
    } else if (direction.equals("West")) {
      int count = lifeForm.getCol() - 1;
      while (count >= lifeForm.getCol() - lifeForm.getWeapon().getMaxRange()) {
        if (count < 0 || count >= ai.getEnvironment().getNumCols()) break;
        LifeForm potentialTarget = ai.getEnvironment().getLifeForm(lifeForm.getRow(), count);
        if (potentialTarget != null && !potentialTarget.getClass().equals(lifeForm.getClass())) {
          target = potentialTarget;
          break;
        }
        count--;
      }
    }

    if (target != null) {
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

// Utility Classes for Randomization
class RandInt implements Random<Integer> {
  // lo, hi
  private final int lo;
  private final int hi;

  public RandInt(int lo, int hi) {
    this.lo = lo;
    this.hi = hi;
  }

  @Override
  public Integer choose() {
    return new java.util.Random().nextInt(hi - lo) + lo;
  }
}

class RandBool implements Random<Boolean> {
  @Override
  public Boolean choose() {
    return new RandInt(0, 2).choose() == 0;
  }
}

class RandDirection implements Random<String> {
  private final List<String> choices = List.of("North", "South", "East", "West");

  @Override
  public String choose() {
    return choices.get(new RandInt(0, choices.size()).choose());
  }
}
