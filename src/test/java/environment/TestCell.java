package environment;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import lifeform.LifeForm;
import lifeform.MockLifeForm;
import org.junit.Test;
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


}

