package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

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

}
