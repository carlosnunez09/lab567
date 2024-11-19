package command;

import environment.Cell;
import environment.Environment;
import lifeform.Alien;
import lifeform.Human;
import lifeform.LifeForm;
import org.junit.Test;
import weapon.Pistol;

import static org.junit.Assert.*;


public class CommandUI {
  @Test
  // Testing whether Timmy Jr. can turn and move south
  public void testMove() {
    Human timmyJr = new Human("timmyJr", 100, 10);
    Environment env = Environment.getEnvironment(6, 6);
    env.clearBoard();
    Pistol pistol = new Pistol();

    // Place little timmy and weapon in (0,0)
    env.addLifeForm(timmyJr, 1, 1);
    env.addWeapon(pistol, 0, 0);

    // Command to move little timmy South
    timmyJr.setDirection("South");
    timmyJr.setLocation(1,1);
    MoveCmd move = new MoveCmd(env);
    move.execute(1, 1);

    assertEquals(4,timmyJr.getRow());
    assertEquals(1,timmyJr.getCol());
    assertNull(env.getLifeForm(1,1));
  }

  @Test
  // testing whether tyrranid (Alien) can move to go and eat little timmy
  public void testMove2() {
    Alien tyrranid = new Alien("Tyrranid", 10);
    Environment env = Environment.getEnvironment(6,6);
    env.clearBoard();

    // Place tyrranid at (0,0)
    env.addLifeForm(tyrranid,0,0);

    // Command to move tyrranid East
    tyrranid.setDirection("East");
    tyrranid.setLocation(0,0);
    MoveCmd move = new MoveCmd(env);
    move.execute(0,0);

    assertEquals(0,tyrranid.getRow());
    assertEquals(2,tyrranid.getCol());
    assertNull(env.getLifeForm(0,0));
  }



  @Test
  // testing whether tyrranid (Alien) can move to go and eat little timmy
  public void testMove3() {
    Alien tyrranid = new Alien("T-Dog", 10);
    //Human T = new Alien("T", 10);
    Environment env = Environment.getEnvironment(7,7);
    Pistol p = new Pistol();
    env.clearBoard();

    // Place tyrranid at (0,0)
    env.addLifeForm(tyrranid,3,3);
    env.addWeapon(p,3,5);

    // Command to move tyrranid East
    tyrranid.setDirection("East");
    tyrranid.setLocation(3,3);
    MoveCmd move = new MoveCmd(env);
    move.execute(3,3);



    assertEquals(3,tyrranid.getRow());
    assertEquals(5,tyrranid.getCol());
    assertNull(env.getLifeForm(3,3));
    assertEquals(tyrranid, env.getLifeForm(3,5));
    assertEquals(p, env.getWeapons(3, 5)[0]);
  }

  @Test
  // testing whether tyrranid (Alien) can move to go and eat little timmy
  public void testMove4() {
    Alien tyrranid = new Alien("T-Dog", 10);
    Human T = new Human("T", 10, 5);
    Environment env = Environment.getEnvironment(7,7);
    Pistol p = new Pistol();
    env.clearBoard();

    // Place tyrranid at (0,0)
    env.addLifeForm(tyrranid,3,3);
    env.addLifeForm(T,3,5);

    // Command to move tyrranid East
    tyrranid.setDirection("East");
    tyrranid.setLocation(3,3);
    MoveCmd move = new MoveCmd(env);
    move.execute(3,3);



    assertEquals(3,tyrranid.getRow());
    assertEquals(3,tyrranid.getCol());
    assertEquals(tyrranid, env.getLifeForm(3,3));
    assertEquals(T, env.getLifeForm(3,5));
  }





}
