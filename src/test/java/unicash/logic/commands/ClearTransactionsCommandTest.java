package unicash.logic.commands;

import static unicash.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import unicash.model.Model;
import unicash.model.ModelManager;
import unicash.model.UniCash;
import unicash.model.UserPrefs;
import unicash.testutil.TypicalTransactions;

public class ClearTransactionsCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        CommandTestUtil.assertCommandSuccess(new ClearTransactionsCommand(), model,
                ClearTransactionsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyUniCash_success() {
        Model model = new ModelManager(TypicalTransactions.getTypicalUniCash(), new UserPrefs());
        Model expectedModel = new ModelManager(new UniCash(), new UserPrefs());
        expectedModel.setUniCash(new UniCash());

        CommandTestUtil.assertCommandSuccess(new ClearTransactionsCommand(), model,
                ClearTransactionsCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
