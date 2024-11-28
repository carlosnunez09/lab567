package state;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import java.lang.reflect.Field;
import org.junit.Before;
import org.junit.Test;
import environment.Environment;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.RecoveryRateException;
import exceptions.WeaponException;
import gameplay.SimpleTimer;
import gameplay.Simulator;
import lifeform.Alien;
import lifeform.Human;
import weapon.Pistol;

public class TestHasWeaponState {

  // Reset the singleton Environment before each test
  @Before
  public void resetSingleton() throws Exception {
    Field instance = Environment.class.getDeclaredField("env");
    instance.setAccessible(true);
    instance.set(null, null);
  }

  @Test
  public void testAlienDiesAndRecovers() throws AttachmentException, RecoveryRateException {
    SimpleTimer timer = new SimpleTimer(1000);
    Environment env = Environment.getEnvironment(2, 2);
    Simulator sim = new Simulator(env, timer, 0, 0);

    Alien alien = new Alien("Alien A", 10);
    timer.addTimeObserver(sim);
    AIContext aiContext = new AIContext(alien, env);
    sim.ais.add(aiContext);

    alien.takeHit(50); // Alien dies
    assertEquals(0, alien.getCurrentLifePoints());
    assertEquals(aiContext.getNoWeaponState(), aiContext.getCurrentState());

    timer.timeChanged(); // Time progresses
    assertEquals(aiContext.getDeadState(), aiContext.getCurrentState());

    timer.timeChanged(); // Alien recovers
    assertEquals(alien.getCurrentLifePoints(), alien.getMaxLifePoints());
    assertEquals(aiContext.getNoWeaponState(), aiContext.getCurrentState());
  }

  @Test
  public void testNoTargetInRange() throws AttachmentException, RecoveryRateException, EnvironmentException, WeaponException {
    Environment env = Environment.getEnvironment(2, 2);
    Alien alien = new Alien("Alien A", 10);
    env.addLifeForm(alien, 0, 0);

    AIContext aiContext = new AIContext(alien, env);
    Pistol pistol = new Pistol();
    alien.pickUpWeapon(pistol);
    aiContext.setCurrentState(aiContext.getHasWeaponState());

    assertNull(env.getTarget(0, 0)); // No target
  }

  @Test
  public void testTargetOutOfRange() throws Exception {
    Environment env = Environment.getEnvironment(10, 10);
    Alien alien = new Alien("Alien A", 10);
    env.addLifeForm(alien, 0, 0);

    AIContext aiContext = new AIContext(alien, env);
    Pistol pistol = new Pistol();
    alien.pickUpWeapon(pistol);

    Human target = new Human("Human B", 50);
    env.addLifeForm(target, 10, 0); // Far away target

    alien.setDirection(Dir.South);
    HasWeaponState hasWeaponState = new HasWeaponState(aiContext);
    hasWeaponState.executeAction();

    assertEquals(target.getMaxLifePoints(), target.getCurrentLifePoints()); // Target should not be hit
  }

  @Test
  public void testAlienDiesBeforeAction() throws Exception {
    Environment env = Environment.getEnvironment(10, 10);
    Alien alien = new Alien("Alien A", 10);
    env.addLifeForm(alien, 0, 0);

    AIContext aiContext = new AIContext(alien, env);
    Pistol pistol = new Pistol();
    alien.pickUpWeapon(pistol);

    alien.takeHit(20); // Alien dies
    Human target = new Human("Human B", 50);
    env.addLifeForm(target, 10, 0);

    alien.setDirection(Dir.South);
    HasWeaponState hasWeaponState = new HasWeaponState(aiContext);
    hasWeaponState.executeAction();

    assertEquals(aiContext.getDeadState(), aiContext.getCurrentState()); // Alien should be in DeadState
  }

  @Test
  public void testTargetIsSameType() throws Exception {
    Environment env = Environment.getEnvironment(10, 10);
    Alien alien = new Alien("Alien A", 10);
    env.addLifeForm(alien, 0, 0);

    AIContext aiContext = new AIContext(alien, env);
    Pistol pistol = new Pistol();
    alien.pickUpWeapon(pistol);

    Alien target = new Alien("Alien B", 10); // Same type target
    env.addLifeForm(target, 1, 0);

    alien.setDirection(Dir.South);
    aiContext.setCurrentState(aiContext.getHasWeaponState());
    aiContext.getCurrentState().executeAction();

    assertEquals(target.getMaxLifePoints(), target.getCurrentLifePoints()); // Target should not be attacked
  }
}
