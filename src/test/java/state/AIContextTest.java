package state;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import exceptions.AttachmentException;
import exceptions.EnvironmentException;
import exceptions.WeaponException;
import environment.Environment;
import lifeform.LifeForm;
import state.AIContext;
import state.ActionState;
import state.DeadState;
import state.HasWeaponState;
import state.NoWeaponState;
import state.OutOfAmmoState;

public class AIContextTest {

    private AIContext aiContext;
    private LifeForm mockLifeForm;
    private Environment mockEnvironment;

    @Before
    public void setUp() {
        // Create mock objects for LifeForm and Environment
        mockLifeForm = new MockLifeForm();
        mockEnvironment = new MockEnvironment();

        // Initialize AIContext with mock objects
        aiContext = new AIContext(mockLifeForm, mockEnvironment);
    }

    @Test
    public void testCanChangeActiveState() {
        // Initially, the current state should be NoWeaponState
        assertTrue(aiContext.getCurrentState() instanceof NoWeaponState);

        // Change to HasWeaponState
        aiContext.setCurrentState(aiContext.getHasWeaponState());
        assertTrue(aiContext.getCurrentState() instanceof HasWeaponState);

        // Change to OutOfAmmoState
        aiContext.setCurrentState(aiContext.getOutOfAmmoState());
        assertTrue(aiContext.getCurrentState() instanceof OutOfAmmoState);

        // Change to DeadState
        aiContext.setCurrentState(aiContext.getDeadState());
        assertTrue(aiContext.getCurrentState() instanceof DeadState);
    }

    @Test
    public void testCanGetStates() {
        // Test that getHasWeaponState returns an instance of HasWeaponState
        assertTrue(aiContext.getHasWeaponState() instanceof HasWeaponState);

        // Test that getNoWeaponState returns an instance of NoWeaponState
        assertTrue(aiContext.getNoWeaponState() instanceof NoWeaponState);

        // Test that getOutOfAmmoState returns an instance of OutOfAmmoState
        assertTrue(aiContext.getOutOfAmmoState() instanceof OutOfAmmoState);

        // Test that getDeadState returns an instance of DeadState
        assertTrue(aiContext.getDeadState() instanceof DeadState);
    }

    // Mock classes for LifeForm and Environment
    class MockLifeForm extends LifeForm {
        public MockLifeForm() {
            super("MockLifeForm", 100);
        }


    }

    class MockEnvironment extends Environment {
        public MockEnvironment() {
            super(10, 10); // Assuming a 10x10 grid
        }

        // Implement necessary methods, or leave empty if not needed for the tests
    }
}
