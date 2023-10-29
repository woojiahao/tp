package unicash.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static unicash.logic.UniCashMessages.MESSAGE_UNKNOWN_COMMAND;
import static unicash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static unicash.logic.commands.HelpCommand.SHOWING_HELP_MESSAGE;
import static unicash.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import unicash.model.Model;
import unicash.model.ModelManager;

public class HelpCommandTest {

    @Test
    public void constructor_nullTransaction_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new HelpCommand(null));
    }

    @Test
    public void execute_emptyArguments_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(SHOWING_HELP_MESSAGE, true, false);

        assertCommandSuccess(new HelpCommand(""), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_unknownCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(String.format("%s\n\n%s",
                        MESSAGE_UNKNOWN_COMMAND, HelpCommand.MESSAGE_USAGE));

        assertCommandSuccess(new HelpCommand("d"), model,
                expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_findCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(FindCommand.MESSAGE_USAGE);

        assertCommandSuccess(new HelpCommand(FindCommand.COMMAND_WORD),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_listCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(ListCommand.MESSAGE_USAGE);

        assertCommandSuccess(new HelpCommand(ListCommand.COMMAND_WORD),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_addTransactionCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(HelpCommand.MESSAGE_USAGE);

        assertCommandSuccess(new HelpCommand(HelpCommand.COMMAND_WORD),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_editTransactionCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(EditTransactionCommand.MESSAGE_USAGE);

        assertCommandSuccess(new HelpCommand(EditTransactionCommand.COMMAND_WORD),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_deleteTransactionCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(DeleteTransactionCommand.MESSAGE_USAGE);

        assertCommandSuccess(new HelpCommand(DeleteTransactionCommand.COMMAND_WORD),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_getTotalExpenditureCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(GetTotalExpenditureCommand.MESSAGE_USAGE);

        assertCommandSuccess(new HelpCommand(GetTotalExpenditureCommand.COMMAND_WORD),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_clearTransactionsCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(ClearTransactionsCommand.MESSAGE_USAGE);

        assertCommandSuccess(new HelpCommand(ClearTransactionsCommand.COMMAND_WORD),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_addBudgetCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(AddBudgetCommand.MESSAGE_USAGE);

        assertCommandSuccess(new HelpCommand(AddBudgetCommand.COMMAND_WORD),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_resetCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();
        System.out.println("&*&*&* " + ResetCommand.MESSAGE_USAGE);
        CommandResult expectedCommandResult =
                new CommandResult(ResetCommand.MESSAGE_USAGE);
        System.out.println("***" + expectedCommandResult);
        assertCommandSuccess(new HelpCommand(ResetCommand.COMMAND_WORD),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_getCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(GetCommand.MESSAGE_USAGE);

        assertCommandSuccess(new HelpCommand(GetCommand.COMMAND_WORD),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_helpCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(HelpCommand.MESSAGE_USAGE);

        assertCommandSuccess(new HelpCommand(HelpCommand.COMMAND_WORD),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_exitCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(ExitCommand.MESSAGE_USAGE);

        assertCommandSuccess(new HelpCommand(ExitCommand.COMMAND_WORD),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void execute_summaryCommand_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandResult expectedCommandResult =
                new CommandResult(SummaryCommand.MESSAGE_USAGE);

        assertCommandSuccess(new HelpCommand(SummaryCommand.COMMAND_WORD),
                model, expectedCommandResult, expectedModel);
    }

    @Test
    public void equals() {
        HelpCommand helpCommandEdit = new HelpCommand(EditTransactionCommand.COMMAND_WORD);
        HelpCommand helpCommandDelete = new HelpCommand(DeleteTransactionCommand.COMMAND_WORD);

        // same object -> returns true
        assertEquals(helpCommandEdit, helpCommandEdit);

        // same values -> returns true
        HelpCommand helpCommandEditCopy = new HelpCommand(EditTransactionCommand.COMMAND_WORD);
        assertEquals(helpCommandEdit, helpCommandEditCopy);

        // different types -> returns false
        assertNotEquals(1, helpCommandEdit);

        // null -> returns false
        assertNotEquals(null, helpCommandEdit);

        // different HelpCommand -> returns false
        assertNotEquals(helpCommandEdit, helpCommandDelete);

        assertFalse(helpCommandEdit.equals(2));
    }
}
