package command;
import environment.Cell;
import environment.Environment;
import exceptions.WeaponException;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import org.junit.Test;
import weapon.Pistol;
import weapon.Weapon;

import static org.junit.Assert.*;

public class TestCommand {

  @Test
  public void testReload() throws WeaponException {
    Human timmyJr = new Human("timmyJr", 100, 10);
    Environment env = Environment.getEnvironment(6, 6);
    env.clearBoard();
    Pistol pistol = new Pistol();
    env.addWeapon(pistol, 0, 0);
    env.addLifeForm(timmyJr, 1, 1);
    timmyJr.pickUpWeapon(pistol);

    pistol.fire(1);

    assertEquals(pistol.getCurrentAmmo(), pistol.getMaxAmmo() - 1);

    ReloadCmd reloadCmd = new ReloadCmd(env);
    reloadCmd.execute(1, 1);

    assertEquals(pistol.getCurrentAmmo(), pistol.getMaxAmmo());
  }


  @Test
  public void testTurnNorth() {
    Human timmyJr = new Human("timmyJr", 100, 10);
    timmyJr.setDirection("North");
    assertEquals("North", timmyJr.getCurrentDirection());
  }

  @Test
  public void testTurnSouth() {
    Human timmyJr = new Human("timmyJr", 100, 10);
    timmyJr.setDirection("South");
    assertEquals("South", timmyJr.getCurrentDirection());
  }

  @Test
  public void testTurnEast() {
    Human paul = new Human("paul", 100, 10);
    paul.setDirection("East");
    assertEquals("East", paul.getCurrentDirection());
  }

  @Test
  public void testTurnWest() {
    Human joe = new Human("joe", 100, 10);
    joe.setDirection("West");
    assertEquals("West", joe.getCurrentDirection());
  }

  @Test
  public void testDropWeaponWithSpaceAvailable() {
    Human jamal = new Human("jamal", 100, 10);
    Environment env = Environment.getEnvironment(6, 6);
    env.clearBoard();
    Pistol pistol = new Pistol();
    env.addLifeForm(jamal, 1, 1);
    jamal.pickUpWeapon(pistol);

    assertTrue(jamal.hasWeapon());
    jamal.dropWeapon();
    assertFalse(jamal.hasWeapon());
  }

  @Test
  public void testDropWeaponWithNoSpace() {
    Human jim = new Human("jim", 100, 10);
    Environment env = Environment.getEnvironment(6, 6);
    env.clearBoard();
    Pistol pistol = new Pistol();
    env.addWeapon(pistol, 1, 1);
    env.addLifeForm(jim, 1, 1);
    jim.pickUpWeapon(pistol);

    assertTrue(jim.hasWeapon());
    jim.dropWeapon();

  }

  @Test
  public void testAcquireWeaponWithOneAvailableAndNoWeapon() {
    Human alex = new Human("alex", 100, 10);
    Environment env = Environment.getEnvironment(6, 6);
    env.clearBoard();
    Pistol pistol = new Pistol();
    env.addWeapon(pistol, 1, 1);
    env.addLifeForm(alex, 1, 1);

    assertFalse(alex.hasWeapon());
    alex.pickUpWeapon(pistol);
    assertTrue(alex.hasWeapon());
  }

  @Test
  public void testAcquireWeaponWithWeaponAvailableButAlreadyEquipped() {
    Human yon = new Human("yon", 100, 10);
    Environment env = Environment.getEnvironment(6, 6);
    env.clearBoard();
    Pistol pistol1 = new Pistol();
    Pistol pistol2 = new Pistol();
    env.addWeapon(pistol1, 1, 1);
    env.addWeapon(pistol2, 1, 1);
    env.addLifeForm(yon, 1, 1);
    yon.pickUpWeapon(pistol1);

    assertTrue(yon.hasWeapon());
    yon.pickUpWeapon(pistol2);
    assertEquals(pistol1, yon.getWeapon());
  }
}




