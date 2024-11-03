package command;

import environment.Environment;
import lifeform.Human;
import org.junit.Test;
import weapon.Pistol;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class CommandUI {
  @Test
  // Testing whether Timmy Jr. can turn and move south
  public void testMove() {
    Human timmyJr = new Human("timmyJr", 100, 10);
    Environment env = Environment.getEnvironment(6, 6);
    env.clearBoard();
    Pistol pistol = new Pistol();

    // Place little timmy and weapon in (0,0)
    env.addLifeForm(timmyJr, 0, 0);
    env.addWeapon(pistol, 0, 0);

    // Command to move little timmy South
    timmyJr.setDirection("South");
    timmyJr.setLocation(0,0);
    MoveCmd move = new MoveCmd(env);
    move.execute(0, 0);

    assertEquals(3,timmyJr.getRow());
    assertEquals(0,timmyJr.getCol());
    assertNull(env.getLifeForm(0,0));
    env.clearBoard();

  }
}
