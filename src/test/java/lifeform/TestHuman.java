package lifeform;
import exceptions.WeaponException;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TestHuman {

  @Test
  public void testHumanInit() {
    Human rl = new Human("Bill", 25, 5);
    assertEquals("Bill", rl.getName());
  }

  @Test
  public void testHumanLifePoints() {
    Human rl = new Human("Bill", 25, 5);
    int results = rl.getCurrentLifePoints();
    assertEquals(25, results);
  }


  @Test
  public void testHumanArmor() {
    Human rl = new Human("Bill", 25, -65);
    int results = rl.getArmorPoints();
    assertEquals(0, results);
  }

  @Test
  public void testHumanAttack() throws WeaponException {
    Alien rl = new Alien("Bill", 25);
    Human rf = new Human("Bob", 25, 0);

    rl.attack(rf,0);
    int results = rf.getCurrentLifePoints();
    assertEquals(15, results);

  }

  @Test
  public void testAttackNoArmor() throws WeaponException {
    Alien bob = new Alien("Bob", 40);
    Human sheryl = new Human("Sheryl", 50, 0);
    bob.attack(sheryl,0);
    Assert.assertEquals(40L, (long) sheryl.getCurrentLifePoints());
    sheryl.attack(bob,0);
    Assert.assertEquals(35L, (long) bob.getCurrentLifePoints());

  }

  /*@Test
  public void testHumanAttackedWithArmor() {
    Alien bob = new Alien("Bob", 40); //attack = 5
    Human sheryl = new Human("Sheryl", 50, 3);
    bob.attack(sheryl);
    Assert.assertEquals(43L, (long)sheryl.getCurrentLifePoints());
    sheryl.setArmorPoints(10);
    bob.attack(sheryl);
    Assert.assertEquals(43L, (long)sheryl.getCurrentLifePoints());
    sheryl.setArmorPoints(5);
    bob.attack(sheryl);
    Assert.assertEquals(48L, (long)sheryl.getCurrentLifePoints());
  }*/
}