package lifeform;

import exceptions.WeaponException;
import org.junit.Test;
import weapon.MockWeapon;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

/**
 * Tests the functionality provided by the LifeForm class
 *
 */

public class TestLifeForm {
  /**
   * When a LifeForm is created, it should know its name and how
   * many life points it has.
   */
  @Test
  public void testLfInitialization() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    assertEquals("Bob", entity.getName());
    assertEquals(40, entity.getCurrentLifePoints());
  }




  /**
   * Test take damage decreases lifePoints.
   */
  @Test
  public void testLfTakeDamage() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    entity.takeHit(10);

    assertEquals(30, entity.getCurrentLifePoints());
  }

  /**
   * Take damage to negative should be zero.
   */
  @Test
  public void testLfTakeDamageNegative() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    entity.takeHit(50);

    assertEquals(0, entity.getCurrentLifePoints());
  }


  /**
   * Test Proper Attack Damage is Set.
   */
  @Test
  public void testAttackDamage() {
    LifeForm entity;
    entity = new MockLifeForm("Bob", 40);
    assertEquals(0, entity.getAttackStrength());
  }


  /**
   * Test Damage can be inflicted on other Lives.
   */
  @Test
  public void testAttackHit() throws WeaponException {
    LifeForm entity1 = new MockLifeForm("p1", 50, 5);
    LifeForm entity2 = new MockLifeForm("p2", 30, 2);

    entity1.attack(entity2, 0);
    assertEquals(25, entity2.getCurrentLifePoints());
  }

  /**
   * No Attack damage if no health
   */
  @Test
  public void testAttackHitNoHealth() throws WeaponException {
    LifeForm entity1 = new MockLifeForm("p1", 0, 5);
    LifeForm entity2 = new MockLifeForm("p2", 30, 2);

    entity1.attack(entity2, 0);

    assertEquals(30, entity2.getCurrentLifePoints());
  }


  @Test
  public void testPickUpWeapon() {
    LifeForm entity = new MockLifeForm("p1", 0, 5);
    MockWeapon weapon = new MockWeapon();

    entity.pickUpWeapon(weapon);

    assertTrue(entity.hasWeapon());
  }

  @Test
  public void testCanNotPickUpWeapon() {
    LifeForm entity = new MockLifeForm("p1", 0, 5);
    MockWeapon weapon = new MockWeapon();
    MockWeapon weapon2 = new MockWeapon();

    entity.pickUpWeapon(weapon);

    assertFalse(entity.pickUpWeapon(weapon2));
    assertTrue(entity.hasWeapon());
  }

  @Test
  public void testCanDropWeapon() {
    LifeForm entity = new MockLifeForm("p1", 0, 5);
    MockWeapon weapon = new MockWeapon();

    entity.pickUpWeapon(weapon);

    entity.dropWeapon();

    assertFalse(entity.hasWeapon());
  }


  @Test
  public void testUseWeapon() throws WeaponException {
    LifeForm entity1 = new MockLifeForm("p1", 10, 5);
    LifeForm entity2 = new MockLifeForm("p1", 15, 5);
    MockWeapon weapon = new MockWeapon();

    entity1.pickUpWeapon(weapon);

    entity1.attack(entity2, 6);

    assertEquals(5, entity2.getCurrentLifePoints());
  }

  @Test
  public void testUseMeleeNoAmmo() throws WeaponException {
    LifeForm entity1 = new MockLifeForm("p1", 10, 5);
    LifeForm entity2 = new MockLifeForm("p1", 15, 5);
    MockWeapon weapon = new MockWeapon();
    weapon.fire(1);
    weapon.fire(1);
    weapon.updateTime(1);
    weapon.fire(1);
    weapon.fire(1);
    weapon.updateTime(1);
    weapon.fire(1);

    assertEquals(0, weapon.getCurrentAmmo());

    entity1.pickUpWeapon(weapon);

    entity1.attack(entity2, 5);

    assertEquals(10, entity2.getCurrentLifePoints());
  }

  @Test
  public void testMeleeOutRange() throws WeaponException {
    LifeForm entity1 = new MockLifeForm("p1", 10, 5);
    LifeForm entity2 = new MockLifeForm("p1", 15, 5);

    entity1.attack(entity2, 6);

    assertEquals(15, entity2.getCurrentLifePoints());
  }

  @Test
  public void testWeaponReload() throws WeaponException {
    LifeForm entity1 = new MockLifeForm("p1", 10, 5);
    MockWeapon weapon = new MockWeapon();

    entity1.pickUpWeapon(weapon);

    entity1.weapon.fire(1);
    entity1.weapon.reload();

    assertEquals(entity1.weapon.getMaxAmmo(), entity1.weapon.getCurrentAmmo());
  }


}