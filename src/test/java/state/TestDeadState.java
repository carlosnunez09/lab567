package state;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import environment.Environment;
import lifeform.Alien;
import lifeform.Human;
import weapon.Pistol;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import exceptions.AttachmentException;

public class TestDeadState {

  private Environment env;

  @Before
  public void setup() throws EnvironmentException {
    env = new Environment(10, 10); // Create a 10x10 environment
  }

  @Test
  public void testWithWeapon() throws EnvironmentException, WeaponException, AttachmentException {
    Alien alien = new Alien("Alien A", 10);
    env.addLifeForm(alien, 0, 0);

    Pistol pistol = new Pistol();
    alien.pickUpWeapon(pistol);
    alien.setDirection("North");

    AIContext aiContext = new AIContext(alien, env);
    aiContext.setCurrentState(aiContext.getDeadState());

    aiContext.execute();

    assertNotNull(env.getLifeForm(alien.getRow(), alien.getCol()));
    assertNotEquals(0, alien.getRow());
    assertNotEquals(0, alien.getCol());

    assertEquals(aiContext.getNoWeaponState(), aiContext.getCurrentState());
  }

  @Test
  public void testWithoutWeapon() throws EnvironmentException, WeaponException, AttachmentException {
    Alien alien = new Alien("Alien A", 10);
    env.addLifeForm(alien, 0, 0);

    AIContext aiContext = new AIContext(alien, env);
    aiContext.setCurrentState(aiContext.getDeadState());

    aiContext.execute();

    assertNotNull(env.getLifeForm(alien.getRow(), alien.getCol()));
    assertNotEquals(0, alien.getRow());
    assertNotEquals(0, alien.getCol());

    assertEquals(aiContext.getNoWeaponState(), aiContext.getCurrentState());
  }
}
