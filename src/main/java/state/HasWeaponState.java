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
    LifeForm target = null;
    if (lifeform.getCurrentDirection() == "North") {
      int count = lifeform.getRow() + 1;
      while (count <= lifeform.getRow() + lifeform.getWeapon().getMaxRange()) {
        if (count > 0 || count < e.getNumRows()){
          break;
        }
        if (e.getLifeForm(count, lifeform.getCol()) != null
                && e.getLifeForm(count, lifeform.getCol()).getClass() != lifeForm.getClass()) {
          target = ai.getEnvironment().getLifeForm(count, lifeform.getCol());
          count += 1;
        } else if (target != null){
          count++;
        }
      }
    } else if (lifeform.getCurrentDirection() == "South") {
      int count = lifeform.getRow() - 1;
      while (count >= lifeform.getRow() - lifeform.getWeapon().getMaxRange()) {
        if (count > 0 || count < e.getNumRows()){
          break;
        }
        if (e.getLifeForm(count, lifeform.getCol()) != null
                && e.getLifeForm(count, lifeform.getCol()).getClass() != lifeForm.getClass()) {
          target = ai.getEnvironment().getLifeForm(count, lifeform.getCol());
          count -= 1;
        } else if (target != null){
          count--;
        }
      }
    } else if (lifeform.getCurrentDirection() == "East") {
      int count = lifeform.getCol() + 1;
      while (count <= lifeform.getCol() + lifeform.getWeapon().getMaxRange()) {
        if (count > 0 || count < e.getNumCols()){
          break;
        }
        if (e.getLifeForm(lifeForm.getRow(), count) != null
                && e.getLifeForm(count, lifeform.getCol()).getClass() != lifeForm.getClass()) {
          target = ai.getEnvironment().getLifeForm(lifeform.getRow(), count);
          count += 1;
        } else if (target != null){
          count++;
        }
      }
    } else if (lifeform.getCurrentDirection() == "West") {
      int count = lifeform.getCol() - 1;
      while (count >= lifeform.getCol() - lifeform.getWeapon().getMaxRange()) {
        if (count > 0 || count < e.getNumCols()){
          break;
        }
        if (e.getLifeForm(lifeForm.getRow(), count) != null
                && e.getLifeForm(count, lifeform.getCol()).getClass() != lifeForm.getClass()) {
          target = ai.getEnvironment().getLifeForm(lifeform.getRow(), count);
          count -= 1;
        } else if (target != null){
          count--;
        }
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
