package unicash.logic.commands;

import static unicash.logic.commands.CommandTestUtil.assertCommandSuccess;
import static unicash.logic.commands.SummaryCommand.MESSAGE_NOT_SHOWING_SUMMARY;
import static unicash.logic.commands.SummaryCommand.MESSAGE_SHOWING_SUMMARY_SUCCESS;
import static unicash.testutil.TypicalTransactions.NUS;

import org.junit.jupiter.api.Test;

import unicash.model.Model;
import unicash.model.ModelManager;

public class SummaryCommandTest {
    @Test
    public void execute_summary_success() {
        Model emptyModel = new ModelManager();
        Model expectedEmptyModel = new ModelManager();
        CommandResult emptyModelExpectedCommandResult =
                new CommandResult(MESSAGE_NOT_SHOWING_SUMMARY, false, false, false);
        assertCommandSuccess(new SummaryCommand(), emptyModel,
                emptyModelExpectedCommandResult, expectedEmptyModel);

        Model nonEmptyModel = new ModelManager();
        nonEmptyModel.addTransaction(NUS);
        Model expectedNonEmptyModel = new ModelManager();
        expectedNonEmptyModel.addTransaction(NUS);
        CommandResult nonEmptyModelExpectedCommandResult =
                new CommandResult(MESSAGE_SHOWING_SUMMARY_SUCCESS, false, false, true);
        assertCommandSuccess(new SummaryCommand(), nonEmptyModel,
                nonEmptyModelExpectedCommandResult, expectedNonEmptyModel);
    }
}
