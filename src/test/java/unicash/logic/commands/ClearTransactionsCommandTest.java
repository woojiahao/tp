package unicash.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static unicash.testutil.TypicalTransactions.getTypicalUniCash;

import org.junit.jupiter.api.Test;

import unicash.commons.util.ToStringBuilder;
import unicash.model.Model;
import unicash.model.ModelManager;
import unicash.model.UniCash;
import unicash.model.UserPrefs;


public class ClearTransactionsCommandTest {

    @Test
    public void execute_emptyUniCash_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearTransactionsCommand(), model,
                ClearTransactionsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyUniCash_success() {
        Model model = new ModelManager(getTypicalUniCash(), new UserPrefs());
        Model expectedModel = new ModelManager(new UniCash(), new UserPrefs());
        expectedModel.setUniCash(new UniCash());

        assertCommandSuccess(new ClearTransactionsCommand(), model,
                ClearTransactionsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void multipleClearTransactionsCommand_equalsTrue() {
        ClearTransactionsCommand clearCommand = new ClearTransactionsCommand();
        assertEquals(clearCommand, new ClearTransactionsCommand());

    }

    @Test
    public void sameClearTransactionsCommand_equalsTrue() {
        ClearTransactionsCommand clearCommand = new ClearTransactionsCommand();
        assertTrue(clearCommand.equals(clearCommand));
        assertTrue(clearCommand.equals(new ClearTransactionsCommand()));

    }

    @Test
    public void equalsMethod_differentCommandTypes_returnsFalse() {
        Command resetCommand = new ResetCommand();
        Command clearCommand = new ClearTransactionsCommand();
        assertNotEquals(resetCommand, clearCommand);
        assertFalse(clearCommand.equals(resetCommand));
        assertFalse(clearCommand.equals(new ResetCommand()));
    }

    @Test
    public void equalsMethod_nullInput_returnsFalse() {
        assertNotEquals(null, new ClearTransactionsCommand());
    }

    @Test
    public void toStringMethod() {
        ClearTransactionsCommand clearCommand = new ClearTransactionsCommand();
        String expected = new ToStringBuilder(new ClearTransactionsCommand()).toString();
        assertEquals(expected, clearCommand.toString());
    }

}
