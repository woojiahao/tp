package unicash.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static unicash.logic.commands.CommandTestUtil.assertCommandSuccess;

import org.junit.jupiter.api.Test;

import unicash.commons.core.index.Index;
import unicash.logic.UniCashMessages;
import unicash.model.Model;
import unicash.model.ModelManager;
import unicash.model.UserPrefs;
import unicash.model.transaction.Transaction;
import unicash.testutil.TypicalIndexes;
import unicash.testutil.TypicalTransactions;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code DeleteTransactionCommand}.
 */
public class DeleteTransactionCommandTest {

    private final Model model = new ModelManager(TypicalTransactions.getTypicalUniCash(), new UserPrefs());

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTransactionList().size() + 1);
        DeleteTransactionCommand deleteCommand = new DeleteTransactionCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteCommand, model, UniCashMessages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        DeleteTransactionCommand deleteFirstCommand = new DeleteTransactionCommand(TypicalIndexes.INDEX_FIRST_TRANSACTION);
        DeleteTransactionCommand deleteSecondCommand = new DeleteTransactionCommand(TypicalIndexes.INDEX_SECOND_TRANSACTION);

        // same object -> returns true
        assertEquals(deleteFirstCommand, deleteFirstCommand);

        // same values -> returns true
        DeleteTransactionCommand deleteFirstCommandCopy =
                new DeleteTransactionCommand(TypicalIndexes.INDEX_FIRST_TRANSACTION);
        assertEquals(deleteFirstCommand, deleteFirstCommandCopy);

        // different types -> returns false
        assertNotEquals(1, deleteFirstCommand);

        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertNotEquals(null, deleteFirstCommand);

        // different person -> returns false
        assertNotEquals(deleteFirstCommand, deleteSecondCommand);
    }

    @Test
    public void toStringMethod() {
        Index targetIndex = Index.fromOneBased(1);
        DeleteTransactionCommand deleteCommand = new DeleteTransactionCommand(targetIndex);
        String expected = DeleteTransactionCommand.class.getCanonicalName()
                + "{targetIndex=" + targetIndex + "}";
        assertEquals(expected, deleteCommand.toString());
    }

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Transaction transactionToDelete = model.getFilteredTransactionList().get(
                TypicalIndexes.INDEX_FIRST_TRANSACTION.getZeroBased());
        DeleteTransactionCommand deleteCommand = new DeleteTransactionCommand(TypicalIndexes.INDEX_FIRST_TRANSACTION);

        String expectedMessage = String.format(DeleteTransactionCommand.MESSAGE_DELETE_TRANSACTION_SUCCESS,
                UniCashMessages.formatTransaction(transactionToDelete));

        ModelManager expectedModel = new ModelManager(TypicalTransactions.getTypicalUniCash(), new UserPrefs());
        expectedModel.deleteTransaction(transactionToDelete);

        CommandTestUtil.assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexFilteredList_success() {
        CommandTestUtil.showTransactionAtIndex(model, TypicalIndexes.INDEX_FIRST_TRANSACTION);

        Transaction transactionToDelete = model.getFilteredTransactionList()
                .get(TypicalIndexes.INDEX_FIRST_TRANSACTION.getZeroBased());
        DeleteTransactionCommand deleteCommand = new DeleteTransactionCommand(TypicalIndexes.INDEX_FIRST_TRANSACTION);

        String expectedMessage = String.format(DeleteTransactionCommand.MESSAGE_DELETE_TRANSACTION_SUCCESS,
                UniCashMessages.formatTransaction(transactionToDelete));

        Model expectedModel = new ModelManager(TypicalTransactions.getTypicalUniCash(), new UserPrefs());
        expectedModel.deleteTransaction(transactionToDelete);
        showNoTransaction(expectedModel);

        CommandTestUtil.assertCommandSuccess(deleteCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        CommandTestUtil.showTransactionAtIndex(model, TypicalIndexes.INDEX_FIRST_TRANSACTION);

        Index outOfBoundIndex = TypicalIndexes.INDEX_SECOND_TRANSACTION;
        // ensures that outOfBoundIndex is still in bounds of transactions list
        assertTrue(outOfBoundIndex.getZeroBased() < TypicalTransactions.getTypicalUniCash().getTransactionList().size());

        DeleteTransactionCommand deleteCommand = new DeleteTransactionCommand(outOfBoundIndex);

        CommandTestUtil.assertCommandFailure(deleteCommand, model,
                UniCashMessages.MESSAGE_INVALID_TRANSACTION_DISPLAYED_INDEX);
    }

    /**
     * Updates {@code model}'s filtered list to show no transaction.
     */
    private void showNoTransaction(Model model) {
        model.updateFilteredTransactionList(p -> false);

        assertTrue(model.getFilteredTransactionList().isEmpty());
    }
}

