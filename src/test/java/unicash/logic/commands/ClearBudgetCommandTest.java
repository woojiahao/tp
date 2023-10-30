package unicash.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
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
    public void execute_validModel_returnsSuccess() {
        var model = getModel();
        model.setBudget(MONTHLY);
        assertEquals(MONTHLY, model.getBudget());
        var command = new ClearBudgetCommand();
        command.execute(model);
        assertCommandSuccess(command, model, ClearBudgetCommand.MESSAGE_SUCCESS, getModel());
    }

    private Model getModel() {
        return new ModelManager(new UniCash(), new UserPrefs());
    }
}
