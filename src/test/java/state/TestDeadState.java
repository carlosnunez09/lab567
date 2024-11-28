package state;

import environment.Environment;
import lifeform.Alien;
import org.junit.Before;
import org.junit.Test;
import weapon.Pistol;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestDeadState {

  @Before
  public void resetSingleton() throws SecurityException, NoSuchFieldException, IllegalArgumentException, IllegalAccessException {
    // Reset the singleton instance of Environment before each test
    Field instance = Environment.class.getDeclaredField("env");
    instance.setAccessible(true);
    instance.set(null, null);
  }

  @Test
  public void withWeapon() throws Exception {
    // Initialize the environment and objects
    Environment environment = Environment.getEnvironment(10, 10);
    Alien alien = new Alien("A", 10);
    environment.addLifeForm(alien, 0, 0);

    AIContext aiContext = new AIContext(alien, environment);
    Pistol pistol = new Pistol();
    alien.pickUpWeapon(pistol);

    // Simulate taking damage and switching to dead state
    alien.takeHit(20);
    aiContext.getCurrentState().executeAction();

    // Verify the state is correctly set to DeadState
    assertEquals(aiContext.getCurrentState(), aiContext.getDeadState());

    // Execute action in DeadState
    aiContext.getCurrentState().executeAction();

    // Verify weapon is dropped and life form is removed
    Weapon[] weapons = environment.getWeapons(0, 0);
    assertEquals(pistol, weapons[0]);
    assertNull(environment.getLifeForm(0, 0));
  }

  @Test
  public void noWeapon() throws Exception {
    // Initialize the environment and objects
    Environment environment = Environment.getEnvironment(10, 10);
    Alien alien = new Alien("A", 10);
    environment.addLifeForm(alien, 0, 0);

    AIContext aiContext = new AIContext(alien, environment);

    // Simulate taking damage and switching to dead state
    alien.takeHit(20);
    aiContext.getCurrentState().executeAction();

    // Verify the state is correctly set to DeadState
    assertEquals(aiContext.getCurrentState(), aiContext.getDeadState());

    // Execute action in DeadState
    aiContext.getCurrentState().executeAction();

    // Verify it transitions to NoWeaponState (or appropriate state logic)
    assertEquals(aiContext.getCurrentState(), aiContext.getNoWeaponState());
  }
}
