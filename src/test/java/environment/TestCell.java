package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import lifeform.LifeForm;
import lifeform.MockLifeForm;
import org.junit.Test;
import weapon.MockWeapon;

/**
 * The test cases for the Cell class
 *
 */

public class TestCell {

  /**
   * At initialization, the Cell should be empty and not contain a
   * LifeForm.
   */
  @Test
  public void testInitialization() {
    Cell cell = new Cell();
    assertNull(cell.getLifeForm());
  }

  /**
   * Checks to see if we change the LifeForm held by the Cell that
   * getLifeForm properly responds to this change.
   */
  @Test
  public void testCellAddLifeForm() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    LifeForm fred = new MockLifeForm("Fred", 40);
    Cell cell = new Cell();
    // The cell is empty so this should work.
    boolean success = cell.addLifeForm(bob);
    assertTrue(success);
    assertEquals(bob,cell.getLifeForm());
  }

  @Test
  public void testLifeFormAlreadyOnCell() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    LifeForm fred = new MockLifeForm("Fred", 40);
    Cell cell = new Cell();

    cell.addLifeForm(bob);

    boolean success = cell.addLifeForm(fred);
    assertFalse(success);
    assertEquals(bob,cell.getLifeForm());
  }


  @Test
  public void testRemoveLifeFormCell() {
    LifeForm bob = new MockLifeForm("Bob", 40);
    Cell cell = new Cell();


    assertTrue(cell.addLifeForm(bob));
    cell.removeLifeForm();

    assertNull(cell.getLifeForm());
  }

  @Test
  public void testAddWeapon() {
    Cell cell = new Cell();
    MockWeapon weapon = new MockWeapon();
    cell.addWeapon(weapon);
    assertEquals(weapon, cell.getWeapon1());
  }

  @Test
  public void testRemoveWeapon() {
    Cell cell = new Cell();
    MockWeapon weapon1 = new MockWeapon();
    MockWeapon weapon2 = new MockWeapon();

    cell.addWeapon(weapon1);
    cell.addWeapon(weapon2);

    assertEquals(weapon1, cell.getWeapon1());
    assertEquals(weapon2, cell.getWeapon2());

    // not working here once I try and remove weapon for some reason???
    cell.removeWeapon(weapon1);
    cell.removeWeapon(weapon2);

    //assertEquals(weapon1, cell.getWeapon1());
    //assertEquals(weapon2, cell.getWeapon2());

    // we are checking if the weapons are removed from the cell
    assertNull(cell.getWeapon1());
    assertNull(cell.getWeapon2());
  }

  @Test
  public void test3WeaponsCellError() {
    Cell cell = new Cell();

    MockWeapon weapon1 = new MockWeapon();
    MockWeapon weapon2 = new MockWeapon();
    MockWeapon weapon3 = new MockWeapon();

    cell.addWeapon(weapon1);
    cell.addWeapon(weapon2);
    cell.addWeapon(weapon3);

    assertEquals(weapon1, cell.getWeapon1());
    assertEquals(weapon2, cell.getWeapon2());
    assertEquals(2, cell.getWeaponsCount()); // Only supposed to have 2 weapons per cell.
  }
}

