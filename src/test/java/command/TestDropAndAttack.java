package command;

import environment.Environment;
import lifeform.Human;
import org.junit.Test;
import weapon.Pistol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;


public class TestDropAndAttack {
  @Test
  // Testing whether Timmy Jr. can turn and move south
  public void testMove() {
    Human timmyJr = new Human("timmyJr", 100, 10);
    Environment env = Environment.getEnvironment(6, 6);
    env.clearBoard();
    Pistol pistol = new Pistol();
    timmyJr.pickUpWeapon(pistol);

    //assertNull(env.getWeapons(1, 1)[0]);

    // Place little timmy and weapon in (0,0)
    env.addLifeForm(timmyJr, 1, 1);

    assertEquals(pistol, timmyJr.getWeapon());

    DropCmd drop = new DropCmd(env);
    drop.execute(1, 1);

    // Command to move little timmy South
    timmyJr.setDirection("South");
    timmyJr.setLocation(1, 1);
    MoveCmd move = new MoveCmd(env);
    move.execute(1, 1);

    assertEquals(pistol, env.getWeapons(1, 1)[0]);

    assertEquals(4, timmyJr.getRow());
    assertEquals(1, timmyJr.getCol());
    assertNull(env.getLifeForm(1, 1));
  }

}
