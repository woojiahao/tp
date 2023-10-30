package unicash.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
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
    public void sameClearTransactionsCommand_equalsTrue() {
        ClearTransactionsCommand clearCommand = new ClearTransactionsCommand();
        assertEquals(clearCommand, new ClearTransactionsCommand());

    }

    @Test
    public void differentCommandTypes_equalsFalse() {
        Command resetCommand = new ResetCommand();
        Command clearCommand = new ClearTransactionsCommand();
        assertNotEquals(resetCommand, clearCommand);
    }

    @Test
    public void nullInput_equalsFalse() {
        assertNotEquals(null, new ClearTransactionsCommand());
    }

    @Test
    public void toStringTest() {
        ClearTransactionsCommand clearCommand = new ClearTransactionsCommand();
        String expected = new ToStringBuilder(new ClearTransactionsCommand()).toString();
        assertEquals(expected, clearCommand.toString());
    }

}
