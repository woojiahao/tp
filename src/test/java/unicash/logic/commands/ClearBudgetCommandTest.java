package unicash.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static unicash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static unicash.testutil.TypicalBudgets.MONTHLY;

import org.junit.jupiter.api.Test;

import unicash.model.Model;
import unicash.model.ModelManager;
import unicash.model.UniCash;
import unicash.model.UserPrefs;

public class ClearBudgetCommandTest {
    @Test
    public void execute_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ClearBudgetCommand().execute(null));
    }

    @Test
    public void execute_validModel_clearsBudget() {
        // Clearing the budget is equivalent to setting the value to null
        var model = getModel();
        model.setBudget(MONTHLY);
        assertEquals(MONTHLY, model.getBudget());
        var command = new ClearBudgetCommand();
        command.execute(model);
        assertNull(model.getBudget());
    }

    @Test
    public void execute_validModel_returnsNoBudgetSuccessMessage() {
        var model = getModel();
        model.setBudget(MONTHLY);
        assertEquals(MONTHLY, model.getBudget());
        var command = new ClearBudgetCommand();
        command.execute(model);
        assertCommandSuccess(command, model, ClearBudgetCommand.MESSAGE_NO_BUDGET, getModel());
    }

    @Test
    public void equals_nullOther_returnsFalse() {
        assertNotEquals(new ClearBudgetCommand(), null);
    }

    @Test
    public void equals_otherClearBudgetCommand_returnsTrue() {
        assertEquals(new ClearBudgetCommand(), new ClearBudgetCommand());
    }

    @Test
    public void equals_otherNotClearBudgetCommand_returnsFalse() {
        var command = new ClearBudgetCommand();
        assertFalse(command.equals(5));
    }

    private Model getModel() {
        return new ModelManager(new UniCash(), new UserPrefs());
    }
}
