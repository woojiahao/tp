package unicash.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static unicash.model.util.SampleDataUtil.getSampleUniCash;

import org.junit.jupiter.api.Test;

import unicash.commons.util.ToStringBuilder;
import unicash.model.Model;
import unicash.model.ModelManager;
import unicash.model.UniCash;
import unicash.model.UserPrefs;


public class ResetCommandTest {

    @Test
    public void execute_emptyUniCash_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        expectedModel.setUniCash(getSampleUniCash());

        assertCommandSuccess(new ResetCommand(), model,
                ResetCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyUniCash_success() {
        Model model = new ModelManager(getSampleUniCash(), new UserPrefs());
        Model expectedModel = new ModelManager(new UniCash(), new UserPrefs());
        expectedModel.setUniCash(getSampleUniCash());

        assertCommandSuccess(new ResetCommand(), model,
                ResetCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_wrongCommandResult_returnsFalse() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(ResetCommand.MESSAGE_SUCCESS, true, false);

        assertFalse(new ResetCommand().execute(model).equals(expectedCommandResult));
    }

    @Test
    public void multipleResetCommand_equalsTrue() {
        ResetCommand resetCommand = new ResetCommand();
        assertEquals(resetCommand, new ResetCommand());

    }

    @Test
    public void sameResetCommand_equalsTrue() {
        ResetCommand resetCommand = new ResetCommand();
        assertTrue(resetCommand.equals(resetCommand));
        assertTrue(resetCommand.equals(new ResetCommand()));

    }

    @Test
    public void equalsMethod_differentCommandTypes_returnsFalse() {
        Command resetCommand = new ResetCommand();
        Command clearCommand = new ClearTransactionsCommand();
        assertNotEquals(resetCommand, clearCommand);
        assertFalse(clearCommand.equals(resetCommand));
        assertFalse(resetCommand.equals(new ClearTransactionsCommand()));

    }

    @Test
    public void equalsMethod_nullInput_returnsFalse() {
        assertNotEquals(null, new ResetCommand());
        assertFalse(new ResetCommand().equals(null));
    }

    @Test
    public void toStringTest() {
        ResetCommand resetCommand = new ResetCommand();
        String expected = new ToStringBuilder(new ResetCommand()).toString();
        assertEquals(expected, resetCommand.toString());
    }

}
