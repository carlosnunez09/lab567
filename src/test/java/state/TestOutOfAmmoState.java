package state;

import environment.Environment;
import lifeform.Alien;
import lifeform.LifeForm;
import org.junit.Test;
import weapon.Pistol;
import weapon.Weapon;

import static org.junit.Assert.assertTrue;

public class TestOutOfAmmoState {



  @Test
  public void testOutOfAmmoStateInitializedProperly() {
    AIContext context = new AIContext(new Alien("Alien A", 10), Environment.getEnvironment(6, 6));
    OutOfAmmoState outOfAmmoState = new OutOfAmmoState(context);
    context.setCurrentState(outOfAmmoState);

    assertTrue(context.getCurrentState() instanceof OutOfAmmoState);
  }

  @Test
  public void testWeaponReloadedProperly() {
    AIContext context = new AIContext(new Alien("Alien A", 10), Environment.getEnvironment(6, 6));
    OutOfAmmoState outOfAmmoState = new OutOfAmmoState(context);
    context.setCurrentState(outOfAmmoState);


    Weapon weapon = new Pistol();
    weapon.reload();
    LifeForm lifeForm = context.getLifeForm();
    lifeForm.pickUpWeapon(weapon);


    assertTrue(context.getCurrentState() instanceof HasWeaponState);
  }

  @Test
  public void testMovesToCorrectState() {
    AIContext context = new AIContext(new Alien("Alien A", 10), Environment.getEnvironment(6, 6));
    OutOfAmmoState outOfAmmoState = new OutOfAmmoState(context);
    context.setCurrentState(outOfAmmoState);


    Weapon weapon = new Pistol();
    weapon.reload();
    LifeForm lifeForm = context.getLifeForm();
    lifeForm.pickUpWeapon(weapon);


    assertTrue(context.getCurrentState() instanceof HasWeaponState);
  }

  @Test
  public void testIfDead() {
    AIContext context = new AIContext(new Alien("Alien A", 10), Environment.getEnvironment(6, 6));
    ////AIContext context = new AIContext(alien, environment);
    //OutOfAmmoState outOfAmmoState = new OutOfAmmoState(context);
    //context.setCurrentState(outOfAmmoState);


    context.getLifeForm().takeHit(20);


    assertTrue(context.getCurrentState() instanceof DeadState);
  }

}
