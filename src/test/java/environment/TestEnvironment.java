package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import exceptions.EnvironmentException;
import lifeform.LifeForm;
import lifeform.MockLifeForm;
import org.junit.Assert;
import org.junit.Test;

public class TestEnvironment {

  @Test
  public void testEnvironmentInit() {
    Environment env = new Environment(1,1);
    assertNull(env.getLifeForm(0,0));
  }

  @Test
  public void testEnvironmentAddLf() {
    Environment env = new Environment(2,3);
    LifeForm lf = new MockLifeForm("Bob", 40);

    boolean success = env.addLifeForm(lf, 0, 1);
    assertTrue(success);
    assertEquals(lf, env.getLifeForm(0,1));
  }


  @Test
  public void testEnvBorderCaseA() {
    Environment env = new Environment(5,5);
    LifeForm lf = new MockLifeForm("Bob", 40);

    boolean success = env.addLifeForm(lf, 0, 0);
    assertTrue(success);
    assertEquals(lf, env.getLifeForm(0,0));
  }

  @Test
  public void testEnvBorderCaseB() {
    Environment env = new Environment(5,5);
    LifeForm lf = new MockLifeForm("Bob", 40);

    boolean success = env.addLifeForm(lf, 4, 4);
    assertTrue(success);
    assertEquals(lf, env.getLifeForm(4,4));
  }


  @Test
  public void environmentRemoveLifeForm() {
    Environment env = new Environment(2,3);
    LifeForm lf = new MockLifeForm("Bob", 40);

    env.addLifeForm(lf, 0, 1);
    env.removeLifeForm(0,1);

    assertNull(env.getLifeForm(0,1));
  }


  @Test
  public void testDistanceLifeForms() throws EnvironmentException {
    MockLifeForm bob = new MockLifeForm("Bob", 10, 2);
    MockLifeForm sheryl = new MockLifeForm("Sheryl", 30, 5);
    Environment env = new Environment(2,3);
    bob.setLocation(3, 3);
    sheryl.setLocation(3, 3);
    Assert.assertEquals(0.0, env.getDistance(bob, sheryl), 0.1);
    sheryl.setLocation(2, 3);
    Assert.assertEquals(5.0, env.getDistance(bob, sheryl), 0.1);
    //sheryl.setLocation(4, 3);
    Assert.assertEquals(5.0, env.getDistance(bob, sheryl), 0.1);
    sheryl.setLocation(3, 4);
    Assert.assertEquals(5.0, env.getDistance(bob, sheryl), 0.1);
    sheryl.setLocation(3, 2);
    Assert.assertEquals(5.0, env.getDistance(bob, sheryl), 0.1);
    sheryl.setLocation(2, 2);
    Assert.assertEquals(7.07, env.getDistance(bob, sheryl), 0.01);
    sheryl.setLocation(2, 4);
    Assert.assertEquals(7.07, env.getDistance(bob, sheryl), 0.01);
    sheryl.setLocation(4, 2);
    Assert.assertEquals(7.07, env.getDistance(bob, sheryl), 0.01);
    sheryl.setLocation(4, 4);
    Assert.assertEquals(7.07, env.getDistance(bob, sheryl), 0.01);
    sheryl.setLocation(0, 3);
    Assert.assertEquals(15.0, env.getDistance(bob, sheryl), 0.1);
    sheryl.setLocation(5, 3);
    Assert.assertEquals(10.0, env.getDistance(bob, sheryl), 0.1);
    sheryl.setLocation(3, 6);
    Assert.assertEquals(15.0, env.getDistance(bob, sheryl), 0.1);
    sheryl.setLocation(3, 1);
    Assert.assertEquals(10.0, env.getDistance(bob, sheryl), 0.1);
    sheryl.setLocation(2, 1);
    Assert.assertEquals(11.18, env.getDistance(bob, sheryl), 0.01);
    sheryl.setLocation(2, 5);
    Assert.assertEquals(11.18, env.getDistance(bob, sheryl), 0.01);
    sheryl.setLocation(5, 2);
    Assert.assertEquals(11.18, env.getDistance(bob, sheryl), 0.01);
    sheryl.setLocation(4, 5);
    Assert.assertEquals(11.18, env.getDistance(bob, sheryl), 0.01);
  }

}
