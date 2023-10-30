package unicash.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static unicash.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import unicash.commons.util.ToStringBuilder;
import unicash.model.Model;
import unicash.model.ModelManager;

public class ExitCommandTest {


    @Test
    public void execute_exit_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(ExitCommand.MESSAGE_EXIT_ACKNOWLEDGEMENT, false, true);

        assertCommandSuccess(new ExitCommand(), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void sameExitCommand_equalsTrue() {
        ExitCommand exitCommand = new ExitCommand();
        assertEquals(exitCommand, new ExitCommand());

    }

    @Test
    public void differentCommandTypes_equalsFalse() {
        Command resetCommand = new ResetCommand();
        Command clearCommand = new ClearTransactionsCommand();
        assertNotEquals(resetCommand, clearCommand);
    }

    @Test
    public void nullInput_equalsFalse() {
        assertNotEquals(null, new ExitCommand());
    }

    @Test
    public void toStringTest() {
        ExitCommand exitCommand = new ExitCommand();
        String expected = new ToStringBuilder(new ExitCommand()).toString();
        assertEquals(expected, exitCommand.toString());
    }
}

