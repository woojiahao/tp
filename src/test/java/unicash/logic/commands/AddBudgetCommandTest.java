package unicash.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static unicash.testutil.Assert.assertThrows;
import static unicash.testutil.TypicalBudgets.DAILY;
import static unicash.testutil.TypicalBudgets.WEEKLY;

import org.junit.jupiter.api.Test;

public class AddBudgetCommandTest {
    @Test
    public void constructor_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddBudgetCommand(null));
    }

    @Test
    public void execute_nullModel_throwsNullPointerException() {
        var command = new AddBudgetCommand(DAILY);
        assertThrows(NullPointerException.class, () -> command.execute(null));
    }

    @Test
    public void equals() {
        AddBudgetCommand addBudgetCommand = new AddBudgetCommand(DAILY);
        AddBudgetCommand addBudgetCommandCopy = new AddBudgetCommand(DAILY);
        AddBudgetCommand addDifferentBudgetCommand = new AddBudgetCommand(WEEKLY);

        //same values -> returns true
        assertEquals(addBudgetCommand, addBudgetCommandCopy);

        //same budget command -> returns true
        assertEquals(addBudgetCommand, addBudgetCommand);

        //null -> returns false
        assertNotEquals(null, addBudgetCommand);

        //different type -> returns false
        assertNotEquals(addDifferentBudgetCommand, addBudgetCommand);

        assertFalse(addBudgetCommand.equals(5));
    }

    @Test
    public void toStringMethod() {
        AddBudgetCommand addBudgetCommand = new AddBudgetCommand(DAILY);
        String expected =
                AddBudgetCommand.class.getCanonicalName() + "{budget=" + DAILY + "}";
        assertEquals(expected, addBudgetCommand.toString());
    }

}
