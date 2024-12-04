package state;

import environment.Environment;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import lifeform.Alien;
import org.junit.Test;
import weapon.Pistol;
import weapon.Weapon;

import static org.junit.Assert.*;

public class TestNoWeaponState {

  @Test
  public void testWeaponInCell() {
    Environment env = Environment.getEnvironment(6, 6);
    env.clearBoard();
    Pistol pistol = new Pistol();
    env.addWeapon(pistol, 1, 1);

    Weapon[] weapons = env.getWeapons(1, 1);
    assertNotNull(weapons);
    assertEquals(2, weapons.length);
    assertEquals(pistol, weapons[0]);
  }


  @Test
  public void testNoWeaponInCell() {
    Environment env = Environment.getEnvironment(6, 6);
    env.clearBoard();

    Weapon[] weapons = env.getWeapons(1, 1);
    //assertNotNull(weapons);
    //assertEquals(0, weapons.length);
  }


  @Test
  public void testIfDeadNoSetup() throws AttachmentException, WeaponException, EnvironmentException {
    Environment env = Environment.getEnvironment(6, 6);
    Alien alien = new Alien("Alien A", 10);
    env.addLifeForm(alien, 1, 1);

    alien.takeHit(20); // Assume this makes the alien dead
    AIContext aiContext = new AIContext(alien, env);
    //aiContext.getCurrentState().executeAction();

    //assertTrue(aiContext.getCurrentState() instanceof DeadState);
    //assertNull(env.getLifeForm(1, 1));
  }



}
