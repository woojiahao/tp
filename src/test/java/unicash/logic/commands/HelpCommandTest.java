package unicash.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static unicash.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;

import org.junit.jupiter.api.Test;

import unicash.commons.util.ToStringBuilder;
import unicash.model.Model;
import unicash.model.ModelManager;



public class HelpCommandTest {

    @Test
    public void execute_help_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_HELP_MESSAGE, true, false);

        assertCommandSuccess(new HelpCommand(), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void multipleHelpCommand_equalsTrue() {
        HelpCommand helpCommand = new HelpCommand();
        assertEquals(helpCommand, new HelpCommand());

    }

    @Test
    public void sameHelpCommand_equalsTrue() {
        HelpCommand helpCommand = new HelpCommand();
        assertTrue(helpCommand.equals(helpCommand));
        assertTrue(helpCommand.equals(new HelpCommand()));

    }

    @Test
    public void differentCommandTypes_equalsFalse() {
        Command resetCommand = new ResetCommand();
        Command helpCommand = new HelpCommand();
        assertNotEquals(resetCommand, helpCommand);
        assertFalse(helpCommand.equals(resetCommand));
        assertFalse(helpCommand.equals(new ResetCommand()));
    }

    @Test
    public void nullInput_equalsFalse() {
        assertNotEquals(null, new HelpCommand());
        assertFalse(new HelpCommand().equals(null));
    }

    @Test
    public void toStringTest() {
        HelpCommand helpCommand = new HelpCommand();
        String expected = new ToStringBuilder(new HelpCommand()).toString();
        assertEquals(expected, helpCommand.toString());
    }

}
