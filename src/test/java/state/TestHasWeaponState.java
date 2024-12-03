package state;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import environment.Environment;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.Alien;
import lifeform.Human;
import weapon.Pistol;

public class TestHasWeaponState {

  private Environment env;

  @Before
  public void setup() throws EnvironmentException {
    env = new Environment(10, 10); // Create a 10x10 environment
  }

  @Test
  public void testNoTarget() throws EnvironmentException, WeaponException, AttachmentException {
    Alien alien = new Alien("Alien A", 10);
    env.addLifeForm(alien, 0, 0);

    Pistol pistol = new Pistol();
    alien.pickUpWeapon(pistol);
    alien.setDirection("North");

    AIContext aiContext = new AIContext(alien, env);
    aiContext.setCurrentState(aiContext.getHasWeaponState());
    aiContext.execute();

    assertNull(env.getLifeForm(1, 0));
  }

  @Test
  public void testTargetOfSameType() throws EnvironmentException, WeaponException, AttachmentException {
    // Alien cannot shoot another alien
    Alien alien = new Alien("Alien A", 10);
    env.addLifeForm(alien, 0, 0);

    Alien target = new Alien("Alien B", 20);
    env.addLifeForm(target, 1, 0);

    Pistol pistol = new Pistol();
    alien.pickUpWeapon(pistol);
    alien.setDirection("North");

    AIContext aiContext = new AIContext(alien, env);
    aiContext.setCurrentState(aiContext.getHasWeaponState());
    aiContext.execute();

    // nothing should be hit
    assertEquals(target.getMaxLifePoints(), target.getCurrentLifePoints());
  }

  @Test
  public void testTargetOfDifferentType() throws EnvironmentException, WeaponException, AttachmentException {
    Alien alien = new Alien("Alien A", 10);
    env.addLifeForm(alien, 0, 0);

    Human target = new Human("Human B", 50, 5);
    env.addLifeForm(target, 1, 0);

    Pistol pistol = new Pistol();
    alien.pickUpWeapon(pistol);
    alien.setDirection("North");

    AIContext aiContext = new AIContext(alien, env);
    aiContext.setCurrentState(aiContext.getHasWeaponState());
    aiContext.execute();

    System.out.println("Target Current Life: " + target.getCurrentLifePoints());
    System.out.println("Target Max Life: " + target.getMaxLifePoints());

    //target should be hit
    assertTrue(target.getCurrentLifePoints() < target.getMaxLifePoints());
  }

  @Test
  public void testValidTargetWithOneShotLeft() throws EnvironmentException, WeaponException, AttachmentException {
    // alien with 1 ammo left shoots a human
    Alien alien = new Alien("Alien A", 10);
    env.addLifeForm(alien, 0, 0);

    Human target = new Human("Human B", 50, 5);
    env.addLifeForm(target, 1, 0);

    Pistol pistol = new Pistol();
    alien.pickUpWeapon(pistol);
    pistol.fire(pistol.getMaxAmmo() - 1); // leave 1 ammo
    alien.setDirection("North");

    AIContext aiContext = new AIContext(alien, env);
    aiContext.setCurrentState(aiContext.getHasWeaponState());
    aiContext.execute();

    System.out.println("Target Current Life: " + target.getCurrentLifePoints());
    System.out.println("Target Max Life: " + target.getMaxLifePoints());

    // targget should be hit and alien should transition to OutOfAmmoState
    assertTrue(target.getCurrentLifePoints() < target.getMaxLifePoints());
    assertEquals(aiContext.getOutOfAmmoState(), aiContext.getCurrentState());
  }

  @Test
  public void testTargetOutOfRange() throws EnvironmentException, WeaponException, AttachmentException {
    // alien's weapon is out of range for the target
    Alien alien = new Alien("Alien A", 10);
    env.addLifeForm(alien, 0, 0);

    Human target = new Human("Human B", 50, 5);
    env.addLifeForm(target, 5, 0);

    Pistol pistol = new Pistol();
    alien.pickUpWeapon(pistol);
    alien.setDirection("North");

    AIContext aiContext = new AIContext(alien, env);
    aiContext.setCurrentState(aiContext.getHasWeaponState());
    aiContext.execute();

    // target should not be hit
    assertEquals(target.getMaxLifePoints(), target.getCurrentLifePoints());
  }

  @Test
  public void testLifeFormIsDead() throws EnvironmentException, WeaponException, AttachmentException {
    // alien is dead
    Alien alien = new Alien("Alien A", 10);
    alien.takeHit(10); // set life points to 0
    env.addLifeForm(alien, 0, 0);

    AIContext aiContext = new AIContext(alien, env);
    aiContext.setCurrentState(aiContext.getHasWeaponState());
    aiContext.execute();

    // state should transition to DeadState
    assertEquals(aiContext.dead, aiContext.getCurrentState());
  }
}
