package seedu.address.logic.commands.transaction;

import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalTransactions.getTypicalUniCash;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearTransactionsCommand;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UniCash;
import seedu.address.model.UserPrefs;

public class ClearTransactionsCommandTest {

    @Test
    public void execute_emptyAddressBook_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearTransactionsCommand(), model,
                ClearTransactionsCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyUniCash_success() {
        Model model = new ModelManager(new AddressBook(), new UserPrefs(), getTypicalUniCash());
        Model expectedModel = new ModelManager(new AddressBook(), new UserPrefs(), new UniCash());
        expectedModel.setUniCash(new UniCash());

        assertCommandSuccess(new ClearTransactionsCommand(), model,
                ClearTransactionsCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
